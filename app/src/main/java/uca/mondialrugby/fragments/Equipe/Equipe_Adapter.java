package uca.mondialrugby.fragments.Equipe;

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
import uca.mondialrugby.bdd.EquipeDAO;
import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.fragments.Equipe.Equipe_Adapter;


/**
 * Created by watson on 19/03/2018.
 */

public class Equipe_Adapter extends ArrayAdapter<Equipe> {
    private ArrayList<Equipe> equipes = new ArrayList<>();
    private static class EquipeHolder /* Objet qui contient les éléments affichés à l'écran */
    {
        TextView equipe_nom;
        ImageButton delete_item;
    }

    public Equipe_Adapter(Context context, ArrayList<Equipe> equipes)

    {
        super(context,0, equipes);
        this.equipes = equipes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //On recupere l'objet
        final Equipe equipe = getItem(position);
        Equipe_Adapter.EquipeHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new Equipe_Adapter.EquipeHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext()); /* On désérialise les données du context */
            convertView = inflater.inflate(R.layout.test,parent,false); /* On désérialise le layout */

            viewHolder.equipe_nom = (TextView) convertView.findViewById(R.id.generique_item_nom); /* On valorise le nom */
            viewHolder.delete_item = (ImageButton) convertView.findViewById(R.id.generique_item_trash);



            convertView.setTag(viewHolder);
        }
        else {

            viewHolder = (Equipe_Adapter.EquipeHolder) convertView.getTag();
        }
        // setText avec notre object

        viewHolder.equipe_nom.setText(equipe.getPays());
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
                                EquipeDAO equipeDAO = new EquipeDAO(getContext());
                                equipeDAO.deleteEquipe(equipe.getPays());
                                equipes.remove(position);
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
                System.out.println(equipe.getPays());
                Bundle bundle = new Bundle();
                bundle.putString("id_stade",(equipe.getPays()));
                System.out.printf(equipe.getPays());
                Equipe_Frament_Update efu = new Equipe_Frament_Update();
                efu.setArguments(bundle);
                ((MainActivity)getContext()).changeFragment(efu);
            }
        });
        return convertView;
    }
}
