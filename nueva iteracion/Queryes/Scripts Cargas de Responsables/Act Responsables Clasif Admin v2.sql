
select *
from siifabsdependencia
where clave in ('00475','00521','00230','00369','00846','00570','00725','00362','00864','00727','00471');

select a.clave,c.clave,c.nombre,c.apepaterno,c.apematerno
from siifabsdependencia a
join siifabsempleado b on a.idempleado = b.idempleado
join siifabscolectiva c on b.idcolectiva = c.idcolectiva
where a.clave in ('00475','00521','00230','00369','00846','00570','00725','00362','00864','00727','00471','00476');

/*
select * from siifabsdependencia where clave in ('00521','00864','00475');
select * from siifabsempleado where idempleado in (388,603,420);

select * from siifabsempleado where idempleado in (172,622);
select * from siifabsdependencia where idempleado in (172,622);

*/

select *
from siifabscolectiva
where clave in ('EM00491','EM00571','EM00562','EM00193','EM00497','EM00355','EM00301','EM00579','EM00390','EM00324');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00491'
and b.clave in ('00475','00521');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00571'
and b.clave in ('00230');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00562'
and b.clave in ('00369');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00193'
and b.clave in ('00846');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00497'
and b.clave in ('00570');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00355'
and b.clave in ('00725');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00301'
and b.clave in ('00362');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00579'
and b.clave in ('00864');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00390'
and b.clave in ('00727');

insert into siifabsempleado(iddefcvepresupuestal,iddependencia,idcolectiva,clave)
select 2,b.iddependencia,a.idcolectiva,a.clave
from siifabscolectiva a,siifabsdependencia b
where a.clave = 'EM00324'
and b.clave in ('00471');

--00475	Martín J. Guadalupe	Mendoza	López	EM00491
--00521	Martín J. Guadalupe	Mendoza	López	EM00491
--00230	Juan Carlos	Flores	Miramontes	EM00571
--00369	Víctor Manuel	González	Romero	EM00562
--00846	Gabriela	Carrillo	Jiménez	EM00193
--00570	Miguel Angel	García	Santana	EM00497
--00725	Gilberto	Tinajero	Díaz	EM00355
--00362	Carlos Eduardo	Anguiano	Gómez	EM00301
--00864	Ignacio Alejandro	Salinas	Osornio	EM00579
--00727	Jorge	Montoya	Orozco	EM00390
--00471	Ernesto Alfredo	Espinosa	Guarro	EM00324

update siifabsdependencia
set idempleado = null::integer
where clave in ('00521','00864','00475');

delete siifabsempleado where idempleado in (388,603,420);
delete siifabsempleado where idempleado = 172;



-- Actualiza Dependencia

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00491' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00475'))
where clave = '00475';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00491' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00521'))
where clave = '00521';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00571' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00230'))
where clave = '00230';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00562' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00369'))
where clave = '00369';

-- A PARTIR DE AQUI

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00193' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00846'))
where clave = '00846';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00497' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00570'))
where clave = '00570';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00355' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00725'))
where clave = '00725';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00301' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00362'))
where clave = '00362';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00579' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00864'))
where clave = '00864';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00390' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00727'))
where clave = '00727';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00324' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00471'))
where clave = '00471';

-- Actualiza Colectiva

update siifabscolectiva
set apematerno = 'Jiménez'
where clave = 'EM00193';
