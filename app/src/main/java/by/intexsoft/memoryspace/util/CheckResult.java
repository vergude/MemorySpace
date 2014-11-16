package by.intexsoft.memoryspace.util;

import java.util.Arrays;

/**
 * Created by vadim on 16.11.2014.
 */
public class CheckResult
{

    public static final String WIN_GAME = "win_game";
    public static final String LOOSE_GAME = "loose_game";
    public static final String EMPTY_CELLS = "empty_cells";

    public static String getResultGame(String[] firstSet, String[] secondSet)
    {
        for (String second : secondSet)
        {
            if (second == null)
            {
                return EMPTY_CELLS;
            }
        }

        if (Arrays.equals(firstSet, secondSet))
        {
            return WIN_GAME;
        }
        else
        {
            return LOOSE_GAME;
        }
    }
}
