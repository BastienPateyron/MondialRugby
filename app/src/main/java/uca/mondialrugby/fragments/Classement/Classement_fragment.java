package uca.mondialrugby.fragments.Classement;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import uca.mondialrugby.R;

/**
 * Created by basti on 3/14/2018.
 */

public class Classement_fragment extends Fragment {
	
	View myView;
	Context context;
	
	public View onCreateView(LayoutInflater inflater, final ViewGroup container) {
		
		myView = inflater.inflate(R.layout.classement_layout, container, false);
		
		context = myView.getContext();

//		Bundle bundle = this.getArguments();
		
		// TODO Liste du classement
	
		return myView;
	}
}
