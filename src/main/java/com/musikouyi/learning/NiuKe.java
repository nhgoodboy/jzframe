package com.musikouyi.learning;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NiuKe {

    private static int a = 1;

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(2);
//        e.execute();
//        int a = 2;
//        int b = 3;
//        System.out.println(a + ~b);
//        System.out.println("10004|com.game.sds.package1|sanxing_183423||1544668161795".length());
//
//        List<String> list      = new ArrayList();
//        List<String> otherList = new ArrayList();
//
//        String element1 = "element 1";
//        String element2 = "element 2";
//        String element3 = "element 3";
//        String element4 = "element 4";
//
//        list.add(element1);
//        list.add(element2);
//        list.add(element3);
//
//        otherList.add(element1);
//        otherList.add(element3);
//        otherList.add(element4);
//
//        list.retainAll(otherList);
//        for (String zz : list){
//            System.out.println(zz);
//        }
//
//        NavigableSet original = new TreeSet();
//        original.add("3");
//        original.add("2");
//        original.add("1");
//
////this headset will contain "1" and "2"
//        SortedSet headset = original.headSet("3");
//        Iterator iterator = headset.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        System.out.println(headset.contains("3"));

        String asd = "asd";
        String asdf = "a" + "sd";
        String asdfg = "sd";
        String asdfgh = asdf + asdfg;
        System.out.println(asd == asdf);

        int[] test = new int[] {1,2,3,4};
        List list = Arrays.asList(test);
        System.out.println(list.size());
        System.out.println(list.get(0));
        Integer a = 3;

//        list.
//        Iterator<Integer> iterator = list.iterator();
//        for (Iterator it = iterator; it.hasNext(); ) {
//            int i = (int) it.next();
//            System.out.println(i);
//        }
    }
}
