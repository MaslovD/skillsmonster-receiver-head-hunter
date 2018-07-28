package com.masdmtr.skillsmonster.utils;

import java.util.Collection;
import java.util.List;

/**
 * Created by dmaslov on 10/25/17.
 */
public class Utils {


    public static <E> void addAllIfNotNull(List<E> list, Collection<? extends E> c) {
        if (c != null) {
            list.addAll(c);
        }


    }

}
