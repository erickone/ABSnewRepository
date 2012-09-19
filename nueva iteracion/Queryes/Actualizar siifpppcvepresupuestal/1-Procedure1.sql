--DROP PROCEDURE updatemontoyadicional();

--drop procedure updateMontoYAdicional;


create procedure "informix".updateMontoYAdicional ()



define nMontoAdicc DECIMAL(15,2);
define nIdcvePresupuesta integer;
define nIdcvePresupuesta1 integer;
define nClave varchar;

foreach
      Select CP.montoadicional, cp2.idcvepresupuestal, cp.idcvepresupuestal into  nMontoAdicc, nIdcvePresupuesta, nIdcvePresupuesta1
	 from siifpppcvepresupuestal CP, siifpppencDEpendenciaObjDestino EDOD, siifpppcvepresupuestal CP2
	 where CP.idfuentefinanciamiento is not null
	 and EDOD.iddependencia = (select SDEP.iddependencia from siifabsdependencia SDEP
	    where SDEP.clave = SUBSTRING(CP.clave FROM 1 FOR 5))
	 and EDOD.idobjetogasto = (select OG.idobjetogasto from siifpppobjetogasto OG
	   where OG.clave = SUBSTRING(CP.clave FROM 16 FOR 4))
	 and EDOD.clavedestino = SUBSTRING(CP.clave FROM 20 FOR 2)
	 and EDOD.inversion = 'f'
	 and CP.montoadicional > 0
	 and CP.clave = CP2.clave
	 and CP.idcvepresupuestal<>CP2.idcvepresupuestal
	 and CP.montooriginal = 0.00
	 and CP2.montoadicional = 0.00

	update siifpppcvepresupuestal 
	set montoadicional = nMontoAdicc
	where idcvepresupuestal = nIdcvePresupuesta;

end foreach;


end procedure;

-- Permissions for routine "updatemontoyadicional"
grant execute on procedure 'informix'.updatemontoyadicional() to 'public';

execute procedure updateMontoYAdicional();

DROP PROCEDURE updatemontoyadicional();

