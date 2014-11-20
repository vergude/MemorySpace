package by.intexsoft.memoryspace.presenter.interactor;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
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
    public void buildPlayField(final ViewGroup viewTop, final ViewGroup viewBot)
    {
		viewTop.removeAllViewsInLayout();
		viewBot.removeAllViewsInLayout();

		viewBot.setVisibility(View.INVISIBLE);

    	generateRandomImagesUrl();

        initPlayField(viewTop, PlayAreaType.TOP);

		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				showAreaForAnswer(viewTop, viewBot);
			}
		};

		Handler mainHandler = new Handler(context.getMainLooper());
		mainHandler.postDelayed(runnable, 2000);

    }

	public void showAreaForAnswer(ViewGroup viewTop, ViewGroup viewBot)
	{
		viewTop.removeAllViewsInLayout();

		initPlayField(viewTop, PlayAreaType.BOTTOM);

		Collections.shuffle(imageUrlList);
		initPlayField(viewBot, PlayAreaType.TOP);
		viewBot.setVisibility(View.VISIBLE);
	}

    public void initPlayField(ViewGroup view, PlayAreaType playAreaType)
    {
		position = 0;
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
            LinearLayout.LayoutParams params = getParams();
            setMargins(params);

            linearLayout.addView(getImageView(position, playAreaType), params);

			position++;
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
		if (playAreaType == PlayAreaType.TOP)
		{
			return getImageWithPicture(index);
		}
		else
		{
			return getImageWithQuestion(index);
		}
    }

	private ImageView getImageWithPicture(int index)
	{
		final SquareImageView imageView = new SquareImageView(context);
		imageView.setId(index + 100);
		imageView.setImageDrawable(ImagesUtils.loadDrawableFromAsset(context, imageUrlList, position));
		imageView.setTag(imageUrlList.get(position));
		imageView.setOnClickListener(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (imageViewTopId != 0 && !imageView.isActivated())
						{
							return;
						}

						if (!imageView.isActivated())
						{
							if (imageViewBotId != 0)
							{
								ImageView oldSelectedImage = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
								oldSelectedImage.setActivated(false);
							}

							imageView.setActivated(true);
							imageViewBotId = imageView.getId();
							currentTag = (String) imageView.getTag();
						}

						if (imageViewTopId != 0 && imageView.isActivated())
						{

							ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewTopId));
							imageView.setImageDrawable(removeImageView.getDrawable());

							removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));

							imageView.setActivated(false);
							imageViewTopId = 0;
							secondImagesSet[currentId] = null;
						}
					}
				});
		return imageView;
	}

	private ImageView getImageWithQuestion(int index)
	{
		final SquareImageView imageView = new SquareImageView(context);
		imageView.setId(index + 1000);
		imageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));
		imageView.setTag(position);
		imageView.setOnClickListener(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (imageViewBotId != 0 && imageView.isActivated())
						{
							return;
						}

						if (imageViewBotId != 0)
						{
							ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
							imageView.setImageDrawable(removeImageView.getDrawable());
							imageView.setActivated(true);
							secondImagesSet[(Integer) imageView.getTag()] = currentTag;

							removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, SELECT_IMAGE_PREFIX));

							imageViewBotId = 0;
						}
						else
						{

							if (imageView.isActivated())
							{
								imageViewTopId = imageView.getId();
								imageView.setActivated(false);
								currentId = (Integer) imageView.getTag();
							}
						}
					}
				});

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
