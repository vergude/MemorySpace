package by.intexsoft.memoryspace.util;

import android.content.Context;

import by.intexsoft.memoryspace.ui.activity.DownloadPicturesActivity_;
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

    public static DownloadPicturesActivity_.IntentBuilder_ downloadPicturesActivity(Context context)
    {
        return DownloadPicturesActivity_.intent(context);
    }
}
