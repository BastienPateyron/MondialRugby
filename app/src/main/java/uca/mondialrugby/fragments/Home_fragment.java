package uca.mondialrugby.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uca.mondialrugby.R;

/**
 * Created by basti on 2/3/2018.
 */

public class Home_fragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.match_layout,container,false);
        return myView;
    }
}
