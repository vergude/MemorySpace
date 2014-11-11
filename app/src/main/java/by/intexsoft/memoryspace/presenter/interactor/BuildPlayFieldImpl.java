package by.intexsoft.memoryspace.presenter.interactor;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import by.intexsoft.memoryspace.util.ImagesUtils;
import by.intexsoft.memoryspace.view.image_view.SquareImageView;

/**
 * Created by vadim on 07.11.2014.
 */

public class BuildPlayFieldImpl implements BuildPlayField
{
    private final static String IMAGE_PREFIX = "cars/car";

    private int rows;
    private int column;
    private Context context;

    private int countImages;
    private ArrayList<String> imageUrlList;

    public BuildPlayFieldImpl(int rows, int column, Context context)
    {
        this.rows = rows;
        this.column = column;
        this.context = context;
    }

    @Override
    public void buildPlayField(ViewGroup viewTop, ViewGroup viewBot)
    {
        setRandomImagesUrl();

        initPlayField(viewTop);

        Collections.shuffle(imageUrlList);

        initPlayField(viewBot);

    }

    public void initPlayField(ViewGroup view)
    {
        countImages = 0;

        for (int i = 0; i < rows; i++)
        {
            view.addView(getLayout());
        }
    }

    public LinearLayout getLayout()
    {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getParams());

        for (int i = 0; i < column; i++)
        {
            LinearLayout.LayoutParams params = getParams();
            setMargins(params);
            linearLayout.addView(getImageView(), params);
        }
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }

    public LinearLayout.LayoutParams getParams()
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f);
        return params;
    }

    public void setMargins(LinearLayout.LayoutParams params)
    {
        params.setMargins(5, 5, 5, 5);
    }

    public ImageView getImageView()
    {
        SquareImageView imageView = new SquareImageView(context);
        try
        {
            imageView.setImageDrawable(ImagesUtils.loadDrawableFromAsset(context, imageUrlList, countImages));
        }
        catch (IOException e)
        {
            handleException(e);
        }
        countImages++;
        return imageView;
    }

    public void setRandomImagesUrl()
    {
        ArrayList<String> allImagesUrlList = ImagesUtils.getAllRandomImagesUrl(IMAGE_PREFIX);

        imageUrlList = new ArrayList<String>();

        for (int i = 0; i < rows * column; i++)
        {
            imageUrlList.add(allImagesUrlList.get(i));
        }
    }

    private void handleException(IOException e)
    {
        Log.e("BuildPlayFieldImpl", e.getMessage());
    }
}
