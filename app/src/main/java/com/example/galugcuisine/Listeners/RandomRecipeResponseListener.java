package com.example.galugcuisine.Listeners;

import com.example.galugcuisine.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);

}
