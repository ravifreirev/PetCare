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

    // Construtor com todos os atributos
    public Pet(int id, String nome, String raca, String especie, int idade, int tutorId) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.especie = especie;
        this.idade = idade;
        this.tutorId = tutorId;

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
        this.nome = nome;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }
}
