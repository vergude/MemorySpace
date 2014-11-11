package by.intexsoft.memoryspace.presenter.interactor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.view.image_view.SquareImageView;

/**
 * Created by vadim on 07.11.2014.
 */

public class BuildPlayFieldImpl implements BuildPlayField
{
    private int rows;
    private int column;

    public BuildPlayFieldImpl(int rows, int column)
    {
        this.rows = rows;
        this.column = column;
    }

    @Override
    public void buildPlayField(Context contexts, ViewGroup viewTop, ViewGroup viewBot)
    {
        for (int i = 0; i < rows; i++)
        {
            viewTop.addView(getLayout(contexts, column));
            viewBot.addView(getLayout(contexts, column));
        }
    }

    public LinearLayout getLayout(Context context, int countImages)
    {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getParams());

        for (int i = 0; i < countImages; i++)
        {
            LinearLayout.LayoutParams params = getParams();
            setMargins(params);
            linearLayout.addView(getImageView(context), params);
        }
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }

    public ImageView getImageView(Context context)
    {
        SquareImageView imageView = new SquareImageView(context);

//        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.question));
//        imageView.setBackgroundColor(context.getResources().getColor(R.color.orange));
        loadImageFromAsset(context, imageView);
        return imageView;
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

    public void loadImageFromAsset(Context context, SquareImageView imageView)
    {
        try
        {
            InputStream ims = context.getAssets().open("car_4.jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        }
        catch (IOException ex)
        {
            return;
        }

    }
}
