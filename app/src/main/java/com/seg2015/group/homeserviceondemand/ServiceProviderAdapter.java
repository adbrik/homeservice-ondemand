package com.seg2015.group.homeserviceondemand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceProviderAdapter extends ArrayAdapter<Service> {

    private final Context context;
    private static ArrayList<Service> serviceProvider;

    public ServiceProviderAdapter(Context context, ArrayList<Service> values) {
        super(context, R.layout.list_item_layout, values);
        this.context = context;
        this.serviceProvider = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Getting Recipe
        Service curService = serviceProvider.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_layout, parent, false);
        TextView serviceName = (TextView) rowView.findViewById(R.id.itemName);
        TextView hourlyRate = (TextView) rowView.findViewById(R.id.itemDescription);

        //Placing content into recipe List Item
        serviceName.setText(curService.getService());
        hourlyRate.setText((curService.getRate()));

        return rowView;
    }

    public static void delete(Service currentService) {
        serviceProvider.remove(currentService);
    }

    public void add(Service newService) {
        serviceProvider.add(newService);
    }

}
