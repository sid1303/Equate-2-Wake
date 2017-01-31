package de.tu_chemnitz.sse.and2015.completealarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;


public class EquationDisplay extends Activity {

    private HorizontalInfiniteCycleViewPager multipleEquations;
    private MultipleEquationsSlider adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_equations);

        multipleEquations = (HorizontalInfiniteCycleViewPager)findViewById(R.id.multipleEquations);
        adapter = new MultipleEquationsSlider(this);
        multipleEquations.setAdapter(adapter);



    }





}