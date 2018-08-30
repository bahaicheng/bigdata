package test;

import org.joda.time.DateTime;

public class DateTimeDemo {
    public static void main(String[] args) {
        DateTime dt = new DateTime();
        System.out.println(dt.plusDays(1).toString(" MM/dd/yyyy"));
    }
}
