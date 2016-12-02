package com.example.career.career;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import com.example.career.career.m_Firebase.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{

    private DatabaseReference myDb;
    private FirebaseHelper fireHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //connects to database and create a new FirebaseHelper
        myDb = FirebaseDatabase.getInstance().getReference();
        fireHelper = new FirebaseHelper(myDb);

        //create adapter
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fireHelper.retrieve() );
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //sets adapter to spinner
        spinner.setAdapter(adp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view)
    {
        Intent i = new Intent(this, Guide.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_about:
               AlertDialog.Builder bu = new AlertDialog.Builder(this);

                bu.setMessage(R.string.dialog_message_about)
                        .setTitle(R.string.dialog_title_about)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                        {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        //User Okay button...
                                    }
                                }
                        );

                AlertDialog dialog;
                dialog = bu.create();
                dialog.show();
                break;

            case R.id.menu_help:
                AlertDialog.Builder b = new AlertDialog.Builder(this);

                b.setMessage(R.string.dialog_message_help)
                        .setTitle(R.string.dialog_title_help)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                                {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        //User Okay button...
                                    }
                                }
                        );

                AlertDialog d = b.create();
                d.show();
                break;

            // // TODO: 11/3/2016 display help info.
            case R.id.menu_email_us:
                showEmailUs();
                break;
            // // TODO: 11/3/2016 display contact info.
            default:
                return super.onOptionsItemSelected(item);
        }

            return super.onOptionsItemSelected(item);
    }

    private void showEmailUs()
    {
        // start an email intent and feed it with email address and subject
        // only
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{getString(R.string.developer_email)});// developer email,please keep it as an array

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));

        try {
            // start the email activity
            startActivity(Intent.createChooser(emailIntent, "Email " + getString(R.string.app_name)
                    + "..."));

        } catch (ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(),
                    "No email clients found/configured: " + e.toString(),
                    Toast.LENGTH_LONG).show();
            Log.e("Email " + getString(R.string.app_name), "No email clients: " + e.toString());
            //log in google analytics
            //AppController.getInstance().trackException(e);

        }
    }

}


