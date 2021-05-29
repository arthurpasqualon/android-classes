package com.arthurpasqualon.appproducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProducts;
    private ProgressBar progressBar;
    private List<Product> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProducts = findViewById(R.id.lvProducts);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setActivated(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add new product");
        menu.add(getResources().getString(R.string.txtClose));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.toString().equals(getResources().getString(R.string.txtClose))) {
            finish();
        }
        if (item.toString().equals("Add new product")) {
            newProduct();
        }
        return super.onOptionsItemSelected(item);
    }

    private void newProduct() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Adicionar novo produto");
        alert.setIcon(android.R.drawable.ic_menu_add);

        EditText etName = new EditText(this);
        etName.setHint("Product Name");
        alert.setView(etName);
        alert.setNeutralButton("Cancel", null);
        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String text = etName.getText().toString();
                if(text.isEmpty()){
                    Toast.makeText(MainActivity.this, "Product must have a name", Toast.LENGTH_SHORT).show();
                } else {
                    AddProduct addProduct = new AddProduct();
                    addProduct.execute("nome="+text+"&preco=0"+"&quantidade=0");
                }
            }
        });
        alert.show();
    }

    private class AddProduct extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setActivated(true);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            return Services.execute("addProdutos.php", strings);
        }

        @Override

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if( s != null ){
                try {
                    JSONObject json = new JSONObject( s );
                    int id =  json.getInt("id");
                    if( id > 0 ){

                        Toast.makeText(MainActivity.this,
                                "Product Added: "+ id , Toast.LENGTH_LONG).show();
                        refresh();
                    }else {
                        Toast.makeText(MainActivity.this,
                                "Error inserting product!" , Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){
                    Toast.makeText(MainActivity.this,
                            "Server Error!" , Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(MainActivity.this,
                        "Server Error!" , Toast.LENGTH_LONG).show();
            }
            progressBar.setActivated(false);
            progressBar.setVisibility( View.INVISIBLE );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();

    }

    private void refresh() {
        SearchProducts connect = new SearchProducts();
        connect.execute();
    }

    private class SearchProducts extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setActivated(true);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            return Services.execute("produtos.php", strings);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s!= null){
                productsList = jsonToProducts(s);
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, productsList);
                lvProducts.setAdapter(adapter);
            }
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setActivated(false);
        }
    }

    private List<Product> jsonToProducts(String json){
        List<Product> list = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arr = jsonObject.getJSONArray("produtos");

            if(arr.length() > 0){
                for(int i=0; i < arr.length(); i++){
                    JSONObject obj = arr.getJSONObject(i);
                    Product product = new Product();
                    product.setId(obj.getInt("id"));
                    product.setName(obj.getString("nome"));
                    product.setPrice(obj.getDouble("preco"));
                    list.add(product);
                }
            }
        }catch (JSONException exception){
        }

        return list;
    }

}

