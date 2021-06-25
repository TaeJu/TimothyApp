package com.example.mainactivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class SpeechListAdapter extends BaseAdapter {

    private Context context;
    private List<Speech> speechList;

    public SpeechListAdapter(Context context, List<Speech> speechList) {
        this.context = context;
        this.speechList = speechList;
    }

    @Override
    public int getCount() {
        return speechList.size();
    }

    @Override
    public Object getItem(int position) {
        return speechList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.speech, null);

        TextView speechID = (TextView) v.findViewById(R.id.speechID);
        TextView speechDay = (TextView) v.findViewById(R.id.speechDay);
        TextView speechYear = (TextView) v.findViewById(R.id.speechYear);
        TextView speechEra = (TextView) v.findViewById(R.id.speechEra);
        TextView speechTitle = (TextView) v.findViewById(R.id.speechTitle);
        TextView speechPerson = (TextView) v.findViewById(R.id.speechPerson);
        TextView speechContent = (TextView) v.findViewById(R.id.speechContent);
        TextView speechLink = (TextView) v.findViewById(R.id.speechLink);


        speechID.setText(speechList.get(position).getSpeechID());
        speechDay.setText(speechList.get(position).getSpeechDay());
        speechYear.setText(speechList.get(position).getSpeechYear());
        speechEra.setText(speechList.get(position).getSpeechEra());
        speechTitle.setText(speechList.get(position).getSpeechTitle());
        speechPerson.setText(speechList.get(position).getSpeechPerson());
        speechContent.setText(speechList.get(position).getSpeechContent());
        speechLink.setText(speechList.get(position).getSpeechLink());

        v.setTag(speechList.get(position).getSpeechID());
        return v;
    }

}
