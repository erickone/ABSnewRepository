
Alter table siifpppenccalendario ADD descdestino  LVARCHAR(2000);

ALTER TABLE siifpppEncDependenciaObjDestino ADD (ramo33 boolean DEFAULT 'f'); 
ALTER TABLE siifpppEncDependenciaObjDestino ADD (congreso boolean DEFAULT 'f'); 
ALTER TABLE siifpppEncDependenciaObjDestino ADD (desagregado boolean DEFAULT 'f'); 
ALTER TABLE siifpppEncDependenciaObjDestino ADD (activo boolean DEFAULT 't');

CREATE TABLE informix.siifpppbitacoraencdepobjdestino( 
	idbitacoraencdepobjdes	SERIAL NOT NULL,
	idencdepobjdes          INTEGER NOT NULL,	
	fecha                 	DATETIME YEAR to FRACTION(5),	
	accion          	VARCHAR(36) NOT NULL,	
	idusuario             	INTEGER NOT NULL,
	PRIMARY KEY(idbitacoraencdepobjdes)constraint "informix".pk_siifpppbitacoraencdepobjdestino
	ENABLED
);

ALTER TABLE informix.siifpppbitacoraencdepobjdestino
	ADD CONSTRAINT ( FOREIGN KEY(idusuario)
	REFERENCES informix.siifabsusuario(idusuario) 
	CONSTRAINT "informix".fk_siifpppbitacoraencdepobjdestino_siifabsusuario
	ENABLED )
;
ALTER TABLE informix.siifpppbitacoraencdepobjdestino
	ADD CONSTRAINT ( FOREIGN KEY(idencdepobjdes)
	REFERENCES informix.siifpppEncDependenciaObjDestino(idencdependenciaobjdestino) 
	CONSTRAINT "informix".fk_siifpppbitacoraencdepobjdestino_siifpppEncDependenciaObjDestino
	ENABLED )
;

create procedure "informix".updateMontoYAdicional ()

define nIdDetCvePreFicha integer;
define nIdCvePresupuestal integer;

define nMonto DECIMAL(15,2);
define nMontoAdicc DECIMAL(15,2);
define vMes VARCHAR(36);
define vMontoMensual DECIMAL(15,2);
define nFuenteFinanciamiento integer;
define nClave VARCHAR(100);
define nIdCveP2 integer;
define nIdCveP3 integer;

foreach Select  cp.clave,  cp2.idcvepresupuestal  into  nClave, nIdCvePresupuestal
                              from siifpppcvepresupuestal CP, siifpppencDEpendenciaObjDestino EDOD, siifpppcvepresupuestal CP2
                               where EDOD.iddependencia = (select SDEP.iddependencia from siifabsdependencia SDEP
                               where SDEP.clave = SUBSTRING(CP.clave FROM 1 FOR 5))
                               and EDOD.idobjetogasto = (select OG.idobjetogasto from siifpppobjetogasto OG
                               where OG.clave = SUBSTRING(CP.clave FROM 16 FOR 4))
                               and EDOD.clavedestino = SUBSTRING(CP.clave FROM 20 FOR 2)
                               and EDOD.inversion = 't'
                               and CP.clave = CP2.clave
                               and CP.idcvepresupuestal<>CP2.idcvepresupuestal
                               and cp.idcvepresupuestal not in (select idcvepresupuestal from siifpppdetcveanteproy)

                foreach
                               select idcvepresupuestal, montooriginal, montoadicional
                               into nIdCveP2, nMonto, nMontoAdicc
                               from siifpppcvepresupuestal
                               where clave = nClave
                               
                               update siifpppdetcvepreficha
                               set 
                                               idcvepresupuestal = nIdCvePresupuestal
                               where idcvepresupuestal = nIdCveP2;
                               
                               
                               
                               

                               delete from siifpppcvemensualpptal where idcvepresupuestal = nIdCveP2;
                               delete from siifpppdetallecvepptal where idcvepresupuestal = nIdCveP2;

                               let nIdCveP3 =nIdCveP2;

                end foreach;
      delete from siifpppcvepresupuestal where idcvepresupuestal = nIdCveP3;

end foreach;

end procedure;


-- Permissions for routine "updatemontoyadicional"
grant execute on procedure 'informix'.updatemontoyadicional() to 'public';

execute procedure updateMontoYAdicional();

DROP PROCEDURE updatemontoyadicional();

delete from siifpppdetcvepreficha where idcvepresupuestal in (1710, 1872);
delete from  siifpppdetcveanteproy where idcvepresupuestal in (1710, 1872);
delete from siifpppdetallecvepptal where idcvepresupuestal in (1710, 1872);
delete from  siifpppcvepresupuestal where idcvepresupuestal in (1710, 1872);

--Confrimaciond de que no existen claves duplicadas
Select CP.montoadicional, cp.clave, cp.idcvepresupuestal, 
cp2.clave, CP2.montoadicional, CP2.montooriginal, cp2.idcvepresupuestal 
 from siifpppcvepresupuestal CP, siifpppencDEpendenciaObjDestino EDOD, siifpppcvepresupuestal CP2
where EDOD.iddependencia = (select SDEP.iddependencia from siifabsdependencia SDEP
    where SDEP.clave = SUBSTRING(CP.clave FROM 1 FOR 5))
and EDOD.idobjetogasto = (select OG.idobjetogasto from siifpppobjetogasto OG
   where OG.clave = SUBSTRING(CP.clave FROM 16 FOR 4))
and EDOD.clavedestino = SUBSTRING(CP.clave FROM 20 FOR 2)
--and EDOD.inversion = 'f'
and CP.clave = CP2.clave
and CP.idcvepresupuestal<>CP2.idcvepresupuestal;

