Alter table siifpppenccalendario ADD clavedestino  VARCHAR(10);

Update siifppptipocalendario set descripcion = 'Se paga de forma Mensual' where idtipocalendario = 1;

Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('002', 'Se paga en Febrero', 'f','t','f','f','f','f','f','f','f','f','f','f','f');
Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('003', 'Se paga en Marzo, Mayo, Julio, Noviembre, Diciembre', 'f','f','t','f','t','f','t','f','f','f','t','t','f');
Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('004', 'Se paga en Mayo, Julio, Septiembre, Noviembre y Diciembre', 'f','f','f','f','t','f','t','f','t','f','t','t','f');
Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('005', 'Se paga en Agosto y Diciembre', 'f','f','f','f','f','f','f','t','f','f','f','t','f');
Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('006', 'Se paga en Septiembre y Diciembre', 'f','f','f','f','f','f','f','f','t','f','f','t','f');
Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('007', 'Se paga en Diciembre', 'f','f','f','f','f','f','f','f','f','f','f','t','f');


-- 1331 - 03 - SUELDO BASE  - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '03' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1131) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1331 - 04 - SUELDO BASE  - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '04' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1131) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1142 - 01 - SOBRESUELDOS  - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1142) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1232 - 00 - GRATIFICADOS  - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1232) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1311 - 01 - GRATIFICADOS  - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1311) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1321 - 00 - PRIMA VACACIONAL Y DOMINICAL  - TODAS - 005
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3) UP,
     (Select *
from siifpppobjetogasto
where clave = 1321) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='005') TIPOCALEN;
	  
-- 1321 - 01 - PRIMA VACACIONAL Y DOMINICAL  - TODAS - 005
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3) UP,
     (Select *
from siifpppobjetogasto
where clave = 1321) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='005') TIPOCALEN;
	  
-- 1322 - 01 - AGUINALDO  - 09 -004
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1322) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='004') TIPOCALEN;

-- 1322 - 01 - AGUINALDO  - EXCEPTO 09 -003
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1322) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='003') TIPOCALEN;

-- 1331 - 00 - ASIGNACIÓN ESPECÍFICA PARA PERSONAL DOCENTE  -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1331) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1332 - 00 - REMUNERACIONES POR HORAS EXTRAORDINARIAS - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1332) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1341 - 00 - COMPENSACIONES A SUSTITUTOS DE PROFESORES EN ESTADO GRÁVIDO Y PERSONAL DOCENTE CON LICENCIATURA PREJUBILATORIA -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1341) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1342 - 00 - COMPENSACIONES A DIRECTORES DE PREESCOLAR, PRIMARIA Y SECUNDARIA; INSPECTORES, PREFECTOS Y F.C. -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1342) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1342 - 01 - COMPENSACIONES A DIRECTORES DE PREESCOLAR, PRIMARIA Y SECUNDARIA; INSPECTORES, PREFECTOS Y F.C. -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1342) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1343 - 00 - COMPENSACIONES PARA MATERIAL DIDÁCTICO -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1343) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1343 - 01 - COMPENSACIONES PARA MATERIAL DIDÁCTICO -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1343) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1344 - 00 - COMPENSACIONES POR TITULACIÓN A NIVEL LICENCIATURA T-3,MA Y DO -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1344) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1345 - 00 - COMPENSACIONES ADICIONALES -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1345) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1347 - 00 - COMPENSACIONES ADICIONALES -  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1347) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1411 - 00 - CUOTAS AL IMSS POR ENFERMEDADES Y MATERNIDAD - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1411) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1411 - 01 - CUOTAS AL IMSS POR ENFERMEDADES Y MATERNIDAD - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1411) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1421 - 00 - CUOTAS PARA LA VIVIENDA - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1421) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1421 - 01 - CUOTAS PARA LA VIVIENDA - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1421) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1431 - 00 - CUOTAS A PENSIONES - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1431) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1431 - 01 - CUOTAS A PENSIONES - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1431) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1432 - 00 - CUOTAS PARA EL SISTEMA DE AHORRO PARA EL RETIRO (SAR) - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1432) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1432 - 01 - CUOTAS PARA EL SISTEMA DE AHORRO PARA EL RETIRO (SAR) - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1432) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1441 - 00 - CUOTAS PARA EL SEGURO DE VIDA DEL  PERSONAL (PLAN MÚLTIPLE DE BENEFICIOS PARA LOS TRABAJADORES DEL ESTADO) - EXCEPTO 09 - 003
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1441) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='003') TIPOCALEN;
	  
