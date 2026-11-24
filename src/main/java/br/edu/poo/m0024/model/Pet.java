
package br.edu.poo.m0024.model;

public class Pet {

    private int id;
    private String nome;
    private String raca;
    private String especie;
    private String sexo;
    private int idade;
    private int tutorId;

    // Construtor vazio
    public Pet() {

    }

    // Construtor com todos os atributos, menos o id
    public Pet(String nome, String raca, String especie, String sexo,
               int idade, int tutorId) {
        setNome(nome);
        setRaca(raca);
        setEspecie(especie);
        setSexo(sexo);
        setIdade(idade);
        setTutorId(tutorId);
    }

    // Construtor com todos os atributos
    public Pet(int id, String nome, String raca, String especie, String sexo,
               int idade, int tutorId) {
        setId(id);
        setNome(nome);
        setRaca(raca);
        setEspecie(especie);
        setSexo(sexo);
        setIdade(idade);
        setTutorId(tutorId);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public String getEspecie() {
        return especie;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

    public int getTutorId() {
        return tutorId;
    }

    // Setters com validações
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do pet não pode ficar vazio."
            );
        }

        nome = nome.trim();

        if (nome.length() > 100) {
            throw new IllegalArgumentException(
                    "O nome do pet não pode ultrapassar 100 caracteres."
            );
        }

        this.nome = nome;
    }

    public void setRaca(String raca) {

        if (raca == null || raca.isBlank()) {
            throw new IllegalArgumentException(
                    "Raça não pode ficar vazia."
            );
        }

        raca = raca.trim();

        if (raca.length() > 100) {
            throw new IllegalArgumentException(
                    "A raça não pode ultrapassar 100 caracteres."
            );
        }

        this.raca = raca;
    }

    public void setEspecie(String especie) {

        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException(
                    "Espécie não pode ficar vazia."
            );
        }

        especie = especie.trim();

        if (especie.length() > 50) {
            throw new IllegalArgumentException(
                    "A espécie não pode ultrapassar 50 caracteres."
            );
        }

        this.especie = especie;
    }

    public void setSexo(String sexo) {

        if (sexo == null || sexo.isBlank()) {
            throw new IllegalArgumentException(
                    "Sexo não pode ficar vazio."
            );
        }

        sexo = sexo.trim();

        if (sexo.length() > 10) {
            throw new IllegalArgumentException(
                    "O sexo não pode ultrapassar 10 caracteres."
            );
        }

        this.sexo = sexo;
    }

    public void setIdade(int idade) {

        if (idade < 0) {
            throw new IllegalArgumentException(
                    "Idade não pode ser negativa."
            );
        }

        this.idade = idade;
    }

    public void setTutorId(int tutorId) {

        if (tutorId <= 0) {
            throw new IllegalArgumentException(
                    "Id do tutor inválido."
            );
        }

        this.tutorId = tutorId;
    }
}
