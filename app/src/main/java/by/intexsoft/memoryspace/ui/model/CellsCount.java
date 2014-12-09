package by.intexsoft.memoryspace.ui.model;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.exception.ButtonClickException;

/**
 * Created by vadim on 15.11.2014.
 */
public enum  CellsCount
{

    GAME_FIELD_1X2(R.id.button_1x2, 1, 2, 300),
    GAME_FIELD_1X3(R.id.button_1x3, 1, 3, 600),
    GAME_FIELD_2X2(R.id.button_2x2, 2, 2, 900),
    GAME_FIELD_2X3(R.id.button_2x3, 2, 3, 1200),
    GAME_FIELD_3X4(R.id.button_3x4, 3, 4, 1500),
    GAME_FIELD_4X4(R.id.button_4x4, 4, 4, 1800),
    GAME_FIELD_5X6(R.id.button_5x6, 5, 6, 2100);

    private int buttonId;
    private int rows;
    private int column;
    private int score;

    public int getScore() {
        return score;
    }

    CellsCount(int buttonId, int rows, int column, int score)
    {
        this.buttonId=buttonId;
        this.rows=rows;
        this.column=column;
        this.score = score;


    }

    public int getRows()
    {
        return rows;
    }

    public int getColumn()
    {
        return column;
    }

    public static CellsCount getCells(int buttonId) throws ButtonClickException
    {
        for(CellsCount cellsCount:CellsCount.values())
        {
            if(cellsCount.buttonId == buttonId)
            {
                return cellsCount;
            }
        }

        throw new ButtonClickException();
    }

}
