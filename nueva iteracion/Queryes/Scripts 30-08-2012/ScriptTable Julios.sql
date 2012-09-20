Create Table siifppptipocalendario(
idtipocalendario SERIAL NOT NULL,
clave VARCHAR(36) NOT NULL,
descripcion VARCHAR(255) NOT NULL,
Enero BOOLEAN DEFAULT 'f',
Febrero BOOLEAN DEFAULT 'f',
Marzo BOOLEAN DEFAULT 'f',
Abril BOOLEAN DEFAULT 'f',
Mayo BOOLEAN DEFAULT 'f',
Junio BOOLEAN DEFAULT 'f',
Julio BOOLEAN DEFAULT 'f',
Agosto BOOLEAN DEFAULT 'f',
Septiembre BOOLEAN DEFAULT 'f',
Octubre BOOLEAN DEFAULT 'f',
Noviembre BOOLEAN DEFAULT 'f',
Diciembre BOOLEAN DEFAULT 'f',
PRIMARY KEY(idtipocalendario) constraint "informix".pk_siifppptipocalendario
	ENABLED
);

Create table siifpppenccalendario (
idenccalendario SERIAL NOT NULL,
iddependencia INTEGER NOT NULL,
idobjetogasto INTEGER NOT NULL,
idtipocalendario INTEGER NOT NULL,
PRIMARY KEY(idenccalendario) constraint "informix".pk_siifpppenccalendario
	ENABLED
);

ALTER TABLE informix.siifpppenccalendario
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT "informix".fk_siifabsdependencia_iddependencia
	ENABLED )
;

ALTER TABLE informix.siifpppenccalendario
	ADD CONSTRAINT ( FOREIGN KEY(idobjetogasto)
	REFERENCES informix.siifpppobjetogasto(idobjetogasto) CONSTRAINT "informix".fk_siifpppobjetogasto_idobjetogasto
	ENABLED )
;

ALTER TABLE informix.siifpppenccalendario
	ADD CONSTRAINT ( FOREIGN KEY(idtipocalendario)
	REFERENCES informix.siifppptipocalendario(idtipocalendario) CONSTRAINT "informix".fk_siifppptipocalendario_idtipocalendario
	ENABLED )
;

Alter Table siifpppcvepresupuestal ADD tipotemplate BOOLEAN DEFAULT 'f';

Alter Table siifpppenccalendario ADD urlgral BOOLEAN DEFAULT 'f';

aLTER TABLE siifppptipocalendario ADD isdefault BOOLEAN DEFAULT 'f';

Insert into siifppptipocalendario (clave, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, isdefault) 
values ('001', 'Anual', 't','t','t','t','t','t','t','t','t','t','t','t','t');
