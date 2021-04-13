package user;

public class User {
  private String nome;
  private double saldo;

  public User(){
    this.nome = "sem nome";
    this.saldo = 3.0;
  }

  public User(String nome, double saldo) {
    this.nome = nome;
    this.saldo = saldo;
  }

  @Override
  public String toString() {
    return "\n" + "Nome: " + nome + "\n" + "Saldo: " + saldo + "\n";
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}
