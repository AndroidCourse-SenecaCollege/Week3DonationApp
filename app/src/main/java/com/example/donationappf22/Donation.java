package com.example.donationappf22;

import android.os.Parcel;
import android.os.Parcelable;

public class Donation implements Parcelable {
    double amount;
    String paymentMethod;


    public Donation(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    protected Donation(Parcel in) {
        amount = in.readDouble();
        paymentMethod = in.readString();
    }

    public static final Creator<Donation> CREATOR = new Creator<Donation>() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(amount);
        parcel.writeString(paymentMethod);
    }
}
