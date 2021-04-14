package store.utils;

public class Produto {
  private String codigo;
  private String nome;
  private double preco;

  public Produto(String codigo, String nome, double preco) {
    this.codigo = codigo;
    this.nome = nome;
    this.preco = preco;
  }

  public String getCodigo() {
    return codigo;
  }

  public double getPreco() {
    return preco;
  }

  @Override
  public String toString() {
    String divide = "------- \n";
    return divide + codigo + "\n" + nome + "\n" + preco + "\n" + divide;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !other.getClass().equals(Produto.class)) {
      return false;
    } else {
      Produto otherProduto = (Produto) other;
      return this.codigo.equals(otherProduto.codigo);
    }
  }

  @Override
  public int hashCode() {
    return this.codigo.hashCode();
  }
}
