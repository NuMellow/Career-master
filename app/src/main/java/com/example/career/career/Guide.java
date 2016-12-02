package com.example.career.career;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

/**
 * Created by Cole on 11/17/2016.
 */

public class Guide extends MainActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        String[] questions = {"Taking care of pets in your neighborhood", "Planting and taking care of flowers & plants", "Working in a garden & creating landscapes",
        "Nursing sick animals back to health", "Hiking & watching wildlife", "Chopping wood & replanting trees", "Identifying environmental hazards & sick/dying plants"};

        ListAdapter careerAdaptor = new CustomAdaptor(this, questions);
        ListView careerListView = (ListView) findViewById(R.id.careerListView);
        careerListView.setAdapter(careerAdaptor);

        careerListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        String question = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(Guide.this, question, Toast.LENGTH_LONG).show();

                    }
                }
        );

    }

}
