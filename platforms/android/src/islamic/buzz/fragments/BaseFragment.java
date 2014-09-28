
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

    private int layoutId;

    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(intializaLayoutId(), null);
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeAttributes();
        initializeViews(savedInstanceState);
        initializeController();
        loadContent();
    }

    /**
     * Initialize fragment controller.
     */
    protected abstract void initializeController();

    /**
     * Initialize fragment attributes.
     */
    protected abstract void initializeAttributes();

    /**
     * Initialize fragment views.
     */
    protected abstract void initializeViews(Bundle savedInstanceState);

    /**
     * Load initial content of the fragment.
     */
    protected abstract void loadContent();

    /**
     * @return returns layout id of the fragment.
     */
    protected abstract int intializaLayoutId();

    public abstract boolean onBackPress();

    /**
     * @return root view of the fragment.
     */
    protected View getFragmentView() {
        return mFragmentView;
    }

    public final void updateFragmentOnSuccess(final Object object) {
        if (getActivity() != null && (!getActivity().isFinishing())) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    updateViewsOnSuccess(object);
                }
            });
        }
    }

    public final void updateFragmentOnFailure(final Object object) {
        if (getActivity() != null && (!getActivity().isFinishing())) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    updateViewsOnFailure(object);
                }
            });
        }
    }

    public abstract void updateViewsOnSuccess(Object object);

    public abstract void updateViewsOnFailure(Object object);
}
