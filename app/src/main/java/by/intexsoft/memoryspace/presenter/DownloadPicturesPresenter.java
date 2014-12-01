package by.intexsoft.memoryspace.presenter;

import by.intexsoft.memoryspace.presenter.download_pictures_interactor.DownloadPictures;
import by.intexsoft.memoryspace.ui.activity.DownloadPicturesActivity;

/**
 * Created by vadim on 27.11.2014.
 */
public interface DownloadPicturesPresenter extends BasePresenter<DownloadPicturesActivity>
{
    void downloadPictures(DownloadPictures downloadPictures);
}
