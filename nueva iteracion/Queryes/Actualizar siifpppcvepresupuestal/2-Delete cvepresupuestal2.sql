delete from siifpppcvemensualpptal where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);


delete from siifpppdetallecvepptal where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);

delete from siifpppdetcveanteproy where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);

delete from siifpppcvepptaldesglose where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);

delete from siifpppdetcvepreficha where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);

delete from siifpppcvepptaladicional where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);



delete from siifpppcvepresupuestal where idcvepresupuestal in (
Select cp.idcvepresupuestal 
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
 and CP.montooriginal = 0.00);



