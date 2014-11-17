package by.intexsoft.memoryspace.presenter.interactor;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import by.intexsoft.memoryspace.ui.model.PlayAreaType;
import by.intexsoft.memoryspace.util.ImagesUtils;
import by.intexsoft.memoryspace.view.image_view.SquareImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vadim on 07.11.2014.
 */
public class BuildPlayFieldImpl implements BuildPlayField, OnFinishPlayListener
{
    private final static String IMAGE_PREFIX = "cars/car";
    private final static String BACK_IMAGE_PREFIX = "back/question";
    private final static String SELECT_IMAGE_PREFIX = "back/selected";

    private int rows;
    private int column;

    private  int imageViewBotId = 0;
    private  int imageViewTopId = 0;
    private int position = 0;

    private Context context;
    private int countImages;
    private ArrayList<String> imageUrlList;

    private String[] firstImagesSet;
    private String[] secondImagesSet;

    private String currentTag;
    private int currentId;


    public BuildPlayFieldImpl(int rows, int column, Context context)
    {
        this.rows = rows;
        this.column = column;
        this.context = context;
        firstImagesSet = new String[getCellsCount()];
        secondImagesSet = new String[getCellsCount()];
    }

    @Override
    public void buildPlayField(ViewGroup viewTop, ViewGroup viewBot)
    {

    	generateRandomImagesUrl();

        initPlayField(viewTop, PlayAreaType.TOP);

        Collections.shuffle(imageUrlList);
        initPlayField(viewBot, PlayAreaType.TOP);
    }

    public void initPlayField(ViewGroup view, PlayAreaType playAreaType)
    {
        countImages = 0;
        for (int i = 0; i < rows; i++)
        {
            view.addView(getLayout(playAreaType));
        }
    }

    public LinearLayout getLayout(PlayAreaType playAreaType)
    {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getParams());

        for (int i = 0; i < column; i++)
        {
            position++;
            LinearLayout.LayoutParams params = getParams();
            setMargins(params);

            linearLayout.addView(getImageView(position, playAreaType), params);
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

    public ImageView getImageView(int index, PlayAreaType playAreaType)
    {
        final SquareImageView imageView = new SquareImageView(context);
        try
        {
            if (playAreaType == PlayAreaType.TOP)
            {
                imageView.setId(index + 100);
                imageView.setImageDrawable(ImagesUtils.loadDrawableFromAsset(context, imageUrlList, countImages));
                imageView.setTag(imageUrlList.get(countImages));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                    if(!imageView.isActivated()) {
                        imageViewBotId = imageView.getId();
                        imageView.setActivated(true);
                        currentTag = (String)imageView.getTag();
                    }

                    if(imageViewTopId != 0 && imageView.isActivated())
                    {

                            ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewTopId));
                            imageView.setImageDrawable(removeImageView.getDrawable());
                            try
                            {
                                removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            imageView.setActivated(false);
                            imageViewTopId = 0;
                            secondImagesSet[currentId]=null;
                    }
                    }
                });
            }

            else
            {
                imageView.setId(index + 1000);
                imageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));
                imageView.setTag(countImages);
                imageView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(imageViewBotId != 0)
                        {
                            ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
                            imageView.setImageDrawable(removeImageView.getDrawable());
                            imageView.setActivated(true);
                            secondImagesSet[(Integer)imageView.getTag()] = currentTag;
                            try
                            {
                                removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, SELECT_IMAGE_PREFIX));
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            imageViewBotId = 0;
                        }else {

                            if (imageView.isActivated()) {
                                imageViewTopId = imageView.getId();
                                imageView.setActivated(false);
                                currentId = (Integer)imageView.getTag();
                            }
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
        firstImagesSet=imageUrlList.toArray(firstImagesSet);
    }

    private void handleException(IOException e)
    {
        Log.e("BuildPlayFieldImpl", e.getMessage());
    }

    private int getCellsCount()
    {
        return rows * column;
    }

    @Override
    public String[] getFirstImagesSet()
    {
        return firstImagesSet;
    }

    @Override
    public String[] getSecondImagesSet()
    {
        return secondImagesSet;
    }

}
