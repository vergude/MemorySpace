package by.intexsoft.memoryspace.ui.model;

import by.intexsoft.memoryspace.Constant;

/**
 * Created by vadim on 27.11.2014.
 */
public enum DownloadPicturesModel
{


    animals("Animals", Constant.urlAnimals),
    flowers("Flowers", Constant.urlFlowers),
    fruits("Fruits", Constant.urlFruits);

    DownloadPicturesModel(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    public String name;
    public String url;

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }
}
