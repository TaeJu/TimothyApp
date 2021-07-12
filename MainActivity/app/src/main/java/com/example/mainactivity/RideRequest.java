package com.example.mainactivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RideRequest extends StringRequest {
    final static private String URL = "https://xown17.cafe24.com/RideRequest.php";
    private Map<String, String> parameters;

    public RideRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
