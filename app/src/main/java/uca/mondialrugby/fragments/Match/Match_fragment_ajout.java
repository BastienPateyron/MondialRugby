package uca.mondialrugby.fragments.Match;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import uca.mondialrugby.classes.Match;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Stade;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.MatchDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.bdd.StadeDAO;

import static android.content.ContentValues.TAG;

/**
 * Created by watson on 11/03/2018.
 */

public class Match_fragment_ajout extends Fragment {
	// private List<Personne> listPersonne = new ArrayList<Personne>();     // Pour choisir l'arbitre
	//private List<Stade> listStade = new ArrayList<Stade>();
	private EditText dateMatch;
	private Calendar myCalendar = Calendar.getInstance();
	
	private int idArbitre;
	private int idStade;
	private int idMatch;
	private View myView;
	private Context context;
	private String date_match_default;
	
	
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		
		myView = inflater.inflate(R.layout.match_layout_form, container, false);
		
		context = myView.getContext();

//		Bundle bundle = this.getArguments();
		
		// Spinner Stade
		final Spinner spinnerStade = myView.findViewById(R.id.stadeSpinner);
		final ArrayList<Stade> listStade;
		final StadeDAO stadeDAO = new StadeDAO(context);
		listStade = stadeDAO.getAllStade();
		
		final ArrayAdapter<Stade> adapterStade = new ArrayAdapter<Stade>(context, android.R.layout.simple_spinner_item, listStade);
		adapterStade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerStade.setAdapter(adapterStade);
		
		
		spinnerStade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (adapterStade.getItem(position).getId() == -1) {
				
				} else idStade = adapterStade.getItem(position).getId();
			}
			
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
		// Spinner Arbitre
		final Spinner spinnerArbitre = myView.findViewById(R.id.arbitreSpinner);
		final ArrayList<Personne> listeArbitres;
		final PersonneDAO personneDAO = new PersonneDAO(context);
		listeArbitres = personneDAO.getAllArbitres(context);
		
		final ArrayAdapter<Personne> adapterArbitres = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listeArbitres);
		adapterArbitres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerArbitre.setAdapter(adapterArbitres);
		
		
		spinnerArbitre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (adapterArbitres.getItem(position).getId() == -1) {
				
				} else idArbitre = adapterArbitres.getItem(position).getId();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
		
		// TODO 1- Spinner DOMICILE
		
		
		// TODO 2- SPINNER EXTERIEUR
		
		
		
		//DATE PICKER SETTINGS
		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}
		};
		//DATE Match
		
		
		dateMatch = (EditText) myView.findViewById(R.id.dateMatchInput);
		final DatePickerDialog datePicker_match = new DatePickerDialog(context, R.style.DatePicker, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
		dateMatch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				date_match_default = "DATE_MATCH";     // Servira peut être
				datePicker_match.show();
			}
		});
		
		
		//BUTTON POSITIF
		Button buttonPos = myView.findViewById(R.id.matchBoutonPositif);
		buttonPos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// Création d'un match
				
				System.out.println("Id Personne: " + idArbitre);
				PersonneDAO personneDAO = new PersonneDAO(context);
				Personne personne = personneDAO.retrievePersonne(idArbitre, context);
				
				System.out.println("Id Stade: " + idStade);
				Stade stade = stadeDAO.retrieveStade(idStade);
				
				// Recupérer les dates
				System.out.println("Date Debut: " + dateMatch.getText());
				
				Match match = new Match(
						idMatch,
						stade,
						personne,
						dateMatch.getText().toString()
				);
				
				try {
					if (champsRemplis(match)) { // Si tout les champs sont bien remplis on réalise l'insertion
						MatchDAO matchDAO = new MatchDAO(context);
						matchDAO.createMatch(match);
						Log.d(TAG, "Ajout match");
						((MainActivity) context).changeFragment(new Match_fragment()); // TODO : changer le fragment
						Toast.makeText(context, "Match ajouté", Toast.LENGTH_SHORT).show();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		
		Button buttonNeg = (Button) myView.findViewById(R.id.matchBouttonNegatif);
		buttonNeg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				((MainActivity) getActivity()).changeFragment(new Match_fragment());
	
			}
		});
		
		return myView;
	}
	
	// Vérifications
	private boolean champsRemplis(Match match) throws ParseException {
		boolean isSet = true;
		String dateMatch = match.getDateMatch();
		String myFormat = "dd/MM/yyyy"; //In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
		Date date_du_Jour = new Date();
		String du_jour = sdf.format(date_du_Jour);
		date_du_Jour = sdf.parse(du_jour);
		Date matchDate = sdf.parse(dateMatch);
		Log.d(TAG, "champsRemplis: date_du_jour: " + date_du_Jour);
		Log.d(TAG, "champsRemplis: matchDate: " + matchDate);
		if (match.getStade().equals(null)) {
			Toast.makeText(context, "Stade manquant", Toast.LENGTH_SHORT).show();
			isSet = false;
		} else if (match.getPersonne().equals(null)) {
			Toast.makeText(context, "Arbitre manquant", Toast.LENGTH_SHORT).show();
			isSet = false;
		} else if (matchDate.compareTo(date_du_Jour) < 0) {
			Toast.makeText(context, "Date de début posterieure a la date de fin", Toast.LENGTH_SHORT).show();
			isSet = false;
		} else if (dateMatch.isEmpty()) {
			Toast.makeText(context, "Saisissez une date pour le match", Toast.LENGTH_SHORT).show();
			isSet = false;
		}
		return isSet;
	}
	
	
	// Mise à jour de la date
	private void updateLabel() {
		String myFormat = "dd/MM/yyyy"; //In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
		if (date_match_default.equals("DATE_MATCH")) {
			dateMatch.setText(sdf.format(myCalendar.getTime()));
		}

	}
}//fin
