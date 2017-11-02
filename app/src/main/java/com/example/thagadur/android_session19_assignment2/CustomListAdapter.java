
package com.example.thagadur.android_session19_assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Thagadur on 11/2/2017.
 */

public class CustomListAdapter extends BaseAdapter {

    Context context;
    List<DataHandler> data;
    LayoutInflater inflater;

    public CustomListAdapter(Context context, List<DataHandler> data) {
        this.context = context;
        this.data = data;
    }

    //    Get the Count of the List Items that have to be iterated
    @Override
    public int getCount() {
        return data.size();
    }

    // get the Item Position
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //    Set the data into the List Item by binding the layout list_item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.count = (TextView) convertView.findViewById(R.id.vote_count);
            holder.id = (TextView) convertView.findViewById(R.id.id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(data.get(position).getName());
        holder.id.setText("Id: " + data.get(position).getId() + "");
        holder.count.setText("Votes: " + data.get(position).getVote_count() + "");

        return convertView;
    }

    //    View Holder which contains Text View ,id and count
    class ViewHolder {
        TextView name, id, count;
    }
}
