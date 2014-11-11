package by.intexsoft.memoryspace.ui.activity;

import android.app.Activity;

import by.intexsoft.memoryspace.R;
import by.intexsoft.memoryspace.util.ActivityRunner;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by anastasya.konovalova on 04.11.2014.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity
{

    @Click(R.id.button_1x2)
    void onButtonSize2Clicked()
    {
        startPlayScreen(1, 2);
    }

    @Click(R.id.button_1x3)
    void onButtonSize3Clicked()
    {
        startPlayScreen(1, 3);
    }

    @Click(R.id.button_2x2)
    void onButtonSize4Clicked()
    {
        startPlayScreen(2, 2);
    }

    @Click(R.id.button_2x3)
    void onButtonSize6Clicked()
    {
        startPlayScreen(2, 3);
    }

    @Click(R.id.button_3x4)
    void onButtonSize12Clicked()
    {
        startPlayScreen(3, 4);
    }

    @Click(R.id.button_4x4)
    void onButtonSize16Clicked()
    {
        startPlayScreen(4, 4);
    }

    @Click(R.id.button_5x6)
    void onButtonSize30Clicked()
    {
        startPlayScreen(5, 6);
    }

    public void startPlayScreen(int rows, int column)
    {
        ActivityRunner.playScreenActivity(this).rows(rows).column(column).start();
    }
}

