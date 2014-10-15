package islamic.buzz.views;

import islamic.buzz.fragments.ImageGridFragment;

import com.eybsolution.islamic.buzz.R;
import com.eybsolution.islamic.buzz.Home;

import android.content.Context;
import android.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class UpdatesView extends LinearLayout {
	private Home home;
	private String TAG = UpdatesView.class.getSimpleName();

	public UpdatesView(Context context) {
		super(context);
		home = (Home) context;
		// LayoutInflater.from(getContext()).inflate(R.layout.updates_item,
		// this);
		LayoutInflater.from(getContext()).inflate(R.layout.updates, this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.upates_holder);

		if (home.getSupportFragmentManager().findFragmentByTag(TAG) == null) {
			final FragmentTransaction ft = home.getFragmentManager().beginTransaction();
			// ft.add(android.R.id.content, new ImageGridFragment(), TAG);
			ft.add(R.id.upates_holder, new ImageGridFragment(), TAG);

			ft.commit();
		}
	}

	/** Will be called only when the layout is inflated in xml layout file. **/
	public UpdatesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// LayoutInflater.from(getContext()).inflate(R.layout.updates_item,
		// this);
	}

}
