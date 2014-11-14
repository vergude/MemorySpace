package by.intexsoft.memoryspace.presenter.interactor;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import by.intexsoft.memoryspace.util.ImagesUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.robolectric.annotation.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * Created by anastasya.konovalova on 12.11.2014.
 */
@Config(emulateSdk = 18)
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({ImagesUtils.class})
public class BuildPlayFieldImplTest
{
	private static final int ROWS = 3;
	private static final int COLUMNS = 4;

	BuildPlayFieldImpl buildPlayerFieldImpl;

	@Before
	public void setUp() throws Exception
	{
		buildPlayerFieldImpl = new BuildPlayFieldImpl(ROWS, COLUMNS, mock(Context.class));
	}

	@Test
	public void testSetRandomImagesUrl()
	{
		List<String> stubRandomList = new ArrayList<String>();
		for(int i = 0; i < 30; i++)
		{
			stubRandomList.add("Some.jpg");
		}

		PowerMockito.mockStatic(ImagesUtils.class);
		PowerMockito.doReturn(stubRandomList).when(ImagesUtils.class);
		ImagesUtils.getAllRandomImagesUrl(anyString());

		buildPlayerFieldImpl.generateRandomImagesUrl();

		List<String> list = Whitebox.getInternalState(buildPlayerFieldImpl, "imageUrlList");

		assertEquals(ROWS * COLUMNS, list.size());
	}


}