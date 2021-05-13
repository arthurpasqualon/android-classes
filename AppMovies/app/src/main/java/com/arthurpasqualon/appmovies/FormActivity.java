package com.arthurpasqualon.appmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private EditText etName;
    private Spinner spYear;
    private Button btnSave;
    private String action;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etName = findViewById(R.id.etMovieName);
        spYear = findViewById(R.id.spYear);
        btnSave = findViewById(R.id.btnSalvar);

        action = getIntent().getStringExtra("action");
        if(action.equals("edit")){
            loadFormData();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFormData();
            }
        });
    }

    private void loadFormData() {
        int idMovie = getIntent().getIntExtra("idMovie",0);

        if(idMovie != 0) {
            movie = MovieDAO.getMovieById(this, idMovie);
            etName.setText(movie.name);
            String[] arrYear = getResources().getStringArray(R.array.arrYear);
            for(int i = 1; i < arrYear.length; i++){
                if(Integer.valueOf(arrYear[i]) == movie.getYear()){
                    spYear.setSelection(i);
                }
            }
        }
    }

    private void saveFormData() {

        if(spYear.getSelectedItemPosition() != 0 || etName.getText().toString() != "") {
            if (action.equals("new")) {
                movie = new Movie();
            }

            movie.name = etName.getText().toString();
            movie.setYear(Integer.valueOf(spYear.getSelectedItem().toString()));

            if (action.equals("edit")){
                MovieDAO.edit(movie, this);
                finish();
            } else {
                MovieDAO.insert(movie, this);
                etName.setText("");
                spYear.setSelection(0);
            }
        }
        else {
            Toast.makeText(this, "Preencha todos os campos para prosseguir", Toast.LENGTH_SHORT).show();
        }
    }
}