package by.intexsoft.memoryspace.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.media.SoundPool;
import android.os.Bundle;

import by.intexsoft.memoryspace.util.ActivityRunner;

/**
 * Created by Евгений on 25.11.2014.
 */
public class VictoryDialog extends DialogFragment
{
    private SoundPool soundPool;
    private LoaderManager loaderManager;
    private int streamId;
    private LoaderManager.LoaderCallbacks loaderCallbacks;

    public VictoryDialog(SoundPool soundPool, LoaderManager loaderManager, int streamId, LoaderManager.LoaderCallbacks loaderCallbacks)
    {
        this.soundPool = soundPool;
        this.loaderManager = loaderManager;
        this.streamId = streamId;
        this.loaderCallbacks = loaderCallbacks;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Победа!")
                .setNegativeButton("Перейти в меню", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        soundPool.stop(streamId);
                        getActivity().finish();
                    }
                })
                .setPositiveButton("Заново", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        soundPool.stop(streamId);
                        loaderManager.restartLoader(0, null,loaderCallbacks);
                    }
                })
                .setMessage("Вы победили!");
        return adb.create();
    }
}
