package mypackage;

/**
 * Base class for date representation
 * It has useful methods for working
 * Implements  Cloneable to handle input data
 */

public class MyDate implements Cloneable {

    private int day, month, year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public MyDate() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
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
    public boolean equals(MyDate date) {
        return this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear();
    }

    /**
     * clone the current date
     * @return cloned date
     * @throws CloneNotSupportedException
     */
    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    /**
     * String representation of our date
     * @return string with current date
     */
    @Override
    public String toString() {
        return this.day + "." + this.month + "." + this.year;
    }
}
