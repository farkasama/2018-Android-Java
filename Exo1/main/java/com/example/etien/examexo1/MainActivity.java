package com.example.etien.examexo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView hello;
    Button cheval;
    Button mouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = (TextView)findViewById(R.id.text);
        cheval = (Button)findViewById(R.id.a);
        mouton = (Button)findViewById(R.id.b);

        if (savedInstanceState != null) {
            hello.setText(savedInstanceState.getString("hello"));
        }
    }

    public void bouton(View view) {
        if (view.getId() == mouton.getId()) {
            hello.setText(mouton.getText());
        }
        if (view.getId() == cheval.getId()) {
            hello.setText(cheval.getText());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("hello", hello.getText().toString());
    }


}
