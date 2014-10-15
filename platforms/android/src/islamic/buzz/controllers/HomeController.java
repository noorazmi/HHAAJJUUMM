package islamic.buzz.controllers;

import islamic.buzz.fragments.HomeFragment;
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
	}

	@Override
	public void onFailure(final Error ex) {
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
