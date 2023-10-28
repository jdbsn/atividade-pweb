package br.upe.garanhus.esw.pweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.upe.garanhus.esw.pweb.IdDto;
import br.upe.garanhus.esw.pweb.exceptions.AplicacaoException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class RequestService {

  private static final String URI_GET = "https://api.thecatapi.com/v1/images/search?limit=10";
  private static final String URI_POST = "https://api.thecatapi.com/v1/images/";
  private static final String MSG_ID_INVALIDO = "ID inválido.";
  private static final String MSG_ERRO = "Ocorreu um erro.";
  
  private HttpClient client;
  private Jsonb jsonb = JsonbBuilder.create();
  private Logger logger;

  
  public RequestService() {
    this.client = HttpClient.newBuilder().build();
    this.logger = Logger.getLogger(RequestService.class.getName());
  }
  
  public String getImagens() throws AplicacaoException {

    try {
      HttpResponse<String> response = sendRequest(URI_GET);
      
      return response.body();
    } catch (IOException | InterruptedException e) {
      logger.log(Level.SEVERE, MSG_ERRO, e);
      throw new AplicacaoException(MSG_ERRO, e); 
    }
  }
  
  public String getImagem(BufferedReader entrada) throws IOException {
    
    StringBuilder json = jsonBuilder(entrada);
    
    IdDto iddto = new IdDto();
    iddto = jsonb.fromJson(json.toString(), IdDto.class);
    
    try {     
      HttpResponse<String> response = sendRequest(URI_POST + iddto.id);

      if(response.statusCode() == 400) {
        logger.log(Level.SEVERE, MSG_ID_INVALIDO);
        throw new AplicacaoException(MSG_ID_INVALIDO, null);
      }
      
        return response.body();
    } catch (IOException | InterruptedException e) {
      logger.log(Level.SEVERE, MSG_ERRO, e);
      throw new AplicacaoException(MSG_ERRO, e);
    }
    
  }
  
  public HttpResponse<String> sendRequest(String uri) throws IOException, InterruptedException  {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    
    return response;
  }
  
  public StringBuilder jsonBuilder(BufferedReader reader) throws IOException {
    StringBuilder json = new StringBuilder();
    String linha;
    
    while((linha = reader.readLine()) != null){
      json.append(linha);
    }
    
    return json;
  }
  
}
