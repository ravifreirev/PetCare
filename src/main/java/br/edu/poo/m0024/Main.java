package br.edu.poo.m0024;

import br.edu.poo.m0024.api.ViaCepResposta;
import br.edu.poo.m0024.api.ViaCepService;
import br.edu.poo.m0024.dao.PetDAO;
import br.edu.poo.m0024.dao.TutorDAO;
import br.edu.poo.m0024.model.Pet;
import br.edu.poo.m0024.model.Tutor;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TutorDAO tutorDAO = new TutorDAO();
    private static final PetDAO petDAO = new PetDAO();
    private static final ViaCepService viaCepService = new ViaCepService();

    public static void main(String[] args) {

        System.out.println("Bem-vindo(a) ao PetCare!");
        System.out.println();

        int opcao;

        do {
            exibirMenu();

            opcao = lerInt("Escolha uma opção: ");

            System.out.println();

            try {
                switch (opcao) {
                    case 1:
                        cadastrarTutor();
                        break;
                    case 2:
                        tutorDAO.listar();
                        break;
                    case 3:
                        atualizarTutor();
                        break;
                    case 4:
                        excluirTutor();
                        break;
                    case 5:
                        cadastrarPet();
                        break;
                    case 6:
                        petDAO.listar();
                        break;
                    case 7:
                        atualizarPet();
                        break;
                    case 8:
                        excluirPet();
                        break;
                    case 0:
                        System.out.println("Encerrando o PetCare. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (IllegalArgumentException e) {
                // Erros de validação lançados pelos setters (CPF inválido, nome vazio etc.)
                System.out.println("Dado inválido: " + e.getMessage());
            } catch (RuntimeException e) {
                // Erros de banco de dados, de API etc.
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();

        } while (opcao != 0);

        viaCepService.fechar();
        scanner.close();
    }

    // ================= MENU =================

    private static void exibirMenu() {
        System.out.println("=================================");
        System.out.println("            PETCARE");
        System.out.println("=================================");
        System.out.println("  TUTORES");
        System.out.println("    1 - Cadastrar tutor");
        System.out.println("    2 - Listar tutores");
        System.out.println("    3 - Atualizar tutor");
        System.out.println("    4 - Excluir tutor");
        System.out.println();
        System.out.println("  PETS");
        System.out.println("    5 - Cadastrar pet");
        System.out.println("    6 - Listar pets");
        System.out.println("    7 - Atualizar pet");
        System.out.println("    8 - Excluir pet");
        System.out.println();
        System.out.println("    0 - Sair");
        System.out.println("=================================");
    }

    // ================= TUTOR =================

    private static void cadastrarTutor() {

        System.out.print("Digite o nome do tutor: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do tutor: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o telefone do tutor: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o CEP do tutor: ");
        String cep = scanner.nextLine();

        // Valida o CEP ANTES de chamar a API (evita erro no ViaCEP)
        String cepLimpo = cep.trim().replace("-", "");
        if (!cepLimpo.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido. Digite 8 números.");
        }

        System.out.print("Digite o número da residência: ");
        String numero = scanner.nextLine();

        ViaCepResposta endereco = viaCepService.consultarCep(cepLimpo);

        // O ViaCEP responde "erro" para CEP que não existe: vem tudo nulo
        if (endereco == null || endereco.getLocalidade() == null) {
            System.out.println("CEP não encontrado! Cadastro cancelado.");
            return;
        }

        String logradouro = endereco.getLogradouro();

        if (logradouro == null || logradouro.isBlank()) {
            System.out.print("O ViaCEP não informou a rua. Digite o nome da rua: ");
            logradouro = scanner.nextLine();
        }

        String enderecoCompleto = logradouro + ", n° " + numero;
        String cidade = endereco.getLocalidade();
        String estado = endereco.getUf();

        Tutor tutor = new Tutor(
                nome,
                cpf,
                telefone,
                cepLimpo,
                enderecoCompleto,
                cidade,
                estado
        );

        tutorDAO.cadastrar(tutor);

        System.out.println();
        System.out.println("Tutor cadastrado com sucesso!");
    }

    private static void atualizarTutor() {

        int id = lerInt("Digite o ID do tutor que deseja atualizar: ");

        Tutor tutorEncontrado = tutorDAO.buscarPorId(id);

        if (tutorEncontrado == null) {
            System.out.println("Tutor não encontrado!");
            return;
        }

        System.out.println("Tutor encontrado: " + tutorEncontrado.getNome());

        System.out.print("Digite o novo nome do tutor: ");
        String novoNome = scanner.nextLine();

        tutorEncontrado.setNome(novoNome);

        tutorDAO.atualizar(tutorEncontrado);

        System.out.println();
        System.out.println("Tutor atualizado com sucesso!");
    }

    private static void excluirTutor() {

        int id = lerInt("Digite o ID do tutor que deseja excluir: ");

        Tutor tutorParaExcluir = tutorDAO.buscarPorId(id);

        if (tutorParaExcluir == null) {
            System.out.println("Tutor não encontrado!");
            return;
        }

        try {
            tutorDAO.excluir(id);
            System.out.println();
            System.out.println("Tutor excluído com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Não foi possível excluir o tutor. "
                    + "Verifique se ele ainda possui pets cadastrados (exclua os pets primeiro).");
        }
    }

    // ================= PET =================

    private static void cadastrarPet() {

        System.out.print("Digite o nome do pet: ");
        String nomePet = scanner.nextLine();

        System.out.print("Digite a raça do pet: ");
        String racaPet = scanner.nextLine();

        System.out.print("Digite a espécie do pet: ");
        String especiePet = scanner.nextLine();

        System.out.print("Digite o sexo do pet: ");
        String sexoPet = scanner.nextLine();

        int idadePet = lerInt("Digite a idade do pet: ");

        int tutorId = lerInt("Digite o ID do tutor responsável: ");

        // Os setters da classe Pet validam os dados aqui
        Pet pet = new Pet(nomePet, racaPet, especiePet, sexoPet, idadePet, tutorId);

        // Garante que o tutor existe antes de gravar
        if (tutorDAO.buscarPorId(tutorId) == null) {
            System.out.println("Tutor não encontrado! Cadastre o tutor primeiro.");
            return;
        }

        petDAO.cadastrar(pet);

        System.out.println();
        System.out.println("Pet cadastrado com sucesso!");
    }

    private static void atualizarPet() {

        int id = lerInt("Digite o ID do pet que deseja atualizar: ");

        Pet petEncontrado = petDAO.buscarPorId(id);

        if (petEncontrado == null) {
            System.out.println("Pet não encontrado!");
            return;
        }

        System.out.println("Pet encontrado: " + petEncontrado.getNome());

        System.out.print("Digite o novo nome do pet: ");
        String novoNomePet = scanner.nextLine();

        petEncontrado.setNome(novoNomePet);

        petDAO.atualizar(petEncontrado);

        System.out.println();
        System.out.println("Pet atualizado com sucesso!");
    }

    private static void excluirPet() {

        int id = lerInt("Digite o ID do pet que deseja excluir: ");

        Pet petParaExcluir = petDAO.buscarPorId(id);

        if (petParaExcluir == null) {
            System.out.println("Pet não encontrado!");
            return;
        }

        petDAO.excluir(id);

        System.out.println();
        System.out.println("Pet excluído com sucesso!");
    }

    // ================= AUXILIAR =================

    // Lê um número inteiro e repete a pergunta enquanto o usuário digitar algo inválido
    private static int lerInt(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}