package uca.mondialrugby.fragments.Personne;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.bdd.PosteDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Poste;

import static android.content.ContentValues.TAG;


/**
 * Created by watson on 12/03/2018.
 */

public class Personne_Fragment_Ajout extends Fragment {
    private String idEquipe;
    private String idPoste;
   	private Poste poste;
    private Equipe equipe;
    private View myView;
   	private boolean validate = true;
    private Context context;
    private EditText dateNaissance;
    private Calendar myCalendar = Calendar.getInstance();
    

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    	
        myView = inflater.inflate(R.layout.personne_layout_add, container, false);
        context = myView.getContext();
        getActivity().setTitle("Nouvelle Personne");
	
	
	
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
	    //DATE naissance
	
	
	    dateNaissance = (EditText) myView.findViewById(R.id.value_date_naissance);
	    final DatePickerDialog datePicker_match = new DatePickerDialog(context, R.style.DatePicker, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
	    dateNaissance.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			    datePicker_match.show();
			

		    }
	    });
	
	    // Spinner Poste
	    final Spinner spinnerPoste = myView.findViewById(R.id.spinner_poste);
	    final ArrayList<Poste> listPoste;
	    final PosteDAO posteDAO = new PosteDAO(context);
	    listPoste = posteDAO.getAllPoste();
	
	    Log.d(TAG, "onClick: Nb postes = " + listPoste.size());
	
	    final ArrayAdapter<Poste> adapterPoste = new ArrayAdapter<Poste>(context, android.R.layout.simple_spinner_item, listPoste);
	    adapterPoste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinnerPoste.setAdapter(adapterPoste);
	
	
	    spinnerPoste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			    if (adapterPoste.getItem(position).getNumero() == null) {
				
			    } else{ idPoste = adapterPoste.getItem(position).getNumero();
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

                spinnerEquipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (adapterEquipe.getItem(position).getPays().isEmpty()) {

                        } else idEquipe = adapterEquipe.getItem(position).getPays();
                    }

		    
		
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
			
		    }
	    });// fin spinner
        System.out.println("id equipe" + idEquipe);
        System.out.println("id poste" + idPoste);

        // Bouton Ajout
        Button button_add_personne = (Button) myView.findViewById(R.id.button_add_personne);

        button_add_personne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value_nom = (EditText) myView.findViewById(R.id.value_nom);
                EditText value_prenom= (EditText) myView.findViewById(R.id.value_prenom);


                String nom = value_nom.getText().toString();
                String prenom = value_prenom.getText().toString();
                System.out.println(" ********id poste" + idPoste);
                 poste =  posteDAO.retrievePoste(idPoste);

                 equipe = equipeDAO.retrieveEquipe(idEquipe);


	            if ( TextUtils.isEmpty(nom)) {
		            Toast.makeText(getContext(), "Nom manquant", Toast.LENGTH_SHORT).show();
		            validate = false;
	            } else if (TextUtils.isEmpty(prenom)) {
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
				if(validate)
	            {
		            System.out.println("Insert");
		            ((MainActivity) getActivity()).closekeyboard(getContext(), myView);
		            PersonneDAO personneDAO = new PersonneDAO(myView.getContext());
		            Personne personne = new Personne
				            (
						            0,
						            poste,
						            equipe,
						            nom,
						            prenom,
						            dateNaissance.getText().toString()


				            );

		            personneDAO.insertPersonne(personne);


		            ((MainActivity) getActivity()).changeFragment(new Personne_Fragment_home());
	            }







            }
        });
	    
        return myView;

    }
	// Permet d'afficher le calendrier
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        dateNaissance.setText(sdf.format(myCalendar.getTime()));
    }

	private boolean dateValidate () throws ParseException {
		String dateN = dateNaissance.getText().toString();
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

}//fin
