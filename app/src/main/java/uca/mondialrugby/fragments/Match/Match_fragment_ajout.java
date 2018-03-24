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

import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.JouerDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Jouer;
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
	private String domicile;
	private String exterieur;
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
		final Spinner spinnerDomicile = myView.findViewById(R.id.equipeDomicileSpinner);
		final ArrayList<Equipe> listeEquipes;
		final EquipeDAO equipeDAO = new EquipeDAO(context);
		listeEquipes = equipeDAO.getAllEquipe();
		
		final ArrayAdapter<Equipe> adapterDomicile = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listeEquipes);
		adapterDomicile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDomicile.setAdapter(adapterDomicile);
		
		spinnerDomicile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == -1) {
					Log.d(TAG, "onItemSelected: position = -1");
				} else domicile = adapterDomicile.getItem(position).getPays();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
		// TODO 2- SPINNER EXTERIEUR
		final Spinner spinnerExterieur = myView.findViewById(R.id.equipeExterieurSpinner);
		
		final ArrayAdapter<Equipe> adapterExterieur = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listeEquipes);
		adapterExterieur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerExterieur.setAdapter(adapterExterieur);
		
		spinnerExterieur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == -1) {
					Log.d(TAG, "onItemSelected: position = -1");
				} else exterieur = adapterExterieur.getItem(position).getPays();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
		
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
				
				// Creation des occurences de jouer participants[0] = domicile et participants [1] = invité / extérieur
				ArrayList<Jouer> participants = new ArrayList<>();
				Log.d(TAG, "onClick: Domicile: " + domicile);
				Jouer jouer = new Jouer(equipeDAO.retrieveEquipe(domicile));
				participants.add(jouer);
				Log.d(TAG, "onClick: Exterieur: " + exterieur);
				jouer = new Jouer(equipeDAO.retrieveEquipe(exterieur));
				participants.add(jouer);
				
				
				try {   // TODO ajouter les 2 equipes en parametre
					if (champsRemplis(match, participants)) { // Si tout les champs sont bien remplis on réalise l'insertion
						MatchDAO matchDAO = new MatchDAO(context);
						JouerDAO jouerDAO = new JouerDAO(context);
						
						matchDAO.createMatch(match);                        // Insertion du match dans la bdd
						Match lastMatch = matchDAO.getLastMatch(context);   // On récupère le match avec son id auto-généré
						Log.d(TAG, "onClick: lastMatch (ID) : " + lastMatch.getIdMatch());
						for(Jouer j : participants){
							j.setMatch(lastMatch);      // On affecte le match créé aux 2 occurences de jouer
							jouerDAO.createJouer(j);    // On insère le match crée
							Log.d(TAG, "onClick: match cree");
							Log.d(TAG, "onClick: new Jouer: " + j);
							
						}
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
	private boolean champsRemplis(Match match, ArrayList<Jouer> participants) throws ParseException {
		boolean isSet = true;
		String dateMatch = match.getDateMatch();
		String myFormat = "dd/MM/yyyy"; //In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
		Date date_du_Jour = new Date();
		String du_jour = sdf.format(date_du_Jour);
		date_du_Jour = sdf.parse(du_jour);
		Date matchDate = sdf.parse(dateMatch);

		if (match.getStade().equals(null)) {
			Toast.makeText(context, "Stade manquant", Toast.LENGTH_SHORT).show();
			isSet = false;
		} else if (match.getPersonne().equals(null)) {
			Toast.makeText(context, "Arbitre manquant", Toast.LENGTH_SHORT).show();
			isSet = false;
		} else if (participants.get(0).getIdPays().getPays().equals(participants.get(1).getIdPays().getPays())){
			Toast.makeText(context, "Les 2 Equipes ne doivent pas être identiques", Toast.LENGTH_SHORT).show();
			isSet = false;
		}
		// TOTO Tester si les 2 equipes sont differentes
		else if (matchDate.compareTo(date_du_Jour) < 0) {
			Toast.makeText(context, "La date du match est dépassée", Toast.LENGTH_SHORT).show();
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
