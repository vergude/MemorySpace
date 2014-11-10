package memoryspace.intexsoft.by.memoryspace.ui.activity;

import android.widget.Toast;
import by.intexsoft.memoryspace.ui.activity.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by anastasya.konovalova on 10.11.2014.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({Toast.class})
public class MainActivityTest
{
	@Rule
	public PowerMockRule rule = new PowerMockRule();

	MainActivity mainActivity;

	@Before
	public void setUp() throws Exception {
		mainActivity = PowerMockito.spy(new MainActivity());

	}

	@Test
	public void testGoToHelp() {
		mainActivity.onButtonSize4Clicked();

		verify(mainActivity, never()).finish();
	}
}
