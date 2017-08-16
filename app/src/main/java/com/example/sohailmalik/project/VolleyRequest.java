package com.example.sohailmalik.project;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sohail malik on 8/8/2017.
 *
 */
public class VolleyRequest {

    private static VolleyRequest volleyRequest;
    private RequestQueue requestQueue;
    private Context context;

    private VolleyRequest(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequest getInstance(Context context) {
        if (volleyRequest == null) {
            volleyRequest = new VolleyRequest(context);
        }
        return volleyRequest;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
