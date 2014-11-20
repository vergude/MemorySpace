package by.intexsoft.memoryspace.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import by.intexsoft.memoryspace.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by vadim on 11.11.2014.
 */
public class ImagesUtils
{
    public static ArrayList<String> getAllRandomImagesUrl(String prefix)
    {
        ArrayList<String> allIRandomImagesUrlList = new ArrayList<String>();
        for (int i = 0; i < Constant.MAX_IMAGES_COUNT; i++)
        {
            allIRandomImagesUrlList.add(prefix + i + ".jpg");
        }
        Collections.shuffle(allIRandomImagesUrlList);

		return allIRandomImagesUrlList;
    }

    public static Drawable loadDrawableFromAsset(Context context, ArrayList<String> imageUrlList, int countImages)
    {
		//TODO Load default image from resource with WARNING
		Drawable drawable = null;
		try
		{
			InputStream inputStream = context.getAssets().open(imageUrlList.get(countImages));
			drawable = Drawable.createFromStream(inputStream, null);
			inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

        return drawable;
    }

    public static Drawable loadBackDrawableFromAsset(Context context, String prefixBack)
    {
		Drawable drawable = null;
		try
		{

			InputStream inputStream = context.getAssets().open(prefixBack + ".jpg");
			drawable = Drawable.createFromStream(inputStream, null);
			inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

        return drawable;
    }
}
