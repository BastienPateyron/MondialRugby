package uca.mondialrugby.fragments.Stade;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.R;
import uca.mondialrugby.bdd.StadeDAO;
import uca.mondialrugby.classes.Stade;

/**
 * Created by watson on 19/03/2018.
 */

public class Stade_Adapter extends ArrayAdapter<Stade> {

    private ArrayList<Stade> stades = new ArrayList<>();
    private static class StadeHolder /* Objet qui contient les éléments affichés à l'écran */
    {
        TextView secteur_nom;
        ImageButton delete_item;
    }

    public Stade_Adapter(Context context, ArrayList<Stade> stades)

    {
        super(context,0, stades);
        this.stades = stades;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //On recupere l'objet
        final Stade stade = getItem(position);
        StadeHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new StadeHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext()); /* On désérialise les données du context */
            convertView = inflater.inflate(R.layout.test,parent,false); /* On désérialise le layout */

            viewHolder.secteur_nom = (TextView) convertView.findViewById(R.id.generique_item_nom); /* On valorise le nom */
            viewHolder.delete_item = (ImageButton) convertView.findViewById(R.id.generique_item_trash);



            convertView.setTag(viewHolder);
        }
        else {

            viewHolder = (StadeHolder) convertView.getTag();
        }
        // setText avec notre object

        viewHolder.secteur_nom.setText(stade.getNom());
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
                                StadeDAO stadeDAO = new StadeDAO(getContext());
                                stadeDAO.deleteStade(stade.getId());
                                stades.remove(position);
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
                System.out.println(stade.getId());
                Bundle bundle = new Bundle();
                bundle.putString("id_secteur",String.valueOf(stade.getId()));
                Stade_fragment_update sfu = new Stade_fragment_update();
                sfu.setArguments(bundle);
                ((MainActivity)getContext()).changeFragment(sfu);
            }
        });
        return convertView;
    }
}
