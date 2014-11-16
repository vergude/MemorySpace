package by.intexsoft.memoryspace.exception;

/**
 * Created by vadim on 15.11.2014.
 */
public class ButtonClickException extends Exception
{
    public static final String ERROR_LOG = "The button isn't registered in CellsCount";

    @Override
    public String getMessage()
    {
        return ERROR_LOG;
    }

}
