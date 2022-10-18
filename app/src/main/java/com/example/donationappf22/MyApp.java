package com.example.donationappf22;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

   static private ArrayList<Donation> allDonations = new ArrayList<Donation>(0);

    static ArrayList<Donation> getDonationArray(){
        return allDonations;
    }

   static void addNewDonation(Donation d){
        allDonations.add(d);
    }
}
