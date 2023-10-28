package br.upe.garanhus.esw.pweb.controle;

import java.io.IOException;
import br.upe.garanhus.esw.pweb.service.RequestService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/processa-imagem")
public class Exec02Servlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	Jsonb jsonb = JsonbBuilder.create();
       
    public Exec02Servlet() {
        super();
    }

    RequestService requestService = new RequestService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {    
        String resultado = requestService.getImagens();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resultado);
        response.setStatus(200);      
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resultado = requestService.getImagem(request.getReader());
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resultado);
        response.setStatus(200);
    }

}
