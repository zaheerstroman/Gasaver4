package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.gasaver.R;

public class SelectCityActivity extends AppCompatActivity {

    RecyclerView rv_cities, rv_autoSearch;
    ImageView iv_back;
    EditText et_autoSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
    }
}