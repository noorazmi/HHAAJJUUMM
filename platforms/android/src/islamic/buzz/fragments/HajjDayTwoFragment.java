package islamic.buzz.fragments;

import com.eybsolution.islamic.buzz.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HajjDayTwoFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.hajj_day_two, container, false);
        return v;
    }

}
