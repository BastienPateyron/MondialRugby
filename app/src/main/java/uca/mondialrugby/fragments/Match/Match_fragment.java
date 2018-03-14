package uca.mondialrugby.fragments.Match;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.classes.Match;

/**
 * Created by basti on 3/12/2018.
 */

public class Match_fragment extends Fragment {
	
	private View myView;
	private Context context;
	private Button creerBouton;
	private ArrayList<Match> listMatchPrevu = new ArrayList<>();;
	private ArrayList<Match> listMatchFini = new ArrayList<>();;
	
	
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState)
	{
		myView = inflater.inflate(R.layout.match_layout,container,false);
		
		
		
		// TODO Ajouter bouton CREER MATCH
		creerBouton = myView.findViewById(R.id.matchBoutonNew);
		creerBouton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Match_fragment_ajout match_fragment_ajout = new Match_fragment_ajout();
				((MainActivity) getActivity()).changeFragment(match_fragment_ajout); // Affiche le fragment ajout
			}
		});
		
		// TODO Get all matchs
		
		// TODO Liste matchsPrévus
		
		
		// TODO Liste matchsFini (Avec score non null)
		
		
		
		return myView;
	}
	
}
