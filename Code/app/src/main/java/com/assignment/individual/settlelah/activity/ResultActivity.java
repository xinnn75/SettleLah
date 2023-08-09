package com.assignment.individual.settlelah.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.individual.settlelah.R;
import com.assignment.individual.settlelah.activity.Adapter.ResultAdapter;
import com.assignment.individual.settlelah.activity.Model.Result;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().hide();

        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        List<Result> splitResults = dbHelper.getAllResults();

        ResultAdapter adapter = new ResultAdapter(splitResults);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
