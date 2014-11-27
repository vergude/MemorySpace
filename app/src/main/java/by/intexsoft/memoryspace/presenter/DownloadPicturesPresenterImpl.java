package by.intexsoft.memoryspace.presenter;

import org.androidannotations.annotations.EBean;

import by.intexsoft.memoryspace.presenter.download_pictures_interactor.DownloadPictures;
import by.intexsoft.memoryspace.ui.activity.DownloadPicturesActivity;

/**
 * Created by vadim on 27.11.2014.
 */
@EBean
public class DownloadPicturesPresenterImpl implements DownloadPicturesPresenter
{
    DownloadPicturesActivity downloadPicturesActivity;

    @Override
    public void init(DownloadPicturesActivity downloadPicturesActivity)
    {
        this.downloadPicturesActivity = downloadPicturesActivity;
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
    public void downloadPictures(DownloadPictures downloadPictures)
    {
        downloadPictures.startDownload();
    }
}
