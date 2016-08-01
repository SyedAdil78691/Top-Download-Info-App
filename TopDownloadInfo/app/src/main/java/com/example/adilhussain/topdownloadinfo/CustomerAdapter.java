package com.example.adilhussain.topdownloadinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adil Hussain on 7/9/2016.
 */
  public class CustomerAdapter extends ArrayAdapter<Application> {

    private static class ViewHolder {
        TextView name;
        TextView artist;
        TextView releaseDate;
    }

    private Context context;
    private ArrayList<Application> item;
    private LayoutInflater inflater;

    public CustomerAdapter(Context context, ArrayList<Application> item) {
        super(context, 0,item);
        this.context= context;
        if (item==null)
        {
            item = new ArrayList<>();
        }
        this.item= item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.list_item, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.app_name);
            viewHolder.artist =  (TextView) convertView.findViewById(R.id.app_artist);
            viewHolder.releaseDate =  (TextView) convertView.findViewById(R.id.app_release_date);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Application item = getItem(position);

        if (item != null){
            viewHolder.name.setText(item.getName());
            viewHolder.artist.setText(item.getArtist());
            viewHolder.releaseDate.setText(item.getReleaseDate());
        }
        return convertView;
    }
}
