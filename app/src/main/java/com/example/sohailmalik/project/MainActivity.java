package com.example.sohailmalik.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CallCompleteListener {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        StudentCollection.getInstance().fetchStudentList(this, this);
    }

    @Override
    public void onSuccess(final List<Student> students) {
        Log.e("onSuccess: ", students.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                StudentAdapter studentAdapter = new StudentAdapter(MainActivity.this, students);
                recyclerView.setAdapter(studentAdapter);
            }
        });
    }

   @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                moveTaskToBack(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onError(VolleyError volleyError) {
        volleyError.printStackTrace();
    }
}
