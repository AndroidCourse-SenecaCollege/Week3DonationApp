package com.example.donationappf22;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    String[] countries;
    String[] coleges;
    ListView donations_list;
    Spinner colleges_spinner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        donations_list = findViewById(R.id.donation_list);
        colleges_spinner = findViewById(R.id.spinner);

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

        ArrayList<Donation> listOfDonations = new ArrayList<>(3);
        listOfDonations.add(new Donation(200.3,"PayPal"));
        listOfDonations.add(new Donation(30,"Credit Card"));
        listOfDonations.add(new Donation(44.5,"PayPal"));

        DonationBaseAdapter donationBaseAdapter = new DonationBaseAdapter(this,listOfDonations);
        donations_list.setAdapter(donationBaseAdapter);
        donations_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReportActivity.this, "The selected donation's amount is " + listOfDonations.get(i).amount, Toast.LENGTH_LONG).show();
            }
        });
    }
}