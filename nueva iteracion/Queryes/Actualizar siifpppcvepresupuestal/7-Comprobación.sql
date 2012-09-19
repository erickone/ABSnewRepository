Select CP.montoadicional, cp.clave, cp.idcvepresupuestal, 
cp2.clave, CP2.montoadicional, CP2.montooriginal, cp2.idcvepresupuestal 
 from siifpppcvepresupuestal CP, siifpppencDEpendenciaObjDestino EDOD, siifpppcvepresupuestal CP2
where EDOD.iddependencia = (select SDEP.iddependencia from siifabsdependencia SDEP
    where SDEP.clave = SUBSTRING(CP.clave FROM 1 FOR 5))
and EDOD.idobjetogasto = (select OG.idobjetogasto from siifpppobjetogasto OG
   where OG.clave = SUBSTRING(CP.clave FROM 16 FOR 4))
and EDOD.clavedestino = SUBSTRING(CP.clave FROM 20 FOR 2)
and EDOD.inversion = 'f'
and CP.clave = CP2.clave
and CP.idcvepresupuestal<>CP2.idcvepresupuestal;
