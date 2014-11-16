package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenter;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenterImpl;
import by.intexsoft.memoryspace.presenter.interactor.BuildPlayFieldImpl;
import by.intexsoft.memoryspace.view.PlayScreenActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

/**
 * Created by anastasya.konovalova on 04.11.2014.
 */

@EActivity(R.layout.activity_play_screen)
public class PlayScreenActivity extends Activity implements PlayScreenActivityView
{
    private final static int SLEEP_DELAY = 5000;

    private BuildPlayFieldImpl buildPlayField;

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
        initPlayField();
    }

    public void initPlayField()
    {
        buildPlayField = new BuildPlayFieldImpl(rows, column, this);
        presenter.buildPlayField(topLayout, botLayout, buildPlayField);
        sleep();
    }

    @Override
    public Activity getContainer()
    {
        return this;
    }

    @UiThread(delay = SLEEP_DELAY)
    void sleep()
    {
        clearViews();
        presenter.buildPlayField(topLayout, botLayout, buildPlayField);
    }

    public void clearViews()
    {
        topLayout.removeAllViewsInLayout();
        botLayout.removeAllViewsInLayout();
    }

    @Override
    public void showGameResult(String result)
    {
        Log.d("end_game", result);
    }

    @Click
    public void buttonCheck()
    {
        presenter.checkFinishGame(buildPlayField);
    }

}


