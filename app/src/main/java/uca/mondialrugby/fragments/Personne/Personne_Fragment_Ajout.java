package uca.mondialrugby.fragments.Personne;

/**
 * Created by watson on 12/03/2018.
 */

/* TODO A supprimer
public class Personne_Fragment_Ajout extends Fragment {
    String idEquipe;
    String idPoste;
    View myView;
    boolean validate = true;
    Context context;
    private EditText dateNaissance;
    private Calendar myCalendar = Calendar.getInstance();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.personne_layout_add, container, false);
        context = myView.getContext();
        getActivity().setTitle("Nouvelle Personne");

        Button button_add_personne = (Button) myView.findViewById(R.id.button_add_personne);

        button_add_personne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value_nom = (EditText) myView.findViewById(R.id.value_nom_personne);
                EditText value_prenom= (EditText) myView.findViewById(R.id.value_prenom_personne);


                String nom = value_nom.getText().toString();
                String prenom = value_prenom.getText().toString();


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


                dateNaissance = (EditText) myView.findViewById(R.id.dateMatch);
                final DatePickerDialog datePicker_match = new DatePickerDialog(context, R.style.DatePicker, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dateNaissance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String editext_state = "Date de naissance";
                        datePicker_match.show();

                        // TODO la selection sur le calendrier ne met pas la date dans le champ
                    }
                });



                // Spinner Arbitre
                final Spinner spinnerEquipe = myView.findViewById(R.id.equipeSpinner); // Création du spinner
                final ArrayList<Equipe> listEquipe;
                final EquipeDAO equipeDAO = new EquipeDAO(context);
                listEquipe = equipeDAO.getAllEquipe();

                final ArrayAdapter<Equipe> adapterEquipe = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listEquipe);
                adapterEquipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                spinnerEquipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (adapterEquipe.getItem(position).getPays() == null) {

                        } else idEquipe = adapterEquipe.getItem(position).getPays();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });// fin spinner

                // Spinner Personne
                final Spinner spinnerPoste = myView.findViewById(R.id.posteSpinner);
                final ArrayList<Poste> listPoste;
                final PosteDAO posteDAO = new PosteDAO(context);
                listPoste = posteDAO.getAllPoste();

                final ArrayAdapter<Poste> adapterPoste = new ArrayAdapter<Poste>(context, android.R.layout.simple_spinner_item, listPoste);
                adapterPoste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPoste.setAdapter(adapterPoste);


                spinnerPoste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (adapterPoste.getItem(position).getNumero() == null) {

                        } else idPoste = adapterPoste.getItem(position).getNumero();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Equipe equipe = equipeDAO.retrieveEquipe(idEquipe); // TODO changer le parametre avec un string :: fait un bug peut venir de la
                Poste poste =  posteDAO.retrievePoste(idPoste); // TODO pareil


                if ( TextUtils.isEmpty(nom)) {
                    Toast.makeText(getContext(), "Nom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                } else if (TextUtils.isEmpty(prenom)) {
                    Toast.makeText(getContext(), "Prénom manquant", Toast.LENGTH_SHORT).show();
                    validate = false;
                }  // Todo : rajouter les vérification de date
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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        // TODO Adapter une fois la saisie de DATE géree
//        if (editext_state.equals("DEBUT_CONTRAT")) {
//            date_debut_contrat.setText(sdf.format(myCalendar.getTime()));
//        }

    }

}//fin
*/