package by.intexsoft.memoryspace.presenter.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.androidannotations.annotations.Click;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.util.ImagesUtils;
import by.intexsoft.memoryspace.view.image_view.SquareImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vadim on 07.11.2014.
 */
public class BuildPlayFieldImpl implements BuildPlayField
{
    private final static String IMAGE_PREFIX = "cars/car";
    private final static String BACK_IMAGE_PREFIX = "back/question";
    private final static String SELECT_IMAGE_PREFIX = "back/selected";

    private int rows;
    private int column;

    private  int imageViewBotId = 0;
    private  int imageViewTopId = 0;
    private int position = 0;
    private boolean clickBot = false;

    private Context context;
    private int countImages;
    private ArrayList<String> imageUrlList;

    private boolean faceImage = true;


    public BuildPlayFieldImpl(int rows, int column, Context context)
    {
        this.rows = rows;
        this.column = column;
        this.context = context;
    }

    @Override
    public void buildPlayField(ViewGroup viewTop, ViewGroup viewBot)
    {
        if (faceImage)
        {
            generateRandomImagesUrl();
        }

        initPlayField(viewTop);
        faceImage = faceImage ? false : true;
        initPlayField(viewBot);
    }

    public void initPlayField(ViewGroup view)
    {
        countImages = 0;
        Collections.shuffle(imageUrlList);
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
            position++;
            LinearLayout.LayoutParams params = getParams();
            setMargins(params);

            linearLayout.addView(getImageView(position), params);
        }
        linearLayout.setGravity(Gravity.CENTER);

        return linearLayout;
    }

    public LinearLayout.LayoutParams getParams()
    {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f);
    }

    public void setMargins(LinearLayout.LayoutParams params)
    {
        params.setMargins(5, 5, 5, 5);
    }

    public ImageView getImageView(int index)
    {
        final SquareImageView imageView = new SquareImageView(context);
        try
        {
            if (faceImage)
            {
                imageView.setId(index + 100);
                imageView.setImageDrawable(ImagesUtils.loadDrawableFromAsset(context, imageUrlList, countImages));

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                    if(!imageView.isActivated()) {
                        imageViewBotId = imageView.getId();
                        imageView.setActivated(true);
                    }

                    if(imageViewTopId != 0 && imageView.isActivated())
                    {

                            ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewTopId));
                            imageView.setImageDrawable(removeImageView.getDrawable());
                            imageViewTopId = 0;
                    }
                    }
                });
            }

            else
            {
                imageView.setId(index + 1000);
                imageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));

                imageView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(imageViewBotId != 0)
                        {   clickBot = true;
                            ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
                            imageView.setImageDrawable(removeImageView.getDrawable());
                            imageView.setActivated(true);
                            try
                            {
                                removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, SELECT_IMAGE_PREFIX));
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            imageViewBotId = 0;
                        }

                        if(imageView.isActivated() && clickBot)
                        {
                            //imageViewTopId = imageView.getId();
                        }
                    }
                });
            }
        }
        catch (IOException e)
        {
            handleException(e);
        }

        countImages++;
        return imageView;
    }

    public void generateRandomImagesUrl()
    {
        ArrayList<String> allImagesUrlList = ImagesUtils.getAllRandomImagesUrl(IMAGE_PREFIX);

        imageUrlList = new ArrayList<String>();
        imageUrlList.addAll(allImagesUrlList.subList(0, getCellsCount()));
    }

    private void handleException(IOException e)
    {
        Log.e("BuildPlayFieldImpl", e.getMessage());
    }

    private int getCellsCount()
    {
        return rows * column;
    }

}
