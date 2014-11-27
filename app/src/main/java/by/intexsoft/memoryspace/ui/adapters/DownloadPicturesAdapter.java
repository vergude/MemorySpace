package by.intexsoft.memoryspace.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.ui.model.DownloadPicturesModel;

/**
 * Created by vadim on 27.11.2014.
 */
public class DownloadPicturesAdapter extends ArrayAdapter
{
    public DownloadPicturesAdapter(Context context, int id, DownloadPicturesModel[] downloadPictures)
    {
        super(context, id, downloadPictures);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.download_list_item, null);
        }

        DownloadPicturesModel downloadPictures = (DownloadPicturesModel) getItem(position);

        ((TextView) convertView.findViewById(R.id.downloadImagesNamed)).setText(downloadPictures.getName());

        return convertView;
    }
}
