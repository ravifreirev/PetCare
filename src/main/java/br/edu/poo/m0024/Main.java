package br.edu.poo.m0024;
import br.edu.poo.m0024.model.*;
import br.edu.poo.m0024.dao.TutorDAO;
import br.edu.poo.m0024.dao.PetDAO;
import java.util.Scanner;
import br.edu.poo.m0024.api.ViaCepService;
import br.edu.poo.m0024.api.ViaCepResposta;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TutorDAO tutorDAO = new TutorDAO();
        PetDAO petDAO = new PetDAO();
        ViaCepService viaCepService = new ViaCepService();

        int opcao;

        do {

            System.out.println("Bem-vindo(a) ao PetCare!");

            System.out.println("========== PETCARE ==========");
            System.out.println("1 - Cadastrar tutor");
            System.out.println("2 - Listar tutores");
            System.out.println("3 - Atualizar tutor");
            System.out.println("4 - Excluir tutor");
            System.out.println("5 - Cadastrar pet");
            System.out.println("6 - Listar pets");
            System.out.println("7 - Atualizar pet");
            System.out.println("8 - Excluir pet");
            System.out.println("0 - Sair");
            System.out.println("=============================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            System.out.println("Você escolheu: " + opcao);

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do tutor: ");
                    scanner.nextLine();
                    String nome = scanner.nextLine();

                    System.out.print("Digite o CPF do tutor: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Digite o telefone do tutor: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Digite o CEP do tutor: ");
                    String cep = scanner.nextLine();

                    System.out.print("Digite o número da residência: ");
                    String numero = scanner.nextLine();

                    ViaCepResposta endereco = viaCepService.consultarCep(cep);

                    String enderecoCompleto = endereco.getLogradouro() + ", n° " + numero;
                    break;

                case 2:
                    tutorDAO.listar();
                    break;

                case 0:
                    System.out.println("Encerrando o PetCare. Até mais!");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;

            }
        } while (opcao != 0);

    }
}