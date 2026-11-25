
package br.edu.poo.m0024.model;

public class Tutor {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String cep;
    private String endereco;
    private String cidade;
    private String estado;

    // Construtor vazio
    public Tutor() {

    }

    // Construtor com todos os atributos, menos o id
    public Tutor(String nome, String cpf, String telefone,
                 String cep, String endereco, String cidade, String estado) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setCep(cep);
        setEndereco(endereco);
        setCidade(cidade);
        setEstado(estado);
    }

    // Construtor com todos os atributos
    public Tutor(int id, String nome, String cpf, String telefone,
                 String cep, String endereco, String cidade, String estado) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setCep(cep);
        setEndereco(endereco);
        setCidade(cidade);
        setEstado(estado);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    // Setters com validações

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome não pode ficar vazio."
            );
        }

        nome = nome.trim();

        if (nome.length() > 100) {
            throw new IllegalArgumentException(
                    "O nome não pode ultrapassar 100 caracteres."
            );
        }

        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = validarCpf(cpf);
    }

    public static String validarCpf(String cpf) {

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        String cpfLimpo = cpf.trim().replaceAll("[.-]", "");

        if (!cpfLimpo.matches("\\d{11}")) {
            throw new IllegalArgumentException(
                    "O CPF deve conter exatamente 11 dígitos."
            );
        }

        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException(
                    "CPF inválido. Todos os dígitos são iguais."
            );
        }

        int soma = 0;

        // Calcula o primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * (10 - i);
        }

        int resto = soma % 11;
        int primeiroDigito = (resto < 2) ? 0 : 11 - resto;

        if (primeiroDigito != Character.getNumericValue(cpfLimpo.charAt(9))) {
            throw new IllegalArgumentException(
                    "CPF inválido. Dígito verificador incorreto."
            );
        }

        soma = 0;

        // Calcula o segundo dígito verificador
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * (11 - i);
        }

        resto = soma % 11;
        int segundoDigito = (resto < 2) ? 0 : 11 - resto;

        if (segundoDigito != Character.getNumericValue(cpfLimpo.charAt(10))) {
            throw new IllegalArgumentException(
                    "CPF inválido. Dígito verificador incorreto."
            );
        }

        return cpfLimpo;
    }

    public void setTelefone(String telefone) {

        if (telefone == null || telefone.isBlank()) {
            this.telefone = null;
            return;
        }

        String telefoneLimpo = telefone.trim()
                .replaceAll("[()\\s-]", "");

        if (!telefoneLimpo.matches("\\d{11}")) {
            throw new IllegalArgumentException(
                    "O telefone deve conter 11 dígitos, incluindo o DDD."
            );
        }

        this.telefone = telefoneLimpo;
    }

    public void setCep(String cep) {

        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("CEP inválido.");
        }

        String cepLimpo = cep.trim().replace("-", "");

        if (!cepLimpo.matches("\\d{8}")) {
            throw new IllegalArgumentException(
                    "O CEP deve conter exatamente 8 dígitos."
            );
        }

        this.cep = cepLimpo;
    }

    public void setEndereco(String endereco) {

        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço inválido.");
        }

        endereco = endereco.trim();

        if (endereco.length() > 150) {
            throw new IllegalArgumentException(
                    "O endereço não pode ultrapassar 150 caracteres."
            );
        }

        this.endereco = endereco;
    }

    public void setCidade(String cidade) {

        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade inválida.");
        }

        cidade = cidade.trim();

        if (cidade.length() > 100) {
            throw new IllegalArgumentException(
                    "A cidade não pode ultrapassar 100 caracteres."
            );
        }

        this.cidade = cidade;
    }

    public void setEstado(String estado) {

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado inválido.");
        }

        estado = estado.trim();

        if (estado.length() > 2) {
            throw new IllegalArgumentException(
                    "O estado deve ter no máximo 2 caracteres (UF)."
            );
        }

        this.estado = estado;
    }
}
