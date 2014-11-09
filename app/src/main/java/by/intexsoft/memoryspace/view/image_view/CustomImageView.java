package by.intexsoft.memoryspace.view.image_view;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by vadim on 08.11.2014.
 */
public class CustomImageView extends ImageView
{
    public CustomImageView(Context context)
    {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int height = getMeasuredHeight();
        final int width = getMeasuredWidth();

        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }
}
