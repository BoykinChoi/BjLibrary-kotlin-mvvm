package com.boykinchoi.star.design_pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 10:18
 */
public class CrayonBag {
    private static Map<String, ICrayon> crayons = new HashMap<>();

    public static ICrayon getCrayon(String color) {
        if (crayons.containsKey(color)) {
            return crayons.get(color);
        }
        Crayon crayon = new Crayon(color);
        crayons.put(color, crayon);
        return crayon;
    }
}
