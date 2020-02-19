package com.example.etien.examexo2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {

    Button addEvent;
    Button learnEvent;
    Button init;
    public static String EVENT = "evenement";
    public static String DAY = "jour";
    public static String MONTH = "mois";
    public static String YEAR = "annee";
    public static final String AUTHORITY = "fr.etiennejouanne.examexo2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        addEvent = (Button)findViewById(R.id.ajouter);
        learnEvent = (Button)findViewById(R.id.apprendre);
        init = (Button)findViewById(R.id.initialiser);
    }

    public void bouton(View v) {
        if (v.getId() == addEvent.getId()){
            Intent iii = new Intent(SelectionActivity.this, AjouterEvenementActivity.class);
            startActivity(iii);
        }
        if (v.getId() == learnEvent.getId()) {
            Intent iii = new Intent(SelectionActivity.this, ChoisirEvenement.class);
            startActivity(iii);
        }
        if (v.getId() == init.getId()) {
            Context context = getApplicationContext();
            ContentResolver cr = context.getContentResolver();

            String [] events = context.getResources().getStringArray(R.array.event);
            int[] year = context.getResources().getIntArray(R.array.annee);
            int[] month = context.getResources().getIntArray(R.array.mois);
            int[] day = context.getResources().getIntArray(R.array.jour);

            for (int i = 0; i < events.length; i++) {
                ContentValues ligne = new ContentValues();
                ligne.put(EVENT, events[i]);
                ligne.put(YEAR, year[i]);
                ligne.put(MONTH, month[i]);
                ligne.put(DAY, day[i]);

                Uri.Builder builder = new Uri.Builder();
                builder.scheme("content").authority(AUTHORITY);
                Uri uri = builder.build();
                uri = cr.insert(uri, ligne);
            }
        }
    }
}
