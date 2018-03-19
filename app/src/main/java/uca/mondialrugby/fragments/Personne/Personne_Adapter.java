package uca.mondialrugby.fragments.Personne;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.PersonneDAO;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.fragments.Personne.Personne_Adapter;


/**
 * Created by watson on 19/03/2018.
 */

public class Personne_Adapter extends ArrayAdapter<Personne> {

    private ArrayList<Personne> personnes = new ArrayList<>();
    private static class PersonneHolder
    {
        TextView personne_nom;
        ImageButton delete_item;
    }

    public Personne_Adapter(Context context, ArrayList<Personne> personnes)

    {
        super(context,0, personnes);
        this.personnes = personnes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //On recupere l'objet
        final Personne personne = getItem(position);
        Personne_Adapter.PersonneHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new Personne_Adapter.PersonneHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.test,parent,false);
            viewHolder.personne_nom = (TextView) convertView.findViewById(R.id.generique_item_nom);
            viewHolder.delete_item = (ImageButton) convertView.findViewById(R.id.generique_item_trash);



            convertView.setTag(viewHolder);
        }
        else {

            viewHolder = (Personne_Adapter.PersonneHolder) convertView.getTag();
        }
        // setText avec notre object

        viewHolder.personne_nom.setText(personne.getId());
        viewHolder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                PersonneDAO personneDAO = new PersonneDAO(getContext());
                                personneDAO.deletePersonne(personne.getId());
                                personnes.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ROW: ", "ROW PRESSED");
                System.out.println(personne.getId());
                Bundle bundle = new Bundle();
                bundle.putString("id_secteur",String.valueOf(personne.getId()));
                Personne_Fragment_Update pfu = new Personne_Fragment_Update();
                pfu.setArguments(bundle);
                ((MainActivity)getContext()).changeFragment(pfu);
            }
        });
        return convertView;
    }




}
