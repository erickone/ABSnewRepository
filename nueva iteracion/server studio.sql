CREATE TABLE informix.siifabsclasifregional  ( 
	idclasifregional   	VARCHAR(36) NOT NULL,
	descripcion        	LVARCHAR(255),
	claveinegi         	VARCHAR(36),
	anioinegi          	INTEGER,
	fronterizo         	BOOLEAN,
	clave              	VARCHAR(36),
	numhombres         	INTEGER,
	codpostal          	INTEGER,
	nummujeres         	INTEGER,
	idpadre            	VARCHAR(36),
	idnivclasifregional	VARCHAR(36),
	PRIMARY KEY(idclasifregional)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifabsdependencia  ( 
	iddependencia           	VARCHAR(36) NOT NULL,
	descripcion             	LVARCHAR(255),
	clave                   	LVARCHAR(255),
	idnivdependencia        	VARCHAR(36),
	iddependenciacapplaninst	VARCHAR(36),
	idpadre                 	VARCHAR(36),
	PRIMARY KEY(iddependencia)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifabsgpovulnerable  ( 
	idgpovulnerable	VARCHAR(36) NOT NULL,
	descripcion    	LVARCHAR(255) NOT NULL,
	clave          	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idgpovulnerable)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifabsnivclasifregional  ( 
	idnivclasifregional	VARCHAR(36) NOT NULL,
	descripcion        	LVARCHAR(255),
	gencolectiva       	BOOLEAN,
	colonia            	BOOLEAN,
	pais               	BOOLEAN,
	inegi              	BOOLEAN,
	localidad          	BOOLEAN,
	municipio          	BOOLEAN,
	codpostal          	BOOLEAN,
	region             	BOOLEAN,
	ambito             	BOOLEAN,
	estado             	BOOLEAN,
	clave              	LVARCHAR(255) NOT NULL,
	nivel              	INTEGER,
	PRIMARY KEY(idnivclasifregional)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifabsnivdependencia  ( 
	idnivdependencia    	VARCHAR(36) NOT NULL,
	iddefcvepresupuestal	VARCHAR(36),
	descripcion         	LVARCHAR(255),
	clave               	VARCHAR(36),
	presupuesta         	BOOLEAN,
	planinstitucional   	BOOLEAN,
	ramo                	BOOLEAN,
	unipresupuestal     	BOOLEAN,
	uniejecutora        	BOOLEAN,
	uniresponsable      	BOOLEAN,
	sector              	BOOLEAN,
	PRIMARY KEY(idnivdependencia)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifabsunimedida  ( 
	idunimedida	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255) NOT NULL,
	clave      	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idunimedida)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifabsusuario  ( 
	idusuario        	VARCHAR(36) NOT NULL,
	cambiarcontrasenia	BOOLEAN,
	descripcion      	LVARCHAR(255) NOT NULL,
	expiracontrasenia	BOOLEAN,
	habilitado       	BOOLEAN,
	fechaultimoacceso	DATE,
	usuario			varchar(25),
	contrasenia		varchar(25),
	PRIMARY KEY(idusuario)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppaccionmetabenef  ( 
	idaccionmetabenef	VARCHAR(36) NOT NULL,
	descripcion      	LVARCHAR(255) NOT NULL,
	clave            	VARCHAR(100) NOT NULL,
	idconceptoobra   	VARCHAR(36),
	PRIMARY KEY(idaccionmetabenef)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppactividad  ( 
	idactividad 	VARCHAR(36) NOT NULL,
	descripcion 	LVARCHAR(255) NOT NULL,
	fechafin    	DATE NOT NULL,
	cantidad    	FLOAT NOT NULL,
	responsable 	LVARCHAR(255) NOT NULL,
	fechainicio 	DATE NOT NULL,
	idcomponente	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idactividad)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppactividadmens  ( 
	idactividadmens	VARCHAR(36) NOT NULL,
	distribucion   	FLOAT NOT NULL,
	mes            	VARCHAR(100) NOT NULL,
	idactividad    	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idactividadmens)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppambitoanteproy  ( 
	idambitoanteproyecto	VARCHAR(36) NOT NULL,
	descripcion         	LVARCHAR(255) NOT NULL,
	PRIMARY KEY(idambitoanteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppanteproyecto  ( 
	idanteproyecto      	VARCHAR(36) NOT NULL,
	tipoficha           	INTEGER,
	fundamentolegal     	LVARCHAR(255),
	descripcion         	LVARCHAR(255) NOT NULL,
	fechafin            	DATE,
	clave               	VARCHAR(36),
	nombre              	VARCHAR(36) NOT NULL,
	proposito           	LVARCHAR(255) NOT NULL,
	nombrecorto         	VARCHAR(100) NOT NULL,
	fechainicio         	DATE,
	iddependencia       	VARCHAR(36),
	idprogramacion      	VARCHAR(36),
	idambitoanteproyecto	VARCHAR(36) NOT NULL,
	idestadoanteproyecto	VARCHAR(36) NOT NULL,
	idtipoanteproyecto  	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idanteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppaportacfisfinan  ( 
	idaportacionpreficha	VARCHAR(36) NOT NULL,
	finanfecha          	FLOAT,
	finanejecucion      	FLOAT,
	finansiganio        	FLOAT,
	fissiganio          	FLOAT,
	fisfecha            	FLOAT,
	fisejecucion        	FLOAT,
	idpreficha          	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idaportacionpreficha)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppaportacionprefich  ( 
	idaportacionpreficha	VARCHAR(36) NOT NULL,
	federal             	FLOAT,
	municipal           	FLOAT,
	particular          	FLOAT,
	especie             	FLOAT,
	estatal             	FLOAT,
	tipo                	VARCHAR(50) NOT NULL,
	idpreficha          	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idaportacionpreficha)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppavancefisprog  ( 
	idavancefisprog	VARCHAR(36) NOT NULL,
	mes            	INTEGER NOT NULL,
	porcentaje     	FLOAT NOT NULL,
	idpreficha     	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idavancefisprog)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppbitacoraanteproy  ( 
	idbitacoraanteproyecto	VARCHAR(36) NOT NULL,
	consecutivo           	INTEGER NOT NULL,
	fecha                 	DATETIME YEAR to FRACTION(5),
	diasreales            	INTEGER NOT NULL,
	idanteproyecto        	VARCHAR(36) NOT NULL,
	idestanteproyecto     	VARCHAR(36) NOT NULL,
	idusuario             	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idbitacoraanteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcamestanteproy  ( 
	idcamestanteproyecto	VARCHAR(36) NOT NULL,
	omision             	BOOLEAN,
	idestadoinicial     	VARCHAR(36) NOT NULL,
	idestadofinal       	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idcamestanteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppclasiffuncional  ( 
	idclasiffuncional   	VARCHAR(36) NOT NULL,
	definicionconac     	LVARCHAR(255),
	descripcion         	LVARCHAR(255),
	clave               	VARCHAR(36),
	idpadre             	VARCHAR(36),
	idnivclasiffuncional	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idclasiffuncional)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcomponente  ( 
	idcomponente   	VARCHAR(36) NOT NULL,
	descripcion    	LVARCHAR(255) NOT NULL,
	descindicador  	LVARCHAR(255) NOT NULL,
	nombreindicador	VARCHAR(100) NOT NULL,
	ponderacion    	INTEGER NOT NULL,
	identregable   	VARCHAR(36) NOT NULL,
	idunimedida    	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idcomponente)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcomponentemens  ( 
	idcomponentemens    	VARCHAR(36) NOT NULL,
	conceptoprogramacion	VARCHAR(100) NOT NULL,
	metaprogramada      	FLOAT NOT NULL,
	idcomponente        	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idcomponentemens)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppconceptogral  ( 
	idconceptogral	VARCHAR(36) NOT NULL,
	descripcion   	LVARCHAR(255) NOT NULL,
	clave         	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idconceptogral)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppconceptoobra  ( 
	idconceptoobra	VARCHAR(36) NOT NULL,
	descripcion   	LVARCHAR(255) NOT NULL,
	clave         	VARCHAR(100) NOT NULL,
	idconceptogral	VARCHAR(36),
	PRIMARY KEY(idconceptoobra)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcveestado  ( 
	idcveestado	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255),
	clave      	VARCHAR(100),
	estatus    	VARCHAR(1),
	PRIMARY KEY(idcveestado)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcvemensualpptal  ( 
	idcvemensualpptal	VARCHAR(36) NOT NULL,
	mes              	VARCHAR(36),
	montooriginal    	FLOAT,
	idcvepresupuestal	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idcvemensualpptal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcvepptalcfg  ( 
	idcvecfg       	VARCHAR(36) NOT NULL,
	idnivelcreacion	SMALLINT,
	posiciones     	SMALLINT,
	tiposql        	INTEGER,
	sqlvalida      	LVARCHAR(2000),
	idelemento     	VARCHAR(36),
	PRIMARY KEY(idcvecfg)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppcvepresupuestal  ( 
	idcvepresupuestal     	VARCHAR(36) NOT NULL,
	clave                 	VARCHAR(36),
	montooriginal         	FLOAT,
	iddefcvepresupuestal  	VARCHAR(36),
	idcveestado           	VARCHAR(36),
	idcomponente          	VARCHAR(36),
	idfuentefinanciamiento	VARCHAR(36),
	idobjetogasto         	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idcvepresupuestal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdefcveestado  ( 
	iddefcveestado	VARCHAR(36) NOT NULL,
	descripcion   	LVARCHAR(255),
	clave         	VARCHAR(100),
	PRIMARY KEY(iddefcveestado)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdefcvepptal  ( 
	iddefcvepresupuestal	VARCHAR(36) NOT NULL,
	descripcion         	VARCHAR(36),
	mascara             	VARCHAR(36),
	anio                	INTEGER,
	iddefcveestado      	VARCHAR(36) NOT NULL,
	PRIMARY KEY(iddefcvepresupuestal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdefcvepresupuestal  ( 
	iddefcvepresupuestal	VARCHAR(36) NOT NULL,
	descripcion         	VARCHAR(36),
	mascara             	VARCHAR(36),
	anio                	INTEGER,
	iddefcveestado      	VARCHAR(36) NOT NULL,
	PRIMARY KEY(iddefcvepresupuestal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdepfederal  ( 
	iddepfederal	VARCHAR(36) NOT NULL,
	descripcion	VARCHAR(36),
	PRIMARY KEY(iddepfederal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdestino  ( 
	iddestino  	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255) NOT NULL,
	clave      	VARCHAR(100) NOT NULL,
	PRIMARY KEY(iddestino)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdetallecvepptal  ( 
	iddetallecvepptal	VARCHAR(36) NOT NULL,
	budgetkeyconfigid	INTEGER NOT NULL,
	idreal           	INTEGER NOT NULL,
	idcvepresupuestal	VARCHAR(36) NOT NULL,
	PRIMARY KEY(iddetallecvepptal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdetcvepreficha  ( 
	iddetcvepreficha 	VARCHAR(36) NOT NULL,
	montoasignado    	FLOAT,
	idcvepresupuestal	VARCHAR(36) NOT NULL,
	idpreficha       	VARCHAR(36) NOT NULL,
	PRIMARY KEY(iddetcvepreficha)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppdimensionamiento  ( 
	iddimensionamiento	VARCHAR(36) NOT NULL,
	adicional         	FLOAT,
	cantidad          	INTEGER,
	consecutivo       	INTEGER,
	descripcion       	VARCHAR(36),
	inicial           	FLOAT,
	porcentaje        	FLOAT,
	suma              	FLOAT,
	idpreficha        	VARCHAR(36) NOT NULL,
	PRIMARY KEY(iddimensionamiento)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppelemento  ( 
	idelemento 	VARCHAR(36) NOT NULL,
	descripcion	VARCHAR(36),
	tabla      	VARCHAR(36),
	elemento   	VARCHAR(36),
	PRIMARY KEY(idelemento)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppencobjclasiffunc  ( 
	idclasiffuncional	VARCHAR(36) NOT NULL,
	idobjetivo       	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idclasiffuncional,idobjetivo)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppencplaninstobj  ( 
	idplaninstitucional	VARCHAR(36) NOT NULL,
	idobjetivo         	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppentregable  ( 
	identregable  	VARCHAR(36) NOT NULL,
	proposito     	LVARCHAR(255) NOT NULL,
	idanteproyecto	VARCHAR(36) NOT NULL,
	PRIMARY KEY(identregable)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppestadoanteproy  ( 
	idestadoanteproyecto	VARCHAR(36) NOT NULL,
	descripcion         	LVARCHAR(255) NOT NULL,
	diasestimados       	INTEGER NOT NULL,
	PRIMARY KEY(idestadoanteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppestconfiganteproy  ( 
	idestconfiganteproyecto	VARCHAR(36) NOT NULL,
	autoriza               	BOOLEAN,
	cancelado              	BOOLEAN,
	final                  	BOOLEAN,
	inicial                	BOOLEAN,
	validasefin            	BOOLEAN,
	validaseplan           	BOOLEAN,
	idestadoanteproyecto   	VARCHAR(36),
	PRIMARY KEY(idestconfiganteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppestrategia  ( 
	idestrategia   	VARCHAR(36) NOT NULL,
	descripcion    	LVARCHAR(255),
	idobjespecifico	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idestrategia)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppfuefinanciamiento  ( 
	idfuentefinanciamiento	VARCHAR(36) NOT NULL,
	descripcion           	LVARCHAR(255) NOT NULL,
	clave                 	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idfuentefinanciamiento)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifppplininversion  ( 
	idlininversion	VARCHAR(36) NOT NULL,
	descripcion   	LVARCHAR(255) NOT NULL,
	clave         	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idlininversion)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppmacroobra  ( 
	idmacroobra	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255) NOT NULL,
	clave      	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idmacroobra)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppmetabeneficiario  ( 
	idmetabeneficiario	VARCHAR(36) NOT NULL,
	benefdirectos     	FLOAT NOT NULL,
	benefindirectos   	FLOAT NOT NULL,
	periodoejecucion  	INTEGER,
	metaporanio       	FLOAT,
	metatotal         	FLOAT,
	idacciongb        	VARCHAR(36) NOT NULL,
	idconceptoobra    	VARCHAR(36) NOT NULL,
	idconceptogral    	VARCHAR(36) NOT NULL,
	idpreficha        	VARCHAR(36),
	idliniversion     	VARCHAR(36) NOT NULL,
	idmacroobra       	VARCHAR(36) NOT NULL,
	idunimedidabenef  	VARCHAR(36) NOT NULL,
	idunimedidameta   	VARCHAR(36) NOT NULL,
	idgpovulnerable   	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idmetabeneficiario)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppnivclasiffunc  ( 
	idnivclasiffuncional	VARCHAR(36) NOT NULL,
	nivel               	INTEGER,
	descripcion         	LVARCHAR(255),
	finalidad           	BOOLEAN,
	funcion             	BOOLEAN,
	subfuncion          	BOOLEAN,
	PRIMARY KEY(idnivclasiffuncional)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppnivelobjetivo  ( 
	idnivelobjetivo     	VARCHAR(36) NOT NULL,
	nivel               	SMALLINT NOT NULL,
	descripcion         	LVARCHAR(255),
	capclasiffuncional  	BOOLEAN,
	capindicador        	BOOLEAN,
	capprogramacion     	BOOLEAN,
	clave               	VARCHAR(100) NOT NULL,
	encuadreueg         	BOOLEAN,
	idnivclasiffuncional	VARCHAR(36),
	PRIMARY KEY(idnivelobjetivo)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppnivelobjgasto  ( 
	idnivelobjgasto	VARCHAR(36) NOT NULL,
	capitulo       	BOOLEAN,
	concepto       	BOOLEAN,
	descripcion    	VARCHAR(36),
	clasifeconomico	VARCHAR(36),
	pargenerica    	BOOLEAN,
	nivel          	INTEGER,
	carrelleno     	VARCHAR(36),
	clave          	VARCHAR(36),
	numcaracteres  	VARCHAR(36),
	prefijo        	VARCHAR(36),
	parespecifica  	BOOLEAN,
	PRIMARY KEY(idnivelobjgasto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppnivelprog  ( 
	idnivelprogramacion	VARCHAR(36) NOT NULL,
	nivel              	SMALLINT NOT NULL,
	descripcion        	LVARCHAR(255),
	anteproyecto       	BOOLEAN,
	clave              	VARCHAR(100) NOT NULL,
	PRIMARY KEY(idnivelprogramacion)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppobjespecifico  ( 
	idobjespecifico	VARCHAR(36) NOT NULL,
	descripcion    	LVARCHAR(255),
	idobjetivo     	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idobjespecifico)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppobjetivo  ( 
	idobjetivo     	VARCHAR(36) NOT NULL,
	definicion     	LVARCHAR(255),
	clave          	VARCHAR(36),
	nombre         	LVARCHAR(255),
	prioridad      	SMALLINT,
	idpadre        	VARCHAR(36),
	idnivelobjetivo	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idobjetivo)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppobjetogasto  ( 
	idobjetogasto       	VARCHAR(36) NOT NULL,
	adefas              	BOOLEAN,
	fondo               	BOOLEAN,
	combancaria         	BOOLEAN,
	descripcion         	VARCHAR(36),
	habilitada          	BOOLEAN,
	genresguardo        	BOOLEAN,
	compinicial         	BOOLEAN,
	irreductible        	BOOLEAN,
	clave               	INTEGER,
	arrendamiento       	BOOLEAN,
	iddefcvepresupuestal	VARCHAR(36),
	inversionpublica    	BOOLEAN,
	partidaespecial     	BOOLEAN,
	tipototalgasto      	INTEGER,
	idtipogasto         	VARCHAR(36),
	viatico             	BOOLEAN,
	idpadre             	VARCHAR(36),
	idnivelobjgasto     	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idobjetogasto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppobjgastodestino  ( 
	iddestino    	VARCHAR(36) NOT NULL,
	idobjetogasto	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppobjgastofuefinan  ( 
	idfuentefinanciamiento	VARCHAR(36) NOT NULL,
	idobjetogasto         	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifppppercamestanteproy  ( 
	idcamestanteproyecto	VARCHAR(36) NOT NULL,
	idperfil            	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppplaninstitucional  ( 
	idplaninstitucional	VARCHAR(36) NOT NULL,
	bloqueado          	BOOLEAN,
	ejecinversion      	BOOLEAN,
	normainversion     	BOOLEAN,
	organismo          	BOOLEAN,
	fechaactorganigrama	DATE,
	fechaextincion     	DATE,
	fechapubperultmodif	DATE,
	diagnostico        	LVARCHAR(255),
	formaextincion     	LVARCHAR(255),
	objetivogeneral    	LVARCHAR(255),
	numperofireginterno	VARCHAR(100),
	fechapubreginterno 	DATE,
	fundamentolegal    	LVARCHAR(255),
	mision             	LVARCHAR(255),
	numperoficial      	VARCHAR(100),
	numperofiultmodif  	VARCHAR(100),
	fechapubperoficial 	DATE,
	organigrama        	LVARCHAR(1000),
	fechainiactividades	DATE,
	vision             	LVARCHAR(255),
	iddependencia      	VARCHAR(36),
	PRIMARY KEY(idplaninstitucional)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppplanregional  ( 
	idplanregional  	VARCHAR(36) NOT NULL,
	objetivo        	LVARCHAR(255),
	idclasifregional	VARCHAR(36),
	PRIMARY KEY(idplanregional)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifppppreficha  ( 
	idpreficha   	VARCHAR(36) NOT NULL,
	accion       	BOOLEAN,
	obra         	BOOLEAN,
	descbeneficios	LVARCHAR(255) NOT NULL,
	descripcion  	LVARCHAR(255) NOT NULL,
	proyespecifico	BOOLEAN,
	folio        	LVARCHAR(255) NOT NULL,
	fondo        	BOOLEAN,
	nombre       	LVARCHAR(255) NOT NULL,
	prioridad    	VARCHAR(100) NOT NULL,
	programa     	BOOLEAN,
	idcomponente 	VARCHAR(36),
	idanteproyecto	VARCHAR(36) NOT NULL,
	iduniejecutora	VARCHAR(36) NOT NULL,
	idurnormativa	VARCHAR(36) NOT NULL,
	idpromotor   	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idpreficha)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppprefichaclasifreg  ( 
	idprefichaclasifreg	VARCHAR(36) NOT NULL,
	porcentaje         	FLOAT,
	idpreficha         	VARCHAR(36) NOT NULL,
	idclasifregional   	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idprefichaclasifreg)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppprefichacompon  ( 
	idpreficha  	VARCHAR(36) NOT NULL,
	idcomponente	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppprefichadep  ( 
	idpreficha   	VARCHAR(36) NOT NULL,
	iddependencia	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppprefichaobserv  ( 
	idprefichaobservacion	VARCHAR(36) NOT NULL,
	comentario           	LVARCHAR(255) NOT NULL,
	fechacomentario      	DATE,
	idpreficha           	VARCHAR(36) NOT NULL,
	idusuario            	VARCHAR(36) NOT NULL,
	iddependencia        	VARCHAR(36),
	PRIMARY KEY(idprefichaobservacion)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppproblema  ( 
	idproblema	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255),
	idobjetivo	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idproblema)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppprogfisfinanciero  ( 
	idprogfisfinanciero        	VARCHAR(36) NOT NULL,
	progfisfinancierofechafinal	DATE,
	progfisfinancieroperiodo   	INTEGER,
	progfisfinancierofechaini  	DATE,
	progfisfinancieroestatus   	INTEGER NOT NULL,
	idpreficha                 	VARCHAR(36),
	PRIMARY KEY(idprogfisfinanciero)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppprogramacion  ( 
	idprogramacion     	VARCHAR(36) NOT NULL,
	descripcion        	LVARCHAR(255),
	clave              	VARCHAR(50),
	idpadre            	VARCHAR(36),
	iddependencia      	VARCHAR(36) NOT NULL,
	idnivelprogramacion	VARCHAR(36) NOT NULL,
	idobjetivo         	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idprogramacion)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifppppromotor  ( 
	idpromotor 	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255) NOT NULL,
	PRIMARY KEY(idpromotor)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppreqpreinversion  ( 
	idreqpreinversion      	VARCHAR(36) NOT NULL,
	actaaceptcomunidad     	BOOLEAN,
	acredpropiedadpredio   	BOOLEAN,
	otrotipoderequerimiento	BOOLEAN,
	numeroautorizacion     	VARCHAR(10),
	autorizacionusosuelo   	BOOLEAN,
	estudiocostobeneficio  	BOOLEAN,
	dictamenimpamb         	BOOLEAN,
	proyectoejecutivo      	BOOLEAN,
	dictamenfactibilidad   	BOOLEAN,
	archivo                	VARCHAR(36),
	actaintegcomiteobra    	BOOLEAN,
	otrosestudios          	VARCHAR(36),
	estudiomecanicasuelos  	BOOLEAN,
	iddepfederal           	VARCHAR(36) NOT NULL,
	idpreficha             	VARCHAR(36) NOT NULL,
	PRIMARY KEY(idreqpreinversion)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifppptechopptal  ( 
	idtechopptal 	VARCHAR(36) NOT NULL,
	monto        	INT8,
	consecutivo  	INT8,
	iddependencia	VARCHAR(36) NOT NULL,
	idobjetogasto	VARCHAR(36),
	PRIMARY KEY(idtechopptal)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifppptipoanteproy  ( 
	idtipoanteproyecto	VARCHAR(36) NOT NULL,
	descripcion       	LVARCHAR(255) NOT NULL,
	PRIMARY KEY(idtipoanteproyecto)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppunimedmeta  ( 
	idunimedmeta	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255),
	PRIMARY KEY(idunimedmeta)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppunimedmetabenef  ( 
	idunimedbenmeta	VARCHAR(36) NOT NULL,
	descripcion    	LVARCHAR(255),
	PRIMARY KEY(idunimedbenmeta)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
CREATE TABLE informix.siifsegperfil  ( 
	idperfil   	VARCHAR(36) NOT NULL,
	descripcion	LVARCHAR(255) NOT NULL,
	PRIMARY KEY(idperfil)
	ENABLED
)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;

CREATE TABLE informix.siifpppanteproyclasifreg  ( 
 idanteproyclasifreg VARCHAR(36) NOT NULL,
 idanteproyecto      VARCHAR(36) NOT NULL,
 idclasifregional    VARCHAR(36) NOT NULL,
 PRIMARY KEY(idanteproyclasifreg)
 ENABLED
)
LOCK MODE PAGE
;
CREATE TABLE informix.siifpppclasificacion  ( 
 idclasificacion VARCHAR(36) NOT NULL,
 idanteproyecto  VARCHAR(36) NOT NULL,
 idfinalidad     VARCHAR(36) NOT NULL,
 idfuncion       VARCHAR(36) NOT NULL,
 idsubfuncion    VARCHAR(36) NOT NULL,
 PRIMARY KEY(idclasificacion)
 ENABLED
)
LOCK MODE PAGE
;
ALTER TABLE informix.siifpppclasificacion
 ADD CONSTRAINT ( FOREIGN KEY(idfinalidad)
 REFERENCES informix.siifpppclasiffuncional(idclasiffuncional) CONSTRAINT fk6a1f1b97c7d3bd0e
 ENABLED )
;
ALTER TABLE informix.siifpppclasificacion
 ADD CONSTRAINT ( FOREIGN KEY(idsubfuncion)
 REFERENCES informix.siifpppclasiffuncional(idclasiffuncional) CONSTRAINT fk6a1f1b97ab6996b4
 ENABLED )
;
ALTER TABLE informix.siifpppclasificacion
 ADD CONSTRAINT ( FOREIGN KEY(idanteproyecto)
 REFERENCES informix.siifpppanteproyecto(idanteproyecto) CONSTRAINT fk6a1f1b979d792edf
 ENABLED )
;
ALTER TABLE informix.siifpppclasificacion
 ADD CONSTRAINT ( FOREIGN KEY(idfuncion)
 REFERENCES informix.siifpppclasiffuncional(idclasiffuncional) CONSTRAINT fk6a1f1b9779c6441e
 ENABLED )
;
ALTER TABLE informix.siifpppanteproyclasifreg
 ADD CONSTRAINT ( FOREIGN KEY(idanteproyecto)
 REFERENCES informix.siifpppanteproyecto(idanteproyecto) CONSTRAINT fk422ed6559d792edf
 ENABLED )
;
ALTER TABLE informix.siifpppanteproyclasifreg
 ADD CONSTRAINT ( FOREIGN KEY(idclasifregional)
 REFERENCES informix.siifabsclasifregional(idclasifregional) CONSTRAINT fk422ed65549439b23
 ENABLED )
;

CREATE TABLE informix.siifpppvalseplan  ( 
 idvalidacion        VARCHAR(36) NOT NULL,
 respuestapregunta   BOOLEAN,
 idtipochecklist     VARCHAR(36),
 observaciones       VARCHAR(255),
 descripcionpregunta VARCHAR(255),
 idpregunta          VARCHAR(36),
 idcomponente        VARCHAR(36) NOT NULL,
 PRIMARY KEY(idvalidacion)
 ENABLED
)
LOCK MODE PAGE
;
ALTER TABLE informix.siifpppvalseplan
 ADD CONSTRAINT ( FOREIGN KEY(idcomponente)
 REFERENCES informix.siifpppcomponente(idcomponente) CONSTRAINT fkc98dfdf4be78bbd
 ENABLED );

CREATE TABLE informix.siifsegusuperfil  ( 
	idperfil 	VARCHAR(36) NOT NULL,
	idusuario	VARCHAR(36) NOT NULL 
	)

EXTENT SIZE 32 NEXT SIZE 32 
LOCK MODE PAGE
;
ALTER TABLE informix.siifpppnivelobjetivo
	ADD CONSTRAINT ( UNIQUE (clave) CONSTRAINT u6202_31171
	ENABLED )
;
ALTER TABLE informix.siifpppnivelobjetivo
	ADD CONSTRAINT ( UNIQUE (nivel) CONSTRAINT u6202_31170
	ENABLED )
;
ALTER TABLE informix.siifpppnivelprog
	ADD CONSTRAINT ( UNIQUE (clave) CONSTRAINT u6180_31083
	ENABLED )
;
ALTER TABLE informix.siifpppnivelprog
	ADD CONSTRAINT ( UNIQUE (nivel) CONSTRAINT u6180_31082
	ENABLED )
;
ALTER TABLE informix.siifabsclasifregional
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifabsclasifregional(idclasifregional) CONSTRAINT fk8033c2362ccf583c
	ENABLED )
;
ALTER TABLE informix.siifpppplanregional
	ADD CONSTRAINT ( FOREIGN KEY(idclasifregional)
	REFERENCES informix.siifabsclasifregional(idclasifregional) CONSTRAINT fkbdcd406549439b23
	ENABLED )
;
ALTER TABLE informix.siifpppprefichaclasifreg
	ADD CONSTRAINT ( FOREIGN KEY(idclasifregional)
	REFERENCES informix.siifabsclasifregional(idclasifregional) CONSTRAINT fk2e4b836949439b23
	ENABLED )
;
ALTER TABLE informix.siifabsdependencia
	ADD CONSTRAINT ( FOREIGN KEY(iddependenciacapplaninst)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk4902d8787c88e1e
	ENABLED )
;
ALTER TABLE informix.siifabsdependencia
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk4902d877743aa93
	ENABLED )
;
ALTER TABLE informix.siifpppanteproyecto
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk4daba680b866b153
	ENABLED )
;
ALTER TABLE informix.siifpppplaninstitucional
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk4204b28eb866b153
	ENABLED )
;
ALTER TABLE informix.siifppppreficha
	ADD CONSTRAINT ( FOREIGN KEY(idurnormativa)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk7b6052d3cc533055
	ENABLED )
;
ALTER TABLE informix.siifppppreficha
	ADD CONSTRAINT ( FOREIGN KEY(iduniejecutora)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk7b6052d3a43c8c2f
	ENABLED )
;
ALTER TABLE informix.siifpppprefichadep
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk6e47dc5cb866b153
	ENABLED )
;
ALTER TABLE informix.siifpppprefichaobserv
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk8f2264fcb866b153
	ENABLED )
;
ALTER TABLE informix.siifpppprogramacion
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fkcc744c9fb866b153
	ENABLED )
;
ALTER TABLE informix.siifppptechopptal
	ADD CONSTRAINT ( FOREIGN KEY(iddependencia)
	REFERENCES informix.siifabsdependencia(iddependencia) CONSTRAINT fk668a83e3b866b153
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idgpovulnerable)
	REFERENCES informix.siifabsgpovulnerable(idgpovulnerable) CONSTRAINT fk353ec49cce1bb0a7
	ENABLED )
;
ALTER TABLE informix.siifabsclasifregional
	ADD CONSTRAINT ( FOREIGN KEY(idnivclasifregional)
	REFERENCES informix.siifabsnivclasifregional(idnivclasifregional) CONSTRAINT fk8033c236ba58ea12
	ENABLED )
;
ALTER TABLE informix.siifabsdependencia
	ADD CONSTRAINT ( FOREIGN KEY(idnivdependencia)
	REFERENCES informix.siifabsnivdependencia(idnivdependencia) CONSTRAINT fk4902d87651fa828
	ENABLED )
;
ALTER TABLE informix.siifabsunimedida
	ADD CONSTRAINT ( FOREIGN KEY(idunimedida)
	REFERENCES informix.siifabsunimedida(idunimedida) CONSTRAINT fk5ac04f8ba9f38ae6
	ENABLED )
;
ALTER TABLE informix.siifpppcomponente
	ADD CONSTRAINT ( FOREIGN KEY(idunimedida)
	REFERENCES informix.siifabsunimedida(idunimedida) CONSTRAINT fk5aed8e65a9f38ae6
	ENABLED )
;
ALTER TABLE informix.siifpppbitacoraanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idusuario)
	REFERENCES informix.siifabsusuario(idusuario) CONSTRAINT fk5c07315690af26f2
	ENABLED )
;
ALTER TABLE informix.siifpppprefichaobserv
	ADD CONSTRAINT ( FOREIGN KEY(idusuario)
	REFERENCES informix.siifabsusuario(idusuario) CONSTRAINT fk8f2264fc90af26f2
	ENABLED )
;
ALTER TABLE informix.siifsegusuperfil
	ADD CONSTRAINT ( FOREIGN KEY(idusuario)
	REFERENCES informix.siifabsusuario(idusuario) CONSTRAINT fkb7d03ee190af26f2
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idacciongb)
	REFERENCES informix.siifpppaccionmetabenef(idaccionmetabenef) CONSTRAINT fk353ec49cb9869497
	ENABLED )
;
ALTER TABLE informix.siifpppactividadmens
	ADD CONSTRAINT ( FOREIGN KEY(idactividad)
	REFERENCES informix.siifpppactividad(idactividad) CONSTRAINT fk481a3a9dfcc9bfa
	ENABLED )
;
ALTER TABLE informix.siifpppanteproyecto
	ADD CONSTRAINT ( FOREIGN KEY(idambitoanteproyecto)
	REFERENCES informix.siifpppambitoanteproy(idambitoanteproyecto) CONSTRAINT fk4daba68068b43f05
	ENABLED )
;
ALTER TABLE informix.siifpppbitacoraanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idanteproyecto)
	REFERENCES informix.siifpppanteproyecto(idanteproyecto) CONSTRAINT fk5c0731569d792edf
	ENABLED )
;
ALTER TABLE informix.siifpppentregable
	ADD CONSTRAINT ( FOREIGN KEY(idanteproyecto)
	REFERENCES informix.siifpppanteproyecto(idanteproyecto) CONSTRAINT fkdebb98009d792edf
	ENABLED )
;
ALTER TABLE informix.siifppppreficha
	ADD CONSTRAINT ( FOREIGN KEY(idanteproyecto)
	REFERENCES informix.siifpppanteproyecto(idanteproyecto) CONSTRAINT fk7b6052d39d792edf
	ENABLED )
;
ALTER TABLE informix.siifppppercamestanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idcamestanteproyecto)
	REFERENCES informix.siifpppcamestanteproy(idcamestanteproyecto) CONSTRAINT fk15136ca1ce79bf58
	ENABLED )
;
ALTER TABLE informix.siifpppclasiffuncional
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifpppclasiffuncional(idclasiffuncional) CONSTRAINT fk16f19ada978ab8a0
	ENABLED )
;
ALTER TABLE informix.siifpppencobjclasiffunc
	ADD CONSTRAINT ( FOREIGN KEY(idclasiffuncional)
	REFERENCES informix.siifpppclasiffuncional(idclasiffuncional) CONSTRAINT fk8941bad6a2bba491
	ENABLED )
;
ALTER TABLE informix.siifpppactividad
	ADD CONSTRAINT ( FOREIGN KEY(idcomponente)
	REFERENCES informix.siifpppcomponente(idcomponente) CONSTRAINT fk49cbc5404be78bbd
	ENABLED )
;
ALTER TABLE informix.siifpppcomponentemens
	ADD CONSTRAINT ( FOREIGN KEY(idcomponente)
	REFERENCES informix.siifpppcomponente(idcomponente) CONSTRAINT fkf338e9424be78bbd
	ENABLED )
;
ALTER TABLE informix.siifpppcvepresupuestal
	ADD CONSTRAINT ( FOREIGN KEY(idcomponente)
	REFERENCES informix.siifpppcomponente(idcomponente) CONSTRAINT fk5d84577c4be78bbd
	ENABLED )
;
ALTER TABLE informix.siifppppreficha
	ADD CONSTRAINT ( FOREIGN KEY(idcomponente)
	REFERENCES informix.siifpppcomponente(idcomponente) CONSTRAINT fk7b6052d34be78bbd
	ENABLED )
;
ALTER TABLE informix.siifpppconceptoobra
	ADD CONSTRAINT ( FOREIGN KEY(idconceptogral)
	REFERENCES informix.siifpppconceptogral(idconceptogral) CONSTRAINT fk40f55b2615935501
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idconceptogral)
	REFERENCES informix.siifpppconceptogral(idconceptogral) CONSTRAINT fk353ec49c15935501
	ENABLED )
;
ALTER TABLE informix.siifpppaccionmetabenef
	ADD CONSTRAINT ( FOREIGN KEY(idconceptoobra)
	REFERENCES informix.siifpppconceptoobra(idconceptoobra) CONSTRAINT fkd0da95e3107e9015
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idconceptoobra)
	REFERENCES informix.siifpppconceptoobra(idconceptoobra) CONSTRAINT fk353ec49c107e9015
	ENABLED )
;
ALTER TABLE informix.siifpppcvepresupuestal
	ADD CONSTRAINT ( FOREIGN KEY(idcveestado)
	REFERENCES informix.siifpppcveestado(idcveestado) CONSTRAINT fk5d84577c20579876
	ENABLED )
;
ALTER TABLE informix.siifpppcvemensualpptal
	ADD CONSTRAINT ( FOREIGN KEY(idcvepresupuestal)
	REFERENCES informix.siifpppcvepresupuestal(idcvepresupuestal) CONSTRAINT fk2364ec91563613a5
	ENABLED )
;
ALTER TABLE informix.siifpppdetallecvepptal
	ADD CONSTRAINT ( FOREIGN KEY(idcvepresupuestal)
	REFERENCES informix.siifpppcvepresupuestal(idcvepresupuestal) CONSTRAINT fk38e993c7563613a5
	ENABLED )
;
ALTER TABLE informix.siifpppdetcvepreficha
	ADD CONSTRAINT ( FOREIGN KEY(idcvepresupuestal)
	REFERENCES informix.siifpppcvepresupuestal(idcvepresupuestal) CONSTRAINT fkd28a7b72563613a5
	ENABLED )
;
ALTER TABLE informix.siifpppdefcvepresupuestal
	ADD CONSTRAINT ( FOREIGN KEY(iddefcveestado)
	REFERENCES informix.siifpppdefcveestado(iddefcveestado) CONSTRAINT fk5b993c91bb31555a
	ENABLED )
;
ALTER TABLE informix.siifpppcvepresupuestal
	ADD CONSTRAINT ( FOREIGN KEY(iddefcvepresupuestal)
	REFERENCES informix.siifpppdefcvepresupuestal(iddefcvepresupuestal) CONSTRAINT fk5d84577c44784a09
	ENABLED )
;
ALTER TABLE informix.siifpppreqpreinversion
	ADD CONSTRAINT ( FOREIGN KEY(iddepfederal)
	REFERENCES informix.siifpppdepfederal(iddepfederal) CONSTRAINT fk69b307517261c3ca
	ENABLED )
;
ALTER TABLE informix.siifpppobjgastodestino
	ADD CONSTRAINT ( FOREIGN KEY(iddestino)
	REFERENCES informix.siifpppdestino(iddestino) CONSTRAINT fkbd81718e6824dfa8
	ENABLED )
;
ALTER TABLE informix.siifpppcvepptalcfg
	ADD CONSTRAINT ( FOREIGN KEY(idelemento)
	REFERENCES informix.siifpppelemento(idelemento) CONSTRAINT fk4d8ca6fa87354368
	ENABLED )
;
ALTER TABLE informix.siifpppcomponente
	ADD CONSTRAINT ( FOREIGN KEY(identregable)
	REFERENCES informix.siifpppentregable(identregable) CONSTRAINT fk5aed8e6563b0479b
	ENABLED )
;
ALTER TABLE informix.siifpppanteproyecto
	ADD CONSTRAINT ( FOREIGN KEY(idestadoanteproyecto)
	REFERENCES informix.siifpppestadoanteproy(idestadoanteproyecto) CONSTRAINT fk4daba68065f46717
	ENABLED )
;
ALTER TABLE informix.siifpppbitacoraanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idestanteproyecto)
	REFERENCES informix.siifpppestadoanteproy(idestadoanteproyecto) CONSTRAINT fk5c0731568cb22461
	ENABLED )
;
ALTER TABLE informix.siifpppcamestanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idestadofinal)
	REFERENCES informix.siifpppestadoanteproy(idestadoanteproyecto) CONSTRAINT fk2d7818fecb28b7c8
	ENABLED )
;
ALTER TABLE informix.siifpppcamestanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idestadoinicial)
	REFERENCES informix.siifpppestadoanteproy(idestadoanteproyecto) CONSTRAINT fk2d7818fe975e7787
	ENABLED )
;
ALTER TABLE informix.siifpppestconfiganteproy
	ADD CONSTRAINT ( FOREIGN KEY(idestadoanteproyecto)
	REFERENCES informix.siifpppestadoanteproy(idestadoanteproyecto) CONSTRAINT fkd976d65565f46717
	ENABLED )
;
ALTER TABLE informix.siifpppcvepresupuestal
	ADD CONSTRAINT ( FOREIGN KEY(idfuentefinanciamiento)
	REFERENCES informix.siifpppfuefinanciamiento(idfuentefinanciamiento) CONSTRAINT fk5d84577c7ea4a6a6
	ENABLED )
;
ALTER TABLE informix.siifpppobjgastofuefinan
	ADD CONSTRAINT ( FOREIGN KEY(idfuentefinanciamiento)
	REFERENCES informix.siifpppfuefinanciamiento(idfuentefinanciamiento) CONSTRAINT fkf666badc7ea4a6a6
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idliniversion)
	REFERENCES informix.siifppplininversion(idlininversion) CONSTRAINT fk353ec49cf603c035
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idmacroobra)
	REFERENCES informix.siifpppmacroobra(idmacroobra) CONSTRAINT fk353ec49c6b12a7fe
	ENABLED )
;
ALTER TABLE informix.siifpppclasiffuncional
	ADD CONSTRAINT ( FOREIGN KEY(idnivclasiffuncional)
	REFERENCES informix.siifpppnivclasiffunc(idnivclasiffuncional) CONSTRAINT fk16f19adabf334e4b
	ENABLED )
;
ALTER TABLE informix.siifpppnivelobjetivo
	ADD CONSTRAINT ( FOREIGN KEY(idnivclasiffuncional)
	REFERENCES informix.siifpppnivclasiffunc(idnivclasiffuncional) CONSTRAINT fk6943d8c1bf334e4b
	ENABLED )
;
ALTER TABLE informix.siifpppobjetivo
	ADD CONSTRAINT ( FOREIGN KEY(idnivelobjetivo)
	REFERENCES informix.siifpppnivelobjetivo(idnivelobjetivo) CONSTRAINT fkd17511f964fcbd5f
	ENABLED )
;
ALTER TABLE informix.siifpppobjetogasto
	ADD CONSTRAINT ( FOREIGN KEY(idnivelobjgasto)
	REFERENCES informix.siifpppnivelobjgasto(idnivelobjgasto) CONSTRAINT fkb6c8eb8e5cf31f28
	ENABLED )
;
ALTER TABLE informix.siifpppprogramacion
	ADD CONSTRAINT ( FOREIGN KEY(idnivelprogramacion)
	REFERENCES informix.siifpppnivelprog(idnivelprogramacion) CONSTRAINT fkcc744c9f3cce007d
	ENABLED )
;
ALTER TABLE informix.siifpppestrategia
	ADD CONSTRAINT ( FOREIGN KEY(idobjespecifico)
	REFERENCES informix.siifpppobjespecifico(idobjespecifico) CONSTRAINT fk34c952166a3e7a10
	ENABLED )
;
ALTER TABLE informix.siifpppencobjclasiffunc
	ADD CONSTRAINT ( FOREIGN KEY(idobjetivo)
	REFERENCES informix.siifpppobjetivo(idobjetivo) CONSTRAINT fk8941bad624f0df45
	ENABLED )
;
ALTER TABLE informix.siifpppencplaninstobj
	ADD CONSTRAINT ( FOREIGN KEY(idobjetivo)
	REFERENCES informix.siifpppobjetivo(idobjetivo) CONSTRAINT fkac21fd6b24f0df45
	ENABLED )
;
ALTER TABLE informix.siifpppobjespecifico
	ADD CONSTRAINT ( FOREIGN KEY(idobjetivo)
	REFERENCES informix.siifpppobjetivo(idobjetivo) CONSTRAINT fka2dd6a7224f0df45
	ENABLED )
;
ALTER TABLE informix.siifpppobjetivo
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifpppobjetivo(idobjetivo) CONSTRAINT fkd17511f966147fb9
	ENABLED )
;
ALTER TABLE informix.siifpppproblema
	ADD CONSTRAINT ( FOREIGN KEY(idobjetivo)
	REFERENCES informix.siifpppobjetivo(idobjetivo) CONSTRAINT fk9014345f24f0df45
	ENABLED )
;
ALTER TABLE informix.siifpppprogramacion
	ADD CONSTRAINT ( FOREIGN KEY(idobjetivo)
	REFERENCES informix.siifpppobjetivo(idobjetivo) CONSTRAINT fkcc744c9f24f0df45
	ENABLED )
;
ALTER TABLE informix.siifpppcvepresupuestal
	ADD CONSTRAINT ( FOREIGN KEY(idobjetogasto)
	REFERENCES informix.siifpppobjetogasto(idobjetogasto) CONSTRAINT fk5d84577cbb9897d6
	ENABLED )
;
ALTER TABLE informix.siifpppobjetogasto
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifpppobjetogasto(idobjetogasto) CONSTRAINT fkb6c8eb8e1ad79a31
	ENABLED )
;
ALTER TABLE informix.siifpppobjgastodestino
	ADD CONSTRAINT ( FOREIGN KEY(idobjetogasto)
	REFERENCES informix.siifpppobjetogasto(idobjetogasto) CONSTRAINT fkbd81718ebb9897d6
	ENABLED )
;
ALTER TABLE informix.siifpppobjgastofuefinan
	ADD CONSTRAINT ( FOREIGN KEY(idobjetogasto)
	REFERENCES informix.siifpppobjetogasto(idobjetogasto) CONSTRAINT fkf666badcbb9897d6
	ENABLED )
;
ALTER TABLE informix.siifppptechopptal
	ADD CONSTRAINT ( FOREIGN KEY(idobjetogasto)
	REFERENCES informix.siifpppobjetogasto(idobjetogasto) CONSTRAINT fk668a83e3bb9897d6
	ENABLED )
;
ALTER TABLE informix.siifpppencplaninstobj
	ADD CONSTRAINT ( FOREIGN KEY(idplaninstitucional)
	REFERENCES informix.siifpppplaninstitucional(idplaninstitucional) CONSTRAINT fkac21fd6b45564731
	ENABLED )
;
ALTER TABLE informix.siifpppaportacfisfinan
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk793d7e2feefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppaportacionprefich
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk8ec5158aeefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppdetcvepreficha
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fkd28a7b72eefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppdimensionamiento
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk92f40d98eefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk353ec49ceefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppprefichaclasifreg
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk2e4b8369eefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppprefichadep
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk6e47dc5ceefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppprefichaobserv
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk8f2264fceefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppprogfisfinanciero
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk61207dbdeefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppreqpreinversion
	ADD CONSTRAINT ( FOREIGN KEY(idpreficha)
	REFERENCES informix.siifppppreficha(idpreficha) CONSTRAINT fk69b30751eefb1288
	ENABLED )
;
ALTER TABLE informix.siifpppanteproyecto
	ADD CONSTRAINT ( FOREIGN KEY(idprogramacion)
	REFERENCES informix.siifpppprogramacion(idprogramacion) CONSTRAINT fk4daba680767342f3
	ENABLED )
;
ALTER TABLE informix.siifpppprogramacion
	ADD CONSTRAINT ( FOREIGN KEY(idpadre)
	REFERENCES informix.siifpppprogramacion(idprogramacion) CONSTRAINT fkcc744c9fbebf0381
	ENABLED )
;
ALTER TABLE informix.siifppppreficha
	ADD CONSTRAINT ( FOREIGN KEY(idpromotor)
	REFERENCES informix.siifppppromotor(idpromotor) CONSTRAINT fk7b6052d3f1d805de
	ENABLED )
;
ALTER TABLE informix.siifpppanteproyecto
	ADD CONSTRAINT ( FOREIGN KEY(idtipoanteproyecto)
	REFERENCES informix.siifppptipoanteproy(idtipoanteproyecto) CONSTRAINT fk4daba68070ac076d
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idunimedidameta)
	REFERENCES informix.siifpppunimedmeta(idunimedmeta) CONSTRAINT fk353ec49c3335f85e
	ENABLED )
;
ALTER TABLE informix.siifpppmetabeneficiario
	ADD CONSTRAINT ( FOREIGN KEY(idunimedidabenef)
	REFERENCES informix.siifpppunimedmetabenef(idunimedbenmeta) CONSTRAINT fk353ec49cf8c3e4b2
	ENABLED )
;
ALTER TABLE informix.siifppppercamestanteproy
	ADD CONSTRAINT ( FOREIGN KEY(idperfil)
	REFERENCES informix.siifsegperfil(idperfil) CONSTRAINT fk15136ca199825642
	ENABLED )
;
ALTER TABLE informix.siifsegusuperfil
	ADD CONSTRAINT ( FOREIGN KEY(idperfil)
	REFERENCES informix.siifsegperfil(idperfil) CONSTRAINT fkb7d03ee199825642
	ENABLED )
;

create table informix.IDs
(
	idn serial,
	idS	varchar(36)
);

LOAD FROM 'IDs1.txt' delimiter '|' INSERT 
INTO informix.IDs(ids);


-- Llenado de Informacion de las Estructuras de Datos
-- ****************************************************************************

-- Niveles de Planeación Estratégica

INSERT INTO informix.siifpppnivelobjetivo(idnivelobjetivo, capindicador, encuadreueg, nivel, descripcion, capclasiffuncional, capprogramacion, clave, idnivclasiffuncional)
VALUES('402881ab-374d82b7-0137-4d82c07f-0000', 'f', 'f', 1, 'Eje Rector', 'f', 'f', '01', NULL::varchar)
;
INSERT INTO informix.siifpppnivelobjetivo(idnivelobjetivo, capindicador, encuadreueg, nivel, descripcion, capclasiffuncional, capprogramacion, clave, idnivclasiffuncional)
VALUES('402881ab-374d82b7-0137-4d82c07f-0001', 'f', 'f', 2, 'Programa', 'f', 'f', '02', NULL::varchar)
;
INSERT INTO informix.siifpppnivelobjetivo(idnivelobjetivo, capindicador, encuadreueg, nivel, descripcion, capclasiffuncional, capprogramacion, clave, idnivclasiffuncional)
VALUES('402881ab-374d82b7-0137-4d82c07f-0002', 'f', 't', 3, 'Subprograma', 't', 't', '03', NULL::varchar)
;

-- Niveles del clasificador Regional

INSERT INTO informix.siifabsnivclasifregional(idnivclasifregional, descripcion, gencolectiva, colonia, pais, inegi, localidad, municipio, codpostal, region, ambito, estado, clave, nivel)
VALUES('40288acb-3800e88f-0121-50e8e990-0001', 'Mundial', 'f', 'f', 't', 'f', 'f', 'f', 'f', 'f', 'f', 'f', '01', 1)
;
INSERT INTO informix.siifabsnivclasifregional(idnivclasifregional, descripcion, gencolectiva, colonia, pais, inegi, localidad, municipio, codpostal, region, ambito, estado, clave, nivel)
VALUES('40288acb-3800e88f-0121-50e8e990-0002', 'Estatal', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 't', 't', '02', 2)
;
INSERT INTO informix.siifabsnivclasifregional(idnivclasifregional, descripcion, gencolectiva, colonia, pais, inegi, localidad, municipio, codpostal, region, ambito, estado, clave, nivel)
VALUES('40288acb-3800e88f-0121-50e8e990-0003', 'Regional', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 't', 't', 'f', '03', 3)
;
INSERT INTO informix.siifabsnivclasifregional(idnivclasifregional, descripcion, gencolectiva, colonia, pais, inegi, localidad, municipio, codpostal, region, ambito, estado, clave, nivel)
VALUES('40288acb-3800e88f-0121-50e8e990-0004', 'Municipal', 'f', 'f', 'f', 'f', 'f', 't', 'f', 'f', 't', 'f', '04', 4)
;

-- Niveles del Clasificador Administrativo

INSERT INTO informix.siifabsnivdependencia(idnivdependencia, iddefcvepresupuestal, descripcion, clave, presupuesta, planinstitucional, ramo, unipresupuestal, uniejecutora, uniresponsable, sector)
VALUES('40288aeb-3750e88f-0137-50e8e990-0000', NULL, 'Ramo', '01', 'f', 'f', 't', 'f', 'f', 'f', 'f')
;
INSERT INTO informix.siifabsnivdependencia(idnivdependencia, iddefcvepresupuestal, descripcion, clave, presupuesta, planinstitucional, ramo, unipresupuestal, uniejecutora, uniresponsable, sector)
VALUES('40288aeb-3750e88f-0137-50e8e990-0001', NULL, 'Sector', '02', 'f', 'f', 'f', 'f', 'f', 'f', 't')
;
INSERT INTO informix.siifabsnivdependencia(idnivdependencia, iddefcvepresupuestal, descripcion, clave, presupuesta, planinstitucional, ramo, unipresupuestal, uniejecutora, uniresponsable, sector)
VALUES('40288aeb-3750e88f-0137-50e8e990-0002', NULL, 'Unidad Presupuestal', '03', 'f', 'f', 'f', 't', 'f', 'f', 'f')
;
INSERT INTO informix.siifabsnivdependencia(idnivdependencia, iddefcvepresupuestal, descripcion, clave, presupuesta, planinstitucional, ramo, unipresupuestal, uniejecutora, uniresponsable, sector)
VALUES('40288aeb-3750e88f-0137-50e8e990-0003', NULL, 'Unidad Responsable', '04', 'f', 't', 'f', 'f', 'f', 't', 'f')
;
INSERT INTO informix.siifabsnivdependencia(idnivdependencia, iddefcvepresupuestal, descripcion, clave, presupuesta, planinstitucional, ramo, unipresupuestal, uniejecutora, uniresponsable, sector)
VALUES('40288aeb-3750e88f-0137-50e8e990-0004', NULL, 'Unidad Ejecutora', '05', 't', 'f', 'f', 'f', 't', 'f', 'f')
;

-- Niveles de Clasificador Funcional
INSERT INTO informix.siifpppnivclasiffunc(idnivclasiffuncional, nivel, descripcion, finalidad, funcion, subfuncion)
VALUES('e929e44a-4e25-465b-8bd4-03591a202d01', 1, 'Finalidad', 't', 'f', 'f')
;
INSERT INTO informix.siifpppnivclasiffunc(idnivclasiffuncional, nivel, descripcion, finalidad, funcion, subfuncion)
VALUES('e929e44a-4e25-465b-8bd4-03591a202d02', 2, 'Funcion', 'f', 't', 'f')
;
INSERT INTO informix.siifpppnivclasiffunc(idnivclasiffuncional, nivel, descripcion, finalidad, funcion, subfuncion)
VALUES('e929e44a-4e25-465b-8bd4-03591a202d03', 3, 'SubFuncion', 'f', 'f', 't')
;

-- Niveles de Objeto del Gasto
INSERT INTO informix.siifpppnivelobjgasto(idnivelobjgasto,  clave, descripcion, nivel, prefijo, numcaracteres, carrelleno, clasifeconomico, capitulo, concepto, pargenerica, parespecifica) 
VALUES('40321aeb-3800e88f-0121-50e8e990-8756',  '1', 'Capitulo', 1, '', 12, '0', 'f', 't','f','f','f')
;
INSERT INTO informix.siifpppnivelobjgasto(idnivelobjgasto, clave, descripcion, nivel, prefijo, numcaracteres, carrelleno, clasifeconomico, capitulo, concepto, pargenerica, parespecifica) 
VALUES('40321aeb-3800e88f-0121-50e8e990-8737', '2', 'Concepto', 2, '', 12, '0', 'f', 'f','t','f','f')
;
INSERT INTO informix.siifpppnivelobjgasto(idnivelobjgasto, clave, descripcion, nivel, prefijo, numcaracteres, carrelleno, clasifeconomico, capitulo, concepto, pargenerica, parespecifica) 
VALUES('40321aeb-3800e88f-0121-50e8e990-8738', '3', 'Partida Generica', 3, '', 12, '0', 'f', 'f','f','t','f')
;
INSERT INTO informix.siifpppnivelobjgasto(idnivelobjgasto, clave, descripcion, nivel, prefijo, numcaracteres, carrelleno, clasifeconomico, capitulo, concepto, pargenerica, parespecifica) 
VALUES('40321aeb-3800e88f-0121-50e8e990-8739', '4', 'Partida Especifica', 4, '', 12, '0', 'f', 'f','f','f','t')
;

-- Niveles de Programacion

INSERT INTO informix.siifpppnivelprog(idnivelprogramacion, nivel, descripcion, anteproyecto, clave)
VALUES('40288aeb-3750e88f-0137-50e8a990-0000', 1, 'Proyecto', 'f', '001')
;
INSERT INTO informix.siifpppnivelprog(idnivelprogramacion, nivel, descripcion, anteproyecto, clave)
VALUES('40288aeb-3750e88f-0137-50e8a990-0001', 2, 'Obra', 'f', '002')
;
INSERT INTO informix.siifpppnivelprog(idnivelprogramacion, nivel, descripcion, anteproyecto, clave)
VALUES('40288aeb-3750e88f-0137-50e8a990-0002', 3, 'Anteproyecto', 't', '003')
;


-- Estado de Definición de Clave Presupuestal
INSERT INTO informix.siifpppdefcveestado(iddefcveestado, descripcion, clave) 
VALUES('34r6b29c-a553-21h8-9c98-4f88f0cd51h0', 'Actual', '01')
;
INSERT INTO informix.siifpppdefcveestado(iddefcveestado, descripcion, clave) 
VALUES('34r6b29c-a553-21h8-9c98-4f88f0cd51h1', 'Programación', '02')
;
INSERT INTO informix.siifpppdefcveestado(iddefcveestado, descripcion, clave) 
VALUES('34r6b29c-a553-21h8-9c98-4f88f0cd51h2', 'Cerrado', '03')
;

-- Definición de Clave Presupuestal

INSERT INTO informix.siifpppdefcvepresupuestal(iddefcvepresupuestal, descripcion, mascara, anio, iddefcveestado) 
VALUES('34r6b29c-a553-45dc-9c98-4f88f0cd9620', 'Definición 2012', '', 2012, '34r6b29c-a553-21h8-9c98-4f88f0cd51h1')
;

-- Clasificador Regional

INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0001', 'México', '', 2011, 'f', '001', 3750041, 0, 3600641, '40288acb-3800e88f-0121-50e8e990-0001', NULL)
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0002', 'Jalisco', '', 2011, 'f', '002', 3750041, 0, 3600641, '40288acb-3800e88f-0121-50e8e990-0002', '40288aeb-3800e88f-0121-50e8e800-0001')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0003', 'Region Altos Norte', '', 2011, 'f', '003', 197957, 0, 185360, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0004', 'Region Altos Sur', '', 2011, 'f', '004', 198741, 0, 185403, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0005', 'Región Centro', '', 2011, 'f', '005', 2336249, 0, 2242451, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0006', 'Región Ciénega', '', 2011, 'f', '006', 257630, 0, 245667, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0007', 'Región Costa Norte', '', 2011, 'f', '007', 149185, 0, 151575, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0008', 'Región Costa Sur', '', 2011, 'f', '008', 85315, 0, 85112, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0009', 'Región Norte', '', 2011, 'f', '009', 40528, 0, 38307, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0010', 'Región Sierra de Amula', '', 2011, 'f', '010', 48455, 0, 47225, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0011', 'Región Sierra Occidental', '', 2011, 'f', '011', 30679, 0, 30578, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0012', 'Región Sur', '', 2011, 'f', '012', 170970, 0, 161441, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0013', 'Región Sureste', '', 2011, 'f', '013', 59500, 0, 56916, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0014', 'Región Valles', '', 2011, 'f', '014', 174832, 0, 170606, '40288acb-3800e88f-0121-50e8e990-0003', '40288aeb-3800e88f-0121-50e8e800-0002')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0015', 'ACATIC', '', 2011, 'f', '015', 10858, 0, 10348, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0016', 'ACATLAN DE JUAREZ', '', 2011, 'f', '016', 11021, 0, 12220, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0017', 'AHUALULCO DE MERCADO', '', 2011, 'f', '017', 11037, 0, 10677, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0018', 'AMACUECA', '', 2011, 'f', '018', 2871, 0, 2674, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0019', 'AMATITAN', '', 2011, 'f', '019', 7375, 0, 7273, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0020', 'AMECA', '', 2011, 'f', '020', 29327, 0, 28013, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0021', 'SAN JUANITO ESCOBEDO', '', 2011, 'f', '021', 4435, 0, 4461, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0022', 'ARANDAS', '', 2011, 'f', '022', 37677, 0, 35135, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0023', 'ARENAL, EL', '', 2011, 'f', '023', 8914, 0, 8631, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0024', 'ATEMAJAC DE BRIZUELA', '', 2011, 'f', '024', 3352, 0, 3303, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0025', 'ATEN;', '', 2011, 'f', '025', 2603, 0, 2797, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0026', 'ATENGUILLO', '', 2011, 'f', '026', 2101, 0, 2014, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0027', 'ATOTONILCO EL ALTO', '', 2011, 'f', '027', 29500, 0, 28217, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0028', 'ATOYAC', '', 2011, 'f', '028', 4314, 0, 3962, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0029', 'AUTLAN DE NAVARRO', '', 2011, 'f', '029', 29349, 0, 28210, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0008')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0030', 'AYOTLAN', '', 2011, 'f', '030', 19688, 0, 18603, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0031', 'AYUTLA', '', 2011, 'f', '031', 6356, 0, 6308, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0032', 'BARCA, LA', '', 2011, 'f', '032', 33349, 0, 30920, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0033', 'BOLAÑOS', '', 2011, 'f', '033', 3436, 0, 3384, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0034', 'CABO CORRIENTES', '', 2011, 'f', '034', 4853, 0, 5176, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0007')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0035', 'CASIMIRO CASTILLO', '', 2011, 'f', '035', 10808, 0, 10667, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0008')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0036', 'CIHUATLAN', '', 2011, 'f', '036', 19326, 0, 19694, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0008')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0037', 'ZAPOTLAN EL GRANDE', '', 2011, 'f', '037', 51873, 0, 48661, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0038', 'COCULA', '', 2011, 'f', '038', 13453, 0, 12721, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0039', 'COLOTLAN', '', 2011, 'f', '039', 9380, 0, 8711, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0040', 'CONCEPCION DE BUENOS AIRES', '', 2011, 'f', '040', 2922, 0, 3011, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0041', 'CUAUTITLAN DE GARCIA BARRAGAN', '', 2011, 'f', '041', 8559, 0, 8763, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0008')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0042', 'CUAUTLA', '', 2011, 'f', '042', 1126, 0, 1045, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0043', 'CUQUIO', '', 2011, 'f', '043', 9307, 0, 8488, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0044', 'CHAPALA', '', 2011, 'f', '044', 24937, 0, 23902, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0045', 'CHIMALTITAN', '', 2011, 'f', '045', 1902, 0, 1869, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0046', 'CHIQUILISTLAN', '', 2011, 'f', '046', 2918, 0, 2896, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0047', 'DE;LLADO', '', 2011, 'f', '047', 11013, 0, 10119, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0048', 'EJUTLA', '', 2011, 'f', '048', 1010, 0, 1072, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0049', 'ENCARNACION DE DIAZ', '', 2011, 'f', '049', 26704, 0, 24692, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0050', 'ETZATLAN', '', 2011, 'f', '050', 9475, 0, 9157, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0051', 'GRULLO, EL', '', 2011, 'f', '051', 12223, 0, 11622, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0052', 'GUACHINAN;', '', 2011, 'f', '052', 2145, 0, 2178, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0053', 'GUADALAJARA', '', 2011, 'f', '053', 777785, 0, 717404, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0054', 'HOSTOTIPAQUILLO', '', 2011, 'f', '054', 4783, 0, 5501, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0055', 'HUEJUCAR', '', 2011, 'f', '055', 3179, 0, 2905, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0056', 'HUEJUQUILLA EL ALTO', '', 2011, 'f', '056', 4544, 0, 4237, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0057', 'HUERTA, LA', '', 2011, 'f', '057', 11583, 0, 11845, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0008')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0058', 'IXTLAHUACAN DE LOS MEMBRILLOS', '', 2011, 'f', '058', 20641, 0, 20419, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0059', 'IXTLAHUACAN DEL RIO', '', 2011, 'f', '059', 9830, 0, 9175, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0060', 'JALOSTOTITLAN', '', 2011, 'f', '060', 16350, 0, 15598, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0061', 'JAMAY', '', 2011, 'f', '061', 11544, 0, 11337, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0062', 'JESUS MARIA', '', 2011, 'f', '062', 10063, 0, 8571, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0063', 'JILOTLAN DE LOS DOLORES', '', 2011, 'f', '063', 4640, 0, 4905, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0064', 'JOCOTEPEC', '', 2011, 'f', '064', 21325, 0, 20839, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0065', 'JUANACATLAN', '', 2011, 'f', '065', 6543, 0, 6675, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0066', 'JUCHITLAN', '', 2011, 'f', '066', 2878, 0, 2637, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0067', 'LA;S DE MORENO', '', 2011, 'f', '067', 79345, 0, 74472, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0068', 'LIMON, EL', '', 2011, 'f', '068', 2747, 0, 2752, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0069', 'MAGDALENA', '', 2011, 'f', '069', 10738, 0, 10583, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0070', 'SANTA MARIA DEL ORO', '', 2011, 'f', '070', 1244, 0, 1273, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0071', 'MANZANILLA DE LA PAZ, LA', '', 2011, 'f', '071', 1965, 0, 1790, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0072', 'MASCOTA', '', 2011, 'f', '072', 7235, 0, 7010, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0073', 'MAZAMITLA', '', 2011, 'f', '073', 6937, 0, 6288, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0074', 'MEXTICACAN', '', 2011, 'f', '074', 3257, 0, 2777, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0075', 'MEZQUITIC', '', 2011, 'f', '075', 9339, 0, 8745, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0076', 'MIXTLAN', '', 2011, 'f', '076', 1774, 0, 1800, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0077', 'OCOTLAN', '', 2011, 'f', '077', 47514, 0, 45453, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0078', 'OJUELOS DE JALISCO', '', 2011, 'f', '078', 15461, 0, 14636, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0079', 'PIHUAMO', '', 2011, 'f', '079', 6074, 0, 6045, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0080', 'PONCITLAN', '', 2011, 'f', '080', 24687, 0, 23721, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0081', 'PUERTO VALLARTA', '', 2011, 'f', '081', 127104, 0, 128577, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0007')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0082', 'VILLA PURIFICACION', '', 2011, 'f', '082', 5690, 0, 5933, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0008')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0083', 'QUITUPAN', '', 2011, 'f', '083', 4625, 0, 4066, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0084', 'SALTO, EL', '', 2011, 'f', '084', 69220, 0, 69006, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0085', 'SAN CRISTOBAL DE LA BARRANCA', '', 2011, 'f', '085', 1593, 0, 1583, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0086', 'SAN DIE; DE ALEJANDRIA', '', 2011, 'f', '086', 3475, 0, 3172, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0087', 'SAN JUAN DE LOS LA;S', '', 2011, 'f', '087', 33153, 0, 32066, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0088', 'SAN JULIAN', '', 2011, 'f', '088', 8158, 0, 7296, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0089', 'SAN MARCOS', '', 2011, 'f', '089', 1844, 0, 1918, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0090', 'SAN MARTIN DE BOLAÑOS', '', 2011, 'f', '090', 1701, 0, 1704, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0091', 'SAN MARTIN HIDAL;', '', 2011, 'f', '091', 13521, 0, 12785, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0092', 'SAN MIGUEL EL ALTO', '', 2011, 'f', '092', 16167, 0, 14999, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0093', ';MEZ FARIAS', '', 2011, 'f', '093', 7233, 0, 6778, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0094', 'SAN SEBASTIAN DEL OESTE', '', 2011, 'f', '094', 2747, 0, 3008, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0095', 'SANTA MARIA DE LOS ANGELES', '', 2011, 'f', '095', 1968, 0, 1758, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0096', 'SAYULA', '', 2011, 'f', '096', 18096, 0, 16733, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0097', 'TALA', '', 2011, 'f', '097', 34718, 0, 34313, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0098', 'TALPA DE ALLENDE', '', 2011, 'f', '098', 7195, 0, 7215, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0011')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0099', 'TAMAZULA DE ;RDIANO', '', 2011, 'f', '099', 19474, 0, 18512, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0100', 'TAPALPA', '', 2011, 'f', '100', 9248, 0, 8848, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0101', 'TECALITLAN', '', 2011, 'f', '101', 8627, 0, 8220, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0102', 'TECOLOTLAN', '', 2011, 'f', '102', 8281, 0, 8292, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0103', 'TECHALUTA DE MONTENEGRO', '', 2011, 'f', '103', 1789, 0, 1722, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0104', 'TENAMAXTLAN', '', 2011, 'f', '104', 3556, 0, 3495, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0105', 'TEOCALTICHE', '', 2011, 'f', '105', 21132, 0, 18973, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0106', 'TEOCUITATLAN DE CORONA', '', 2011, 'f', '106', 5530, 0, 5307, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0107', 'TEPATITLAN DE MORELOS', '', 2011, 'f', '107', 69879, 0, 66244, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0108', 'TEQUILA', '', 2011, 'f', '108', 20549, 0, 20148, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0109', 'TEUCHITLAN', '', 2011, 'f', '109', 4663, 0, 4425, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0014')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0110', 'TIZAPAN EL ALTO', '', 2011, 'f', '110', 10694, 0, 10163, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0111', 'TLAJOMULCO DE ZUÑIGA', '', 2011, 'f', '111', 209668, 0, 206958, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0112', 'TLAQUEPAQUE', '', 2011, 'f', '112', 308210, 0, 299904, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0113', 'TOLIMAN', '', 2011, 'f', '113', 4867, 0, 4724, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0114', 'TOMATLAN', '', 2011, 'f', '114', 17228, 0, 17822, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0007')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0115', 'TONALA', '', 2011, 'f', '115', 235448, 0, 243241, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0116', 'TONAYA', '', 2011, 'f', '116', 3056, 0, 2874, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0117', 'TONILA', '', 2011, 'f', '117', 3683, 0, 3573, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0118', 'TOTATICHE', '', 2011, 'f', '118', 2226, 0, 2209, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0119', 'TOTOTLAN', '', 2011, 'f', '119', 11203, 0, 10668, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0120', 'TUXCACUESCO', '', 2011, 'f', '120', 2066, 0, 2168, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0121', 'TUXCUECA', '', 2011, 'f', '121', 3262, 0, 3054, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0122', 'TUXPAN', '', 2011, 'f', '122', 17740, 0, 16442, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0123', 'UNION DE SAN ANTONIO', '', 2011, 'f', '123', 9019, 0, 8306, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0124', 'UNION DE TULA', '', 2011, 'f', '124', 7117, 0, 6620, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0010')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0125', 'VALLE DE GUADALUPE', '', 2011, 'f', '125', 3372, 0, 3333, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0126', 'VALLE DE JUAREZ', '', 2011, 'f', '126', 2992, 0, 2806, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0013')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0127', 'SAN GABRIEL', '', 2011, 'f', '127', 7803, 0, 7507, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0128', 'VILLA CORONA', '', 2011, 'f', '128', 8612, 0, 8357, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0129', 'VILLA GUERRERO', '', 2011, 'f', '129', 2853, 0, 2785, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0009')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0130', 'VILLA HIDAL;', '', 2011, 'f', '130', 9668, 0, 9043, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0003')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0131', 'CAÑADAS DE OBRE;N', '', 2011, 'f', '131', 2137, 0, 2015, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0132', 'YAHUALICA DE ;NZALEZ GALLO', '', 2011, 'f', '132', 11698, 0, 10586, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0133', 'ZACOALCO DE TORRES', '', 2011, 'f', '133', 14203, 0, 13698, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0134', 'ZAPOPAN', '', 2011, 'f', '134', 635849, 0, 607907, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0135', 'ZAPOTILTIC', '', 2011, 'f', '135', 15011, 0, 14181, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0136', 'ZAPOTITLAN DE VADILLO', '', 2011, 'f', '136', 3357, 0, 3328, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0012')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0137', 'ZAPOTLAN DEL REY', '', 2011, 'f', '137', 8914, 0, 8671, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0006')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0138', 'ZAPOTLANEJO', '', 2011, 'f', '138', 32522, 0, 31114, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0005')
;
INSERT INTO informix.siifabsclasifregional(idclasifregional, descripcion, claveinegi, anioinegi, fronterizo, clave, numhombres, codpostal, nummujeres, idnivclasifregional, idpadre)
VALUES('40288aeb-3800e88f-0121-50e8e800-0139', 'SAN IGNACIO CERRO ;RDO', '', 2011, 'f', '139', 9125, 0, 8501, '40288acb-3800e88f-0121-50e8e990-0004', '40288aeb-3800e88f-0121-50e8e800-0004')
;


drop table informix.IDs;

create table informix.IDs
(
	idn serial,
	idS	varchar(36)
);

create table informix.TmpClasificadorAdmin
(
	R	LVARCHAR(500),
	RAMO	LVARCHAR(500),
	S	LVARCHAR(500),
	SECTOR	LVARCHAR(500),
	UP	LVARCHAR(500),
	UPRESUPUESTAL	LVARCHAR(500),
	UR	LVARCHAR(500),
	URESPONSABLE	LVARCHAR(500),
	UEG	LVARCHAR(500),
	UEJECUTORADEGASTO	LVARCHAR(500)
);

create table informix.Tmppppfinalidad
(
	idrpppfinalidad	LVARCHAR(500),
	idpppfinalidad	LVARCHAR(500),
	anio	LVARCHAR(500),
	nombre	LVARCHAR(500),
	descripcioncorta	LVARCHAR(500),
	descripcionlarga	LVARCHAR(500),
	baja LVARCHAR(500)
);

CREATE TABLE informix.Tmppppsubfun
(
	idrpppsubfuncion	 LVARCHAR(500),
	idrpppfuncion	 LVARCHAR(500),
	idpppsubfuncion	 LVARCHAR(500),
	nombre	 LVARCHAR(500),
	descripcion	 LVARCHAR(500),
	idpppsubfuncion2	 LVARCHAR(500),
	anio	 LVARCHAR(500),
	idpppfuncion	 LVARCHAR(500),
	nombre2	 LVARCHAR(500),
	descripcion2	 LVARCHAR(500),
	idrfinalidad LVARCHAR(500)
);

CREATE TABLE informix.Tmppppfuncion
(
	idrpppfuncion LVARCHAR(500),	
	anio	 LVARCHAR(500),
	idpppfuncion	 LVARCHAR(500),
	nombre	 LVARCHAR(500),
	descripcion	 LVARCHAR(500),
	idrfinalidad LVARCHAR(500)
);

CREATE TABLE informix.TmpObjgasto  ( 
	idrobjgasto	LVARCHAR(500),
	idrgrupogen	LVARCHAR(500),	
	idrconceptobanco    LVARCHAR(500),	
	idobjgasto	LVARCHAR(500),	
	descripcion	LVARCHAR(500),	
	habilitadaejer	LVARCHAR(500),	
	irreductibleejer	LVARCHAR(500),	
	habilitadaejeant	LVARCHAR(500),	
	irreductibleant	LVARCHAR(500),	
	sesaldan	LVARCHAR(500),	
	parametrizable	LVARCHAR(500),	
	tipo	LVARCHAR(500),	
	reqclavepptal	LVARCHAR(500),	
	difobjgasto	LVARCHAR(500),	
	reqbeneficiario	LVARCHAR(500),	
	fondo	LVARCHAR(500),	
	viatico	LVARCHAR(500),	
	negativo	LVARCHAR(500),	
	especial	LVARCHAR(500),	
	participable	LVARCHAR(500),	
	idrctabcofondo	LVARCHAR(500),	
	anio	LVARCHAR(500),	
	anualizado	LVARCHAR(500),	
	observaciones	LVARCHAR(500),	
	retencion	LVARCHAR(500),	
	comisionbancaria	LVARCHAR(500),	
	arrendamiento	LVARCHAR(500),	
	subtransporte	LVARCHAR(500),	
	idrconac_tipogasto	LVARCHAR(500),	
	modingreso	LVARCHAR(500),	
	nivelconac	LVARCHAR(500)
);

CREATE TABLE informix.Tmpnipftefinan 
(
	idrftefinan		LVARCHAR(500),
	idftefinan		LVARCHAR(500),
	nombrecorto		LVARCHAR(500),
	descripcion		LVARCHAR(500),
	vigente		LVARCHAR(500),
	vigenciaapartir		LVARCHAR(500),
	vigenciavence	LVARCHAR(500),
	fechafincontratar	LVARCHAR(500),
	inversion		LVARCHAR(500),
	tratamientopptal		LVARCHAR(500),
	baja		LVARCHAR(500),
	fechaasiginicial		LVARCHAR(500),
	idrusrasiginicial		LVARCHAR(500),
	importeasiginicial		LVARCHAR(500),	
	obsasiginicial		LVARCHAR(500),
	presupuestal		LVARCHAR(500),
	basica		LVARCHAR(500),
	cuentaconchequera		LVARCHAR(500),
	recursospropios		LVARCHAR(500),
	leyenda	LVARCHAR(500)
);
create table tmpsiifppppromotor
(
    descripcion lVarchar(255)
);

LOAD FROM 'Promotor.txt' delimiter '|' INSERT 
INTO informix.tmpsiifppppromotor;

LOAD FROM 'Objgasto.txt' delimiter '|' INSERT 
INTO informix.TmpObjgasto;

LOAD FROM 'nipftefinan.txt' delimiter '|' INSERT 
INTO informix.tmpnipftefinan;

LOAD FROM 'pppfuncion.txt' delimiter '|' INSERT 
INTO informix.Tmppppfuncion;

LOAD FROM 'pppsubfun.txt' delimiter '|' INSERT 
INTO informix.Tmppppsubfun;

LOAD FROM 'pppfinalidad.txt' delimiter '|' INSERT 
INTO informix.Tmppppfinalidad;

LOAD FROM 'ClasificadorAdmin.txt' delimiter '|' INSERT 
INTO informix.TmpClasificadorAdmin;

LOAD FROM 'IDs.txt' delimiter '|' INSERT 
INTO informix.IDs(ids);

--Finalidad
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifpppclasiffuncional ( 
	idclasiffuncional2   	serial,
	definicionconac     	LVARCHAR(500),
	descripcion         	LVARCHAR(500),
	clave               	VARCHAR(255),
	idpadre             	VARCHAR(255),
	idnivclasiffuncional	VARCHAR(255) NOT NULL
);

insert into tmpsiifpppclasiffuncional(definicionconac,descripcion,clave,idpadre,idnivclasiffuncional)
select T.descripcionlarga,T.nombre,T.idpppfinalidad,NULL::varchar,N.idnivclasiffuncional from tmppppfinalidad T,informix.siifpppnivclasiffunc N
where N.nivel=1;

delete siifpppencobjclasiffunc;
delete siifpppclasiffuncional where length(clave)>4;
delete siifpppclasiffuncional where length(clave)>3;
delete siifpppclasiffuncional where length(clave)>2;
delete siifpppclasiffuncional where length(clave)>1;
delete siifpppclasiffuncional;

insert into siifpppclasiffuncional
select i.ids,T.definicionconac,T.descripcion,T.clave,T.idpadre,T.idnivclasiffuncional from tmpsiifpppclasiffuncional as T join tmpid  as I on (T.idclasiffuncional2=I.idn);

delete from Ids 
where ids in (select idclasiffuncional from siifpppclasiffuncional);

drop table TmpId;

drop table tmpsiifpppclasiffuncional;

--Funcion
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifpppclasiffuncional ( 
	idclasiffuncional2   	serial,
	definicionconac     	LVARCHAR(500),
	descripcion         	LVARCHAR(500),
	clave               	VARCHAR(255),
	idpadre             	VARCHAR(255),
	idnivclasiffuncional	VARCHAR(255) NOT NULL
);

insert into tmpsiifpppclasiffuncional(definicionconac,descripcion,clave,idpadre,idnivclasiffuncional)
select 
    T.Descripcion,T.nombre,C.Clave || '.' || T.idpppfuncion,C.idclasiffuncional,N.idnivclasiffuncional
from tmppppfuncion as T join tmppppfinalidad as B on (T.idrfinalidad = B.idrpppfinalidad)
join siifpppclasiffuncional C on (C.Clave=B.idpppfinalidad),siifpppnivclasiffunc as N
where N.nivel=2;

insert into siifpppclasiffuncional
select i.ids,T.definicionconac,T.descripcion,T.clave,T.idpadre,T.idnivclasiffuncional from tmpsiifpppclasiffuncional as T join tmpid  as I on (T.idclasiffuncional2=I.idn);

delete from Ids 
where ids in (select idclasiffuncional from siifpppclasiffuncional);

drop table TmpId;

drop table tmpsiifpppclasiffuncional;

--SubFuncion
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifpppclasiffuncional ( 
	idclasiffuncional2   	serial,
	definicionconac     	LVARCHAR(500),
	descripcion         	LVARCHAR(500),
	clave               	VARCHAR(255),
	idpadre             	VARCHAR(255),
	idnivclasiffuncional	VARCHAR(255) NOT NULL
);

insert into tmpsiifpppclasiffuncional(definicionconac,descripcion,clave,idpadre,idnivclasiffuncional)
select 
    T2.Descripcion,T2.nombre,C.Clave || '.' || T2.idpppsubfuncion,C.idclasiffuncional,N.idnivclasiffuncional
from tmppppsubfun T2 
join tmppppfuncion as T on (T2.idrpppfuncion = T.idrpppfuncion) 
join tmppppfinalidad as B on (T.idrfinalidad = B.idrpppfinalidad)
join siifpppclasiffuncional C on (C.Clave=B.idpppfinalidad|| '.' || T.idpppfuncion),siifpppnivclasiffunc as N
where N.nivel=3;

insert into siifpppclasiffuncional
select i.ids,T.definicionconac,T.descripcion,T.clave,T.idpadre,T.idnivclasiffuncional from tmpsiifpppclasiffuncional as T join tmpid  as I on (T.idclasiffuncional2=I.idn);

delete from Ids 
where ids in (select idclasiffuncional from siifpppclasiffuncional);

drop table TmpId;

drop table tmpsiifpppclasiffuncional;

create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;


CREATE TABLE tmpsiifpppobjetogasto  ( 
	idobjetogasto       	serial,
	adefas              	BOOLEAN,
	fondo               	BOOLEAN,
	combancaria         	BOOLEAN,
	descripcion         	VARCHAR(255),
	habilitada          	BOOLEAN,
	genresguardo        	BOOLEAN,
	compinicial         	BOOLEAN,
	irreductible        	BOOLEAN,
	clave               	INTEGER,
	arrendamiento       	BOOLEAN,
	iddefcvepresupuestal	VARCHAR(255),
	inversionpublica    	BOOLEAN,
	partidaespecial     	BOOLEAN,
	tipototalgasto      	INTEGER,
	idtipogasto         	VARCHAR(255),
	viatico             	BOOLEAN,
	idpadre             	VARCHAR(255),
	idnivelobjgasto     	VARCHAR(255)
);

insert into tmpsiifpppobjetogasto  ( 		adefas              	,	fondo               	,	combancaria         	,	descripcion         	,	habilitada          	,	genresguardo        	,	compinicial         	,	irreductible        	,	clave               	,	arrendamiento       	,	iddefcvepresupuestal	,	inversionpublica    	,	partidaespecial     	,	tipototalgasto      	,	idtipogasto         	,	viatico             	,	idpadre             	,	idnivelobjgasto     	)
select 
        'f' as adefas,
        case when t.fondo='N' or  t.fondo is null then 'f' else 't'end,
        case when t.comisionbancaria='N' or t.comisionbancaria is null then 'f' else 't'end,
        t.descripcion,
        case when t.habilitadaejer='N' or t.habilitadaejer is null then 'f' else 't'end,
        'f' as GenResgu,
        'f' as CompInicial,
        case when t.irreductibleejer='N' or t.irreductibleejer is null then 'f' else 't'end,
        t.idobjgasto,
        case when t.arrendamiento='N' or t.arrendamiento is null then 'f' else 't'end,
        null::varchar,
        'f' as Inversionpublica,
        case when t.especial='N' or t.especial is null then 'f' else 't'end,
        1 as tipoTotalGasto,
        null::varchar,
        case when t.viatico='N' or t.viatico is null then 'f' else 't' end,
        null::varchar as IdPadre,
        null::varchar as idNivel
 from tmpObjgasto as T;

delete siifpppobjgastodestino;

delete siifpppobjgastofuefinan;

delete siifppptechopptal;

delete siifpppobjetogasto where idnivelobjgasto in (select idnivelobjgasto from siifpppnivelobjgasto where nivel=4);

delete siifpppobjetogasto where idnivelobjgasto in (select idnivelobjgasto from siifpppnivelobjgasto where nivel=3);

delete siifpppobjetogasto where idnivelobjgasto in (select idnivelobjgasto from siifpppnivelobjgasto where nivel=2);

delete siifpppobjetogasto where idnivelobjgasto in (select idnivelobjgasto from siifpppnivelobjgasto where nivel=1);


insert into siifpppobjetogasto  (idobjetogasto, 		adefas              	,	fondo               	,	combancaria         	,	descripcion         	,	habilitada          	,	genresguardo        	,	compinicial         	,	irreductible        	,	clave               	,	arrendamiento       	,	iddefcvepresupuestal	,	inversionpublica    	,	partidaespecial     	,	tipototalgasto      	,	idtipogasto         	,	viatico             	,	idpadre             	,	idnivelobjgasto     	)
select 
B.ids,
A.adefas              	,	fondo               	,	combancaria         	,	A.descripcion         	,	
habilitada          	,	genresguardo        	,	compinicial         	,	irreductible        	,	
abs(A.clave)               	,	arrendamiento       	,	iddefcvepresupuestal	,	inversionpublica    	,	partidaespecial     	,	abs(tipototalgasto)      	,	idtipogasto         	,	viatico             	,	idpadre             	,	N.idnivelobjgasto
from tmpsiifpppobjetogasto A join tmpid B on (A.idobjetogasto = B.idn)
join siifpppnivelobjgasto N on (N.nivel=case 
    when substr(A.clave,2,3)='000' then 1 
    when substr(A.clave,3,2)='00' then 2
    when substr(A.clave,4,1)='0' then 3
    when A.clave='0' then 1
else 4
end);

create table tmpRelacion
(
    idClave varchar(36),
    idPadre varchar(36)
);

insert into tmpRelacion
select a.idobjetogasto,b.idobjetogasto
 from siifpppobjetogasto A join siifpppobjetogasto B on (abs(case 
    when substr(A.clave,2,3)='000' then NULL::varchar 
    when substr(A.clave,3,2)='00' then substr(a.clave,1,1)||'000'
    when substr(A.clave,4,1)='0' then substr(a.clave,1,2)||'00'
    when a.clave='0' then null::varchar
else substr(a.clave,1,3)||'0'
end)=B.clave);

update siifpppobjetogasto
set idpadre=(select idpadre from tmpRelacion where idclave=siifpppobjetogasto.idobjetogasto);

delete from Ids 
where ids in (select idobjetogasto from siifpppobjetogasto);

drop table TmpId;

drop table tmpsiifpppobjetogasto;

drop table tmpRelacion;

--Fuente de Financiamiento
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifpppfuefinanciamiento  
( 
	idfuentefinanciamiento2	serial,
	descripcion           	LVARCHAR(500),
	clave                 	VARCHAR(100) 
);

insert into tmpsiifpppfuefinanciamiento(descripcion,Clave)
select
    descripcion ,
    substr('000'||idftefinan,LENGTH ('000'||idftefinan)-2,3)  
from tmpnipftefinan;

delete siifpppfuefinanciamiento;

insert into siifpppfuefinanciamiento
select B.ids,a.descripcion,a.clave from tmpsiifpppfuefinanciamiento A join tmpid B on (A.idfuentefinanciamiento2 = B.idn);

delete from Ids 
where ids in (select idfuentefinanciamiento from siifpppfuefinanciamiento);

drop table TmpId;

drop table tmpsiifpppfuefinanciamiento;

create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifabsdependencia  ( 
	id           	serial,
    idDependencia               varchar(36),
	descripcion             	LVARCHAR(500),
	clave                   	LVARCHAR(500),
	idnivdependencia        	VARCHAR(255),
	iddependenciacapplaninst	VARCHAR(255),
	idpadre                 	VARCHAR(255)
);
 
insert into tmpsiifabsdependencia(descripcion,clave,idnivdependencia,iddependenciacapplaninst,idpadre)
select A.ramo , A.R,b.idnivdependencia, null::varchar,null::varchar
from tmpClasificadorAdmin A ,siifabsnivdependencia B
WHERE B.ramo ='t'
group by A.ramo , A.R,b.idnivdependencia;

update siifabsdependencia set idpadre=null::varchar,iddependenciacapplaninst=null::varchar;
delete siifpppanteproyecto;
delete siifpppprefichadep;
delete siifpppprefichaobserv;
delete siifppptechopptal;
delete siifpppprogramacion;
delete siifppppreficha;
delete siifpppencplaninstobj;
delete siifpppplaninstitucional;
delete siifabsdependencia;

insert into siifabsdependencia
select b.ids,a.descripcion, a.clave, a.idnivdependencia,a.iddependenciacapplaninst, a.idpadre 
from tmpsiifabsdependencia A join tmpid B on (A.id= B.idn);

delete from Ids 
where ids in (select iddependencia from siifabsdependencia);

drop table TmpId;

drop table tmpsiifabsdependencia;

--Dependencias Nivel 2  SELECT * FROM siifabsdependencia
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifabsdependencia  ( 
	id           	serial,
    idDependencia               varchar(36),
	descripcion             	LVARCHAR(500),
	clave                   	LVARCHAR(500),
	idnivdependencia        	VARCHAR(255),
	iddependenciacapplaninst	VARCHAR(255),
	idpadre                 	VARCHAR(255)
);

insert into tmpsiifabsdependencia(descripcion,clave,idnivdependencia,iddependenciacapplaninst,idpadre)
select 
        A.sector , A.s,b.idnivdependencia, null::varchar,C.iddependencia
from 
        tmpClasificadorAdmin A ,siifabsnivdependencia B, siifabsdependencia C
WHERE 
        B.sector ='t' and 
        C.descripcion=A.ramo and c.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where ramo='t')
group by A.sector , A.s,b.idnivdependencia,C.iddependencia;

insert into siifabsdependencia
select b.ids,a.descripcion, a.clave, a.idnivdependencia,a.iddependenciacapplaninst, a.idpadre 
from tmpsiifabsdependencia A join tmpid B on (A.id= B.idn);

delete from Ids 
where ids in (select iddependencia from siifabsdependencia);

drop table TmpId;

drop table tmpsiifabsdependencia;

--Dependencias Nivel 3
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifabsdependencia  ( 
	id           	serial,
    idDependencia               varchar(36),
	descripcion             	LVARCHAR(500),
	clave                   	LVARCHAR(500),
	idnivdependencia        	VARCHAR(255),
	iddependenciacapplaninst	VARCHAR(255),
	idpadre                 	VARCHAR(255)
);

insert into tmpsiifabsdependencia(descripcion,clave,idnivdependencia,iddependenciacapplaninst,idpadre)
select 
        A.upresupuestal , A.up,b.idnivdependencia, null::varchar,C.iddependencia
from 
        tmpClasificadorAdmin A ,siifabsnivdependencia B,siifabsdependencia C,siifabsdependencia d
WHERE
        B.unipresupuestal ='t' and 
        C.descripcion=A.sector and c.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where sector='t') AND
        D.descripcion=A.ramo and D.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where ramo='t')
        and c.idpadre=d.iddependencia 
group by A.upresupuestal , A.up,b.idnivdependencia,C.iddependencia;


insert into siifabsdependencia
select b.ids,a.descripcion, a.clave, a.idnivdependencia,a.iddependenciacapplaninst, a.idpadre 
from tmpsiifabsdependencia A join tmpid B on (A.id= B.idn);

delete from Ids 
where ids in (select iddependencia from siifabsdependencia);

drop table TmpId;

drop table tmpsiifabsdependencia;

--Dependencias Nivel 4
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifabsdependencia  ( 
	id           	serial,
    idDependencia               varchar(36),
	descripcion             	LVARCHAR(500),
	clave                   	LVARCHAR(500),
	idnivdependencia        	VARCHAR(255),
	iddependenciacapplaninst	VARCHAR(255),
	idpadre                 	VARCHAR(255)
);

insert into tmpsiifabsdependencia(descripcion,clave,idnivdependencia,iddependenciacapplaninst,idpadre)
select 
        A.uresponsable , A.ur,b.idnivdependencia, null::varchar,C.iddependencia
from 
        tmpClasificadorAdmin A ,siifabsnivdependencia B,siifabsdependencia C,siifabsdependencia d,siifabsdependencia e
WHERE
        B.uniresponsable ='t' and 
        C.descripcion=A.upresupuestal and c.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where unipresupuestal='t') AND
        D.descripcion=A.sector and D.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where sector='t')and
        E.descripcion=A.ramo and E.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where ramo='t') and
        c.idpadre=d.iddependencia and
        d.idpadre=e.iddependencia 
group by A.uresponsable , A.ur,b.idnivdependencia,C.iddependencia;


insert into siifabsdependencia
select b.ids,a.descripcion, a.clave, a.idnivdependencia,a.iddependenciacapplaninst, a.idpadre 
from tmpsiifabsdependencia A join tmpid B on (A.id= B.idn);

delete from Ids 
where ids in (select iddependencia from siifabsdependencia);

drop table TmpId;

drop table tmpsiifabsdependencia;

--Dependencias Nivel 5
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

CREATE TABLE tmpsiifabsdependencia  ( 
	id           	serial,
    idDependencia               varchar(36),
	descripcion             	LVARCHAR(500),
	clave                   	LVARCHAR(500),
	idnivdependencia        	VARCHAR(255),
	iddependenciacapplaninst	VARCHAR(255),
	idpadre                 	VARCHAR(255)
);

insert into tmpsiifabsdependencia(descripcion,clave,idnivdependencia,iddependenciacapplaninst,idpadre)
select 
       A.uejecutoradegasto , A.ueg,b.idnivdependencia, C.iddependencia,C.iddependencia
from 
        tmpClasificadorAdmin A ,siifabsnivdependencia B,siifabsdependencia C,siifabsdependencia d,siifabsdependencia e,siifabsdependencia f
WHERE
        B.uniejecutora ='t' and 
        C.descripcion=A.uresponsable and c.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where uniresponsable='t') AND
        D.descripcion=A.upresupuestal and D.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where unipresupuestal='t')and
        E.descripcion=A.sector and E.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where sector='t') and
        F.descripcion=A.ramo and F.idnivdependencia in (select idnivdependencia from siifabsnivdependencia where ramo='t') and
        c.idpadre=d.iddependencia and
        d.idpadre=e.iddependencia and
        e.idpadre=f.iddependencia
group by A.uejecutoradegasto , A.ueg,b.idnivdependencia,C.iddependencia;


insert into siifabsdependencia
select b.ids,a.descripcion, a.clave, a.idnivdependencia,a.iddependenciacapplaninst, a.idpadre 
from tmpsiifabsdependencia A join tmpid B on (A.id= B.idn);

delete from Ids 
where ids in (select iddependencia from siifabsdependencia);

drop table TmpId;

drop table tmpsiifabsdependencia;




create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

create table tmpPI
(
    IdPI                serial, 
	bloqueado          	BOOLEAN,
	ejecinversion      	BOOLEAN,
	normainversion     	BOOLEAN,
	organismo          	BOOLEAN,
	fechaactorganigrama	DATE,
	fechaextincion     	DATE,
	fechapubperultmodif	DATE,
	diagnostico        	LVARCHAR(500),
	formaextincion     	LVARCHAR(500),
	objetivogeneral    	LVARCHAR(500),
	numperofireginterno	VARCHAR(100),
	fechapubreginterno 	DATE,
	fundamentolegal    	LVARCHAR(500),
	mision             	LVARCHAR(500),
	numperoficial      	VARCHAR(100),
	numperofiultmodif  	VARCHAR(100),
	fechapubperoficial 	DATE,
	organigrama        	LVARCHAR(1000),
	fechainiactividades	DATE,
	vision             	LVARCHAR(500),
	iddependencia      	VARCHAR(255)
);

INSERT INTO tmpPI(bloqueado, ejecinversion, normainversion, organismo, 
fechaactorganigrama, fechaextincion, fechapubperultmodif, diagnostico, formaextincion, 
objetivogeneral, numperofireginterno, fechapubreginterno, fundamentolegal, mision, numperoficial, 
numperofiultmodif, fechapubperoficial, organigrama, vision, fechainiactividades, iddependencia)
select 'f', 'f', 't', 't', 
null::date, null::date, null::date, '', '', 
'Objetivo de ' || d.descripcion, '', EXTEND(MDY(5,31,2012), YEAR to SECOND), '', 'Misión de la U.R.', '',  
'', null::date, null::varchar, 'Visión de la U.R.', EXTEND(MDY(5,31,2012), YEAR to SECOND), d.iddependencia
from siifabsdependencia d
inner join siifabsnivdependencia nd on d.idnivdependencia = nd.idnivdependencia and nd.uniresponsable = 't';

INSERT INTO siifpppplaninstitucional (idplaninstitucional, bloqueado, ejecinversion, normainversion, organismo, 
fechaactorganigrama, fechaextincion, fechapubperultmodif, diagnostico, formaextincion, 
objetivogeneral, numperofireginterno, fechapubreginterno, fundamentolegal, mision, numperoficial, 
numperofiultmodif, fechapubperoficial, organigrama, vision, fechainiactividades, iddependencia)
SELECT ids, bloqueado, ejecinversion, normainversion, organismo, 
fechaactorganigrama, fechaextincion, fechapubperultmodif, diagnostico, formaextincion, 
objetivogeneral, numperofireginterno, fechapubreginterno, fundamentolegal, mision, numperoficial, 
numperofiultmodif, fechapubperoficial, organigrama, vision, fechainiactividades, iddependencia
FROM tmpPI
inner join TmpId on idpi = idn;

delete from Ids 
where ids in (select idplaninstitucional from siifpppplaninstitucional);

drop table TmpId;

drop table tmpPI;

drop table informix.IDs;

create table tmpsiifpppobjespecifico
(
    idn serial,
    Clave lvarchar(255),	
    ClavePadre lvarchar(255),	
    Nivel lvarchar(255),	
    Descripcion lvarchar(255)
);

create table tmpsiifpppproblema
(
    idn serial,
    Clave lvarchar(255),	
    ClavePadre lvarchar(255),	
    Nivel lvarchar(255),	
    Descripcion lvarchar(255)
);
    
create table tmpSiifPPPDestino
(
    idn serial,
    descripcion lVarchar(255),
    clave lVarchar(255)
);

create table tmpsiifpppestadoanteproy
(
    idn serial,
    descripcion lVarchar(255),
    diasEstimados lVarchar(255)
);

create table tmpusuario
(
    idn serial,
    descripcion lVarchar(255),
    usuario	varchar(25),
    Contrasenia varchar(25)
);

create table tmpsiifpppunimedmeta
(
    idn serial,
    descripcion lVarchar(255),
    descripcion2 lVarchar(255)
);

create table pepi
(
    idur	lvarchar(255),
    ideje	lvarchar(255),
    idprograma	lvarchar(255),
    idsubprograma lvarchar(255)
);

create table informix.objfund
(
	idn serial,
    idrobjfund	varchar(36),
    idobjfund	varchar(36),
    descripcion	varchar(36),
    idrestrucanual	varchar(255),
    propositogral varchar(255)
);


create table informix.programa
(
    idn serial,
    idrprograma	 varchar(36),
    idrobjfund	 varchar(36),
    anio	 varchar(36),
    idprograma	 varchar(36),
    nombre	 varchar(255),
    descripcion	 varchar(255),
    definicion	 varchar(255),
    prioridad  varchar(255)
);

create table informix.subprograma
(
    idn serial,
    idrsubprograma	varchar(36),
    idsubprograma	varchar(36),
    anio	varchar(36),
    nombre	varchar(255),
    idrprograma varchar(36)
);

create table informix.IDs
(
	idn serial,
	idS	varchar(36)
);

create table tmpsiifppppromotorID
(
    idn serial,
    descripcion lVarchar(255)
);

create table tmpsiifpppdepfederal
(
    idn serial,
    descripcion lVarchar(255)
);

create table tmpsiifppplininversion
(
    idn serial,
    descripcion lVarchar(255),
    clave   varchar(25)
);

create table tmpsiifabsgpovulnerable
(
    idn serial,
    descripcion lVarchar(255),
    clave   varchar(25)
);

create table tmpsiifpppconceptogral
(
    idn serial,
    descripcion lVarchar(255),
    clave   varchar(25)
);

create table tmpsiifpppconceptoobra
(
    idn serial,
    descripcion lVarchar(255),
    clave   varchar(25),
    ClaveConceptoGral varchar(36)
);

create table tmpsiifpppaccionmetabenef
(
    idn serial,
    descripcion lVarchar(255),
    clave   varchar(25),
    ClaveConceptoObra varchar(36)
);

create table tmpsiifpppmacroobra
(
    idn serial,
    clave   varchar(25),
    nombre lVarchar(255),
    descripcion varchar(36)
);

create table tmpssiifppptipoanteproy
(
    idn serial,
    clave varchar(25),
    descripcion lVarchar(255)
);

create table informix.tmpsiifpppambitoanteproy
(
	idn serial,
	descripcion	varchar(36)
);

create table tmpsiifabsunimedida
(
    idn serial,
    descripcion lVarchar(255),
    clave   varchar(25)
);

create table tmpsiifpppunimedmetabenef
(
    idn serial,
    descripcion lVarchar(255)
);

create table tmpsiifpppelemento
(
    idn serial,
    descripcion lVarchar(255),
    Tabla lVarchar(255),
    Elemento lVarchar(255)
);


LOAD FROM 'tmpsiifpppobjespecifico.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppobjespecifico(Clave,ClavePadre,Nivel,Descripcion);

LOAD FROM 'tmpsiifpppproblema.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppproblema(Clave,ClavePadre,Nivel,Descripcion);

LOAD FROM 'tmpSiifPPPDestino.txt' delimiter '|' INSERT 
INTO informix.tmpSiifPPPDestino(clave,descripcion);

LOAD FROM 'tmpsiifpppestadoanteproy.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppestadoanteproy(descripcion,diasestimados);

LOAD FROM 'usuario.txt' delimiter '|' INSERT 
INTO informix.tmpusuario(descripcion,usuario,contrasenia);

LOAD FROM 'UnidadMedidaMeta.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppunimedmeta(descripcion,descripcion2);

LOAD FROM 'PEPI.txt' delimiter '|' INSERT 
INTO informix.pepi;

LOAD FROM 'objfund.txt' delimiter '|' INSERT 
INTO informix.objfund(idrobjfund,idobjfund,descripcion,idrestrucanual,propositogral);

LOAD FROM 'programa.txt' delimiter '|' INSERT 
INTO informix.programa(idrprograma,idrobjfund,anio,idprograma,nombre,descripcion,definicion,prioridad);

LOAD FROM 'subprograma.txt' delimiter '|' INSERT 
INTO informix.subprograma(idrsubprograma,idsubprograma,anio,nombre,idrprograma);

LOAD FROM 'Elementos.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppelemento(descripcion,Tabla,Elemento);

LOAD FROM 'UnidadMedidaBen.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppunimedmetabenef(descripcion);

LOAD FROM 'UnidadMedida.txt' delimiter '|' INSERT 
INTO informix.tmpsiifabsunimedida(clave,descripcion);

LOAD FROM 'Ambitoanteproy.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppambitoanteproy(descripcion);

LOAD FROM 'TipoAnteproyecto.txt' delimiter '|' INSERT 
INTO informix.tmpssiifppptipoanteproy(clave,descripcion);

LOAD FROM 'MacroObra.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppmacroobra(clave,nombre,descripcion);

LOAD FROM 'Acciones.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppaccionmetabenef(clave,descripcion,ClaveConceptoobra);

LOAD FROM 'ConceptoObra.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppconceptoobra(clave,descripcion,ClaveConceptoGral);

LOAD FROM 'ConceptoGral.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppconceptogral(clave,descripcion);

LOAD FROM 'GpoVulnerable.txt' delimiter '|' INSERT 
INTO informix.tmpsiifabsgpovulnerable(clave,descripcion);

LOAD FROM 'LineaInversion.txt' delimiter '|' INSERT 
INTO informix.tmpsiifppplininversion(clave,descripcion);

LOAD FROM 'depfed.txt' delimiter '|' INSERT 
INTO informix.tmpsiifpppdepfederal(descripcion);

LOAD FROM 'Promotor.txt' delimiter '|' INSERT 
INTO informix.tmpsiifppppromotorID(descripcion);

LOAD FROM 'IDs.txt' delimiter '|' INSERT 
INTO informix.IDs(ids);

--Promotores
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifppppromotor;

insert into informix.siifppppromotor(
        idpromotor,
        descripcion
)
select 
    B.ids,
    A.descripcion
from 
    tmpsiifppppromotorid A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idpromotor from siifppppromotor);

drop table TmpId;

drop table tmpsiifppppromotorid;


--Dependencia Federal
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppdepfederal;

insert into informix.siifpppdepfederal(iddepfederal,descripcion)
select b.ids,a.descripcion
from tmpsiifpppdepfederal  A join tmpid B on (A.idn = B.idn);

delete from Ids 
where ids in (select iddepfederal from siifpppdepfederal);

drop table TmpId;

drop table tmpsiifpppdepfederal;


--Linea de Inversión
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifppplininversion;

insert into informix.siifppplininversion(idLininversion,descripcion,clave)
select b.ids,a.descripcion,a.clave
from tmpsiifppplininversion  A join tmpid B on (A.idn = B.idn);

delete from Ids 
where ids in (select idLininversion from siifppplininversion);

drop table TmpId;

drop table tmpsiifppplininversion;

--Grupo Vulnerable
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifabsgpovulnerable;

insert into informix.siifabsgpovulnerable(idgpovulnerable,descripcion,clave)
select b.ids,a.descripcion,a.clave
from tmpsiifabsgpovulnerable  A join tmpid B on (A.idn = B.idn);

delete from Ids 
where ids in (select idgpovulnerable from siifabsgpovulnerable);

drop table TmpId;

drop table tmpsiifabsgpovulnerable;

--Concepto General
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppaccionmetabenef;
delete siifpppconceptoobra;
delete siifpppconceptogral;

insert into informix.siifpppconceptogral(idConceptogral,descripcion,clave)
select b.ids,a.descripcion,a.clave
from tmpsiifpppconceptogral  A join tmpid B on (A.idn = B.idn);

delete from Ids 
where ids in (select idconceptogral from siifpppconceptogral);

drop table TmpId;

drop table tmpsiifpppconceptogral;
--Concepto Obra
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppconceptoobra;
insert into informix.siifpppconceptoobra(idConceptoobra,descripcion,clave,idconceptogral)
select b.ids,a.descripcion,a.clave,C.idconceptogral
from tmpsiifpppconceptoobra  A join tmpid B on (A.idn = B.idn)
    join siifpppconceptogral C on (A.ClaveConceptoGral = C.clave);

delete from Ids 
where ids in (select idConceptoobra from siifpppconceptoobra);

drop table TmpId;

drop table tmpsiifpppconceptoobra;

--Acciones Benef.
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppaccionmetabenef;

insert into informix.siifpppaccionmetabenef(IdAccionMetaBenef,descripcion,clave,idconceptoobra)
select b.ids,a.descripcion,a.clave,C.idconceptoobra
from tmpsiifpppaccionmetabenef  A join tmpid B on (A.idn = B.idn)
    join siifpppconceptoobra C on (A.ClaveConceptoobra = C.clave);

delete from Ids 
where ids in (select IdAccionMetaBenef from siifpppaccionmetabenef);

drop table TmpId;

drop table tmpsiifpppaccionmetabenef;

--Macro Obra
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppmacroobra;

insert into informix.siifpppmacroobra(idMacroobra,descripcion,clave)
select b.ids,a.nombre,a.clave
from tmpsiifpppmacroobra  A join tmpid B on (A.idn = B.idn);

delete from Ids 
where ids in (select idMacroobra from siifpppmacroobra);

drop table TmpId;

drop table tmpsiifpppmacroobra;


--Tipo de Anteproyecto
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifppptipoanteproy;

insert into informix.siifppptipoanteproy(idtipoanteproyecto,descripcion)
select b.ids,a.descripcion
from tmpssiifppptipoanteproy  A join tmpid B on (A.idn = B.idn);

delete from Ids 
where ids in (select idtipoanteproyecto from siifppptipoanteproy);

drop table TmpId;

drop table tmpssiifppptipoanteproy;

--Ambito de Anteproyecto
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppambitoanteproy;

insert into informix.siifpppambitoanteproy(idAmbitoAnteProyecto,descripcion)
select 
    B.ids,
    A.descripcion
from 
    tmpsiifpppambitoanteproy A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idAmbitoAnteProyecto from siifpppambitoanteproy);

drop table TmpId;

drop table tmpsiifpppambitoanteproy;

--Unidad de Medida
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifabsunimedida;

insert into informix.siifabsunimedida(idUniMedida,descripcion,Clave)
select 
    B.ids,
    A.descripcion,
    A.Clave
from 
    tmpsiifabsunimedida A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idUniMedida from siifabsunimedida);

drop table TmpId;

drop table tmpsiifabsunimedida;

--Unidad de Medida Beneficiario
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppunimedmetabenef;

insert into informix.siifpppunimedmetabenef(idUniMedbenmeta,descripcion)
select 
    B.ids,
    A.descripcion
from 
    tmpsiifpppunimedmetabenef A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idUniMedbenmeta from siifpppunimedmetabenef);

