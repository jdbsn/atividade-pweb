package br.upe.garanhus.esw.pweb.exceptions;

public class AplicacaoException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public AplicacaoException(String msg, Throwable cause) {
    super(msg, cause);
  }

  
}
