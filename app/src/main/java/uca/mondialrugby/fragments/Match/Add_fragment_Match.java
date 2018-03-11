package uca.mondialrugby.fragments.Match;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import java.util.List;
import java.util.Locale;

import uca.mondialrugby.Classe.Match;
import uca.mondialrugby.Classe.Personne;
import uca.mondialrugby.Classe.Stade;
import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.MatchDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.bdd.StadeDAO;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

/**
 * Created by watson on 11/03/2018.
 */

public class Add_fragment_Match {
   // private List<Personne> listPersonne = new ArrayList<Personne>();
    //private List<Stade> listStade = new ArrayList<Stade>();
    private EditText dateMatch;
    private Calendar myCalendar = Calendar.getInstance();

    private int idPersonne;
    private int idStade;
    private int idMatch;


    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.match_layout_form, container, false);


        Bundle bundle = this.getArguments();


        // Spinner Arbitre
        final Spinner spinnerArbitre = myView.findViewById(R.id.arbitreSpinner); // Création du spinner
        final ArrayList<Personne> listPersonne; // Création de la liste de secteurs
        final PersonneDAO personneDAO = new PersonneDAO(getContext()); // Creation de l'object secteur DAO
        listPersonne = personneDAO.getAllPersonne(getContext()); // Remplissage de la liste des secteurs

        final ArrayAdapter<Personne> adapterPersonne = new ArrayAdapter<Personne>(getActivity(), android.R.layout.simple_spinner_item, listPersonne); // Création de l'adapter
        adapterPersonne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Définition du style de l'adapter


        spinnerArbitre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterPersonne.getItem(position).getId() == -1) { // Si l'item est l'item par défaut on fait rien

                } else idPersonne = adapterPersonne.getItem(position).getId(); // On récupère l'ID du secteur selectionné
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Spinner Stade
        final Spinner spinnerStade = myView.findViewById(R.id.spinnerStade);
        final ArrayList<Stade> listStade;
        final StadeDAO stadeDAO = new StadeDAO(getContext());
        listStade = stadeDAO.getAllStade();

        final ArrayAdapter<Stade> adapterStade = new ArrayAdapter<Stade>(getActivity(), android.R.layout.simple_spinner_item, listStade);
        adapterStade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStade.setAdapter(adapterStade);

        // Récupération de l'ID Activité sélectionné
        spinnerStade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterStade.getItem(position).getId() == -1) {

                } else idStade = adapterStade.getItem(position).getId(); // On récupère l'ID de l'activité selectionné
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


        dateMatch = (EditText) myView.findViewById(R.id.date_match);
        final DatePickerDialog datePicker_match = new DatePickerDialog(getActivity(), R.style.DatePicker, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        dateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editext_state = "Date du match";
                datePicker_match.show();
            }
        });


        //BUTTON
        Button buttonPos = (Button) myView.findViewById(R.id.ajoutBouton);
        buttonPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Création d'un match


                System.out.println("Id Personne: " + idPersonne);
                PersonneDAO personneDAO = new PersonneDAO(getContext());
                Personne personne = personneDAO.retrievePersonne(idPersonne, getContext());


                System.out.println("Id Stade: " + idStade);
                Stade stade = stadeDAO.retrieveStade(idStade);


                // Recupérer les dates
                System.out.println("Date Debut: " + dateMatch.getText());


                Match match = new Match(

                        idMatch, stade, personne, dateMatch.getText().toString()


                );

                try {
                    if (champsRemplis(match)) { // Si tout les champs sont bien remplis on réalise l'insertion
                        MatchDAO matchDAO = new MatchDAO(getContext());
                        matchDAO.insertMatch(match);
                        Log.d(TAG, "Ajout match");
                        ((MainActivity) getActivity()).changeFragment(new Intervention_fragment_home()); // TODO : changer le fragment
                        Toast.makeText(getActivity(), "Match ajoutée", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        });



        Button buttonNeg = (Button) myView.findViewById(R.id.Cancel_bouton);
        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ((MainActivity) getActivity()).changeFragment(new Intervention_fragment_home());


            }
        });
        return myView;
    }

    private boolean champsRemplis(Match match) throws ParseException {
        boolean isSet = true;
        String dateMatch = match.getDateMatch();
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        Date date_du_Jour = new Date();
        String du_jour = sdf.format(date_du_Jour);
        date_du_Jour = sdf.parse(du_jour);
        Date matchDate = sdf.parse(dateMatch);



        if (match.getStade().equals("")){
            Toast.makeText(getActivity(), "Stade manquant", Toast.LENGTH_SHORT).show();
            isSet = false;
        } else if(match.getPersonne().equals("")) {
            Toast.makeText(getActivity(), "Arbitre manquant", Toast.LENGTH_SHORT).show();
            isSet = false;
        } else if (matchDate.compareTo(date_du_Jour) > 0){
            Toast.makeText(getActivity(), "Date de début après la date de fin", Toast.LENGTH_SHORT).show();
            isSet = false;
        }

        return isSet;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        if (editext_state.equals("DEBUT_CONTRAT")) {
            date_debut_contrat.setText(sdf.format(myCalendar.getTime()));
        }

    }


}//fin
