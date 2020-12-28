package mypackage;


public class Gregor implements Cloneable {

    private int day, month, year;

    public Gregor(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Gregor() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    private boolean checkDays(int day) {
        if(day < 1 || day > 31) {
            return false;
        }
        if(month == 2 && !isLeap(year) && day > 28)  {
            return false;
        }
        if(month == 2 && isLeap(year) && day > 29)  {
            return false;
        }
        if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }
        return true;
    }

    public int getDay() throws IllegalArgumentException{
        if(!checkDays(day)) {
            throw new IllegalArgumentException("Incorrect day in date");
        }
        return day;
    }

    public int getMonth() {
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Incorrect month in date");
        }
        return month;
    }

    public int getYear() {
        if(year < 0) {
            throw new IllegalArgumentException("Incorrect year in date");
        }
        return year;
    }

    public void setDay(int day) throws IllegalArgumentException {
        if(!checkDays(day)) {
            throw new IllegalArgumentException("Incorrect day in date");
        }

        this.day = day;
    }

    public void setMonth(int month) throws IllegalArgumentException {
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Incorrect month in date");
        }
        this.month = month;
    }

    public void setYear(int year) throws IllegalArgumentException {
        if(year < 0) {
            throw new IllegalArgumentException("Incorrect year in date");
        }
        this.year = year;
    }

    public void incDay() {
        this.day++;
    }

    public void  incMonth() {
        this.month++;
    }

    public void incYear() {
        this.year++;
    }

    public void decDay() {
        this.day--;
    }

    public void decMonth() {
        this.month--;
    }

    public void decYear() {
        this.year--;
    }
    /**
     * check if date equals to input date
     * @param date - comparing
     * @return if dates are equal or not
     */
    public boolean equals(Gregor date) {
        return this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear();
    }

    /**
     * clone the current date
     * @return cloned date
     * @throws CloneNotSupportedException
     */
    public Gregor clone() throws CloneNotSupportedException {
        return (Gregor) super.clone();
    }

    /**
     * String representation of our date
     * @return string with current date
     */
    @Override
    public String toString() {
        return this.day + "." + this.month + "." + this.year;
    }

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
     * @return Gregor - date after adding days
     * @throws CloneNotSupportedException
     */
    public Gregor addDays(Gregor d, int days) throws CloneNotSupportedException, IllegalArgumentException {

        boolean isNeg = false;
        if(days < 0) {
            isNeg = true;
            days *= -1;
        }

        Gregor date = d.clone();

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
                    if (date.getMonth() == 1) {
                        date.setDay(cntOfDays[12 - 1]);
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
     * @return Gregor - date after adding months
     * @throws CloneNotSupportedException
     * @throws IllegalArgumentException
     */
    public Gregor addMonths(Gregor d, int months) throws CloneNotSupportedException, IllegalArgumentException {

        Gregor date = d.clone();
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
     * @return Gregor - date after adding years
     * @throws CloneNotSupportedException
     * @throws IllegalArgumentException
     */
    public Gregor addYears(Gregor d, int years) throws CloneNotSupportedException, IllegalArgumentException {

        Gregor date = d.clone();

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
     * @throws IllegalArgumentException
     */
    public int futureWeekDay(Gregor date, int days) throws CloneNotSupportedException, IllegalArgumentException {

        return weekDayByDate(addDays(date, days));
    }

    /**
     * Function to get week day according to input date
     * @param date input date
     * @return int - day ordinal of the corresponding week
     * @throws CloneNotSupportedException
     * @throws IllegalArgumentException
     */
    public int weekDayByDate(Gregor date) throws CloneNotSupportedException, IllegalArgumentException {
        Gregor d = date.clone();

        int code = monthCodes[date.getMonth() - 1];
        if (d.getMonth() <= 2) {
            d.decYear();
        }

        return (d.getYear() + d.getYear() / 4 - d.getYear() / 100 + d.getYear() / 400 + code + d.getDay()) % 7;
    }


    /**
     * Function to get the nearest friday 13'th
     * @param date input date
     * @return Gregor - date of the nearest friday 13'th
     * @throws CloneNotSupportedException
     * @throws IllegalArgumentException
     */
    public Gregor friday13(Gregor date) throws CloneNotSupportedException, IllegalArgumentException {
        Gregor d = date.clone();
        int weekDay = weekDayByDate(date);

        while (d.getDay() != 13  || weekDay != 5) {
            d = addDays(d, 1);
            weekDay = weekDayByDate(d);
        }

        return d;
    }

    /**
     * Function to get how many days are till new next year after current year
     * @param date input date
     * @return int - days until new year
     * @throws CloneNotSupportedException
     * @throws IllegalArgumentException
     */
    public int daysTillNewYear(Gregor date) throws CloneNotSupportedException, IllegalArgumentException {
        Gregor d = date.clone();
        d.setMonth(1);
        d.setDay(1);
        d.setYear(date.getYear() + 1);

        Gregor diff = difference(d, date);
        return diff.getDay();
    }


    /**
     * Function to get the difference between two dates
     * @param date1 input date
     * @param date2 input date
     * @cond - date1 should be less or equal than date2
     * @return Gregor - difference between two dates
     * @throws CloneNotSupportedException
     * @throws IllegalArgumentException
     */
    public Gregor difference(Gregor date1, Gregor date2) throws CloneNotSupportedException, IllegalArgumentException {
        Gregor d1 = date1.clone();
        Gregor d2 = date2.clone();
        Gregor d = new Gregor();

        if (d1.getDay() >= d2.getDay()) {
            d.setDay(d1.getDay() - d2.getDay());
            helper(d1, d2, d);
        } else {
            if (d1.getMonth() != 1) {
                d1.decMonth();
            } else {
                d1.decYear();
            }
            d.setDay(d1.getDay() + cntOfDays[d1.getMonth() - 1] - d2.getDay());
            helper(d2, d1, d);
        }
        return d;
    }

    private void helper(Gregor d1, Gregor d2, Gregor d) throws IllegalArgumentException {
        if (d1.getMonth() >= d2.getMonth()) {
            d.setMonth(d1.getMonth() - d2.getMonth());
        } else {
            d1.decYear();
            d.setMonth(d1.getMonth() + 12 - d2.getMonth());
        }
        d.setYear(d1.getYear() - d2.getYear());
    }
}