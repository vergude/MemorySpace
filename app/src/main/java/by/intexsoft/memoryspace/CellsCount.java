package by.intexsoft.memoryspace;

import by.intexsoft.memoryspace.exception.ButtonClickException;

/**
 * Created by vadim on 15.11.2014.
 */
public enum  CellsCount
{

    GAME_FIELD_1X2(R.id.button_1x2,1,2),
    GAME_FIELD_1X3(R.id.button_1x3,1,3),
    GAME_FIELD_2X2(R.id.button_2x2,2,2),
    GAME_FIELD_2X3(R.id.button_2x3,2,3),
    GAME_FIELD_3X4(R.id.button_3x4,3,4),
    GAME_FIELD_4X4(R.id.button_4x4,4,4),
    GAME_FIELD_5X6(R.id.button_5x6,5,6);

    private int buttonId;
    private int rows;
    private int column;

    CellsCount(int buttonId, int rows, int column)
    {
        this.buttonId=buttonId;
        this.rows=rows;
        this.column=column;

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