-- 1442 - 00 - CUOTAS PARA EL SEGURO DE GASTOS MÉDICOS - EXCEPTO 09 - 002
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1442) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='002') TIPOCALEN;
	  
-- 1522 - 00 - INDEMNIZACIONES POR ACCIDENTE EN EL TRABAJO - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1522) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1523 - 00 - LAUDOS, LIQUIDACIONES, INDEMNIZACIONES POR SUELDOS Y SALARIOS CAÍDOS - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1523) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;

-- 1531 - 00 - FONDO DE RETIRO - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1531) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1541 - 01 - PREVISIÓN SOCIAL MÚLTIPLE PARA PERSONAL DE EDUCACIÓN Y SALUD - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1541) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1542 - 00 - GRATIFICACIONES GENÉRICAS - 09 - 004
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1542) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='004') TIPOCALEN;
	  
-- 1545 - 00 - AYUDA PARA ACTIVIDADES DE ORGANIZACIÓN Y SUPERVISIÓN - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1545) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1545 - 01 - AYUDA PARA ACTIVIDADES DE ORGANIZACIÓN Y SUPERVISIÓN - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1545) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1546 - 00 - ASIGNACIÓN DOCENTE - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1546) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1546 - 01 - ASIGNACIÓN DOCENTE - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1546) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1547 - 00 - SERVICIOS COCURRICULARES - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1547) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1548 - 00 - SUELDOS, DEMÁS PERCEPCIONES Y GRATIFICACIÓN ANUAL - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1548) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1593 - 00 - PRESTACIÓN SALARIAL COMPLEMENTARIA POR FALLECIMIENTO - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1593) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1611 - 00 - IMPACTO AL SALARIO EN EL TRANSCURSO DEL AÑO - EXCEPTO  09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1611) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1611 - 03 - IMPACTO AL SALARIO EN EL TRANSCURSO DEL AÑO - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '03' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1611) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1611 - 12 - IMPACTO AL SALARIO EN EL TRANSCURSO DEL AÑO - 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '12' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1611) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1612 - 00 - OTRAS MEDIDAS DE CARÁCTER LABORAL Y ECONÓMICAS  - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1612) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1713 - 01 - AYUDA PARA PASAJES  - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1713) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1714 - 00 - AYUDA PARA ACTIVIDADES DE ESPARCIMIENTO  - EXCEPTO 09 - 007
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1714) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='007') TIPOCALEN;

-- 1715 - 00 - ESTÍMULO POR EL DÍA DEL SERVIDOR PÚBLICO  - TODAS - 006
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3) UP,
     (Select *
from siifpppobjetogasto
where clave = 1715) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='006') TIPOCALEN;
	  
-- 1715 - 01 - ESTÍMULO POR EL DÍA DEL SERVIDOR PÚBLICO  - EXCEPTO 09 - 006
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1715) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='006') TIPOCALEN;
	  
-- 1716 - 00 - ESTÍMULOS DE ANTIGÜEDAD  - 09 - 004
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave = '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1716) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='004') TIPOCALEN;
	  
-- 1718 - 00 - GRATIFICACIONES   - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '00' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1718) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1718 - 01 - GRATIFICACIONES   - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1718) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  
-- 1719 - 01 - OTROS ESTÍMULOS  - EXCEPTO 09 - 001
insert into siifpppenccalendario (iddependencia, idobjetogasto, clavedestino, idtipocalendario)
Select UP.iddependencia, OBJGASTO.idobjetogasto, '01' Destino, TIPOCALEN.idtipocalendario
from (Select *
from siifabsdependencia
where idnivdependencia = 3
  and clave != '09') UP,
     (Select *
from siifpppobjetogasto
where clave = 1719) OBJGASTO,
     (Select *
      from siifppptipocalendario
      where clave ='001') TIPOCALEN;
	  