drop table TmpId;

drop table tmpsiifpppunimedmetabenef;

--Elemento de Clave PPTAL
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppcvepptalcfg;
delete siifpppelemento;

insert into informix.siifpppelemento(idElemento,descripcion,Tabla,Elemento)
select 
    B.ids,
    A.descripcion,
    A.tabla,
    A.Elemento
from 
    tmpsiifpppelemento A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select IdElemento from siifpppelemento);

drop table TmpId;

drop table tmpsiifpppelemento;

--Objetivos planeacion estrategica.
--Nivel 1

CREATE TABLE informix.tmpsiifpppobjetivo  ( 
	idobjetivo     	VARCHAR(36) NOT NULL,
	definicion     	LVARCHAR(255),
	clave          	VARCHAR(36),
	claveReal       VARCHAR(36),
	nombre         	LVARCHAR(255),
	prioridad      	SMALLINT,
	idpadre        	VARCHAR(36),
	idnivelobjetivo	VARCHAR(36) NOT NULL
);

update siifpppobjetivo set idpadre=null;
delete siifpppencobjclasiffunc;
delete siifpppencplaninstobj;
delete siifpppobjespecifico;
delete siifpppproblema;
delete siifpppprogramacion;
delete siifpppobjetivo;

create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

insert into tmpsiifpppobjetivo(clavereal,idobjetivo,definicion,clave,nombre,prioridad,idnivelobjetivo)
select 
    A.idobjfund,
    c.ids,
    A.descripcion ,
    A.idrobjfund, 
    A.descripcion,
    1000,
    B.idnivelobjetivo
