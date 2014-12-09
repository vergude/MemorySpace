package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;

import android.util.Log;
import android.view.View;

import by.intexsoft.memoryspace.db.DatabaseQueryHelper_;
import by.intexsoft.memoryspace.ui.model.CellsCount;
import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.exception.ButtonClickException;
import by.intexsoft.memoryspace.util.ActivityRunner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;


/**
 * Created by anastasya.konovalova on 04.11.2014.
 */

@OptionsMenu(R.menu.activity_main_menu)
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity
{

    public void buttonClicked(View view)
    {
        try
        {
            CellsCount cellsCount = CellsCount.getCells(view.getId());
            startPlayScreen(cellsCount.getRows(), cellsCount.getColumn(), cellsCount.getScore());
        }
        catch (ButtonClickException e)
        {
            haleException(e);
        }
    }

    public void startPlayScreen(int rows, int column, int score)
    {
        ActivityRunner.playScreenActivity(this).rows(rows).column(column).score(score).start();
    }

    @AfterViews
    public void fillTable()
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                DatabaseQueryHelper_.getInstance_(getBaseContext()).fillTable();
            }
        };
        runnable.run();
    }

    public void haleException(ButtonClickException e)
    {
        Log.e("MainActivity", e.getMessage());
    }

    @OptionsItem(R.id.downloadImages)
    public void startDownloadPicturesActivity()
    {
        ActivityRunner.downloadPicturesActivity(this).start();
    }
}

