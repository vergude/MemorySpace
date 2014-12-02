package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.data.loader.PlayScreenActivityCursorLoader;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenter;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenterImpl;
import by.intexsoft.memoryspace.presenter.interactor.BuildPlayFieldImpl;
import by.intexsoft.memoryspace.ui.VictoryDialog;
import by.intexsoft.memoryspace.util.GameTimer;
import by.intexsoft.memoryspace.util.ImagesUtils;
import by.intexsoft.memoryspace.view.PlayScreenActivityView;
import by.intexsoft.memoryspace.view.TimerView;
import by.intexsoft.memoryspace.view.VictoryDialogListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by anastasya.konovalova on 04.11.2014.
 */

@EActivity(R.layout.activity_play_screen)
public class PlayScreenActivity extends Activity implements PlayScreenActivityView, LoaderManager.LoaderCallbacks<Cursor>,
        SoundPool.OnLoadCompleteListener, VictoryDialogListener, TimerView
{
    BuildPlayFieldImpl buildPlayField;

    DialogFragment dialogFragment;

    private GameTimer gameTimer;

    private SoundPool soundPool;
    private int soundVictoryId;
    private int soundFailureId;
    private int streamId;

    @Extra
    int rows;

    @Extra
    int column;

    @ViewById
    LinearLayout topLayout;

    @ViewById(R.id.roundTime)
    TextView roundTime;

    @ViewById
    LinearLayout botLayout;

    @Bean(PlayScreenActivityPresenterImpl.class)
    PlayScreenActivityPresenter presenter;

    @AfterViews
    public void init()
    {
        presenter.init(this);
    }

    @AfterViews
    public void startGame()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundPool.setOnLoadCompleteListener(this);
        soundVictoryId = soundPool.load(this,R.raw.victory,1);
        soundFailureId = soundPool.load(this,R.raw.failure,1);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    public void initPlayField(ArrayList<String> imageUrlList)
    {
        buildPlayField = new BuildPlayFieldImpl(rows, column, imageUrlList, this,this);
        presenter.buildPlayField(topLayout, botLayout, buildPlayField);
    }

    @Override
    public Activity getContainer()
    {
        return this;
    }


    public void clearViews()
    {
        topLayout.removeAllViewsInLayout();
        botLayout.removeAllViewsInLayout();
    }

    @Override
    public void showGameResult(String result)
    {
        if(result.equals("win_game"))
        {
            gameTimer.stopTimer();
            streamId = soundPool.play(soundVictoryId,1, 1, 0, 0, 1);
            dialogFragment = new VictoryDialog(gameTimer.getText().toString(),this);
            dialogFragment.show(getFragmentManager(),"dialogVictory");
            dialogFragment.setCancelable(false);
        }
        else
        {
            soundPool.play(soundFailureId,1, 1, 0, 0, 1);
        }
    }

    @Click
    public void buttonCheck()
    {
        presenter.checkFinishGame(buildPlayField);
    }

    @Click
    public void repeatButton()
    {
        gameTimer.stopTimer();
        clearViews();
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle)
    {
        return new PlayScreenActivityCursorLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor)
    {
        if (cursor != null)
        {
            initPlayField(ImagesUtils.getImageUrlListFromCursor(cursor, getCellsCount()));
            presenter.updateImagesWeight(cursor, this, getCellsCount());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader)
    {

    }

    public int getCellsCount()
    {
        return rows * column;
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }


    @Override
    public void onRepeatGame()
    {
        soundPool.stop(streamId);
        getLoaderManager().restartLoader(0, null, this);
        //gameTimer.startTimer();
        dialogFragment.dismiss();
    }

    @Override
    public void onBackMenu() {
        soundPool.stop(streamId);
        soundPool.release();
        this.finish();
    }

    @Override
    public void showTimer() {
        gameTimer = new GameTimer(this,roundTime);
        gameTimer.startTimer();
    }
}


