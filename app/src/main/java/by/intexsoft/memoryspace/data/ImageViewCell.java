package by.intexsoft.memoryspace.data;

/**
 * Created by vadim on 22.11.2014.
 */
public class ImageViewCell
{
    private int id;
    private String imagePath;

    public ImageViewCell(int id, String imagePath)
    {
        this.id = id;
        this.imagePath = imagePath;
    }

    public int getId()
    {
        return id;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
}
