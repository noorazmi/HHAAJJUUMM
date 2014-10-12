package islamic.buzz.views;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eybsolution.islamic.buzz.R;

public class AboutFragment  extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.about, container);
    	TextView  eybSolutionsLinkText =(TextView) view.findViewById(R.id.id_about_eybSolutionsLinkText);
    	eybSolutionsLinkText.setMovementMethod(LinkMovementMethod.getInstance());
    	return view;
    }

}
