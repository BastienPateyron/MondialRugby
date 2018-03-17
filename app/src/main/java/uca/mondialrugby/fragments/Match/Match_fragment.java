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
	private ArrayList<Match> listMatchPrevu = new ArrayList<>();
	private ArrayList<Match> listMatchFini = new ArrayList<>();
	
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
		
		// TODO Get all matchs
		MatchDAO matchDAO = new MatchDAO(getContext());
		ArrayList<Match> matchFinisListe = matchDAO.getAllMatch(getContext());
		
		
		ListView listView = myView.findViewById(R.id.matchPrevuList);
		
		// Liste centrée
		ArrayAdapter<String> ad = new ArrayAdapter<String>(getContext(), R.layout.text_center_layout, R.id.textItem);
		ad.addAll(matchFinisListe.toString());
		listView.setAdapter(ad);
		
		
		Log.d(TAG, "onCreateView: matchs affichés");
		
		listView.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Ouvrir dialog modification ?
					}
				}
		
		);
		
		// TODO Liste matchsPrévus
		ArrayList<Match> matchPrevusListe = new ArrayList<>();
		
		// On parcours la liste des matchs finis (qui contient pour l'instant tout les matchs) et on supprime les matchs qui n'ont pas  encore de score pour les placer dans la liste des matchs Prévus
		for (Match m : matchFinisListe) {
			
			// TODO REPRISE !!! Lire ci dessous
			// Récupérer les 2 occurences de JOUER qui font référence à ce match
			// Faire une requete spécifique pour récupérer
		}
		
		
		// TODO Liste matchsFini (Avec score non null)
		
		
		return myView;
	}
	
}
