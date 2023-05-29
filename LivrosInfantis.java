package Biblioteca;

public class LivrosInfantis extends Livro { // classe filha de Livro

    private static final long serialVersionUID = 1L; // numero de serie para serializar
    private String tipo;

    public LivrosInfantis(String titulo, String autor, String editora, int ano) { // construtor
        super(titulo, autor, editora, ano); // chama o construtor da classe mae
        this.tipo = "Infantil"; // inicializa o atributo tipo
    }
}
