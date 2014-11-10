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

	@Click(R.id.button_size_4)
	public void onButtonSize4Clicked()
	{
		ActivityRunner.playScreenActivity(this).start();
	}
}
