package com.example.donationappf22;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity
        implements View.OnClickListener {

    int a = 99;
    String[] countries;
    String[] coleges;
    ListView donations_list;
    Spinner colleges_spinner;
    Button websearch_button;
    EditText search_edittext;
    Button takePhotoButton;
    ImageView image;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)// UI variables
            a = savedInstanceState.getInt("a");

        setContentView(R.layout.activity_report);
        donations_list = findViewById(R.id.donation_list);
        colleges_spinner = findViewById(R.id.spinner);
        websearch_button = findViewById(R.id.search_button);
        search_edittext = findViewById(R.id.search_term);
        takePhotoButton = findViewById(R.id.takephoto_button);
        image = findViewById(R.id.image);
        websearch_button.setOnClickListener(this);
        Log.d("donation app a value",a + "");

        Donation donationFromMA = getIntent().getExtras().getParcelable("dObject");

        countries = new String[] {"Canada","USA","Italy","France","Eygpt"};
        coleges = new String[] {"Seneca","Humber","Centennial"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_row_layout,R.id.list_item,countries);
      //  countries_list.setAdapter(adapter);

        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this,R.layout.list_row_layout,R.id.list_item,coleges);
        colleges_spinner.setAdapter(spinner_adapter);
        colleges_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReportActivity.this, "The selected college is " + coleges[i], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // to build custom row and lists, we have to use Base Adapter

//        ArrayList<Donation> listOfDonations = new ArrayList<>(3);
//        listOfDonations.add(new Donation(200.3,"PayPal"));
//        listOfDonations.add(new Donation(30,"Credit Card"));
//        listOfDonations.add(new Donation(44.5,"PayPal"));

        DonationBaseAdapter donationBaseAdapter = new DonationBaseAdapter(this,MyApp.getDonationArray());
        donations_list.setAdapter(donationBaseAdapter);
        donations_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReportActivity.this, "The selected donation's amount is " + MyApp.getDonationArray().get(i).amount, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.search_button: {
                a = 200;
                Log.d("donation app a value",a + "");
               if (!search_edittext.getText().toString().isEmpty()) {
                String query = search_edittext.getText().toString();
                //implicit intent
                Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                searchIntent.putExtra(SearchManager.QUERY, query);

                   if (searchIntent.resolveActivity(getPackageManager()) != null) {
                       startActivity(searchIntent);
                   }
               }

                break;
            }
            default:break;

        };
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a", a);
    }
}