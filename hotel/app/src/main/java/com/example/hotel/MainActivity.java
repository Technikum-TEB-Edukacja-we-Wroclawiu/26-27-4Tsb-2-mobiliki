package com.example.hotel;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.spec.EdDSAParameterSpec;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MainActivity extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[3];
    int selectedRoom = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


         imageViews[0] = findViewById(R.id.room1);
         imageViews[1] = findViewById(R.id.room2);
         imageViews[2] = findViewById(R.id.room3);

        imageViews[0].setOnClickListener( v -> imageClicked(0));
        imageViews[1].setOnClickListener(v -> imageClicked(1));
        imageViews[2].setOnClickListener(v -> imageClicked(2));

        Button button = findViewById(R.id.reserveButton);
        button.setOnClickListener(v -> {
            EditText arrivalDate = findViewById(R.id.startDate);
            EditText departureDate = findViewById(R.id.endDate);
            try{
                LocalDate arrival = LocalDate.parse(arrivalDate.getText(),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                LocalDate departure = LocalDate.parse(departureDate.getText(),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                Toast.makeText(getBaseContext(), "Błędna data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imageClicked(int index){
        GradientDrawable border = new GradientDrawable();
        border.setShape(GradientDrawable.RECTANGLE);
        border.setStroke(10, Color.BLACK);
        imageViews[index].setBackground(border);
        // jeśli coś wybrano
        if (selectedRoom >= 0)  imageViews[selectedRoom].setBackground(null);
        selectedRoom = index;
    }
}