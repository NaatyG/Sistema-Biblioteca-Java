package Biblioteca;

public class LivrosCulinaria extends Livro {

    private static final long serialVersionUID = 1L; // numero de serie para serializar
    private String tipo;

    public LivrosCulinaria(String titulo, String autor, String editora, int ano) { // construtor
        super(titulo, autor, editora, ano); // chama o construtor da classe mae
        this.tipo = "Culinaria"; // inicializa o atributo tipo
    }
}