from objfund A,siifpppnivelobjetivo B, tmpid C
where b.nivel=1 and A.idn=C.idn;

delete from Ids 
where ids in (select idObjetivo from tmpsiifpppobjetivo);

drop table TmpId;

drop table objfund;

--Objetivos planeacion estrategica.
--Nivel 2
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

insert into tmpsiifpppobjetivo(clavereal,idpadre,idobjetivo,definicion,clave,nombre,prioridad,idnivelobjetivo)
select 
    A.idPrograma,
    B.idobjetivo,
    c.ids,
    A.descripcion ,
    A.idrprograma, 
    A.descripcion,
    1000,
    D.idnivelobjetivo
from programa A join tmpsiifpppobjetivo B
        on(A.idrobjfund = B.clave)
       join tmpid C on (A.idn=C.idn),siifpppnivelobjetivo D
where D.nivel=2;

delete from Ids 
where ids in (select idObjetivo from tmpsiifpppobjetivo);

drop table TmpId;

drop table programa;


--Objetivos planeacion estrategica.
--Nivel 3
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

insert into tmpsiifpppobjetivo(clavereal,idpadre,idobjetivo,definicion,clave,nombre,prioridad,idnivelobjetivo)
select 
    A.idSubPrograma,
    B.idobjetivo,
    c.ids,
    A.nombre ,
    A.idrprograma, 
    A.nombre,
    1000,
    D.idnivelobjetivo
