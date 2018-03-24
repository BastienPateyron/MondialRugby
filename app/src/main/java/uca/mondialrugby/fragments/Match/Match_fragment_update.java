package uca.mondialrugby.fragments.Match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uca.mondialrugby.R;

/**
 * Created by basti on 3/22/2018.
 */

public class Match_fragment_update extends Fragment {
	View myView;
	
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		// TODO
		
		myView = inflater.inflate(R.layout.match_layout_update, container, false);
		
		
		return myView;
	}
	
}