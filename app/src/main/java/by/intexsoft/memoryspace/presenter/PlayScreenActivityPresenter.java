package by.intexsoft.memoryspace.presenter;

import android.content.Context;
import android.view.ViewGroup;

import by.intexsoft.memoryspace.presenter.interactor.BuildPlayField;
import by.intexsoft.memoryspace.view.PlayScreenActivityView;

/**
 * Created by vadim on 07.11.2014.
 */
public interface PlayScreenActivityPresenter extends BasePresenter <PlayScreenActivityView>
{
    void buildPlayField(Context contexts,ViewGroup viewTop, ViewGroup viewBot,BuildPlayField interactor);
}