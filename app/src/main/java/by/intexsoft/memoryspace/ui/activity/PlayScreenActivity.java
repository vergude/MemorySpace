package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;
import android.content.res.Configuration;;
import android.widget.LinearLayout;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenter;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenterImpl;
import by.intexsoft.memoryspace.presenter.interactor.BuildPlayFieldImpl;
import by.intexsoft.memoryspace.view.PlayScreenActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by anastasya.konovalova on 04.11.2014.
 */

@EActivity(R.layout.activity_play_screen)
public class PlayScreenActivity extends Activity implements PlayScreenActivityView
{

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
        presenter.buildPlayField(this, topLayout, botLayout, new BuildPlayFieldImpl(rows, column));
    }

    @Override
    public Activity getContainer()
    {
        return this;
    }
}


