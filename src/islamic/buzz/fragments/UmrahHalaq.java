package islamic.buzz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haj.umrah.R;

public class UmrahHalaq extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	return inflater.inflate(R.layout.umrah_halaq, container, false);
    }

}
