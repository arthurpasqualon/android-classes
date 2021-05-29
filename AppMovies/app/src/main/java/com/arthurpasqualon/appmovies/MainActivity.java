package com.arthurpasqualon.appmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovies;
    private ArrayAdapter adapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        List<String> spinnerArr =  new ArrayList<String>();

        spinnerArr.add("Manhã");

        spinnerArr.add("tarde");

        spinnerArr.add("Noite");


        ArrayAdapter<String> adpt = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, spinnerArr);

        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spItems = (Spinner) findViewById(R.id.spShifts);
        spItems.setAdapter(adapter);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("action", "new");
                startActivity( intent );
            }
        });

        lvMovies = findViewById(R.id.lvMovies);

        loadMovies();

        configureListView();


    }

    private void configureListView() {

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie selectedMovie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("idFilme", selectedMovie.id);
                startActivity( intent );
            }
        });

        lvMovies.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie selectedMovie = movieList.get(position);
                deleteMovie(selectedMovie);
                return true;
            }
        });
    }

    private void deleteMovie(Movie movie){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setIcon(android.R.drawable.ic_input_delete);
        alert.setTitle(R.string.alertTitle);
        alert.setMessage(R.string.alertMessage + movie.name);
        alert.setNeutralButton("Cancelar", null);
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MovieDAO.delete(movie.id, MainActivity.this);
                loadMovies();
            }
        });
        alert.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        loadMovies();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void loadMovies() {
        movieList = MovieDAO.getMovies(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movieList);
        lvMovies.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}