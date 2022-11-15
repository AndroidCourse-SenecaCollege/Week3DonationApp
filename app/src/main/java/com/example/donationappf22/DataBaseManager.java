package com.example.donationappf22;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataBaseManager {

    interface DataBaseListener {
        void onGettingListDone(List<Donation> d);
        void onGettingListWithSpecifValueDone(List<Donation> dv);
        void onInsertDone();
    }

    public DataBaseListener listener;

    List<Donation> allDonationsFromDB;
    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);

    Handler mainThreadHander = new Handler(Looper.getMainLooper());

    static DonationDataBase db;
    static private void buildDB(Context context){
        db = Room.databaseBuilder(context,
                DonationDataBase.class, "donation_database").build();
    }

    public static DonationDataBase getDB(Context context){
        if (db == null){
          buildDB(context);
        }
        return db;
    }


    public void insertNewDonationInBGThread(Donation d){

        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {// this will run in background thread
                db.donationDao().insertOneDonation(d);
                mainThreadHander.post(new Runnable() {
                    @Override
                    public void run() {
                        // I could now access main thread
                        listener.onInsertDone();
                    }
                });
            }
        });

    }

    public void getAllDonations(){// no way to use return
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {// this will run in background thread
                List<Donation> dlist = db.donationDao().getAll();
                mainThreadHander.post(new Runnable() {
                    @Override
                    public void run() {
                        // I could now access main thread
                        listener.onGettingListDone(dlist);
                    }
                });
            }

        });
    }

    public void getListOfDonationsMoreThanValue(double v){// no way to use return
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {// this will run in background thread
                List<Donation> dlist = db.donationDao().getDonationsMoreThan(v);
                mainThreadHander.post(new Runnable() {
                    @Override
                    public void run() {
                        // I could now access main thread
                        listener.onGettingListWithSpecifValueDone(dlist);
                    }
                });
            }

        });

    }
}
