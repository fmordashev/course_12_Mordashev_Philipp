package ru.atc.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class C {

    public static void println(String str){
        System.out.println(str);
    }

    public static <E>  void println(String ... str){

        StringBuilder sb = new StringBuilder();
        Iterator iti = Arrays.asList(str).iterator();
        while (iti.hasNext()) {

            E s = (E) iti.next();
            sb.append( s.toString() );
        }

        System.out.println(sb.toString());
    }

    public static <E>  void println(List<String> str){

        StringBuilder sb = new StringBuilder();
        Iterator iti = Arrays.asList(str).iterator();
        while (iti.hasNext()) {

            E s = (E) iti.next();
            sb.append( s.toString() );
        }

        System.out.println(sb.toString());
    }

    public static <E> void println(String Title, Collection coll){
        System.out.println("");
        System.out.println("===> " + Title);

        for(Object o: coll){
            System.out.println(o.toString());
        }

/*        Iterator iti = coll.iterator();
        while (iti.hasNext()) {

            E s = (E) iti.next();
            System.out.println(s.toString());
        }*/
    }

    public static  <E> void print(String Title, Collection coll){

        System.out.println("");

        StringBuilder sb = new StringBuilder();
        Iterator iti = coll.iterator();
        while (iti.hasNext()) {

            E s = (E) iti.next();
            sb.append( s.toString() + " | " );
        }
        System.out.println("===> " + Title);
        System.out.println(sb.toString());
    }



}
