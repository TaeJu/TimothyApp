package com.example.mainactivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RideCancelRequest extends StringRequest {
    final static private String URL = "https://xown17.cafe24.com/RideCancelRequest.php";
    private Map<String, String> parameters;

    public RideCancelRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
