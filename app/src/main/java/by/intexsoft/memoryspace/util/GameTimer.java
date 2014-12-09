package by.intexsoft.memoryspace.util;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Евгений on 27.11.2014.
 */

public class GameTimer extends TextView
{
    private long time = 0;

    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    private Timer  timer;
    private Context context;

    private TextView textView;

    private GameTimerTask gameTimerTask;

    private int maxScore;
    private long score;


    public GameTimer(Context context,TextView textView, int maxScore)
    {
        super(context);
        this.maxScore = maxScore;
        this.context = context;
        this.textView = textView;
    }

    public void startTimer()
    {
        textView.setText("0:00");
        score = maxScore;
        gameTimerTask = new GameTimerTask();
        timer = new Timer();
        timer.schedule(gameTimerTask, 1000, 1000);
    }

    public void stopTimer()
    {
        timer.cancel();
        timer = null;
        time = 0;
        setText(simpleDateFormat.format(calendar.getTime()));
    }

    public long getScore()
    {
        if(score<0)
        {
            score = 0;
        }
        return score;
    }


    class GameTimerTask extends TimerTask
    {
        @Override
        public void run() {
            calendar  = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat(
                    "m:ss", Locale.getDefault());

            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    time += 1000;
                    score -= 5;
                    calendar.setTimeInMillis(time);
                    textView.setText(simpleDateFormat.format(calendar.getTime()));
                }
            });
        }
    }
}
