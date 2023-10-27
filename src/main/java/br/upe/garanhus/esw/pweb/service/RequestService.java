package br.upe.garanhus.esw.pweb.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RequestService {

  private static final String URI_GET = "https://api.thecatapi.com/v1/images/search";
  
  public String getImagens() {
    
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URI_GET)).GET().build();
    
    HttpResponse<String> response;
    try {
      response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
      
      return response.body();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
}
