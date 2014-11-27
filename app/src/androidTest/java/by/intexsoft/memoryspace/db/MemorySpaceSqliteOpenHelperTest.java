package by.intexsoft.memoryspace.db;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by vadim on 25.11.2014.
 */

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({MemorySpaceSqliteOpenHelperTest.class})
public class MemorySpaceSqliteOpenHelperTest
{

    @Test
    public void internalStateTest()
    {
        Whitebox.setInternalState(MemorySpaceSqliteOpenHelper.class, "memorySpaceSqliteOpenHelper",
                new MemorySpaceSqliteOpenHelper(mock(Context.class)));
    }

    @Test
    public void doReturnTest()
    {
        mockStatic(MemorySpaceSqliteOpenHelper.class);
        PowerMockito.doReturn(mock(MemorySpaceSqliteOpenHelper.class)).when(MemorySpaceSqliteOpenHelper.class);
    }

    @Test
    public void getInstanceTest()
    {
        MemorySpaceSqliteOpenHelper helper = MemorySpaceSqliteOpenHelper.getInstance(mock(Context.class));
        assertNotNull(helper);
    }
}
