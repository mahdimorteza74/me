package com.mahdi.mrt.xxxmxkxkxlxl.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mahdi.mrt.xxxmxkxkxlxl.R;

public class ThanksActivity extends AppCompatActivity {
    final String LOG = "ThanksActivity";

    Button home, track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        home = (Button)findViewById(R.id.btnHome);
        track = (Button)findViewById(R.id.btnTrack);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThanksActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanksActivity.this, TrackOrderActivity.class);
                startActivity(intent);

            }
        });
    }

}
