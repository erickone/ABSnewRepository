--El primero esta facil es solo una actualizacion del nombre del responsable
update siifabscolectiva set nombre = 'Rafael'
where idcolectiva 
in(select idcolectiva
from siifabscolectiva
where nombre like 'Rafaél %' and apepaterno like 'Guzmán%' and apematerno like 'Esparza%');
--Hay que crear una tabla temporal en la cual se guardaran los datos de los empleados que vamos a dar de alta
-- Colectivas - Responsables Deps
create table tmpsiifabscolecresp
(
	idn 			SERIAL NOT NULL,
	clavedep		VARCHAR(50),
	descripcion 	VARCHAR(255),
	claveemp		VARCHAR(50),
	nombre 		VARCHAR(100),
	apepaterno 	VARCHAR(100),
	apematerno 	VARCHAR(100),
	anio 			INTEGER,
	tipocolectiva 	VARCHAR(20),
	estcolectiva 	VARCHAR(100)
);
--Hay que hacer los inserts de los registros que se van a dar de alta aqui el unico campo que hay que consultar 
es el de claveemp, el cual es un consecutivo de la tabla de colectivas para saber cual es la clave que sigue

select first 5 *
from siifabscolectiva
where idtipocolectiva = 9
order by clave desc;
--Esta consulta nos da los ultimos cinco resgitros que se van a dar de alta en este caso el ultimo registro que me dio
--en mi base de datos local tiene el campo clave = EM00597 para insertar los nuevos responsables comenzaremos con el
--00598

INSERT INTO informix.tmpsiifabscolecresp(clavedep,descripcion,claveemp,nombre,apepaterno,apematerno,anio,tipocolectiva,estcolectiva)
VALUES('00633','Instituto Tecnológico Superior de Puerto Vallarta','00598','Emilio','Contreras','Reyes',2013,'EM','HABILITADO');

INSERT INTO informix.tmpsiifabscolecresp(clavedep,descripcion,claveemp,nombre,apepaterno,apematerno,anio,tipocolectiva,estcolectiva)
VALUES('00732','Instituto Tecnológico Superior de La Huerta','00599','Aurelio','Quezada','García',2013,'EM','HABILITADO');

--con este insert se insertan los nuevos registros en la tabla de colectiva con los responsables que se dieron de alta
insert into siifabscolectiva(idtipocolectiva,idestcolectiva,clave,nombre,apepaterno,apematerno,fechaalta,fechaultmodif,idclasifregional,direccion,numero,telefono,email,rfc,curp,nip)
select c.idtipocolectiva,d.idestatuscolectiva,a.tipocolectiva || a.claveemp,
a.nombre,a.apepaterno,a.apematerno,EXTEND(MDY(1,1,2013), YEAR to SECOND),null::date,null::integer,null::varchar,
null::varchar,null::varchar,null::varchar,null::varchar,null::varchar,null::varchar
from tmpsiifabscolecresp a
	join siifabstipocolectiva c on (UPPER(c.clave) = UPPER(a.tipocolectiva))
	join siifabsestcolectiva d on (UPPER(d.descripcion) = UPPER(a.estcolectiva))
group by a.claveemp,a.nombre,a.apepaterno,a.apematerno,a.tipocolectiva,c.idtipocolectiva,d.idestatuscolectiva;
--para vizualizar los registros agregados se utiliza el siguiente select 
select first 2 *
from siifabscolectiva
where idtipocolectiva = 9
order by clave desc;

--a continuacion se tiene que Dar de alta los nuevos registros en la tabla de Empleados
insert into siifabsempleado(iddefcvepresupuestal,iddependencia,clave,idcolectiva)
select b.iddefcvepresupuestal,d.iddependencia,f.clave,f.idcolectiva
from tmpsiifabscolecresp a 
	join siifpppdefcvepresupuestal b on (b.anio = a.anio) and b.anio = 2013
	join siifabstipocolectiva c on (UPPER(c.clave) = UPPER(a.tipocolectiva))
	join siifabscolectiva as f on (UPPER(f.clave) = UPPER(a.tipocolectiva) || UPPER(a.claveemp))
	join siifabsdependencia as d on (a.clavedep = d.claveinterna)
group by b.iddefcvepresupuestal,d.iddependencia,f.clave,f.idcolectiva;

--para vizualizar los registros se utiliza el siguiente query
select first 2 *
from siifabsempleado
order by clave desc;

-- 1. Obtener IDs de Empleados (Responsables) Actuales y Respaldarlos
select * from siifabsdependencia where clave in (select clavedep from tmpsiifabscolecresp);
-- 547,481

-- 2. Actualizar las dependencias que se va a actualizar sus Responsables a nulo
update siifabsdependencia
set idempleado = null::integer
where clave in (select clavedep from tmpsiifabscolecresp);

-- 3. Borrar los empleados (respnsables) actuales, esperando que solo esten ligados a las dependencias en cuestión
delete siifabsempleado where idempleado in (547,481);

-- 4. Actualizar dependencias con nuevos Responsables
update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00594' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00732'))
where clave = '00732';

update siifabsdependencia
set idempleado = (select idempleado from siifabsempleado where clave = 'EM00593' and iddependencia = (select iddependencia from siifabsdependencia where clave = '00633'))
where clave = '00633';

--aqui ya se dieron de alta los nuevos responsables solo queda hacer el cambio de responsable en la dep  con clave 00636
--con este query obtenemos la clave y el iddependencia del responsable a cambiar
select *
from siifabscolectiva
where nombre like 'David%' and apepaterno like 'Ávalos%' and apematerno like 'Cueva%';
--CLAVE = EM00314, IDCOLECTIVA = 854
select * from siifabsdependencia where claveinterna= '00636';
--id 440
--ahora se ejecuta el insert
insert into siifabsempleado(iddefcvepresupuestal,iddependencia,clave,idcolectiva)
values (2,440,'EM00314',854);
--para visualizar el registro recien agregado se ejecuta el siguiente query
select *
from siifabsempleado
where clave = 'EM00314';
--se borran los id de los empleados guardados en la tabla de los temporales
delete tmpsiifabscolecresp;
-- 1. Obtener IDs de Empleados (Responsables) Actuales y Respaldarlos
select * from siifabsdependencia where clave in ('00636');
-- 274

-- 2. Actualizar las dependencias que se va a actualizar sus Responsables a nulo
update siifabsdependencia
set idempleado = null::integer
where clave in ('00636');

-- 3. Borrar los empleados (respnsables) actuales, esperando que solo esten ligados a las dependencias en cuestión
delete siifabsempleado where idempleado in (274);
--agregar el nuevo id de empleado a la dependencia
select * from siifabsdependencia
update siifabsdependencia set idempleado = (select idempleado
from siifabsempleado
where clave = 'EM00314') 
where iddependencia in(select iddependencia from siifabsdependencia where clave in ('00636'));
--aqui podemos ver las dependencia modificadas
Select a.clave,a.descripcion,c.clave,c.nombre,c.apepaterno,c.apematerno from siifabsdependencia a  
join siifabsempleado b  on a.idempleado = b.idempleado
join siifabscolectiva c on b.idcolectiva=c.idcolectiva
where a.clave in ('00633','00636','00706','00732') ;


drop table tmpsiifabscolecresp;