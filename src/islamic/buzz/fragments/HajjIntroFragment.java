package islamic.buzz.fragments;


import com.haj.umrah.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HajjIntroFragment extends Fragment
{
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.hajj_intro, container, false);
	//TextView tv = (TextView)v.findViewById(R.id.virtues_of_umrah_desc_textview);
	//tv.setText(Html.fromHtml("<p><ol><li>Coffee</li><li>Milk</li></ol></p>"));
	return v; 
    }

}

