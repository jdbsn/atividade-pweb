package br.upe.garanhus.esw.pweb.modelo.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.upe.garanhus.esw.pweb.excecoes.AplicacaoException;
import br.upe.garanhus.esw.pweb.modelo.RequestModel;
import br.upe.garanhus.esw.pweb.modelo.dtos.IdDTO;
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
    
    public List<RequestModel> obterImagens() throws AplicacaoException {
    
        List<RequestModel> imagens;
        
        try {
            HttpResponse<String> response = enviarRequisicao(URI_GET);
            
            imagens = jsonb.fromJson(
                response.body(), new ArrayList<RequestModel>(){}.getClass().getGenericSuperclass()
                );
            
            return imagens;
        } catch (Exception e) {
            throw new AplicacaoException(e.getMessage(), e); 
        }
    }
    
    public RequestModel obterImagemPorId(IdDTO iddto) throws IOException {
      
        RequestModel requestModel;
        
        try {     
            HttpResponse<String> resposta = enviarRequisicao(URI_POST + iddto.getId());
            
            if(resposta.statusCode() == 400) {
                throw new AplicacaoException(MSG_ID_INVALIDO, null);
            }
            
            requestModel = jsonb.fromJson(resposta.body(), RequestModel.class);
            
            return requestModel;
        } catch (Exception e) {
            throw new AplicacaoException(e.getMessage(), e);
        }
      
    }
    
    public HttpResponse<String> enviarRequisicao(String uri) throws IOException, InterruptedException  {
        HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();
        HttpResponse<String> resposta = client.send(requisicao, BodyHandlers.ofString());
        
        String resultado = resposta.body();
        
        logger.log(Level.INFO, resultado);
        
        return resposta;
    }
    
    public String jsonBuilder(BufferedReader reader) throws IOException {
        StringBuilder json = new StringBuilder();
        String linha;
        
        while((linha = reader.readLine()) != null){
          json.append(linha);
        }
        
        return json.toString();
    }
  
}
