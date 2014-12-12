package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import by.intexsoft.memoryspace.db.DatabaseQueryHelper_;
import by.intexsoft.memoryspace.ui.model.CellsCount;
import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.exception.ButtonClickException;
import by.intexsoft.memoryspace.util.ActivityRunner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;


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

    @Click(R.id.menuButton)
    public void startDownloadPicturesActivity()
    {
        ActivityRunner.downloadPicturesActivity(this).start();
    }

    public void haleException(ButtonClickException e)
    {
        Log.e("MainActivity", e.getMessage());
    }
}

