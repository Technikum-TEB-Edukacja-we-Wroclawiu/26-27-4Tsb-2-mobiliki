package com.example.informator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private int[] zdjecia = {
            R.drawable.kot1,
            R.drawable.kot2,
            R.drawable.kot3,
            R.drawable.kot4
    };

    private int wybor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNext = findViewById(R.id.next);
        Button buttonPrev = findViewById(R.id.prev);
        ImageView imageView = findViewById(R.id.zdjecie);
        EditText numer = findViewById(R.id.numer);
        Switch tlo = findViewById(R.id.tlo);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wybor = (wybor+1)%4;
                imageView.setImageResource(zdjecia[wybor]);
            }
        });


        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wybor == 0)
                    wybor = 3;
                else
                    wybor--;
                imageView.setImageResource(zdjecia[wybor]);
            }
        });

        numer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String zawartosc = editable.toString();
                try{
                    int numer = Integer.parseInt(zawartosc);
                    if(numer >= 0 && numer < zdjecia.length){
                        imageView.setImageResource(zdjecia[numer]);
                        wybor = numer;
                    }
                }catch (NumberFormatException e){

                }

            }
        });

        tlo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ConstraintLayout layout = findViewById(R.id.layout);
                if(b){
                    layout.setBackgroundColor(Color.parseColor("#1565c0"));
                }else{
                    layout.setBackgroundColor(Color.parseColor("#00796B"));
                }
            }
        });
    }
}