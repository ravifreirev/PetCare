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

    //Getters
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

    //Setters
    public void setId( int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ficar vazio.");
        }

        this.nome = nome;
    }

    public void setCpf(String cpf) {

        if (cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("CPF inválido.");
        }

        String cpfLimpo = cpf.replaceAll("[.-]", "");

        if (cpfLimpo.length() != 11|| !cpfLimpo.matches("\\d{11}")){
            throw new IllegalArgumentException("CPF inválido.");
        }

        this.cpf = cpfLimpo;
    }

    public void setTelefone(String telefone) {

        if (telefone != null) {
            telefone = telefone.replaceAll("[()\\s-]","");
        }

        if (!telefone.matches("\\d{11}")) {
            throw new IllegalArgumentException("O telefone deve conter 11 dígitos, incluindo o DDD");
        }
        this.telefone = telefone;
    }

    public void setCep(String cep) {

        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("CEP inválido.");
        }

        String cepLimpo = cep.replaceAll("-", "");

        if (cepLimpo.length() != 8 || !cepLimpo.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido");
        }

        this.cep = cepLimpo;
    }

    public void setCidade(String cidade) {

        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade inválida.");
        }

        this.cidade = cidade;
    }

    public void setEndereco(String endereco) {

        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço inválido.");
        }

        this.endereco = endereco;
    }

    public void setEstado(String estado) {

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado inválido.");
        }

        this.estado = estado;
    }
}
