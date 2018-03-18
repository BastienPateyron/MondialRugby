package uca.mondialrugby.fragments.Match;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.MatchDAO;
import uca.mondialrugby.classes.Match;

import static android.content.ContentValues.TAG;

/**
 * Created by basti on 3/12/2018.
 */

public class Match_fragment extends Fragment {
	
	private View myView;
	private Button creerBouton;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.match_layout, container, false);
		
		
		// TODO Ajouter bouton CREER MATCH
		creerBouton = myView.findViewById(R.id.matchBoutonNew);
		creerBouton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Match_fragment_ajout match_fragment_ajout = new Match_fragment_ajout();
				((MainActivity) getActivity()).changeFragment(match_fragment_ajout); // Affiche le fragment ajout
			}
		});
		
		
		
		
		/* Liste Matchs Finis */
		MatchDAO matchDAO = new MatchDAO(getContext());
		ArrayList<Match> matchFinisListe = matchDAO.getAllMatchFini(getContext());
		ListView matchFiniListView = myView.findViewById(R.id.matchFiniList);
		final ArrayAdapter<Match> adapterFini = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, matchFinisListe);
		matchFiniListView.setAdapter(adapterFini);
		
		Log.d(TAG, "onCreateView: matchs Finis affichés");
		
		// TODO Définir le comportement d'un click sur un élément de la liste
		matchFiniListView.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Ouvrir dialog modification ?
					}
				}
		);
		
		/* Liste matchsPrévus */
		ArrayList<Match> matchPrevusListe = matchDAO.getAllMatchPrevu(getContext());
		ListView matchPrevuListView = myView.findViewById(R.id.matchPrevuList);
		final ArrayAdapter<Match> adapterPrevu = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, matchPrevusListe);
		matchPrevuListView.setAdapter(adapterPrevu);
		
		Log.d(TAG, "onCreateView: matchs Finis affichés");
		
		// TODO Définir le comportement d'un click sur un élément de la liste
		matchPrevuListView.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Ouvrir dialog modification ?
					}
				}
		);
		
		
		
		return myView;
	}

}
