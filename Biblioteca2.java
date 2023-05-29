package Biblioteca;

import java.io.EOFException; // import para tratar o fim do arquivo
import java.io.FileNotFoundException; // import para tratar arquivo nao encontrado
import java.io.FileOutputStream; // import para escrever no arquivo
import java.io.IOException; // import para tratar erro de IO (entrada e saida)
import java.io.ObjectInputStream; // import para ler objetos do arquivo
import java.io.ObjectOutputStream; // import para escrever objetos no arquivo
import java.util.ArrayList; // import para usar ArrayList

public class Biblioteca2 {
    private ArrayList<Livro> livros; // ArrayList de livros

    public Biblioteca2() { // construtor
        this.livros = new ArrayList<Livro>(); // inicializa o ArrayList
    }

    public void adicionaLivro(Livro liv) { // metodo para adicionar livro
        this.livros.add(liv); // adiciona o livro no ArrayList
    }

    public void listarLivros() { // metodo para listar livros
        for (Livro liv : livros) { // percorre o ArrayList
            System.out.println(liv.toString()); // imprime o livro
        }
        System.out.println("Total = " + this.livros.size() + " livros listados com sucesso!\n");// imprime o total
                                                                                                // de livros
    }

    public void excluirLivro(Livro liv) { // metodo para excluir livro
        if (this.livros.contains(liv)) { // verifica se o livro existe
            this.livros.remove(liv); // remove o livro do ArrayList
            System.out.println("[Livro " + liv.toString() + "excluido com sucesso!]\n"); // imprime mensagem de
                                                                                         // sucesso
        } else
            System.out.println("Livro inexistente!\n"); // imprime mensagem de erro
    }

    public void excluirLivros() { // metodo para excluir todos os livros
        livros.clear(); // limpa o ArrayList
        System.out.println("Livros excluidos com sucesso!\n"); // imprime mensagem de sucesso
    }

    public void gravarLivros() { // metodo para gravar livros
        ObjectOutputStream outputStream = null; // inicializa o objeto de gravacao
        try { // tenta gravar os livros
            outputStream = new ObjectOutputStream(new FileOutputStream("c:\\temp\\livros.dat")); // cria o arquivo
            for (Livro liv : livros) { // percorre o ArrayList
                outputStream.writeObject(liv); // grava o livro no arquivo
            }
        } catch (FileNotFoundException ex) { // trata o erro de arquivo nao encontrado
            ex.printStackTrace(); // imprime o erro
        } catch (IOException ex) { // trata o erro de IO
            ex.printStackTrace(); // imprime o erro
        } finally { // finaliza a gravacao
            try { // tenta fechar o arquivo
                if (outputStream != null) { // verifica se o arquivo esta aberto
                    outputStream.flush(); // limpa o buffer
                    outputStream.close(); // fecha o arquivo
                }
            } catch (IOException ex) { // trata o erro de IO
                ex.printStackTrace(); // imprime o erro
            }
        }
    }

    public void recuperaLivros() { // metodo para recuperar livros
        ObjectInputStream inputStream = null; // inicializa o objeto de leitura
        try { // tenta ler os livros
            inputStream = new ObjectInputStream(getClass().getResourceAsStream("livros.dat")); // abre o arquivo do
                                                                                               // mesmo pacote
            Object obj = null; // inicializa o objeto
            while ((obj = inputStream.readObject()) != null) { // percorre o arquivo
                if (obj instanceof Livro) { // verifica se o objeto e um livro
                    Livro liv = (Livro) obj; // converte o objeto para livro
                    this.livros.add(liv); // adiciona o livro no ArrayList
                }
            }
        } catch (EOFException ex) { // trata o erro de fim do arquivo
            System.out.println("Fim do arquivo!\n"); // imprime mensagem de fim do arquivo
        } catch (FileNotFoundException ex) { // trata o erro de arquivo nao encontrado
            ex.printStackTrace(); // imprime o erro
        } catch (IOException ex) { // trata o erro de IO
            ex.printStackTrace(); // imprime o erro
        } catch (ClassNotFoundException ex) { // trata o erro de classe nao encontrada
            ex.printStackTrace(); // imprime o erro
        } finally { // finaliza a leitura
            try { // tenta fechar o arquivo
                if (inputStream != null) { // verifica se o arquivo esta aberto
                    inputStream.close(); // fecha o arquivo
                }
            } catch (IOException ex) { // trata o erro de IO
                ex.printStackTrace(); // imprime o erro
            }
        }
    }

    public static void main(String[] args) { // metodo principal
        Biblioteca2 bib = new Biblioteca2(); // cria um objeto da classe Biblioteca
        bib.recuperaLivros(); // recupera os livros do arquivo
        bib.listarLivros(); // lista os livros
        bib.excluirLivros(); // exclui todos os livros
        bib.listarLivros(); // lista os livros
        bib.adicionaLivro(new Livro("Java", "Deitel", 2010)); // adiciona um livro
        bib.adicionaLivro(new Livro("C++", "Deitel", 2010)); // adiciona um livro
        bib.adicionaLivro(new Livro("C#", "Deitel", 2010)); // adiciona um livro
        bib.adicionaLivro(new Livro("Java", "Deitel", 2010)); // adiciona um livro
        bib.listarLivros(); // lista os livros
        bib.excluirLivro(new Livro("C#", "Deitel", 2010)); // exclui um livro
        bib.listarLivros(); // lista os livros
        bib.recuperaLivros(); // recupera os livros do arquivo
        bib.gravarLivros(); // grava os livros no arquivo
    }
}
