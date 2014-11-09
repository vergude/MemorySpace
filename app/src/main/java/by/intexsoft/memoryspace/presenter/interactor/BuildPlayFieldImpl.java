package by.intexsoft.memoryspace.presenter.interactor;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EBean;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.view.image_view.CustomImageView;

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
        ImageView imageView = null;
        if (rows * column > 3)
        {
            imageView = new ImageView(context);
        }
        else
        {
            imageView = new CustomImageView(context);
        }
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.vopros));
        imageView.setBackgroundColor(context.getResources().getColor(R.color.orange));
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
}
