select * from siifabsdependencia where clave = '00358';
select * from siifabsempleado where idempleado = 630;
select * from siifabscolectiva where idcolectiva = 859;
select * from siifabsempleado where idcolectiva =896;
select * from siifabscolectiva where idcolectiva = 896;

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,clave,idcolectiva)
values(2,483,'EM00505',896);

update siifabsdependencia
set idempleado = null::integer
where clave in ('00358');

delete siifabsempleado where idempleado in (247);

update siifabsdependencia set idempleado = 630
where clave in ('00358');

l


select * 
from siifabscolectiva
where nombre like 'Norma%';


update siifabsempleado set idcolectiva=1101,clave="EM00593"
where idempleado in(
select idempleado
from siifabsempleado
where clave = "EM00339"); 



Select * from siifabscolectiva where clave = "EM00339";
Select * from siifabsdependencia where idempleado = 398;
Select * from siifabsdependencia where clave ="00771";

update siifabsdependencia
set idempleado = null::integer
where clave ="00771";