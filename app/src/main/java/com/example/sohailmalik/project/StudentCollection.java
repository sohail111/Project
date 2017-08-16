package com.example.sohailmalik.project;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sohail malik on 8/8/2017.
 */
public class  StudentCollection {

    private static StudentCollection studentCollection;
    private ArrayList<Student> studentArrayList;

    private StudentCollection() {
        studentArrayList = new ArrayList<>();
    }

    public static StudentCollection getInstance() {
        if (studentCollection == null) {
            studentCollection = new StudentCollection();
            return studentCollection;
        }
        return studentCollection;
    }

    public void fetchStudentList(final Context context, final CallCompleteListener callCompleteListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://api.myjson.com/bins/2cl6n", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String imageUrl = jsonObject.getString("image");
                        String name = jsonObject.getString("name");
                        String city = jsonObject.getString("city");
                        String email = jsonObject.getString("email");
                        boolean gender = jsonObject.getBoolean("gender");
                        studentArrayList.add(new Student(imageUrl, name, city, email, gender));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callCompleteListener.onSuccess(studentArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callCompleteListener.onError(error);
            }
        });
        VolleyRequest.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
