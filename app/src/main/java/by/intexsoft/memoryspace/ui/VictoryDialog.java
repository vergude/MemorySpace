package by.intexsoft.memoryspace.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import by.intexsoft.memoryspace.R;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.victory_dialog_fragment, null);
        v.findViewById(R.id.imageRepeatGame).setOnClickListener(repeatClick);
        v.findViewById(R.id.imageBackMenu).setOnClickListener(menuClick);
        return v;
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    View.OnClickListener repeatClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPool.stop(streamId);
            loaderManager.restartLoader(0, null,loaderCallbacks);
            dismiss();
        }
    };

    View.OnClickListener menuClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPool.stop(streamId);
            soundPool.release();
            getActivity().finish();
            dismiss();
        }
    };
}
