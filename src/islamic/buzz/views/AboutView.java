package islamic.buzz.views;

import com.bitstacksolutions.R;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutView  extends LinearLayout
{

    public AboutView(Context context)
    {
	super(context);
	LayoutInflater.from(getContext()).inflate(R.layout.about, this);
	TextView  eybSolutionsLinkText =(TextView) findViewById(R.id.id_about_eybSolutionsLinkText);
	eybSolutionsLinkText.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
