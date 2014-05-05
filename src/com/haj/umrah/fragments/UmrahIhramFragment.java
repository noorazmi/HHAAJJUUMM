package com.haj.umrah.fragments;

import com.haj.umrah.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
