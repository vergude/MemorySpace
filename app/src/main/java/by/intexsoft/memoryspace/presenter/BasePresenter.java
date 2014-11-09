package by.intexsoft.memoryspace.presenter;

import by.intexsoft.memoryspace.view.BaseView;

/**
 * Created by vadim on 07.11.2014.
 */
public interface BasePresenter <T extends BaseView>
{
    void init(T view);

    void onStart();

    void onStop();
}
