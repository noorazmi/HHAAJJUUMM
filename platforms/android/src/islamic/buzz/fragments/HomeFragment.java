package islamic.buzz.fragments;

import islamic.buzz.bitmap.ImageCache.ImageCacheParams;
import islamic.buzz.bitmap.ImageFetcher;
import islamic.buzz.controllers.HomeController;
import islamic.buzz.interfaces.listeners.ListItemClickListener;
import islamic.buzz.interfaces.type.ControllerFactory;
import islamic.buzz.util.AppInfo;
import islamic.buzz.util.Images;
import islamic.buzz.util.Util;

import java.lang.ref.WeakReference;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.eybsolution.islamic.buzz.ImageDetailActivity;
import com.eybsolution.islamic.buzz.R;

public class HomeFragment extends BaseFragment implements ListItemClickListener, AdapterView.OnItemClickListener {

	private static final String IMAGE_CACHE_DIR = "thumbs";

	// private int mImageThumbSize;
	// private int mImageThumbSpacing;
	private ImageAdapter mAdapter;
	private ImageFetcher mImageFetcher;
	private final int LIST_VIEW_DIVIDER_HEIGHT = 20;

	public HomeController mHomeController;

	private LinearLayout mProgressBarLayout;

	public static String TAG = HomeFragment.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		setHasOptionsMenu(true);

		// mImageThumbSize =
		// getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
		// mImageThumbSpacing =
		// getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

		mAdapter = new ImageAdapter(getActivity());

		ImageCacheParams cacheParams = new ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);

		cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of
		// app memory

		// The ImageFetcher takes care of loading images into our ImageView
		// children asynchronously
		// This (second parameter:AppInfo.getScreenWidth()) parameter is
		// important.If the size is not coming according to parameter, uninstall
		// and reinstall the application*/
		mImageFetcher = new ImageFetcher(getActivity(), AppInfo.getScreenWidth());
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
		mImageFetcher.addImageCache(getActivity().getFragmentManager(), cacheParams);

	}

	@Override
	public void onClick(View arg0) {

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

		// final View v = inflater.inflate(R.layout.image_grid_fragment,
		// container, false);
		final ListView mGridView = (ListView) getView().findViewById(R.id.gridView);
		mGridView.setAdapter(mAdapter);
		mGridView.setDivider(this.getResources().getDrawable(R.drawable.transperent_color));
		mGridView.setDividerHeight((int) Util.getPixels(LIST_VIEW_DIVIDER_HEIGHT, getResources()));
		mGridView.setOnItemClickListener(this);
		mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView absListView, int scrollState) {
				// Pause fetcher to ensure smoother scrolling when flinging
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
					mImageFetcher.setPauseWork(true);
				} else {
					mImageFetcher.setPauseWork(false);
				}
			}

			@Override
			public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
		});

		// This listener is used to get the final width of the GridView and then
		// calculate the
		// number of columns and the width of each column. The width of each
		// column is variable
		// as the GridView has stretchMode=columnWidth. The column width is used
		// to set the height
		// of each view so we get nice square thumbnails.
		// mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new
		// ViewTreeObserver.OnGlobalLayoutListener()
		// {
		// @Override
		// public void onGlobalLayout()
		// {
		// if (mAdapter.getNumColumns() == 0)
		// {
		// final int numColumns = (int) Math.floor(mGridView.getWidth() /
		// (mImageThumbSize + mImageThumbSpacing));
		// if (numColumns > 0)
		// {
		// final int columnWidth = (mGridView.getWidth() / numColumns) -
		// mImageThumbSpacing;
		// // mAdapter.setNumColumns(numColumns);
		// mAdapter.setItemHeight(columnWidth);
		// if (BuildConfig.DEBUG)
		// {
		// Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
		// }
		// }
		// }
		// }
		// });

		// return v;

	}

	@Override
	protected void loadContent() {}

	@Override
	protected int intializaLayoutId() {
		return R.layout.image_grid_fragment;
	}

	@Override
	public void updateViewsOnSuccess(Object object) {}

	@Override
	public void updateViewsOnFailure(Object object) {

	}

	@Override
	public void onClickItem(int position) {

	}

	@Override
	public boolean onBackPress() {
		return false;
	}

	@Override
	public void onResume() {
		super.onResume();
		mImageFetcher.setExitTasksEarly(false);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onPause() {
		super.onPause();
		mImageFetcher.setPauseWork(false);
		mImageFetcher.setExitTasksEarly(true);
		mImageFetcher.flushCache();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mImageFetcher.closeCache();
	}
	
	
	@TargetApi(16)
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
	final Intent i = new Intent(getActivity(), ImageDetailActivity.class);
	i.putExtra(ImageDetailActivity.EXTRA_IMAGE, (int) id);
	if (Util.hasJellyBean())
	{
	    // makeThumbnailScaleUpAnimation() looks kind of ugly here as the
	    // loading spinner may
	    // show plus the thumbnail image in GridView is cropped. so using
	    // makeScaleUpAnimation() instead.
	    ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
	    getActivity().startActivity(i, options.toBundle());
	}
	else
	{
	    startActivity(i);
	}
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
	inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
	switch (item.getItemId())
	{
	case R.id.clear_cache:
	    mImageFetcher.clearCache();
	    Toast.makeText(getActivity(), R.string.clear_cache_complete_toast, Toast.LENGTH_SHORT).show();
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    /**
     * The main adapter that backs the GridView. This is fairly standard except
     * the number of columns in the GridView is used to create a fake top row of
     * empty views as we use a transparent ActionBar and don't want the real top
     * row of images to start off covered by it.
     */
    private class ImageAdapter extends BaseAdapter
    {

	private final Context mContext;
	private int mItemHeight = 0;
	// private int mNumColumns = 0;
	// private int mActionBarHeight = 0;
	private LinearLayout.LayoutParams mImageViewLayoutParams;
	LayoutInflater inflater;

	public ImageAdapter(Context context)
	{
	    super();
	    mContext = context;
	    mImageViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    // Calculate ActionBar height
	    // TypedValue tv = new TypedValue();
	    // if
	    // (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
	    // tv, true))
	    // {
	    // mActionBarHeight =
	    // TypedValue.complexToDimensionPixelSize(tv.data,
	    // context.getResources().getDisplayMetrics());
	    // }
	}

	@Override
	public int getCount()
	{
	    // Size + number of columns for top empty row
	    return Images.imageUrls.length;
	}

	@Override
	public Object getItem(int position)
	{
	    return Images.imageUrls[position];
	}

	@Override
	public long getItemId(int position)
	{
	    return position;
	}

	@Override
	public int getViewTypeCount()
	{
	    // Two types of views, the normal ImageView and the top row of empty
	    // views
	    return 2;
	}

	@Override
	public int getItemViewType(int position)
	{
	    // return (position < mNumColumns) ? 1 : 0;
	    return 0;
	}

	@Override
	public boolean hasStableIds()
	{
	    return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	}

}
