package com.example.donationappf22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DonationBaseAdapter extends BaseAdapter {
    Context context;
    // this array list is the tables datasource
    ArrayList<Donation> listOfDonations = new ArrayList<>(0);

    DonationBaseAdapter(Context c, ArrayList<Donation> list){
        context = c;
        listOfDonations = list;
    }

    @Override
    public int getCount() {
        return listOfDonations.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfDonations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.donationlist_row_layout,null);
        TextView donationAmount = view.findViewById(R.id.donation_amount);
        TextView donationMethod = view.findViewById(R.id.payment_method);

        // each row will have information about 1 donation in the data source
        donationAmount.setText(String.valueOf(listOfDonations.get(i).amount));
        donationMethod.setText(listOfDonations.get(i).paymentMethod);
        return view;
    }
}
