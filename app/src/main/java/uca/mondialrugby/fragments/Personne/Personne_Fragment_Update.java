package uca.mondialrugby.fragments.Personne;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.bdd.PosteDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Poste;
import uca.mondialrugby.fragments.Stade.Stade_fragment_home;

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
    private Calendar myCalendar = Calendar.getInstance();
    private String editext_state;

    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.personne_layout_update, container, false);

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
        final PosteDAO posteDAO = new PosteDAO(getContext());
        listPoste = posteDAO.getAllPoste();


        final ArrayAdapter<Poste> adapterPoste = new ArrayAdapter<Poste>(getActivity(), android.R.layout.simple_spinner_item, listPoste);
        adapterPoste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPoste.setAdapter(adapterPoste);

        if (!personne.getPoste().getNumero().equals(null)){
            spinnerPoste.setSelection(getIndex(spinnerPoste, personne.getPoste().toString()));}

        spinnerPoste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterPoste.getItem(position).getNumero() == null) {

                } else {
                    idPoste = adapterPoste.getItem(position).getNumero();

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner equipe
        final Spinner spinnerEquipe = myView.findViewById(R.id.spinner_equipe); // Création du spinner
        final ArrayList<Equipe> listEquipe;
        final EquipeDAO equipeDAO = new EquipeDAO(getContext());
        listEquipe = equipeDAO.getAllEquipe();

        final ArrayAdapter<Equipe> adapterEquipe = new ArrayAdapter<Equipe>(getActivity(), android.R.layout.simple_spinner_item, listEquipe);
        adapterEquipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquipe.setAdapter(adapterEquipe);
        if (!personne.getEquipe().getPays().equals(null)){

            spinnerEquipe.setSelection(getIndex(spinnerEquipe, personne.getEquipe().toString()));}

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
        System.out.println(personne.getDate());
        dateNaissance = (EditText) myView.findViewById(R.id.value_date_naissance);
        dateNaissance.setText(personne.getDate());
        final DatePickerDialog datePicker_match = new DatePickerDialog(getActivity(), R.style.DatePicker, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        dateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editext_state = "DATE_NAISSANCE";
                datePicker_match.show();
            }
        });

        System.out.println("id poste : " + idPoste);
        Button button_annule_personne = (Button) myView.findViewById(R.id.button_noupdate_personne);

        button_annule_personne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.closekeyboard(getContext(), myView);
                ((MainActivity) getActivity()).changeFragment(new Personne_Fragment_home());
            }
        });

       Button button_update_stade = (Button) myView.findViewById(R.id.button_update_personne);

        button_update_stade.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                PosteDAO posteDAO = new PosteDAO(getContext());
                Poste poste = posteDAO.retrievePoste(idPoste);

                EquipeDAO equipeDAO = new EquipeDAO(getContext());
                Equipe equipe = equipeDAO.retrieveEquipe(idEquipe);

                boolean validate = true;
                if ( TextUtils.isEmpty(value_nom.getText())) {
                    Toast.makeText(getContext(), "Nom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(value_prenom.getText())) {
                    Toast.makeText(getContext(), "Prénom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else try {
                    if (dateValidate() == false) {
                        Toast.makeText(getContext(), "date impossible", Toast.LENGTH_SHORT).show();
                        validate = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(validate) {
                    Personne personne_modify = new Personne();
                    personne_modify.setId(personne.getId());
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
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        dateNaissance.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean dateValidate () throws ParseException {
        String dateN = personne.getDate();
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        Date date_du_Jour = new Date();
        String du_jour = sdf.format(date_du_Jour);
        date_du_Jour = sdf.parse(du_jour);
        Date naissanceDate = sdf.parse(dateN);
        System.out.println(date_du_Jour);
        System.out.println(naissanceDate);
        if (date_du_Jour.compareTo(naissanceDate) < 0) {
            Toast.makeText(getContext(), "date impossible", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
