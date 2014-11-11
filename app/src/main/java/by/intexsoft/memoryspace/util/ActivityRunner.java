package by.intexsoft.memoryspace.util;

import android.content.Context;

import by.intexsoft.memoryspace.ui.activity.PlayScreenActivity_;

/**
 * Created by anastasya.konovalova on 04.11.2014.
 */
public class ActivityRunner
{
    private ActivityRunner()
    {

    }

    public static PlayScreenActivity_.IntentBuilder_ playScreenActivity(Context context)
    {
        return PlayScreenActivity_.intent(context);
    }
}
