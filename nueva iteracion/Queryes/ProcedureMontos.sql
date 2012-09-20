--DROP PROCEDURE updatemontoyadicional();

--drop procedure updateMontoYAdicional;
create procedure "informix".updateMontoYAdicional ()

define nIdDetCvePreFicha integer;
define nIdCvePresupuestal integer;

define nMonto DECIMAL(15,2);
define nMontoAdicc DECIMAL(15,2);
define vMes VARCHAR(36);
define vMontoMensual DECIMAL(15,2);
define nFuenteFinanciamiento integer;

foreach
       Select iddetcvepreficha, idcvepresupuestal
       into nIdDetCvePreFicha, nIdCvePresupuestal
       from siifpppdetcvepreficha

       select montooriginal, montoadicional, idfuentefinanciamiento
       into nMonto, nMontoAdicc, nFuenteFinanciamiento
       from siifpppcvepresupuestal
       where idcvepresupuestal = nIdCvePresupuestal;

       update siifpppdetcvepreficha
       set montoasignado = nMonto,
           montoadicional = nMontoAdicc,
           idfuentefinanciamiento = nFuenteFinanciamiento
       where iddetcvepreficha = nIdDetCvePreFicha;

       foreach Select mes, montooriginal
               into vMes, vMontoMensual
               from siifpppcvemensualpptal where idcvepresupuestal = nIdCvePresupuestal
              insert into siifpppcvemensualpptalpreficha (mes, montooriginal, iddetcvepreficha)
              values (vMes,vMontoMensual, nIdDetCvePreFicha );
       end foreach;
       
       Update siifpppcvepresupuestal
       set (montooriginal, montoadicional) = (0.0, 0.0)
       where idcvepresupuestal = nIdCvePresupuestal;

       delete from siifpppcvemensualpptal where idcvepresupuestal = nIdCvePresupuestal;

end foreach;

Update siifpppcvepresupuestal
       set (montooriginal, montoadicional) = (0.0, 0.0)
where idcvepresupuestal in (Select idcvepresupuestal
       from siifpppdetcvepreficha);

delete from siifpppcvemensualpptal where  idcvepresupuestal in (Select idcvepresupuestal
       from siifpppdetcvepreficha);
end procedure;

-- Permissions for routine "updatemontoyadicional"
grant execute on procedure 'informix'.updatemontoyadicional() to 'public';

execute procedure updateMontoYAdicional();

DROP PROCEDURE updatemontoyadicional();