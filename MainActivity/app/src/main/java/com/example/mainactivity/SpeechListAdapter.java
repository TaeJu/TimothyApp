package com.example.mainactivity;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SpeechListAdapter extends BaseAdapter {

    private Context context;
    private List<Speech> speechList;
    private Fragment parent;
    private String userID = MainActivity.userID;
    private List<Integer> speechIDList;

    public SpeechListAdapter(Context context, List<Speech> speechList, Fragment parent) {
        this.context = context;
        this.speechList = speechList;
        this.parent = parent;
        speechIDList = new ArrayList<Integer>();
        new BackgroundTask().execute();
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
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

        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;
                if (!alreadyIn(speechIDList, Integer.parseInt(speechList.get(position).getSpeechID()))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("이미 추가한 말씀입니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("말씀이 추가되었습니다.")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    speechIDList.add(Integer.parseInt(speechList.get(position).getSpeechID()));
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("말씀 추가에 실패하엿습니다.")
                                            .setNegativeButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    AddRequest addRequest = new AddRequest(userID, speechList.get(position).getSpeechID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                    queue.add(addRequest);
                }
            }
        });

        return v;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://xown17.cafe24.com/SpecchListException.php?userID=" + URLEncoder.encode(userID, "UTF-8");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String speechPerson;
                String speechYear;
                String speechID;
                while(count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    speechID = object.getString("speechID");
                    speechYear = object.getString("speechYear");
                    speechPerson = object.getString("speechPerson");
                    speechIDList.add(Integer.parseInt(speechID));
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public boolean alreadyIn(List<Integer> speechIDList, int item) {
        for(int i = 0; i < speechIDList.size(); i++) {
            if (speechIDList.get(i) == item) {
                return false;
            }
        }
        return true;
    }

}