from subprograma A join tmpsiifpppobjetivo B
        on(A.idrprograma = B.clave)
       join tmpid C on (A.idn=C.idn),siifpppnivelobjetivo D
where D.nivel=3;

delete from Ids 
where ids in (select idObjetivo from tmpsiifpppobjetivo);

drop table TmpId;

drop table subprograma;

insert into siifpppobjetivo(clave,idpadre,idobjetivo,definicion,nombre,prioridad,idnivelobjetivo)
select 
    a.clavereal,
    a.idpadre,
    a.idobjetivo,
    a.definicion,
    a.nombre,
    a.prioridad,
    a.idnivelobjetivo
from 
    tmpsiifpppobjetivo A join siifpppnivelobjetivo B on (A.idnivelobjetivo = B.idnivelobjetivo);

drop table tmpsiifpppobjetivo;

--Encuadre entre PE y PI
delete siifpppencplaninstobj;

insert into siifpppencplaninstobj(idobjetivo,idPlanInstitucional)
    SELECT A.idobjetivo ,CC.idPlanInstitucional
    FROM 
        PEPI P
        join siifpppobjetivo A on P.idsubprograma=A.clave and A.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=3)
        join siifpppobjetivo B on P.idprograma=B.clave and B.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=2) and A.idpadre=B.idobjetivo
        join siifpppobjetivo C on P.ideje=C.clave and C.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=1) and b.idpadre=c.idobjetivo
        join siifabsdependencia AA on P.idur=AA.clave and AA.idnivdependencia in (select idNivDependencia from siifabsnivdependencia where uniejecutora='t')
        join siifabsdependencia BB on AA.idpadre=BB.iddependencia
        join siifpppplaninstitucional CC on CC.iddependencia = BB.iddependencia;

