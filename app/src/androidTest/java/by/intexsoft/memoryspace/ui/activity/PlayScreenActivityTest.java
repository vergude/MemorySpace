package by.intexsoft.memoryspace.ui.activity;

import android.view.ViewGroup;
import android.widget.Toast;
import by.intexsoft.memoryspace.presenter.PlayScreenActivityPresenter;
import by.intexsoft.memoryspace.presenter.interactor.BuildPlayField;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.powermock.reflect.Whitebox;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by anastasya.konovalova on 10.11.2014.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({Toast.class})
public class PlayScreenActivityTest
{
	@Rule
	public PowerMockRule rule = new PowerMockRule();

	PlayScreenActivity mainActivity;

	@Mock
	PlayScreenActivityPresenter mockPresenter;

	@Before
	public void setUp() throws Exception
	{
		mainActivity = PowerMockito.spy(new PlayScreenActivity());
		mockPresenter = mock(PlayScreenActivityPresenter.class);

		Whitebox.setInternalState(mainActivity, "presenter", mockPresenter);
	}

	@Test
	public void testInit()
	{
		mainActivity.init();

		verify(mockPresenter).init(mainActivity);
		verify(mainActivity).initPlayField();
	}

	@Test
	public void testInitPlayField()
	{
		mainActivity.initPlayField();

		verify(mockPresenter).buildPlayField(any(ViewGroup.class), any(ViewGroup.class), any(BuildPlayField.class));
	}
}
