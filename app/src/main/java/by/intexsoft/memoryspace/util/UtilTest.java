package by.intexsoft.memoryspace.util;

import java.util.Calendar;

/**
 * Created by Евгений on 20.11.2014.
 */
public class UtilTest
{
    public static int sum(int a, int b)
    {
        return a + b;
    }

    public static boolean emtyString(String s)
    {
        return s.equals("");
    }

    public static long testCalendar()
    {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar2.add(Calendar.MINUTE, 1);
        long difference = calendar2.getTimeInMillis() - calendar1.getTimeInMillis();

        return difference;
    }
}
