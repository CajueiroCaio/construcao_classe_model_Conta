package conta.model;

import conta.controller.ContaController;

import conta.util.Cores;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.IOException;

public class Menu {
    public static void main(String[] args) {

        Scanner leia = new Scanner(System.in);

        int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        ContaController contas = new ContaController();

        opcao = 0;

        while (true) {

            System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
                    + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                BANCO DO BRAZIL COM Z                ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Numero              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            9 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_RESET);

            try {
                opcao = leia.nextInt();
            } catch(InputMismatchException e){
                System.out.println("\nDigite valores inteiros!");
                leia.nextLine();
                opcao=0;
            }

            if (opcao == 9) {
                System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
                sobre();
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n Criar Conta");

                    System.out.println("Digite o número da agência: ");
                    agencia = leia.nextInt();
                    System.out.println("Digite o número do titular: ");
                    leia.skip("\\R?");
                    titular = leia.nextLine();

                    do {
                        System.out.println("Digite o tipo de conta: 1 - Conta Corrente ou 2 - Conta Poupança");
                        tipo = leia.nextInt();
                    } while(tipo < 1 && tipo > 2);

                    System.out.println("Digite o saldo da conta: ");
                    saldo = leia.nextFloat();

                    switch (tipo) {
                        case 1:
                            System.out.println("Digite o limite de crédito: ");
                            limite = leia.nextFloat();
                            contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                            break;
                        case 2:
                            System.out.println("Digite a data de aniversário da conta: ");
                            aniversario = leia.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                            break;
                    }
                    keyPress();
                    break;
                case 2:
                    System.out.println("\n Listar todas as Contas");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println("\n Buscar Conta por número");
                    numero = leia.nextInt();

                    contas.procurarPorNumero(numero);

                    keyPress();
                    break;
                case 4:
                    System.out.println("\n Atualizar dados da Conta");

                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    var buscaConta = contas.buscarNaCollection(numero);

                    if (buscaConta != null) {

                        tipo = buscaConta.getTipo();

                        System.out.println("Digite o número da agência: ");
                        agencia = leia.nextInt();
                        System.out.println("Digite o número do titular: ");
                        leia.skip("\\R?");
                        titular = leia.nextLine();

                        System.out.println("Digite o saldo da conta (R$): ");
                        saldo = leia.nextFloat();;

                        switch (tipo) {
                            case 1:
                                System.out.println("Digite o limite de crédito: ");
                                limite = leia.nextFloat();
                                contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                                break;
                            case 2:
                                System.out.println("Digite a data de aniversário da conta: ");
                                aniversario = leia.nextInt();
                                contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                                break;
                            default:
                                System.out.println("Tipo de conta inválida");
                                break;

                        }

                    } else {
                        System.out.println("A conta não foi encontrada");
                    }

                    keyPress();
                    break;
                case 5:
                    System.out.println("\n Apagar Conta");

                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    contas.deletar(numero);

                    keyPress();
                    break;
                case 6:
                    System.out.println("\n Sacar");

                    System.out.println("Digite um número da conta: ");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o valor do saque (R$): ");
                        valor = leia.nextFloat();
                    } while (valor <= 0);

                    contas.sacar(numero, valor);

                    keyPress();
                    break;
                case 7:
                    System.out.println("\n Depositar");

                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o valor do deposito (R$): ");
                        valor = leia.nextFloat();
                    } while (valor <= 0);

                    contas.depositar(numero, valor);

                    keyPress();
                    break;
                case 8:
                    System.out.println("\n Transferir");

                    System.out.println("Digite o número da conta de origem: ");
                    numero = leia.nextInt();
                    System.out.println("Digite o número da conta de destino: ");
                    numeroDestino = leia.nextInt();

                    do {
                        System.out.println("Digite o valor da transferência (R$): ");
                        valor = leia.nextFloat();
                    } while (valor <= 0);

                    contas.transferir(numero, numeroDestino, valor);

                    keyPress();
                    break;
                default:
                    System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);

                    keyPress();
                    break;
            }
        }
    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: ");
        System.out.println("Caio Vinícius Cajueiro dos Santos");
        System.out.println("github.com/CajueiroCaio");
        System.out.println("*********************************************************");
    }

    public static void keyPress() {

        try {

            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
            System.in.read();

        } catch (IOException e) {

            System.out.println("Você pressionou uma tecla diferente de enter!");

        }
    }
    }

