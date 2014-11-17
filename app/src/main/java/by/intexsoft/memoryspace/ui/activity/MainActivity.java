package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import by.intexsoft.memoryspace.ui.model.CellsCount;
import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.exception.ButtonClickException;
import by.intexsoft.memoryspace.util.ActivityRunner;

import org.androidannotations.annotations.EActivity;

/**
 * Created by anastasya.konovalova on 04.11.2014.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity
{

    public void buttonClicked(View view)
    {
        try
        {
            CellsCount cellsCount = CellsCount.getCells(view.getId());
            startPlayScreen(cellsCount.getRows(), cellsCount.getColumn());
        }
        catch (ButtonClickException e)
        {
            haleException(e);
        }
    }

    public void startPlayScreen(int rows, int column)
    {
        ActivityRunner.playScreenActivity(this).rows(rows).column(column).start();
    }

    public void haleException(ButtonClickException e)
    {
        Log.e("MainActivity", e.getMessage());
    }
}

