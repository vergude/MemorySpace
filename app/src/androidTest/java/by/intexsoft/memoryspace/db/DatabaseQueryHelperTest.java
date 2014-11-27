package by.intexsoft.memoryspace.db;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

import org.powermock.reflect.Whitebox;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.net.URISyntaxException;
import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by vadim on 24.11.2014.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({DatabaseQueryHelper.class})
public class DatabaseQueryHelperTest
{


    DatabaseQueryHelper helper;

    @Before
    public void init() throws URISyntaxException
    {
        helper = spy(new DatabaseQueryHelper());
    }

    @Test
    public void getHelperTest() throws Exception
    {
        MemorySpaceSqliteOpenHelper openHelper = Whitebox.invokeMethod(helper, "getHelper");
        assertNotNull(openHelper);
    }

//    @Test
//    public void getReadWriteDbTest() throws Exception
//    {
//        SQLiteDatabase sqLiteDatabase =  Whitebox.invokeMethod(helper, "getReadWriteDb");
//        assertNotNull(sqLiteDatabase);
//    }
//
//
//    @Test
//    public void getReadableDbTest() throws Exception
//    {
//        SQLiteDatabase sqLiteDatabase = Whitebox.invokeMethod(helper, "getReadableDb");
//        assertNotNull(sqLiteDatabase);
//    }


    @Test
    public void fillTableTest()
    {
        SQLiteDatabase sqLiteDatabase = mock(SQLiteDatabase.class);

        ArrayList<String> randomList = new ArrayList<String>();
        for (int i = 0; i < 30; i++)
        {
            randomList.add("Some.jpg");
        }

        for (String image : randomList)
        {
            ContentValues contentValues = mock(ContentValues.class);
            contentValues.put(DatabaseQueryHelper.COLUMN_IMAGE_PATH, image);
            contentValues.put(DatabaseQueryHelper.COLUMN_IMAGE_WEIGHT, 0);

            sqLiteDatabase.insert(DatabaseQueryHelper.TABLE_IMAGES, null, contentValues);

        }
    }

    @Test
    public void getCursorImagesPathTest()
    {
        SQLiteDatabase sqLiteDatabase = mock(SQLiteDatabase.class);

        String[] columns = {DatabaseQueryHelper.COLUMN_IMAGE_PATH,};
        sqLiteDatabase.query(DatabaseQueryHelper.TABLE_IMAGES, columns, null, null, null, null,
                DatabaseQueryHelper.COLUMN_IMAGE_WEIGHT);
    }

    @Test
    public void updateImagesWeightTest()
    {
        Cursor cursor = mock(Cursor.class);
        SQLiteDatabase sqLiteDatabase = mock(SQLiteDatabase.class);

        cursor.moveToFirst();

        for (int i = 0; i < 6; i++)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseQueryHelper.COLUMN_IMAGE_WEIGHT,
                    cursor.getInt(cursor.getColumnIndex(DatabaseQueryHelper.COLUMN_IMAGE_WEIGHT)) + 1);

            sqLiteDatabase.update(DatabaseQueryHelper.TABLE_IMAGES, contentValues, DatabaseQueryHelper.COLUMN_IMAGE_PATH + " = ?",
                    new String[]{cursor.getString(cursor.getColumnIndex(DatabaseQueryHelper.COLUMN_IMAGE_PATH))});
            cursor.moveToNext();
        }
    }
}