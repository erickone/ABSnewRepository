
CREATE TABLE informix.siifppptipogastointerno  ( 
	idtipogastointerno	SERIAL NOT NULL,
	descripcion             LVARCHAR(500) NOT NULL,
	clave                   LVARCHAR(50) NOT NULL,
	PRIMARY KEY(idtipogastointerno) 
        constraint "informix".pk_siifppptipogastointerno
	ENABLED);

CREATE TABLE informix.siifppptipogastoconac  ( 
	idtipogastoconac	SERIAL NOT NULL,
	descripcion             LVARCHAR(500) NOT NULL,
	clave                   LVARCHAR(50) NOT NULL,
	PRIMARY KEY(idtipogastoconac) 
        constraint "informix".pk_siifppptipogastoconac
	ENABLED);

ALTER TABLE informix.siifpppobjetogasto ADD idtipogastoconac INTEGER;
--ALTER TABLE informix.siifpppobjetogasto DROP anualizado;
ALTER TABLE informix.siifpppobjetogasto ADD idtipogastointerno INTEGER;
--ALTER informix.siifpppobjetogasto DROP idaniooperacionppp;
ALTER TABLE informix.siifpppobjetogasto ADD concepto LVARCHAR(3000);
ALTER TABLE informix.siifpppnivelobjgasto ADD idpadre INTEGER;

--Para los que ya habian corrido el otro script Favor de correr 
--ALTER TABLE informix.siifpppobjetogasto DROP CONSTRAINT "informix".fk_siifpppobjetogasto_siifpppdefcvepresupuestal
--UPDATE siifpppobjetogasto SET iddefcvepresupuestal = null;
--ALTER TABLE informix.siifpppobjetogasto
	--ADD CONSTRAINT ( FOREIGN KEY(iddefcvepresupuestal)
	--REFERENCES informix.siifpppdefcvepresupuestal(iddefcvepresupuestal) CONSTRAINT "informix".fk_siifpppobjetogasto_siifpppdefcvepresupuestal
	--ENABLED );
--UPDATE siifpppobjetogasto SET iddefcvepresupuestal = 2;

ALTER TABLE informix.siifpppobjetogasto
	ADD CONSTRAINT ( FOREIGN KEY(idtipogastoconac)
	REFERENCES informix.siifppptipogastoconac(idtipogastoconac) CONSTRAINT "informix".fk_siifpppobjetogasto_siifppptipogastoconac
	ENABLED );

ALTER TABLE informix.siifpppobjetogasto
	ADD CONSTRAINT ( FOREIGN KEY(idtipogastointerno)
	REFERENCES informix.siifppptipogastointerno(idtipogastointerno) CONSTRAINT "informix".fk_siifpppobjetogasto_siifppptipogastointerno
	ENABLED );
	
ALTER TABLE informix.siifpppobjetogasto
	ADD CONSTRAINT ( FOREIGN KEY(iddefcvepresupuestal)
	REFERENCES informix.siifpppdefcvepresupuestal(iddefcvepresupuestal) CONSTRAINT "informix".fk_siifpppobjetogasto_siifpppdefcvepresupuestal
	ENABLED );
	
ALTER TABLE informix.siifpppnivelobjgasto
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifpppnivelobjgasto(idnivelobjgasto) CONSTRAINT "informix".fk_siifpppnivelobjgasto_siifpppnivelobjgasto
	ENABLED );
	
UPDATE siifpppnivelobjgasto SET idpadre = 1 WHERE idnivelobjgasto = 2;
UPDATE siifpppnivelobjgasto SET idpadre = 2 WHERE idnivelobjgasto = 3;
UPDATE siifpppnivelobjgasto SET idpadre = 3 WHERE idnivelobjgasto = 4;
UPDATE siifpppobjetogasto SET anualizado = 'f';
UPDATE siifpppobjetogasto SET iddefcvepresupuestal = 2;