drop table pepi;

--Unidad de Medida META
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppunimedmeta;

insert into informix.siifpppunimedmeta(idUniMedMeta,descripcion)
select 
    B.ids,
    A.descripcion
from 
    tmpsiifpppunimedmeta A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idUniMedMeta from siifpppunimedmeta);

drop table TmpId;

drop table tmpsiifpppunimedmeta;
--Usuario
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifabsusuario;

insert into informix.siifabsusuario(idusuario,cambiarcontrasenia,descripcion,expiracontrasenia,habilitado,fechaultimoacceso,usuario,contrasenia)
select 
    B.ids,
    'f',
    A.descripcion,
    'f','t',
TO_DATE("5/24/2012", "%m/%d/%iY")
,a.usuario,a.contrasenia
from 
    tmpusuario A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idUsuario from siifabsusuario);

drop table TmpId;

drop table tmpusuario;

--Estado Anteproyecto
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppestadoanteproy;

insert into informix.siifpppestadoanteproy(idEstadoAnteproyecto,descripcion,diasestimados)
select 
    B.ids,
    A.descripcion,
    A.diasestimados
from 
    tmpsiifpppestadoanteproy A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idEstadoAnteproyecto from siifpppestadoanteproy);

drop table TmpId;

drop table tmpsiifpppestadoanteproy;

