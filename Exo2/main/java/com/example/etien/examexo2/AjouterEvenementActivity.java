package com.example.etien.examexo2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterEvenementActivity extends AppCompatActivity {

    EditText event;
    EditText annee;
    EditText mois;
    EditText jour;
    Button enregistrer;
    public static String EVENT = "evenement";
    public static String DAY = "jour";
    public static String MONTH = "mois";
    public static String YEAR = "annee";
    public static final String AUTHORITY = "fr.etiennejouanne.examexo2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_evenement);

        event = (EditText)findViewById(R.id.evenement);
        annee = (EditText)findViewById(R.id.annee);
        mois = (EditText)findViewById(R.id.mois);
        jour = (EditText)findViewById(R.id.jour);
        enregistrer = (Button) findViewById(R.id.button);
    }

    public void click(View v) {
        String e = event.getText().toString();
        String a = annee.getText().toString();
        String m = mois.getText().toString();
        String j = mois.getText().toString();

        if (e.equals("") || a.equals("") || m.equals("") || j.equals("")) {
            Toast.makeText(getApplicationContext(), "Une des valeurs est vide !", Toast.LENGTH_LONG).show();
        }
        else {
            ContentValues ligne = new ContentValues();
            ContentResolver cr = getApplicationContext().getContentResolver();

            ligne.put(EVENT, e);
            ligne.put(YEAR, a);
            ligne.put(MONTH, m);
            ligne.put(DAY, j);

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content").authority(AUTHORITY);
            Uri uri = builder.build();
            uri = cr.insert(uri, ligne);
            Toast.makeText(getApplicationContext(), "Une des valeurs est vide !", Toast.LENGTH_LONG).show();
        }
    }
}
