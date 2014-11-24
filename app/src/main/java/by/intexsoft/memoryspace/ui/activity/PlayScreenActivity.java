package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.data.loader.PlayScreenActivityCursorLoader;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenter;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenterImpl;
import by.intexsoft.memoryspace.presenter.interactor.BuildPlayFieldImpl;
import by.intexsoft.memoryspace.util.ImagesUtils;
import by.intexsoft.memoryspace.view.PlayScreenActivityView;

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
        SoundPool.OnLoadCompleteListener
{
    private final static int SLEEP_DELAY = 1000;

    BuildPlayFieldImpl buildPlayField;

    private SoundPool soundPool;
    int soundVictoryId;

    @Extra
    int rows;

    @Extra
    int column;

    @ViewById
    LinearLayout topLayout;

    @ViewById
    LinearLayout botLayout;

    @Bean(PlayScreenActivityPresenterImpl.class)
    PlayScreenActivityPresenter presenter;

    @AfterViews
    public void init()
    {
        presenter.init(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundPool.setOnLoadCompleteListener(this);
        soundVictoryId = soundPool.load(this,R.raw.victory,1);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    public void initPlayField(ArrayList<String> imageUrlList)
    {
        buildPlayField = new BuildPlayFieldImpl(rows, column, imageUrlList, this);
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
            Toast.makeText(this,"Победа", Toast.LENGTH_LONG).show();

            soundPool.play(soundVictoryId,1, 1, 0, 0, 1);

            clearViews();
            getLoaderManager().restartLoader(0, null, this);
        }
        else
        {
            Toast.makeText(this,"Неправильно пробуй еще!", Toast.LENGTH_LONG).show();
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
}


