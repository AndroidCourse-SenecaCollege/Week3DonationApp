package com.example.donationappf22;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DonationDAO {

        @Query("SELECT * FROM Donation")
        List<Donation> getAll();

        @Query("SELECT * FROM Donation WHERE amount >= :value")
        List<Donation> getDonationsMoreThan(double value);

        @Query("SELECT * FROM Donation WHERE payment_method == :method")
        List<Donation> findByPaymentMethod(String method);

        @Insert
        void insertOneDonation(Donation donation);


        @Delete
        void delete(Donation d);

}
