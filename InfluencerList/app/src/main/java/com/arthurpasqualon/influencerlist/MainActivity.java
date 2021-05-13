package com.arthurpasqualon.influencerlist;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvInfluencers;
    private ArrayAdapter adapter;
    private List<Influencer> influencerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("action", "new");
                startActivity( intent );
            }
        });

        lvInfluencers = findViewById(R.id.lvInfluencers);

        loadInfluencers();

        configureListView();


    }

    private void configureListView() {

        lvInfluencers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Influencer selectedInfluencer = influencerList.get(position);
                deleteInfluencer(selectedInfluencer);
                return true;
            }
        });
    }

    private void deleteInfluencer(Influencer influencer){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setIcon(android.R.drawable.ic_input_delete);
        alert.setTitle(R.string.alertTitle);
        alert.setMessage(R.string.alertMessage);
        alert.setNeutralButton(R.string.cancel, null);
        alert.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                InfluencerDAO.delete(influencer.id, MainActivity.this);
                loadInfluencers();
            }
        });
        alert.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        loadInfluencers();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void loadInfluencers() {
        influencerList = InfluencerDAO.getInfluencers(this);
        adapter = new ArrayAdapter(this, R.layout.list_text, influencerList);
        lvInfluencers.setAdapter(adapter);
    }
}