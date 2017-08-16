package com.example.sohailmalik.project;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by sohail malik on 8/8/2017.
 *
 */
public interface CallCompleteListener {

    void onSuccess(List<Student> students);
    void onError(VolleyError volleyError);
}
