package islamic.buzz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitstacksolutions.R;

public class UmrahPriorToDepartureFragment extends Fragment
{
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.umrah_prior_departure, container, false);
	return v; 
    }

}
