package by.intexsoft.memoryspace.presenter;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import java.util.Arrays;

import by.intexsoft.memoryspace.presenter.interactor.BuildPlayField;
import by.intexsoft.memoryspace.presenter.interactor.OnFinishPlayListener;
import by.intexsoft.memoryspace.util.CheckResult;
import by.intexsoft.memoryspace.view.PlayScreenActivityView;

/**
 * Created by vadim on 07.11.2014.
 */

@EBean
public class PlayScreenActivityPresenterImpl implements PlayScreenActivityPresenter
{

    PlayScreenActivityView playScreenActivityView;

    @Override
    public void init(PlayScreenActivityView view)
    {
        this.playScreenActivityView = view;
    }

    @Override
    public void onStart()
    {

    }

    @Override
    public void onStop()
    {

    }

    @Override
    public void buildPlayField(ViewGroup viewTop, ViewGroup viewBot, BuildPlayField interactor)
    {
        interactor.buildPlayField(viewTop, viewBot);
    }

    @Override
    public void checkFinishGame(OnFinishPlayListener onFinishPlayListener)
    {
        playScreenActivityView.showGameResult(CheckResult.getResultGame(
                onFinishPlayListener.getFirstImagesSet(), onFinishPlayListener.getSecondImagesSet()));
    }

}
