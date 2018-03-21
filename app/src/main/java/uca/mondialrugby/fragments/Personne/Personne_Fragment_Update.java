package uca.mondialrugby.fragments.Personne;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.bdd.PosteDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Poste;
import uca.mondialrugby.fragments.Stade.Stade_fragment_home;

import static android.content.ContentValues.TAG;

/**
 * Created by watson on 12/03/2018.
 */

public class Personne_Fragment_Update extends Fragment {

    private int idPersonne;
    private String idEquipe;
    private String idPoste;
    private Context context;
    private Personne personne;
    private EditText dateNaissance;
    private PersonneDAO personneDAO;
    private Calendar myCalendar;
    private String editext_state;

    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.stade_layout_update, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            idPersonne = Integer.valueOf(bundle.get("id").toString());
        }
        System.out.println("Id personne : " + idPersonne);

        final EditText value_nom = (EditText) myView.findViewById(R.id.value_nom);
        final EditText value_prenom = (EditText) myView.findViewById(R.id.value_prenom);

        personneDAO = new PersonneDAO(getContext());
        personne = personneDAO.retrievePersonne(idPersonne, getContext());

        value_nom.setText(personne.getNom());
        value_prenom.setText(personne.getPrenom());

        // Spinner Poste
        //
        final Spinner spinnerPoste = myView.findViewById(R.id.spinner_poste);
        final ArrayList<Poste> listPoste;
        final PosteDAO posteDAO = new PosteDAO(context);
        listPoste = posteDAO.getAllPoste();


        final ArrayAdapter<Poste> adapterPoste = new ArrayAdapter<Poste>(context, android.R.layout.simple_spinner_item, listPoste);
        adapterPoste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPoste.setAdapter(adapterPoste);

        if (!personne.getPoste().getNumero().equals(null))
            spinnerPoste.setSelection(getIndex(spinnerPoste, personne.getPoste().toString()));
        spinnerPoste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterPoste.getItem(position).getNumero() == null) {

                } else {
                    idPoste = adapterPoste.getItem(position).getNumero();
                    System.out.println("spinner : " + idPoste);
                    System.out.println("spinner : " + adapterPoste.getItem(position).getNumero());
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner equipe
        final Spinner spinnerEquipe = myView.findViewById(R.id.spinner_equipe); // Création du spinner
        final ArrayList<Equipe> listEquipe;
        final EquipeDAO equipeDAO = new EquipeDAO(context);
        listEquipe = equipeDAO.getAllEquipe();

        final ArrayAdapter<Equipe> adapterEquipe = new ArrayAdapter<Equipe>(context, android.R.layout.simple_spinner_item, listEquipe);
        adapterEquipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquipe.setAdapter(adapterEquipe);
        if (!personne.getEquipe().getPays().toString().equals(null))
            spinnerPoste.setSelection(getIndex(spinnerPoste, personne.getEquipe().toString()));
        spinnerEquipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterEquipe.getItem(position).getPays().isEmpty()) {

                } else idEquipe = adapterEquipe.getItem(position).getPays();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// fin spinner

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dateNaissance = (EditText) myView.findViewById(R.id.value_date_naissance);
        dateNaissance.setText(personne.getDate());
        final DatePickerDialog datePicker_match = new DatePickerDialog(context, R.style.DatePicker, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        dateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editext_state = "DATE_NAISSANCE";
                datePicker_match.show();
            }
        });
        Button button_annule_personne = (Button) myView.findViewById(R.id.button_noupdate_personne);

        button_annule_personne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Stade_fragment_home());
            }
        });

        Button button_update_stade = (Button) myView.findViewById(R.id.button_update_personne);

        button_update_stade.setOnClickListener(new View.OnClickListener() {

            PosteDAO posteDAO = new PosteDAO(getContext());
            Poste poste = posteDAO.retrievePoste(idPoste);

            EquipeDAO equipeDAO = new EquipeDAO(getContext());
            Equipe equipe = equipeDAO.retrieveEquipe(idEquipe);
            @Override
            public void onClick(View v) {
                Personne personne_modify = new Personne();
                personne_modify.setId(idPersonne);
                personne_modify.setPoste(poste);
                personne_modify.setEquipe(equipe);
                personne_modify.setNom(value_nom.getText().toString());
                personne_modify.setPrenom(value_prenom.getText().toString());
                personne_modify.setDate(dateNaissance.getText().toString());


                personneDAO.updatePersonne(personne_modify);
                System.out.println("Personne modifie");
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Personne_Fragment_home());
            }
        });


        return myView;

    }



    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);


        //if (editext_state.equals("DATE_NAISSANCE")) {
        dateNaissance.setText(sdf.format(myCalendar.getTime()));
    }
}
