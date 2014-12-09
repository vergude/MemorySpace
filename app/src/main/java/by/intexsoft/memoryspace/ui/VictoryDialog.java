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
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.util.ActivityRunner;
import by.intexsoft.memoryspace.util.GameTimer;
import by.intexsoft.memoryspace.view.VictoryDialogListener;

/**
 * Created by Евгений on 25.11.2014.
 */

public class VictoryDialog extends DialogFragment
{
    private VictoryDialogListener victoryDialogListener;
    private String gameTime;
    private long score;

    public VictoryDialog(String gameTime,VictoryDialogListener victoryDialogListener, long score)
    {
        this.victoryDialogListener = victoryDialogListener;
        this.gameTime = gameTime;
        this.score = score;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.victory_dialog_fragment, null);
        v.findViewById(R.id.imageRepeatGame).setOnClickListener(repeatClick);
        v.findViewById(R.id.imageBackMenu).setOnClickListener(menuClick);
        ((TextView) v.findViewById(R.id.gameTime)).setText("Time : " + gameTime);
        ((TextView) v.findViewById(R.id.scoreText)).setText("Score : " + score);
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
            victoryDialogListener.onRepeatGame();
        }
    };

    View.OnClickListener menuClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            victoryDialogListener.onBackMenu();
        }
    };
}
