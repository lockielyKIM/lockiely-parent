package org.lockiely.test;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.stream.Stream;

public class MathTest {

    public static void main(String[] args) {
        int i = 0;
        int s = 8;
//        System.out.println(i % s);
//        System.out.println(i/s);
        Stream.iterate(1, item -> item + 1).limit(1).forEach(System.out::println);
        Calendar time = Calendar.getInstance();
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        time.set(Calendar.HOUR_OF_DAY, 25);
        System.out.println(time.getTime());
        System.out.println(time.get(Calendar.HOUR_OF_DAY) + "-------" + time.get(Calendar.DATE));
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        System.out.println(time.getTime());
        System.out.println(time.get(Calendar.HOUR_OF_DAY) + "-------" + time.get(Calendar.DATE));
        time.set(Calendar.DATE, 31);
        time.set(Calendar.HOUR_OF_DAY, 25);
        System.out.println(time.getTime());
        time.set(Calendar.HOUR_OF_DAY, 0);
        System.out.println(time.getTime());
//        time.set(Calendar.MONTH, Calendar.DECEMBER);
//        time.set(Calendar.DATE, 0);
//        time.set(Calendar.HOUR_OF_DAY, 1);
//        time.set(Calendar.MINUTE, 0);
//        time.set(Calendar.SECOND, 0);
//        time.set(Calendar.MILLISECOND, 0);
//        System.out.println(time.get(Calendar.DATE));
//        System.out.println(time.get(Calendar.DAY_OF_YEAR));
//        time.set(2020, 11, 31, 23, 0, 0);
//        System.out.println(time.getTime().toInstant().atZone(ZoneId.systemDefault()));
//        time.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY) + 1);
//        System.out.println(time.getTime().toInstant().atZone(ZoneId.systemDefault()));
//        System.out.println(time.get(Calendar.DATE));
//        Calendar time1 = Calendar.getInstance();
//        time1.set(2020, 5, 1, 0, 0,0);
//        System.out.println((time1.getTimeInMillis() - time.getTimeInMillis())/(1000 * 3600 * 24));

    }
}
