package com.musikouyi.learning.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapTest {

    public static void main(String[] args) {
        Map<String, String> myMap = new HashMap<>();
        String keyA = "A";
        String keyB = "B";
        String keyC = "C";
        String keyD = "D";
        String keyE = "E";
        String keyF = "F";
        String keyG = "G";
        String keyH = "H";
        myMap.put(keyA, "str01A");
        myMap.put(keyB, "str01B");
        myMap.put(keyC, "str01C");

        System.out.println("myMap initial content:"+ myMap);

        myMap.merge(keyA, "merge01", String::concat);
        myMap.merge(keyD, "merge01", String::concat);
        System.out.println("Map merge demo content:"+ myMap);

        BiFunction<String, String, String> biFunc = new BiFunction<String, String, String>(){
            @Override
            public String apply(String t, String u) {
                String result = t;
                if (t == null) {
                    result = u;
                }
                else {
                    result += "," + u;
                }
                return result;
            }
        };

        myMap.merge(keyA, "BiFuncMerge01", biFunc);
        myMap.merge(keyE, "BiFuncMerge01", biFunc);
        System.out.println("Map customized BiFunction merge demo content:"+ myMap);

        String msg = "msgCompute";
        myMap.compute(keyB, (k, v) -> (v == null) ? msg : v.concat(msg));
        myMap.compute(keyF, (k, v) -> (v == null) ? msg : v.concat(msg));
        System.out.println("Map customized BiFunction compute demo content:"+ myMap);

        myMap.computeIfAbsent(keyC, k -> genValue(k));
        myMap.computeIfAbsent(keyG, k -> genValue(k));
        System.out.println("Map customized Function computeIfAbsent demo content:"+ myMap);

        myMap.computeIfPresent(keyC, biFunc);
        myMap.computeIfPresent(keyH, biFunc);
        System.out.println("Map customized biFunc computeIfPresent demo content:"+ myMap);
    }

    static String genValue(String str) {
        System.out.println("===");
        return str + "2";
    }

}
