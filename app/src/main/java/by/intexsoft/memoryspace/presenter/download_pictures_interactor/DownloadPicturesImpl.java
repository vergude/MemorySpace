package by.intexsoft.memoryspace.presenter.download_pictures_interactor;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

/**
 * Created by vadim on 27.11.2014.
 */
public class DownloadPicturesImpl implements DownloadPictures
{
    private String url;
    private Context context;

    public DownloadPicturesImpl(Context context, String url)
    {
        this.url = url;
        this.context = context;
    }

    @Override
    public void startDownload()
    {
        if (isNetworkConnected())
        {
            DownloadManager manager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            manager.enqueue(getRequest());
        }

    }

    public DownloadManager.Request getRequest()
    {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);

        String nameOfFile = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url));

        request.setDestinationInExternalFilesDir(context, null, nameOfFile);
        return request;
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            return false;
        }
        return true;
    }
}