--Destino
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppobjgastodestino;
delete SiifPPPDestino;

insert into informix.SiifPPPDestino(idDestino,descripcion,clave)
select 
    B.ids,
    A.descripcion,
    A.clave
from 
    tmpSiifPPPDestino A
    join TmpId B
        on A.idn = B.idn;

delete from Ids 
where ids in (select idDestino from SiifPPPDestino);

drop table TmpId;

drop table tmpSiifPPPDestino;

--Problemas
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppproblema;

insert into informix.siifpppproblema(idproblema,descripcion,idobjetivo)
select 
    C.ids,
    A.descripcion,
    B.idObjetivo
from 
    tmpsiifpppproblema A
    join siifpppobjetivo B on 
B.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=A.nivel) AND A.CLAVE=B.clave
    join TmpId C
        on A.idn = C.idn;

delete from Ids 
where ids in (select idproblema from siifpppproblema);

drop table TmpId;

drop table tmpsiifpppproblema;

--Problemas
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

delete siifpppobjespecifico;

insert into informix.siifpppobjespecifico(idobjespecifico,descripcion,idobjetivo)
select 
    C.ids,
    A.descripcion,
    B.idobjetivo
from 
    tmpsiifpppobjespecifico A
    join siifpppobjetivo B on B.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=A.nivel) AND A.CLAVE=B.clave
    join TmpId C on (A.idn=C.idn)
