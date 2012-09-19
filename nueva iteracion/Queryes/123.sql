begin work;

 -- Actualizar el sqlvalida en la configuración de la clave presupuestal (SIIF WEB)
update siifpppcvepptalcfg
set sqlvalida = 'select prog.objectiveId,prog.objectiveKey from DraftProjectEntity proj left outer join proj.draftProjectProgramming prog left outer join prog.programmingObjective objsp left outer join objsp.objectiveFather prog where (proj.draftProjectId=''$idproyecto'')'
where idcvecfg = 10;

commit work;
rollback work;

select * from siifpppcvepptalcfg;