package islamic.buzz.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eybsolution.islamic.buzz.R;

public class HajjTawafulwidaFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	return inflater.inflate(R.layout.hajj_tawaful_wida, container, false);
    }

}