where A.nivel=1;

delete from Ids 
where ids in (select idobjespecifico from siifpppobjespecifico);

drop table TmpId;


create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

insert into informix.siifpppobjespecifico(idobjespecifico,descripcion,idobjetivo)
select 
    C.ids,
    A.descripcion,
    B.idobjetivo
from 
    tmpsiifpppobjespecifico A
    join siifpppobjetivo B on B.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=A.nivel) AND A.CLAVE=B.clave
    join TmpId C on (A.idn=C.idn)
where A.nivel=2;

delete from Ids 
where ids in (select idobjespecifico from siifpppobjespecifico);

drop table TmpId;

create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;


insert into informix.siifpppobjespecifico(idobjespecifico,descripcion,idobjetivo)
select 
    E.ids,
    A.descripcion,
    B.idobjetivo
from 
    tmpsiifpppobjespecifico A
    join siifpppobjetivo B on B.idnivelobjetivo in (select idnivelobjetivo from siifpppnivelobjetivo where nivel=A.nivel) AND A.CLAVE=B.clave
    join siifpppobjetivo C on B.idpadre=C.idobjetivo and A.clavepadre=C.clave
    join siifpppobjetivo d on c.idpadre=d.idobjetivo
    join TmpId E on (A.idn=E.idn)
where A.nivel=3;

