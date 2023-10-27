package br.upe.garanhus.esw.pweb.controle;

import java.io.IOException;
import br.upe.garanhus.esw.pweb.service.RequestService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/processa-imagem")
public class Exec02Servlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
    public Exec02Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    RequestService requestService = new RequestService();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String resultado = requestService.getImagens();
      
      response.getWriter().write(resultado);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.getWriter().write("Post");
      
    }

}
