package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.imageio.IIOException;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public Comparator<String> ORDERBYDAYS = new SortByDays();
    public Comparator<String> ORDERBYTIME = new SortByTime();

    @Override
    public Comparator<String> sortByDays() {
        return ORDERBYDAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return ORDERBYTIME;
    }

    public enum Month {
        
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(30), JUNE(30), JULY(31),
        AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);


        private int numDays;

        Month(int days) {
            this.numDays = days;
        }
        
        public static Month fromString(final String myName) {
            try {
                return valueOf(myName);                                         //Tries to see if string passed by parameter is already matching.
            } catch (IllegalArgumentException e) {
                Month result = null;
                for (Month currentMonth : values()) {                           //iterating the values contained into my enum.
                    if (currentMonth.toString().toLowerCase(Locale.ROOT).startsWith(myName.toLowerCase(Locale.ROOT))) {
                        if (result != null) {
                            throw new IllegalArgumentException(
                                "Your input is ambigous ( " + myName + " ). I cannot decide.");
                        } 
                        result = currentMonth;
                    }
                }
                if (result == null) {
                    throw new IllegalArgumentException("No matching, sorry");
                }
                return result;
            }
        }
    }

    public static  class SortByDays implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            final int firstmonth = Month.fromString(o1).numDays;
            final int secondmonth = Month.fromString(o2).numDays;
            return Integer.compare(firstmonth, secondmonth);
        }
    }

    public static class SortByTime implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
        
    }
    
}
