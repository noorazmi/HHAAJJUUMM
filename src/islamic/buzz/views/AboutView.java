package islamic.buzz.views;

import com.bitstacksolutions.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class AboutView  extends LinearLayout
{

    public AboutView(Context context)
    {
	super(context);
	LayoutInflater.from(getContext()).inflate(R.layout.about, this);
    }

}
