package by.intexsoft.memoryspace.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import by.intexsoft.memoryspace.util.ImagesUtils;

/**
 * Created by vadim on 22.11.2014.
 */

@EBean(scope = EBean.Scope.Singleton)
public class DatabaseQueryHelper
{

    public static final String TABLE_IMAGES = "table_images";
    public static final String COLUMN_IMAGE_ID = "_id";
    public static final String COLUMN_IMAGE_PATH = "image_path";
    public static final String COLUMN_IMAGE_WEIGHT = "image_weight";

    public static final String CREATE_IMAGES_TABLE =
            "create table " + TABLE_IMAGES + "(" +
                    COLUMN_IMAGE_ID + " integer primary key autoincrement, " +
                    COLUMN_IMAGE_PATH + " text, " +
                    COLUMN_IMAGE_WEIGHT + " integer " +
                    ");";

    private final static String IMAGE_PREFIX = "cars/car";
    private static final int DEFAULT_WEIGHT = 0;

    @RootContext
    Context context;

    private MemorySpaceSqliteOpenHelper getHelper()
    {
        return MemorySpaceSqliteOpenHelper.getInstance(context);
    }

    private SQLiteDatabase getReadableDb()
    {
        return getHelper().getReadableDatabase();
    }

    private SQLiteDatabase getReadWriteDb()
    {
        return getHelper().getWritableDatabase();
    }


    public void fillTable()
    {
        getReadWriteDb().delete(TABLE_IMAGES, null, null);

        ArrayList<String> imagesList = ImagesUtils.getAllRandomImagesUrl(IMAGE_PREFIX);

        for (String image : imagesList)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_IMAGE_PATH, image);
            contentValues.put(COLUMN_IMAGE_WEIGHT, DEFAULT_WEIGHT);
            getReadWriteDb().replace(TABLE_IMAGES, null, contentValues);
        }

        showAllFields(getReadableDb().query(TABLE_IMAGES, null, null, null, null, null, null),
                "**********AFTER ADD IMAGES*********");
    }

    @Nullable
    public Cursor getCursorImagesPath()
    {
        try
        {
            String[] columns = {COLUMN_IMAGE_PATH, COLUMN_IMAGE_WEIGHT};
            Cursor cursor = getReadableDb().query(TABLE_IMAGES, columns, null, null, null, null, COLUMN_IMAGE_WEIGHT);
            showAllFields(cursor, "**********AFTER LOADER*********");
            return cursor;
        }
        catch (SQLException e)
        {
            handleException(e);
            return null;
        }
    }

    public void updateImagesWeight(Cursor cursor, int cellsCount)
    {
        cursor.moveToFirst();

        for (int i = 0; i < cellsCount; i++)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_IMAGE_WEIGHT, cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_WEIGHT)) + 1);

            getReadWriteDb().update(TABLE_IMAGES, contentValues, COLUMN_IMAGE_PATH + " = ?",
                    new String[]{cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH))});

            cursor.moveToNext();
        }

        showAllFields(getReadableDb().query(TABLE_IMAGES, null, null, null, null, null, COLUMN_IMAGE_WEIGHT),
                "**********AFTER UPDATE*********");
    }

    /*Log for debug**/
    public void showAllFields(Cursor cursor, String message)
    {
        Log.d("image", message);
        while (cursor.moveToNext())
        {
            Log.d("image", cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH))
                    + "   " + cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_WEIGHT)));
        }
    }

    private void handleException(SQLException e)
    {
        Log.e("DatabaseQueryHelper", e.getMessage());
    }

}
