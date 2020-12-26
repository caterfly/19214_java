package mypackage;

/**
 * Implementation of gregorian calendar based on MyCalendar interface
 */
public class Gregor {

    private int[] monthCodes = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
    private int[] cntOfDays = { 31, 28, 31 , 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) return true;
        return false;
    }
    /**
     * Function to get date after adding positive or negative amount of days
     * @param d current date
     * @param days number of days to add
     * @return MyDate - date after adding days
     * @throws CloneNotSupportedException
     */
    public MyDate addDays(MyDate d, int days) throws CloneNotSupportedException {

        boolean isNeg = false;
        if(days < 0) {
            isNeg = true;
            days *= -1;
        }

        MyDate date = d.clone();

        for (int i = 0; i < days; i++) {

            if (isLeap(date.getYear())) {
                cntOfDays[1] = 29;
            } else {
                cntOfDays[1] = 28;
            }

            if (!isNeg) {
                if (date.getDay() == cntOfDays[date.getMonth() - 1]) {
                    date.setDay(1);
                    if (date.getMonth() == 12) {
                        date.incYear();
                        date.setMonth(1);
                    } else {
                        date.incMonth();
                    }
                } else {
                    date.incDay();
                }
            } else {
                if (date.getDay() == 1) {
                    if (date.getMonth() - 2 < 0) {
                        date.setDay(cntOfDays[date.getMonth() + 12]);
                        date.decYear();
                        date.setMonth(12);
                    } else {
                        date.decMonth();
                        date.setDay(cntOfDays[date.getMonth() - 1]);
                    }
                } else {
                    date.decDay();
                }
            }
        }

        return date;
    }

    /**
     * Function to get date after adding positive or negative amount of months
     * @param d current date
     * @param months number of months to count
     * @return MyDate - date after adding months
     * @throws CloneNotSupportedException
     */
    public MyDate addMonths(MyDate d, int months) throws CloneNotSupportedException {

        MyDate date = d.clone();
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        month += months;
        year += (month-1) / 12;
        month = (month-1) % 12 + 1;
        if (month <= 0) {
            year--;
            month = 12 + month;
        }
        int max = cntOfDays[month];
        if (isLeap(d.getYear()) && month == 1) max = 29;
        if (day > max) {
            month++;
            day = day - max;
        }
        if (month == 13) {
            month = 1;
        }
        date.setMonth(month);
        date.setYear(year);
        date.setDay(day);

        return date;
    }

    /**
     * Function to get date after adding positive or negative amount of years
     * @param d current date
     * @param years number of years to add
     * @return MyDate - date after adding years
     * @throws CloneNotSupportedException
     */
    public MyDate addYears(MyDate d, int years) throws CloneNotSupportedException {

        MyDate date = d.clone();

        int year = date.getYear();
        if (isLeap(year) && !isLeap(year+years) && date.getMonth() == 2 && date.getDay() == 29) {
            date.setDay(1);
            date.setMonth(3);
        }

        date.setYear(year+years);

        return date;
    }

    /**
     * Function to get future week day according to current date and number of days to add
     * @param date current date
     * @param days number of days to add
     * @return int - day ordinal of the corresponding week
     * @throws CloneNotSupportedException
     */
    public int futureWeekDay(MyDate date, int days) throws CloneNotSupportedException {

        return weekDayByDate(addDays(date, days));
    }

    /**
     * Function to get week day according to input date
     * @param date input date
     * @return int - day ordinal of the corresponding week
     * @throws CloneNotSupportedException
     */
    public int weekDayByDate(MyDate date) throws CloneNotSupportedException {
        MyDate d = date.clone();

        int code = monthCodes[date.getMonth() - 1];
        if (d.getMonth() <= 2) {
            d.decYear();
        }

        return (d.getYear() + d.getYear() / 4 - d.getYear() / 100 + d.getYear() / 400 + code + d.getDay()) % 7;
    }

    /**
     * Function to get how many days are till new next year after current year
     * @param date input date
     * @return int - days until new year
     * @throws CloneNotSupportedException
     */
    public int daysTillNewYear(MyDate date) throws CloneNotSupportedException {
        MyDate d = date.clone();
        d.setMonth(1);
        d.setDay(1);
        d.incYear();

        MyDate diff = difference(d, date);
        return diff.getDay();
    }

    /**
     * Function to get the nearest friday 13'th
     * @param date input date
     * @return MyDate - date of the nearest friday 13'th
     * @throws CloneNotSupportedException
     */
    public MyDate friday13(MyDate date) throws CloneNotSupportedException {
        MyDate d = date.clone();
        int weekDay = weekDayByDate(date);

        while (d.getDay() != 13  || weekDay != 5) {
            d = addDays(d, 1);
            weekDay = weekDayByDate(d);
        }

        return d;
    }

    /**
     * Function to get the difference between two dates
     * @param date1 input date
     * @param date2 input date
     * @cond - date1 should be less or equal than date2
     * @return MyDate - difference between two dates
     * @throws CloneNotSupportedException
     */
    public MyDate difference(MyDate date1, MyDate date2) throws CloneNotSupportedException {
        MyDate d1 = date1.clone();
        MyDate d2 = date2.clone();
        MyDate d = new MyDate();

        if (d1.getDay() >= d2.getDay()) {
            d.setDay(d1.getDay() - d2.getDay());
            helper(d2, d1, d);
        } else {
            if (d1.getMonth() != 1)
                d1.decMonth();
            else
                d1.decYear();
            d.setDay(d1.getDay() + cntOfDays[d1.getMonth() - 1] - d2.getDay());
            helper(d2, d1, d);
        }
        return d;
    }

    private void helper(MyDate d2, MyDate d1, MyDate d) {
        if (d1.getMonth() >= d2.getMonth()) {
            d.setMonth(d1.getMonth() - d2.getMonth());
        } else {
            d1.decYear();
            d.setMonth(d1.getMonth() + 12 - d2.getMonth());
        }
        d.setYear(d1.getYear() - d2.getYear());
    }
}