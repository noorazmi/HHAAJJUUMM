package islamic.buzz.fragments;

import islamic.buzz.activities.BaseActivityWithSlider;
import islamic.buzz.activities.HomeActivity;
import islamic.buzz.app.BuzzApplication;
import islamic.buzz.controllers.HomeController;
import islamic.buzz.fragment.utility.FragmentOnScreen;
import islamic.buzz.interfaces.listeners.ListItemClickListener;
import islamic.buzz.interfaces.type.ControllerFactory;
import islamic.buzz.util.ConstantValues;
import islamic.buzz.util.Logger;
import islamic.buzz.util.UtilityMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eybsolution.islamic.buzz.R;
import com.google.gson.Gson;

public class HomeFragment extends BaseFragment implements ListItemClickListener {

	public HomeController mHomeController;

	private LinearLayout mProgressBarLayout;

	public static String TAG = HomeFragment.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeController() {
		mHomeController = (HomeController) ControllerFactory.getHomeController(new WeakReference<HomeFragment>(this));
	}

	@Override
	protected void initializeAttributes() {

	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
	}

	@Override
	protected void loadContent() {
		// Convert Dummy JSON into Response VO, Will remove later.
		/*
		 * parseJSONResponse(); displayHomePage();
		 */

	}

	@Override
	protected int intializaLayoutId() {
		return R.layout.updates;
	}

	@Override
	public void updateViewsOnSuccess(Object object) {
	}

	@Override
	public void updateViewsOnFailure(Object object) {

	}

	@Override
	public void onClickItem(int position) {

	}

	private void parseJSONResponse() {
		InputStream inputStream = null;

		BufferedReader reader = null;
		try {
			inputStream = getActivity().getAssets().open("cms.json");
			reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line.trim());
			}
			Gson gsonObject = new Gson();
			// mCmsResponseVo = gsonObject.fromJson(out.toString(),
			// CMSResponseVO.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void loadImage(ImageView view, String url) {
		// LoadImageTask.getInstance().loadImage(url, view, 0, 0);
	}

	@Override
	public boolean onBackPress() {
		return false;
	}

}
