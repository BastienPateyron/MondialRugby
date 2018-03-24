package uca.mondialrugby.fragments.Match;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.JouerDAO;
import uca.mondialrugby.bdd.MatchDAO;
import uca.mondialrugby.bdd.StadeDAO;
import uca.mondialrugby.classes.Jouer;
import uca.mondialrugby.classes.Match;
import uca.mondialrugby.classes.Stade;
import uca.mondialrugby.fragments.Stade.Stade_fragment_home;

import static android.content.ContentValues.TAG;
import static java.sql.Types.NULL;

/**
 * Created by basti on 3/22/2018.
 */

public class Match_fragment_update extends Fragment {
	View myView;
	Context context = new MainActivity().getsContext();
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		
		myView = inflater.inflate(R.layout.match_layout_update, container, false);
		
		int idMatch = NULL;
		Bundle bundle = this.getArguments();
		
		if (bundle != null) {
			idMatch = Integer.valueOf(bundle.get("idMatch").toString());
		}
		// Recuperation du match
		MatchDAO matchDAO = new MatchDAO(context);
		final Match match = matchDAO.retrieveMatch(context, idMatch);
		Log.d(TAG, "onCreateView: Match: " + match);
		
		// Recuperation des equipes du match
		final JouerDAO jouerDAO = new JouerDAO(context);
		final ArrayList<Jouer> jouerArrayList = jouerDAO.getAllJouerOf("MATCHS", idMatch);
		
		
		// Stade
		TextView stade = myView.findViewById(R.id.stadeText);
		stade.setText(match.getStade().getNom());
		
		// Arbitre
		TextView arbitre = myView.findViewById(R.id.arbitreText);
		arbitre.setText(match.getPersonne().toString());
		
		// Domicile - Equipe
		TextView domicileText = myView.findViewById(R.id.equipeDomicileText);
		domicileText.setText(jouerArrayList.get(0).getIdPays().getPays());
		
		// Domicile - Score
		final EditText domicileScore = myView.findViewById(R.id.scoreDomicileInput);
		
		// Exterieur
		TextView exterieurText = myView.findViewById(R.id.equipeExterieurTexte);
		exterieurText.setText(jouerArrayList.get(1).getIdPays().getPays());
		
		// Exterieur - Score
		final EditText exterieurScore = myView.findViewById(R.id.scoreExterieurInput);
		
		// Date
		TextView date = myView.findViewById(R.id.dateMatchText);
		date.setText(match.getDateMatch());
		
		// Boutons
		Button updateBouton = myView.findViewById(R.id.matchBoutonPositif);

		updateBouton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Mise à jour du score de l'hote
				jouerArrayList.get(0).setScore(Integer.valueOf(domicileScore.getText().toString()));
				
				// Mise à jour du score de l'invite
				jouerArrayList.get(1).setScore(Integer.valueOf(exterieurScore.getText().toString()));

				// Pour chacune des 2 equipes, on met son score a jour
				for (Jouer equipes : jouerArrayList){
					Log.d(TAG, "onClick: Equipe: " + equipes.toString());
					jouerDAO.updateJouer(equipes);
				}
				
				Toast.makeText(context, "Match mis à jour", Toast.LENGTH_SHORT).show();
				MainActivity.closekeyboard(getContext(), myView);
				((MainActivity) getActivity()).changeFragment(new Match_fragment());
			}
		});

		Button cancelBouton = myView.findViewById(R.id.matchBouttonNegatif);
		
		cancelBouton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.closekeyboard(getContext(), myView);
				((MainActivity) getActivity()).changeFragment(new Match_fragment());
			}
		});
		
		return myView;
	}
	
}