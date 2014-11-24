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

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.data.ImageViewCell;
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
//    private final static String IMAGE_PREFIX = "cars/car";
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

    public BuildPlayFieldImpl(int rows, int column, ArrayList<String> imageUrlList, Context context)
    {
        this.rows = rows;
        this.column = column;
        this.context = context;
        this.imageUrlList = imageUrlList;

        firstImagesSet = new String[getCellsCount()];
        secondImagesSet = new String[getCellsCount()];

        imageUrlList.toArray(firstImagesSet);
    }

    @Override
    public void buildPlayField(final ViewGroup viewTop, final ViewGroup viewBot)
    {
		viewTop.removeAllViewsInLayout();
		viewBot.removeAllViewsInLayout();

		viewBot.setVisibility(View.INVISIBLE);

//    	generateRandomImagesUrl();

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
        imageView.setPadding(10,10,10,10);
        imageView.setBackground(context.getResources().getDrawable(R.drawable.image));
		imageView.setImageDrawable(ImagesUtils.loadDrawableFromAsset(context, imageUrlList, position));
        imageView.setTag(new ImageViewCell(position,imageUrlList.get(position)));
		imageView.setOnClickListener(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
                        //Если выбрана верхняя и нижняя не свободна
						if (isMovingBotNotAvailable(imageView))
						{
//                            imageViewTopId = 0;
//                            imageViewBotId = imageView.getId();
//                            imageView.setActivated(true);
							return;
						}


                        //Если с картинкой
						if (!imageView.isCellFree())
						{
							if (imageViewBotId != 0)
							{
								ImageView oldSelectedImage = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
								oldSelectedImage.setActivated(false);
                                imageViewTopId = 0;
							}
                            //Запоминаем для перемещения
							imageView.setActivated(true);
                            imageView.setSelected(true);
							imageViewBotId = imageView.getId();
                            return;
						}

                        //Если верхняя выбрана и нижняя свободна
						if (isMovingBotAvailable(imageView))
						{
                            //Перемещаем
                            moveImageBot(imageView);
						}
					}
				});
		return imageView;
	}

	private ImageView getImageWithQuestion(int index)
	{
		final SquareImageView imageView = new SquareImageView(context);
		imageView.setId(index + 1000);
        imageView.setPadding(10,10,10,10);
        imageView.setBackground(context.getResources().getDrawable(R.drawable.image));
		imageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));
        imageView.setTag(new ImageViewCell(position, null));
		imageView.setOnClickListener(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
                        //Если нижняя выбрана и верхняя свободна
                        if (isMovingTopAvailable(imageView))
                        {
                            //Пемещяем
                            moveImageTop(imageView);
                            return;
                        }

                        //Если выбрана нижняя и верхняя не свободна
						if (isMovingTopNotAvailable(imageView))
						{
//                            ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
//                            removeImageView.setActivated(false);
//                            imageViewBotId = 0;
//                            imageViewTopId =imageView.getId();
//                            imageView.setActivated(true);
							return;
						}

                        //Если с картинкой
						if (imageView.isCellFree())
						{
                            if (imageViewTopId != 0)
                            {
                                ImageView oldSelectedImage = ((ImageView) ((Activity) context).findViewById(imageViewTopId));
                                oldSelectedImage.setSelected(false);
                            }

                            //Запоминаем для перемещения
                            imageView.setSelected(true);
							imageViewTopId = imageView.getId();
						}
					}
				});

		return imageView;
	}


    public void moveImageTop(ImageView imageView)
    {
        ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewBotId));
        removeImageView.setSelected(false);
        imageView.setImageDrawable(removeImageView.getDrawable());
        imageView.setActivated(true);

        switchTagTop((ImageViewCell) removeImageView.getTag(), (ImageViewCell) imageView.getTag());

        removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, SELECT_IMAGE_PREFIX));

        imageViewBotId = 0;
    }

    public void moveImageBot(ImageView imageView)
    {
        ImageView removeImageView = ((ImageView) ((Activity) context).findViewById(imageViewTopId));
        removeImageView.setSelected(false);
        imageView.setImageDrawable(removeImageView.getDrawable());

        removeImageView.setImageDrawable(ImagesUtils.loadBackDrawableFromAsset(context, BACK_IMAGE_PREFIX));
        removeImageView.setActivated(false);

        imageView.setActivated(false);
        imageViewTopId = 0;

        switchTagBot((ImageViewCell) removeImageView.getTag(), (ImageViewCell) imageView.getTag());
    }

    public void switchTagTop(ImageViewCell imageViewCellBot, ImageViewCell imageViewCellTop)
    {
        imageViewCellTop.setImagePath(imageViewCellBot.getImagePath());

        secondImagesSet[imageViewCellTop.getId()] = imageViewCellTop.getImagePath();
    }

    public void switchTagBot(ImageViewCell imageViewCellTop, ImageViewCell imageViewCellBot)
    {
        secondImagesSet[imageViewCellTop.getId()] = null;
        imageViewCellBot.setImagePath(imageViewCellTop.getImagePath());
    }

    public boolean isMovingBotAvailable(SquareImageView imageView)
    {
        return isTopImageSelected() && imageView.isCellFree();
    }

    public boolean isMovingTopAvailable(SquareImageView imageView)
    {
        return isBotImageSelected() && !imageView.isCellFree();
    }

    public boolean isMovingBotNotAvailable(SquareImageView imageView)
    {
        return isTopImageSelected() && !imageView.isCellFree();
    }

    public boolean isMovingTopNotAvailable(SquareImageView imageView)
    {
        return isBotImageSelected() && imageView.isCellFree();
    }

    public boolean isBotImageSelected()
    {
        return imageViewBotId != 0;
    }

    public boolean isTopImageSelected()
    {
        return imageViewTopId != 0;
    }

    public boolean isCellFree(ImageView imageView)
    {
        return imageView.isActivated();
    }

//    public void generateRandomImagesUrl()
//    {
//        ArrayList<String> allImagesUrlList = ImagesUtils.getAllRandomImagesUrl(IMAGE_PREFIX);
//
//        imageUrlList = new ArrayList<String>();
//        imageUrlList.addAll(allImagesUrlList.subList(0, getCellsCount()));
//        firstImagesSet=imageUrlList.toArray(firstImagesSet);
//    }

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
