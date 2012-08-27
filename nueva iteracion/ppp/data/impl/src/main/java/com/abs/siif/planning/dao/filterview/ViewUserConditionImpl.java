/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ViewUserDependecyFilter
 *  Purpose:  [ short Description  ]
 *
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao.filterview;

import com.abs.siif.support.AliasEntityEnum;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Israel Ruiz
 */
@Component("viewUserCondition")
@Scope("prototype")
public class ViewUserConditionImpl extends ViewUserCondition {

    @Override
    public String addConstrains(String sqlData) {
        sqlData = setWhereConstrains(sqlData);
        if (this.filter != null) {
            sqlData = filter.addConstrains(sqlData);
        }

        return sqlData;
    }

    /**
     * Este metodo realiza la inserción de la condición que se encuentre en los
     * parametros de la base dados por el parametro "constrains"
     *
     * @param sqlData
     * @return
     */
    private String setWhereConstrains(String sqlData) {
        String whereCondition = "where";
        String orderClause = "order";
        String wildcard = "%";
        String groupBy = "group by";
        String tempData = "as tempData";
        StringBuilder queryNew;
        String columName;
        String tempSql;
        Pattern patternWhere;
        Pattern patternTempData;
        Pattern patterOrder;
        Pattern patternGropuBy;
        Matcher matcherWhere;
        Matcher matcherTempData;
        Matcher matcherOrder;
        Matcher matchGroupBy;
        String aliasEntity;
        boolean levelQueryOk = true;
        int indxEnd = 0;
        boolean noWhere = true;


        AliasEntityEnum element = AliasEntityEnum.getEnumData(
                constrains.getEntityName());

        if (constrains.getEntityName() == null
                || element == null || sqlData == null) {
            levelQueryOk = false;
        }
        if (levelQueryOk) {
            //Se coloca todo en lowarcase para simplificar el manejo
            tempSql = sqlData;



            //Encuentra la columna contra la que se mapea
            columName = element.getEntityClave();
            //Encuentra el alias que se utiliza en el query
            aliasEntity = element.name();


            if (constrains.getLevelEntity() != null
                    && !tempSql.contains(constrains.getLevelEntity())) {
                levelQueryOk = false;
            }

            /*
             * Se verifica si el query que esta registrandose tenga las
             * propiedades con las cuales se va a generar el contrain
             */

            if ((tempSql != null)
                    && (this.constrains.getEntityName() != null)
                    && tempSql.contains(
                    this.constrains.getEntityName())) {




                //Se utiliza expresiones regulares para encontrar los patrones
                // indicados y poder insertar los datos
                patternWhere = Pattern.compile(whereCondition);
                patterOrder = Pattern.compile(orderClause);
                patternGropuBy = Pattern.compile(groupBy);
                patternTempData = Pattern.compile(tempData);


                matcherWhere = patternWhere.matcher(tempSql);
                matcherTempData = patternTempData.matcher(tempSql);
                matcherOrder = patterOrder.matcher(tempSql);
                matchGroupBy = patternGropuBy.matcher(tempSql);
                queryNew = new StringBuilder(tempSql);

                // Si hay un where se asume que almenos existe una condición
                if (matcherWhere.find()) {
                    noWhere = false;
                    indxEnd = matcherWhere.start() - 1;

                    if (matcherTempData.find()) {
                        int indxAll = matcherTempData.start()-3; 
                        if (constrains.getCondition().contains(wildcard)) {
                            queryNew.insert(indxAll, " UNION ALL select "
                                    + columName + "  from "
                                    + constrains.getEntityName() + " where "
                                    + columName + " like '"
                                    + constrains.getCondition() + "' and "
                                    + element.getLinkToLevel() + "."
                                    + constrains.getLevelEntity() + "='t' ");
                        } else {
                            queryNew.insert(indxAll, " UNION ALL select "
                                    + columName + "  from "
                                    + constrains.getEntityName() + " where "
                                    + columName + " = '"
                                    + constrains.getCondition() + "' and "
                                    + element.getLinkToLevel() + "."
                                    + constrains.getLevelEntity() + "='t' ");
                        }
                    } else {
                        if (constrains.getCondition().contains(wildcard)) {

                            queryNew.insert(indxEnd, "  inner join ( select "
                                    + columName + "  from "
                                    + constrains.getEntityName() + " where "
                                    + columName + " like '"
                                    + constrains.getCondition() + "' and "
                                    + element.getLinkToLevel() + "."
                                    + constrains.getLevelEntity() + "='t'   )"
                                    + " as tempData on " + element.name()
                                    + "." + element.getEntityClave() + " = "
                                    + " tempData." + element.getEntityClave()
                                    + " ");


                        } else {
                            queryNew.insert(indxEnd, "  inner join ( select "
                                    + columName + "  from "
                                    + constrains.getEntityName() + " where "
                                    + columName + " = '"
                                    + constrains.getCondition() + "' and "
                                    + element.getLinkToLevel() + "."
                                    + constrains.getLevelEntity() + "='t'   )"
                                    + " as tempData on " + element.name()
                                    + "." + element.getEntityClave() + " = "
                                    + " tempData." + element.getEntityClave()
                                    + " ");
                        }


                    }
                }
                /*
                 * Revisar si los queries pueden caer en estas condiciones
                 *
                 * // No where en la sentencia pero hay un order } else if
                 * (matcherOrder.find()) { noWhere = true; indxEnd =
                 * matcherOrder.start() - 1;
                 *
                 *
                 * } else if (matchGroupBy.find()) { noWhere = true; indxEnd =
                 * matchGroupBy.start() - 1;
                 *
                 * } else { indxEnd = tempSql.length(); } if (noWhere) { if
                 * (constrains.getCondition().contains(wildcard)) {
                 * queryNew.insert(indxEnd, " where " + aliasEntity + "." +
                 * columName + " like '" + constrains.getCondition() + "' "); }
                 * else { queryNew.insert(indxEnd, " where " + aliasEntity + "."
                 * + columName + " = '" + constrains.getCondition() + "' "); } }
                 */
                sqlData = queryNew.toString();

            }
        }
        return sqlData;
    }
}
