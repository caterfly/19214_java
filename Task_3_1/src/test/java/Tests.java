import org.junit.Assert;
import org.junit.Test;
import mypackage.Gregor;
import mypackage.MyDate;

public class Tests {

    private Gregor greg = new Gregor();
    private MyDate today = new MyDate(24, 12, 2020);

    @Test
    public void test_1() throws CloneNotSupportedException {
        int weekDay = greg.futureWeekDay(today, 1024);
        Assert.assertEquals(weekDay, 6);
    }

    @Test
    public void test_2() throws CloneNotSupportedException {
        MyDate war = new MyDate(9, 5, 1945);
        MyDate actual = new MyDate(15, 7, 75);
        MyDate diff = greg.difference(today, war);

        Assert.assertTrue(actual.equals(diff));
    }

    @Test
    public void test_3() throws CloneNotSupportedException {
        MyDate birth = new MyDate(31, 5, 2000);
        int weekDay = greg.weekDayByDate(birth);

        Assert.assertEquals(3, weekDay);
    }

    @Test
    public void test_4() throws CloneNotSupportedException {
        MyDate future = greg.addDays(today, 17 * 7);

        Assert.assertEquals(4, future.getMonth());
    }

    @Test
    public void test_5() throws CloneNotSupportedException {
        MyDate future = greg.addMonths(today, 11);

        Assert.assertEquals(11, future.getMonth());
    }

    @Test
    public void test_6() throws CloneNotSupportedException {
        MyDate future = greg.addYears(today, 208);

        Assert.assertEquals(2228, future.getYear());
    }
    @Test
    public void test_7() throws CloneNotSupportedException {
        int days = greg.daysTillNewYear(today);

        Assert.assertEquals(8, days);
    }

    @Test
    public void test8() throws CloneNotSupportedException {
        MyDate friday = greg.friday13(today);
        MyDate actual = new MyDate(13, 8, 2021);

        Assert.assertTrue(actual.equals(friday));
    }

    @Test
    public void test_9() throws CloneNotSupportedException {
        MyDate past = greg.addDays(today, -365);

        Assert.assertEquals(12, past.getMonth());
    }

}