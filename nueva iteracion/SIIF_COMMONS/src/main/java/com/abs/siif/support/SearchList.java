/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 *
 * @author Israel Ruiz
 */
public class SearchList {

    public static <T> T findObjectList(Collection<?> list,
            final Object objectId) {
        return (T) CollectionUtils.find(list, new Predicate() {

            @Override
            public boolean evaluate(Object arg0) {
                return ((Comparable) arg0).compareTo(
                        objectId) == 0 ? true : false;
            }
        });
    }

    public static <T> T findObjectListCom(Collection<?> list,
            final Object objectId, final Comparator comp) {

        return (T) CollectionUtils.find(list, new Predicate() {

            @Override
            public boolean evaluate(Object arg0) {
                return comp.compare(arg0, objectId) == 0 ? true : false;
            }
        });

    }

    public static List findAllinList(Collection<?> list,
            final Object objectId, final Comparator comp){
        List findIt = new ArrayList();
        for(Object obj: list){
            if(comp.compare(obj, objectId) == 0 ){
              findIt.add(obj);
            }
        }
        return findIt;
    }

    public static int indexList(Collection<?> list, final Object objectId) {
        int i = -1;
        for (Object obj : list) {
            if ((((Comparable) obj).compareTo(objectId)) == 0) {

                return ++i;
            }
            i++;
        }
        if (i >= list.size()-1) {
            i = -1;
        }
        return i;
    }
}