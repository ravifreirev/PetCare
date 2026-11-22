package br.edu.poo.m0024.model;

public class Pet {

    private int id;
    private String nome;
    private String raca;
    private String especie;
    private int idade;
    private int tutorId;

    // Construtor vazio
    public Pet() {

    }

    // Construtor com todos os atributos, menos o id
    public Pet(String nome, String raca, String especie, int idade, int tutorId) {
        setNome(nome);
        setRaca(raca);
        setEspecie(especie);
        setIdade(idade);
        setTutorId(tutorId);
    }

    // Construtor com todos os atributos
    public Pet(int id, String nome, String raca, String especie, int idade, int tutorId) {
        setId(id);
        setNome(nome);
        setRaca(raca);
        setEspecie(especie);
        setIdade(idade);
        setTutorId(tutorId);

    }

    //Getters
    public int getId() {
        return  id;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public String getEspecie() {
        return  especie;
    }

    public int getIdade() {
        return idade;
    }

    public int getTutorId() {
        return tutorId;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do pet não pode ficar vazio.");
        }

        this.nome = nome;
    }

    public void setRaca(String raca) {

        if (raca == null || raca.isBlank()) {
            throw new IllegalArgumentException("Raça não pode ficar vazia.");
        }

        this.raca = raca;
    }

    public void setEspecie(String especie) {

        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("Espécie não pode ficar vazia.");
        }

        this.especie = especie;
    }

    public void setIdade(int idade) {

        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }

        this.idade = idade;
    }

    public void setTutorId(int tutorId) {

        if (tutorId <= 0) {
            throw new IllegalArgumentException("Id do tutor inválido.");
        }

        this.tutorId = tutorId;
    }


}
