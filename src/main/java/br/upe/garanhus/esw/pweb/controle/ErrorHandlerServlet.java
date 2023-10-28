package br.upe.garanhus.esw.pweb.controle;

import java.io.IOException;
import br.upe.garanhus.esw.pweb.Erro;
import br.upe.garanhus.esw.pweb.exceptions.AplicacaoException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ErrorHandlerServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    processarErro(request, response);
	}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processarErro(request, response);
    }
    
    private void processarErro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AplicacaoException appExc = (AplicacaoException) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        
        
        
        Erro erro = new Erro(500, appExc.getMessage(), appExc.getCause().getClass().getName());
        Jsonb jsonb = JsonbBuilder.create();
        String resultado = jsonb.toJson(erro);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(resultado);
        
        response.setStatus(500);
    }

}
