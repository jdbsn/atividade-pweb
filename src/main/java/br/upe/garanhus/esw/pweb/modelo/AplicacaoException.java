package br.upe.garanhus.esw.pweb.modelo;

public class AplicacaoException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  private final int codigoErro;

  public AplicacaoException(int codigoErro, String msg, Throwable cause) {
    super(msg, cause);
    this.codigoErro = codigoErro;
  }

  public int getCodigoErro() {
    return codigoErro;
  }
  
}
