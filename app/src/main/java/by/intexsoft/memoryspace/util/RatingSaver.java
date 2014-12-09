package by.intexsoft.memoryspace.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Евгений on 09.12.2014.
 */

public class RatingSaver
{
    public static final String GAME_RATING = "game_rate";

    private SharedPreferences preferences;


    public void saveText(Context context, long rating) {
        preferences = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(GAME_RATING, rating);
        editor.commit();
    }

    public long loadText(Context context)
    {
        preferences = ((Activity) context).getPreferences(Context.MODE_PRIVATE);

        if(preferences.contains(GAME_RATING))
        {
            return preferences.getLong(GAME_RATING, 0);
        }
        else
        {
            return 0;
        }
    }
}
