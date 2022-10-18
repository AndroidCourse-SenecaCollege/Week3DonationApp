package com.example.donationappf22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button donation_button;
    EditText damountET;
    RadioButton ppRB;
    RadioButton ccRB;
    String selectedPMethod;
    double amount;
    Donation currentDonation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         donation_button = (Button)findViewById(R.id.donatebutton);
         damountET = (EditText) findViewById(R.id.amountText);
         ppRB = (RadioButton) findViewById(R.id.ppRB);
         ccRB = findViewById(R.id.ccRB);


         // click listener
         donation_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Log.d("donation App", "donation button clicked");
                    if (validateUI()){
                        String msg = "Thank You for your " + amount + " CAD donation completed via " + selectedPMethod+ " .";
                        currentDonation = new Donation(amount,selectedPMethod);
                        MyApp.addNewDonation(currentDonation);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Missing Info", Toast.LENGTH_LONG).show();
                    }

             }
         });

         ppRB.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Log.d("donation App", "PayPal selected");
                 selectedPMethod = "PayPal";
             }
         });

        ccRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("donation App", "Credit Card selected");
                selectedPMethod = "Credit Card";
            }
        });

    }

    boolean validateUI(){
        boolean valid = false;
        if (!damountET.getText().toString().isEmpty()) {
            amount = Double.parseDouble(damountET.getText().toString());
            if (!selectedPMethod.isEmpty() && amount > 0 )
                valid = true;
        }
        return valid;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.report_menu_item:
                // explicit intent
                Intent reportIntent = new Intent(this,ReportActivity.class);
                reportIntent.putExtra("dObject",currentDonation);

                startActivity(reportIntent);

                return true;
            case R.id.close_menu_item:

                return true;

            case R.id.camera_activity:
                Intent cameraIntent = new Intent(this,CameraActivity.class);
                startActivity(cameraIntent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}