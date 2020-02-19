package com.example.etien.examexo2;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class ChoisirEvenement extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    CheckedTextView ctv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_evenement);

        lv =(ListView)findViewById(R.id.list);
        ctv = (CheckedTextView)findViewById(android.R.id.text1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice);
        lv.setAdapter(adapter);

        ContentResolver cr = getContentResolver();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority("fr.etiennejouanne.examexo2").appendPath("all_events");
        Uri uri = builder.build();
        Cursor c = cr.query(uri,null, null, null, null);

        if (c != null && c.getCount() > 0) {
            for (int i = 0; i < c.getCount(); i++) {
                ctv.add
            }
        }
    }
}
