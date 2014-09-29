package islamic.buzz.controllers;

import islamic.buzz.fragments.HomeFragment;
import islamic.buzz.util.Logger;
import islamic.buzz.vo.IValueObject;

import java.lang.ref.WeakReference;

/**
 * Implementation class for Home Controller
 */
public class HomeController extends BaseControllerImpl {

	private WeakReference<HomeFragment> mFragment;

	public HomeController(WeakReference<HomeFragment> fragment) {
		this.mFragment = fragment;
	}

	@Override
	public void onSuccess(IValueObject valueObject) {
		if (mFragment != null && mFragment.get() != null && mFragment.get().getActivity() != null) {
//			if (valueObject instanceof CMSResponseVO) {
//				mFragment.get().updateFragmentOnSuccess(cmsResponseVO);
//			}
		}

	}

	@Override
	public void onFailure(final Error ex) {
		Logger.debug("Error", ex.toString());
		if (mFragment != null && mFragment.get() != null && mFragment.get().getActivity() != null) {
			mFragment.get().getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (mFragment.get() != null && mFragment.get().isVisible() && !mFragment.get().isRemoving()) {
						mFragment.get().updateFragmentOnFailure(ex);
					}
				}
			});
		}
	}

	@Override
	public Error getPayloadError(IValueObject payload) {
		return null;
	}

	@Override
	public long getCacheHeader(IValueObject payload) {
		return 0;
	}

}
