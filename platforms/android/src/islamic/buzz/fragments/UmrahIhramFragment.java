package islamic.buzz.fragments;

import com.eybsolution.islamic.buzz.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UmrahIhramFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.from(getActivity()).inflate(R.layout.umrah_ihram, container, false);
    }

}
