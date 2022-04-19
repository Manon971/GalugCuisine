package com.example.galugcuisine.Listeners;

import com.example.galugcuisine.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
