package br.upe.garanhus.esw.pweb;

import jakarta.json.bind.annotation.JsonbProperty;

public class Erro {

  @JsonbProperty("codigo")
  private int codigo;
  @JsonbProperty("mensagem")
  private String mensagem;
  @JsonbProperty("origem")
  private String origem;
  
  public Erro(int codigo, String mensagem, String origem) {
    this.codigo = codigo;
    this.mensagem = mensagem;
    this.origem = origem;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public String getOrigem() {
    return origem;
  }
  
}
