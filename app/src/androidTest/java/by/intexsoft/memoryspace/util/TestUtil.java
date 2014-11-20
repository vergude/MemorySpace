package by.intexsoft.memoryspace.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.annotation.Config;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Евгений on 20.11.2014.
 */

@Config(emulateSdk = 18)
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({UtilTest.class})
public class TestUtil
{
    @Test
    public void testSum()
    {
        int sum = 4;
        PowerMockito.mockStatic(ImagesUtils.class);
        PowerMockito.doReturn(sum).when(ImagesUtils.class);
        UtilTest.sum(anyInt(),anyInt());

        int actualValue = UtilTest.sum(2, 2);

        assertEquals(sum, actualValue);
    }

    @Test
    public void testEmtyString()
    {
        boolean result = true;
        PowerMockito.mockStatic(ImagesUtils.class);
        PowerMockito.doReturn(result).when(ImagesUtils.class);
        UtilTest.emtyString(anyString());

        boolean actualValue = UtilTest.emtyString("ddd");

        assertEquals(result, actualValue);
    }

    @Test
    public void testCalendar()
    {
        long result = 60000;
        PowerMockito.mockStatic(ImagesUtils.class);
        PowerMockito.doReturn(result).when(ImagesUtils.class);
        UtilTest.testCalendar();

        long actualValue = UtilTest.testCalendar();

        assertEquals(result, actualValue);
    }
}
