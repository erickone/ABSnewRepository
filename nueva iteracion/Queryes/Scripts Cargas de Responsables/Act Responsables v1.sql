
select * 
from siifabscolectiva
where nombre like 'Rosa%';

select first 5 *
from siifabscolectiva
where idtipocolectiva = 9
order by clave desc;

select * from siifabscolectiva where nombre = 'Héctor'

-- 00597

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

INSERT INTO informix.tmpsiifabscolecresp(clavedep,descripcion,claveemp,nombre,apepaterno,apematerno,anio,tipocolectiva,estcolectiva)
VALUES('00183','Coordinación de Comunicación y Apoyo','00597','Rosa Emma','Herrera',null::varchar,2013,'EM','HABILITADO');


-- Colectivas de Empleados
insert into siifabscolectiva(idtipocolectiva,idestcolectiva,clave,nombre,apepaterno,apematerno,fechaalta,fechaultmodif,idclasifregional,direccion,numero,telefono,email,rfc,curp,nip)
select c.idtipocolectiva,d.idestatuscolectiva,a.tipocolectiva || a.claveemp,
a.nombre,a.apepaterno,a.apematerno,EXTEND(MDY(1,1,2013), YEAR to SECOND),null::date,null::integer,null::varchar,
null::varchar,null::varchar,null::varchar,null::varchar,null::varchar,null::varchar
from tmpsiifabscolecresp a
	join siifabstipocolectiva c on (UPPER(c.clave) = UPPER(a.tipocolectiva))
	join siifabsestcolectiva d on (UPPER(d.descripcion) = UPPER(a.estcolectiva))
group by a.claveemp,a.nombre,a.apepaterno,a.apematerno,a.tipocolectiva,c.idtipocolectiva,d.idestatuscolectiva;

-- Empleados
insert into siifabsempleado(iddefcvepresupuestal,iddependencia,clave,idcolectiva)
select b.iddefcvepresupuestal,d.iddependencia,f.clave,f.idcolectiva
from tmpsiifabscolecresp a 
	join siifpppdefcvepresupuestal b on (b.anio = a.anio) and b.anio = 2013
	join siifabstipocolectiva c on (UPPER(c.clave) = UPPER(a.tipocolectiva))
	join siifabscolectiva as f on (UPPER(f.clave) = UPPER(a.tipocolectiva) || UPPER(a.claveemp))
	join siifabsdependencia as d on (a.clavedep = d.claveinterna)
group by b.iddefcvepresupuestal,d.iddependencia,f.clave,f.idcolectiva;

/*

-- 1. Obtener IDs de Empleados (Responsables) Actuales y Respaldarlos
select * from siifabsdependencia where clave in (select clavedep from tmpsiifabscolecresp);
-- 4402

-- 2. Actualizar las dependencias que se va a actualizar sus Responsables a nulo
update siifabsdependencia
set idempleado = null::integer
where clave in (select clavedep from tmpsiifabscolecresp);

-- 3. Borrar los empleados (respnsables) actuales, esperando que solo esten ligados a las dependencias en cuestión
delete siifabsempleado where idempleado in (442);

-- 4. Actualizar dependencias con nuevos Responsables
update siifabsdependencia
set siifabsdependencia.idempleado = 
	(select idempleado
	from siifabsempleado b
		join tmpsiifabscolecresp c on (UPPER(b.clave) = ('EM' || UPPER(c.claveemp)))
					and (UPPER(c.clavedep) = UPPER(siifabsdependencia.claveinterna))
					and siifabsdependencia.iddependencia = b.iddependencia
)
where claveinterna in (select clavedep from tmpsiifabscolecresp);

select a.clave,c.nombre,c.apepaterno,c.apematerno
from siifabsdependencia a
join siifabsempleado b on a.idempleado = b.idempleado
join siifabscolectiva c on b.idcolectiva = c.idcolectiva
where a.clave in (select clavedep from tmpsiifabscolecresp);

drop table tmpsiifabscolecresp;

