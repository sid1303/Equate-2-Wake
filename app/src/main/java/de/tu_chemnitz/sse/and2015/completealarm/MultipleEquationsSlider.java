package de.tu_chemnitz.sse.and2015.completealarm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MultipleEquationsSlider extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int numberOfEquations = 3;
    private TextView Equation;
    private GenerateRandomEquation generateRandomEquation;
    private TextView EquationNumber;
    private Button check;
    private String result;
    private int equationsSolved = 0;
    private EditText UserInput;
    private TextView Result;
    private ArrayList<GenerateRandomEquation> multipleEquations = CreateMultipleEquations();

    public MultipleEquationsSlider(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return numberOfEquations;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.equation, container, false);

        Equation = (TextView) view.findViewById(R.id.equation);
        EquationNumber = (TextView)view.findViewById(R.id.equationNumber);
        check = (Button)view.findViewById(R.id.checkButton);

        String equation = multipleEquations.get(position).GenerateEquation();
        EquationNumber.setText(String.valueOf(position + 1));
        Equation.setText(equation);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View checkButton) {
                UserInput = (EditText)view.findViewById(R.id.user_input);
                result = multipleEquations.get(position).CheckEquationResult(UserInput.getText().toString());
                Result = (TextView)view.findViewById(R.id.result);
                Result.setText(String.valueOf(result));
                if (result.equals("Correct"))
                {
                    checkButton.setEnabled(false);
                    equationsSolved = equationsSolved + 1;
                        if (equationsSolved == 3) {

                            Toast.makeText(MultipleEquationsSlider.this.context,"ALL SOLVED",Toast.LENGTH_LONG).show();
                            Intent service_intent = new Intent(MultipleEquationsSlider.this.context,RingtonePlayingService.class);
                            MultipleEquationsSlider.this.context.stopService(service_intent);
                            Intent main_Activity_intent = new Intent(MultipleEquationsSlider.this.context,MainActivity.class);
                            MultipleEquationsSlider.this.context.startActivity(main_Activity_intent);
                        }
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    public ArrayList<GenerateRandomEquation> CreateMultipleEquations()
    {
        ArrayList<GenerateRandomEquation> multipleEquations = new ArrayList<GenerateRandomEquation>();

        for (int i =0; i<3; i++)
        {

            multipleEquations.add(new GenerateRandomEquation());

        }

        return  multipleEquations;
    }

}
