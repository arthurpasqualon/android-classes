package com.arthurpasqualon.influencerlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etUsername;
    private EditText etCategory;
    private Button btnSave;
    private Influencer influencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etName = findViewById(R.id.etInfluencerName);
        etUsername = findViewById(R.id.etInfluencerUsername);
        etCategory = findViewById(R.id.etCategory);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFormData();
            }
        });
    }

    private void saveFormData() {

        if(etName.getText().toString().isEmpty() || etUsername.toString().isEmpty() || etCategory.toString().isEmpty()) {
            Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
        }
        else {
            influencer = new Influencer();
            influencer.name = etName.getText().toString();
            influencer.username = etUsername.getText().toString();
            influencer.category = etCategory.getText().toString();

            InfluencerDAO.insert(influencer, this);
            etName.setText("");
            etUsername.setText("");
            etCategory.setText("");
            finish();
        }
    }
}