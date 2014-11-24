package by.intexsoft.memoryspace.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vadim on 22.11.2014.
 */


public class MemorySpaceSqliteOpenHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "memory_space.db";
    private static final int DB_VERSION = 1;
    private static MemorySpaceSqliteOpenHelper memorySpaceSqliteOpenHelper;

    public MemorySpaceSqliteOpenHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static MemorySpaceSqliteOpenHelper getInstance(Context context)
    {
        if (memorySpaceSqliteOpenHelper == null)
        {
            synchronized (MemorySpaceSqliteOpenHelper.class)
            {
                memorySpaceSqliteOpenHelper = new MemorySpaceSqliteOpenHelper(context);
            }
        }
        return memorySpaceSqliteOpenHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DatabaseQueryHelper.CREATE_IMAGES_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

    }
}
