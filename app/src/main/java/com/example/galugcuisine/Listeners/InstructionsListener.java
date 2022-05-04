package com.example.galugcuisine.Listeners;

import com.example.galugcuisine.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
