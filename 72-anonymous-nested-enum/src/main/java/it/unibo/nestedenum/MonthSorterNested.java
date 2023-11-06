package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

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
            if (myName == "JANUARY") {
                return Month.JANUARY;
            } else if (myName == "FEBRUARY") {
                return Month.FEBRUARY;
            } else if (myName == "MARCH") {
                return Month.MARCH;
            } else if (myName == "APRIL") {
                return Month.APRIL;
            } else if (myName == "MAY") {
                return Month.MAY;
            } else if (myName == "JUNE") {
                return Month.JUNE;
            } else if (myName == "JULY") {
                return Month.JULY;
            } else if (myName == "SEPTEMBER") {
                return Month.SEPTEMBER;
            } else if (myName == "OCTOBER") {
                return Month.OCTOBER;
            } else if (myName == "NOVEMBER") {
                return Month.NOVEMBER;
            } else if (myName == "DECEMBER") {
                return Month.DECEMBER;
            }
            throw new NoSuchElementException();
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
