
select * from indexito where anio = 2013;

select * from pppsubfuncion;

select * from siifpppcvepptalcfg;

select * from proyecto a
join programa b on a.idrprograma = b.idrprograma and b.anio = 2013;

select * from pppramo;
select * from siifabsramo;

select * from nipftefinan where baja = 'N' and vigente = 'S';
select * from siifpppfuefinanciamiento;

select * 
from siifabsramo b
where b.clave not in (
select a.idpppramo
from pppramo a);

select a.* 
from siifabsramo a
join pppramo b on b.idpppramo = a.clave and b.nombre <> a.descripcion;

