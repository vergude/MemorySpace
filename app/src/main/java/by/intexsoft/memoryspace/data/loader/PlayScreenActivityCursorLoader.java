package by.intexsoft.memoryspace.data.loader;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import by.intexsoft.memoryspace.db.DatabaseQueryHelper;
import by.intexsoft.memoryspace.db.DatabaseQueryHelper_;

/**
 * Created by vadim on 22.11.2014.
 */
public class PlayScreenActivityCursorLoader extends CursorLoader
{
    private DatabaseQueryHelper databaseQueryHelper;

    public PlayScreenActivityCursorLoader(Context context)
    {
        super(context);
        this.databaseQueryHelper = DatabaseQueryHelper_.getInstance_(context);
    }

    @Override
    public Cursor loadInBackground()
    {
        return databaseQueryHelper.getCursorImagesPath();
    }
}
