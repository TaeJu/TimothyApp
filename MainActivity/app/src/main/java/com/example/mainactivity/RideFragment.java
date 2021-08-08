package com.example.mainactivity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RideFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RideFragment newInstance(String param1, String param2) {
        RideFragment fragment = new RideFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private AlertDialog dialog;

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        Button rideQueueResetButton = (Button) getView().findViewById(R.id.rideQueueReset);
        rideQueueResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;
                MainActivity.rideQueue.clear();
                System.out.println(MainActivity.rideQueue.getAl().size());
                android.app.AlertDialog dialog;
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                dialog = builder.setMessage("라이드 서비스 리셋 완료.")
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();

            }
        });

        Button rideQueueStartButton = (Button) getView().findViewById(R.id.rideQueueStart);
        rideQueueStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;
                MainActivity.rideQueue.update();
                MainActivity.rideQueue.fill();
                System.out.println(MainActivity.rideQueue.getAl().size());
                android.app.AlertDialog dialog;
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                dialog = builder.setMessage("라이드 서비스 시작 완료.")
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();

            }
        });

        Button registerCancelRiderButton = (Button) getView().findViewById(R.id.registerRiderCancel);
        registerCancelRiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("라이더 등록 취소.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            } else {
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("오류.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RiderCancelRequest riderCancelRequest = new RiderCancelRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RideFragment.this.getActivity());
                queue.add(riderCancelRequest);
            }
        });

        Button registerRiderButton = (Button) getView().findViewById(R.id.registerRider);
        registerRiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("라이더 등록 선공.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            } else {
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("오류.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RiderRequest rideRegisterRequest = new RiderRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RideFragment.this.getActivity());
                queue.add(rideRegisterRequest);
            }
        });

        Button requestCancelRideButton = (Button) getView().findViewById(R.id.requestCancelRide);
        requestCancelRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                System.out.println(MainActivity.rideQueue.getAl().size());
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("라이드 취소 선공.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            } else {
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("오류.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RideCancelRequest rideCancelRequest = new RideCancelRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RideFragment.this.getActivity());
                queue.add(rideCancelRequest);
            }
        });


        Button requestRideButton = (Button) getView().findViewById(R.id.requestRide);
        requestRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = MainActivity.userID;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                MainActivity.rideQueue.found = false;
                                MainActivity.rideQueue.addIn(MainActivity.userID);
                                MainActivity.rideQueue.printAll();
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("라이드 신청 선공.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            } else {
                                android.app.AlertDialog dialog;
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideFragment.this.getActivity());
                                dialog = builder.setMessage("라이드 이미 신청 했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RideRequest rideRequest = new RideRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RideFragment.this.getActivity());
                queue.add(rideRequest);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride, container, false);
    }


}