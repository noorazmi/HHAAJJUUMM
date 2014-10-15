package islamic.buzz.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * Base fragment that will be extended by all the fragments to make sure all the
 * fragments are maintaining order of intialization & all the fragments should
 * implement common methods.
 * <p>
 * Order of initialization is 1. Fragment attributes. 2. Fragment views. 3.
 * Fragment controller. 4. Load content of fragment. Also it is implementing any
 * fragment's view click listener.
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {

	// Root view for this fragment
	private View mFragmentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mFragmentView = inflater.inflate(getLayoutId(), container, false);
		return mFragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initController();
		initFields(savedInstanceState);

	}

	/**
	 * Initialize fragment controller.
	 */
	protected abstract void initController();

	/**
	 * Initialize fragment fields.
	 */
	protected abstract void initFields(Bundle bundle);

	/**
	 * @return returns layout id of the fragment.
	 */
	protected abstract int getLayoutId();

	public abstract boolean onBackPress();

	/**
	 * @return root view of the fragment.
	 */
	protected View getFragmentView() {
		return mFragmentView;
	}
}
