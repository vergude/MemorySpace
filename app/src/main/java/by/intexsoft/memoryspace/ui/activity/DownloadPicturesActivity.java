package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.presenter.DownloadPicturesPresenter;
import by.intexsoft.memoryspace.presenter.DownloadPicturesPresenterImpl;
import by.intexsoft.memoryspace.presenter.download_pictures_interactor.DownloadPicturesImpl;
import by.intexsoft.memoryspace.ui.adapters.DownloadPicturesAdapter;
import by.intexsoft.memoryspace.ui.model.DownloadPicturesModel;
import by.intexsoft.memoryspace.view.DownloadPicturesView;

@EActivity(R.layout.activity_download_pictures)
public class DownloadPicturesActivity extends Activity implements DownloadPicturesView
{

    @ViewById(R.id.lvDownloadPictures)
    ListView listView;

    @Bean(DownloadPicturesPresenterImpl.class)
    DownloadPicturesPresenter presenter;

    @Override
    public Activity getContainer()
    {
        return this;
    }

    @AfterViews
    public void init()
    {
        presenter.init(this);
        initListView();
    }

    private void initListView()
    {
        DownloadPicturesAdapter downloadPicturesAdapter = new DownloadPicturesAdapter(
                this, R.layout.download_list_item, DownloadPicturesModel.values());

        listView.setAdapter(downloadPicturesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                DownloadPicturesModel downloadedPictures = (DownloadPicturesModel) adapterView.getItemAtPosition(position);
                presenter.downloadPictures(new DownloadPicturesImpl(getContainer(), downloadedPictures.getUrl()));
            }
        });
    }


}
