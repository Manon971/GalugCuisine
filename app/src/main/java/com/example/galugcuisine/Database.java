package com.example.galugcuisine;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.galugcuisine.Models.Favorite;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    //On definit les différentes colonnes en dans notre table
    public static final String IDRecette = "ID";
    public static final String IDRecetteAPI = "ApiID";
    public static final String TitreRecette = "Title";
    public Database(@Nullable Context context) {
        super(context, "FavRecipeDB", null, 2);

    }

    //Permet de créer la base de donnée
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableFavRecipe= "CREATE TABLE " + "FavRecipe" + "( "+ IDRecette + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + IDRecetteAPI + " String DEFAULT '991010',"
                + TitreRecette + " String DEFAULT 'Recipe');";
        db.execSQL(createTableFavRecipe);
    }


    //Permet de supprimer les données deja existantes lors d'un changement de version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "FavRecipe");
        onCreate(db);
    }

    public boolean insertRecipe (String idRecette, String titre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDRecetteAPI, idRecette);
        contentValues.put(TitreRecette, titre);
        db.insert("FavRecipe", null, contentValues);

        return true;
    }

    public boolean removeRecipe (String idRecette) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("FavRecipe", IDRecetteAPI+" = ?", new String[]{idRecette});

        return true;
    }


    public Cursor viewRecipe() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from FavRecipe";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }


    public boolean checkFavorite (String idRecette) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from FavRecipe where " + IDRecetteAPI + " = ?" ;
        Cursor cursor = db.rawQuery(query, new String[]{idRecette});

        return cursor.getCount() > 0;
    }

    public String getIDRecetteAPI (String titre) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select ApiID from FavRecipe where " + TitreRecette + " = ?" ;
        Cursor cursor = db.rawQuery(query, new String[]{titre});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

}

