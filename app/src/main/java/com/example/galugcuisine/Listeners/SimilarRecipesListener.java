package com.example.galugcuisine.Listeners;

import com.example.galugcuisine.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}
