package com.example.galugcuisine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.galugcuisine.Adapters.RandomRecipeAdapter;
import com.example.galugcuisine.Listeners.RandomRecipeResponseListener;
import com.example.galugcuisine.Listeners.RecipeClickListener;

import com.example.galugcuisine.Models.RandomRecipeApiResponse;

import java.util.ArrayList;


public class FavoritesActivity extends AppCompatActivity {

    // Classe de l'activité par des favoris

    ProgressDialog dialog;
    Database myDB;
    ListView listView;

    ArrayList<String> listItem;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        listView = findViewById(R.id.list_view);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        myDB = new Database(this);
        listItem = new ArrayList<>();

        viewData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = listView.getItemAtPosition(i).toString();
                String id = myDB.getIDRecetteAPI(text);
                //Toast.makeText(FavoritesActivity.this, ""+apiID, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(FavoritesActivity.this, RecipeDetailsActivity.class)
                        .putExtra("id", id));
            }
        });

        final Button button = findViewById(R.id.button_main);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                startActivity(new Intent(FavoritesActivity.this, MainActivity.class));
            }
        });

    }

    @SuppressLint("Range")
    private void viewData() {
        Cursor cursor = myDB.viewRecipe();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No favorite Recipes", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(cursor.getColumnIndex("Title")));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);
        }
    }
}