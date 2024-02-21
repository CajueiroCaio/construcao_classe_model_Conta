package conta.model;

public class Menu {
    public static void main(String[] args) {
        ContaBancaria novaConta = new ContaBancaria(32,453,2,"Caio",2345);

        System.out.println("Número da conta: " + novaConta.getNumero());
        System.out.println("Número da agência: " + novaConta.getAgencia());
        System.out.println("Tipo da conta: " + novaConta.getTipo());
        System.out.println("Titular da conta: " + novaConta.getTitular());
        System.out.println("Saldo da conta: " + novaConta.getSaldo());

    }
}
