package com.itsada.icorodinateconvert;

import android.app.Application;
import android.util.Log;

import com.itsada.framework.models.Configuration;

public class App extends Application {

	/* ------------------------------------------------------------------------- */
	/* -----------------------------------Fields-------------------------------- */
	/* ------------------------------------------------------------------------- */
	public static Configuration configuration;
	public static android.content.res.Configuration resourceConfig;

	/* ------------------------------------------------------------------------- */
	/* -----------------------------------Static Fields------------------------- */
	/* ------------------------------------------------------------------------- */

	/* ------------------------------------------------------------------------- */
	/* -----------------------------------Cons Fields--------------------------- */
	/* ------------------------------------------------------------------------- */

	@Override
	public void onCreate() {

		try {

			super.onCreate();

			resourceConfig = new android.content.res.Configuration();

			if (configuration != null)
				resourceConfig.locale = configuration.getLocale();


		} catch (Exception e) {
			Log.e(App.class.getName(), e.getMessage());
		} finally {
			// accountTypeRepository.dispose();
		}
	}


}
