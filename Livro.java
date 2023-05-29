package Biblioteca;

import java.io.Serializable; // import para serializar

public abstract class Livro implements Serializable {

    private static final long serialVersionUID = 1L; // numero de serie para serializar
    private String titulo; // atributo titulo
    private String autor; // atributo autor
    private String editora; // atributo editora
    private int ano; // atributo ano
    private String tipo; // atributo tipo

    public Livro(String titulo, String autor, String editora, int ano) { // construtor
        this.titulo = titulo; // inicializa o atributo titulo
        this.autor = autor; // inicializa o atributo autor
        this.editora = editora; // inicializa o atributo editora
        this.ano = ano; // inicializa o atributo ano
        this.tipo = "Livro"; // inicializa o atributo tipo
    }

    public String toString() { // metodo toString
        String retorno = ""; // cria uma string vazia
        retorno += "Titulo: " + this.titulo + "\n"; // concatena a string com o titulo
        retorno += "Autor: " + this.autor + "\n"; // concatena a string com o autor
        retorno += "Editora: " + this.editora + "\n"; // concatena a string com a editora
        retorno += "Ano: " + this.ano + "\n"; // concatena a string com o ano
        return retorno; // retorna a string
    }

    public abstract String getTipo(); // metodo abstrato getTipo
}
