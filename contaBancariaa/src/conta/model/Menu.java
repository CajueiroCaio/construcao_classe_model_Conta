package conta.model;

public class Menu {
    public static void main(String[] args) {
        ContaCorrente contaCorrente1 = new ContaCorrente(35,32,1,"Caio", 5000.0f,3000.0f);

        System.out.println("Saldo inicial: " + contaCorrente1.getSaldo());

        contaCorrente1.sacar(2000.00f);

        System.out.println("Saldo final: " + contaCorrente1.getSaldo());

        contaCorrente1.visualizar();

        ContaPoupanca contaPoupanca1 = new ContaPoupanca(37,33,2,"Cajueiro",9000.0f,15);

        contaPoupanca1.visualizar();
    }
}