delete from Ids 
where ids in (select idobjespecifico from siifpppobjespecifico);

drop table TmpId;

drop table tmpsiifpppobjespecifico;


--Encuadre PE vs PI se realizo en la carega2daparte

--Encuadre PE vs CF
create table tmpCF
(
    IdCF                serial,
    idclasiffuncional   VARCHAR(255)
);

create table tmpPE
(
    IdPE                serial,
    idobjetivo          VARCHAR(255)
);

create table tmpPECF
(
    IdPECF              serial, 
    idclasiffuncional   VARCHAR(255),
    idobjetivo          VARCHAR(255)
);

insert into tmpCF(idclasiffuncional)
select cf.idclasiffuncional
from siifpppclasiffuncional cf
inner join siifpppnivclasiffunc ncf on cf.idnivclasiffuncional = ncf.idnivclasiffuncional and ncf.subfuncion = 't';

insert into tmpPE(idobjetivo)
select pe.idobjetivo
from siifpppobjetivo pe
inner join siifpppnivelobjetivo npe on pe.idnivelobjetivo = npe.idnivelobjetivo and nivel = 3;

--insert into tmpPECF(idclasiffuncional, idobjetivo)
--select tmpCF.idclasiffuncional, tmpPE.idobjetivo
--FROM tmpPE, tmpCF

insert into siifpppencobjclasiffunc(idclasiffuncional, idobjetivo)
select tmpCF.idclasiffuncional, tmpPE.idobjetivo
FROM tmpPE, tmpCF;

drop table tmpCF;

drop table tmpPE;

drop table tmpPECF;

--Encuadre Dest vs OG

create table tmpOG
(
    IdOG                serial,
    idobjetogasto       VARCHAR(255)
);


create table tmpDest
(
    IdDest              serial,
    iddestino           VARCHAR(255)
);


create table tmpDestOG
(
    IdDestOG            serial, 
    iddestino           VARCHAR(255),
    idobjetogasto       VARCHAR(255)
);


insert into tmpOG(idobjetogasto)
select og.idobjetogasto
from siifpppobjetogasto og
inner join siifpppnivelobjgasto nog on og.idnivelobjgasto = nog.idnivelobjgasto and nog.parespecifica = 't';

insert into tmpDest(iddestino)
select dest.iddestino
from siifpppdestino dest;

--insert into tmpDestOG(idobjetogasto, iddestino)
--select tmpOG.idobjetogasto, tmpDest.iddestino
--FROM tmpOG, tmpDest

insert into siifpppobjgastodestino(idobjetogasto, iddestino)
select tmpOG.idobjetogasto, tmpDest.iddestino
FROM tmpOG, tmpDest;

drop table tmpOG;

drop table tmpDest;

drop table tmpDestOG;

--Encuadre FF vs OG
create table tmpOG
(
    IdOG                serial,
    idobjetogasto       VARCHAR(36)
);

create table tmpFuente
(
    IdFuente           serial,
    idftefinan         VARCHAR(36)
);

create table tmpFteOG
(
    IdFteOG             serial, 
    idftefinan          VARCHAR(36),
    idobjetogasto       VARCHAR(36)
);

insert into tmpOG(idobjetogasto)
select og.idobjetogasto
from siifpppobjetogasto og
inner join siifpppnivelobjgasto nog on og.idnivelobjgasto = nog.idnivelobjgasto and nog.parespecifica = 't';

insert into tmpFuente(idftefinan)
select fte.idfuentefinanciamiento
from siifpppfuefinanciamiento fte;

--insert into tmpFteOG(idobjetogasto, idftefinan)
--select tmpOG.idobjetogasto, tmpFuente.idftefinan
--FROM tmpOG, tmpFuente
insert into siifpppobjgastofuefinan(idobjetogasto, idfuentefinanciamiento)
select tmpOG.idobjetogasto, tmpFuente.idftefinan
FROM tmpOG, tmpFuente;

drop table tmpOG;

drop table tmpFuente;

drop table tmpFteOG;

--Encuadre Techo PPtal OG
create table TmpId
(
    idn serial,
    ids varchar(36)
);

insert into tmpid(ids)
select ids from ids;

create table tmpOG
(
    IdOG                serial,
    idobjetogasto       VARCHAR(36)
);

create table tmpUEG
(
    IdUEG               serial,
    iduniejecutora      VARCHAR(36)
);

create table tmpUEGOG
(
    IdUEGOG             serial, 
	monto        	INT8,
	consecutivo  	INT8,
	iddependencia	VARCHAR(36) NOT NULL,
	idobjetogasto	VARCHAR(36)
);

insert into tmpOG(idobjetogasto)
select og.idobjetogasto
from siifpppobjetogasto og
inner join siifpppnivelobjgasto nog on og.idnivelobjgasto = nog.idnivelobjgasto and nog.parespecifica = 't';

insert into tmpUEG(iduniejecutora)
select d.iddependencia
from siifabsdependencia d
inner join siifabsnivdependencia nd on d.idnivdependencia = nd.idnivdependencia and nd.uniejecutora = 't';

insert into tmpUEGOG(monto,consecutivo, iddependencia, idobjetogasto)
select 10000000, 1, tmpUEG.iduniejecutora,tmpOG.idobjetogasto
FROM tmpOG, tmpUEG;

INSERT INTO siifppptechopptal(idtechopptal, monto, consecutivo, iddependencia, idobjetogasto) 
select ids, a.monto, a.consecutivo, a.iddependencia, a.idobjetogasto
from tmpUEGOG as A
inner join TmpId on  a.iduegog= idn;

delete from Ids 
where ids in (select idtechopptal from siifppptechopptal);

drop table tmpOG;

drop table tmpUEG;

drop table tmpUEGOG;

drop table TmpId;

--Temporal dejar siempre al final.
update siifpppestadoanteproy set idestadoanteproyecto='132881ab-3750e88f-0137-50e8e998-0002'
where descripcion='Validado SEPLAN';

--select * from siifpppbitacoraanteproy;
update siifpppestadoanteproy set idestadoanteproyecto='132881ab-3750e88f-0137-50e8e998-0000'
where descripcion='Nuevo';
