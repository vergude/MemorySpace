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

    public static Drawable loadDrawableFromAsset(Context context, ArrayList<String> imageUrlList, int countImages) throws IOException
    {
        InputStream inputStream = context.getAssets().open(imageUrlList.get(countImages));
        Drawable drawable = Drawable.createFromStream(inputStream, null);
        inputStream.close();
        return drawable;
    }
}
