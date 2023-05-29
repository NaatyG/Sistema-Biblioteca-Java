package Biblioteca;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; // import para escrever no arquivo
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; // import para escrever objetos no arquivo
import java.util.ArrayList; // import para usar ArrayList

import javax.swing.JOptionPane; // import para usar JOptionPane

public class Biblioteca {
    private ArrayList<Livro> livros; // ArrayList de livros

    public Biblioteca() { // construtor
        this.livros = new ArrayList<Livro>(); // inicializa o ArrayList
    }

    public String[] leValores(String[] dadosIn) {
        String[] dadosOut = new String[dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++) {
            String input = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");
            if (input == null) {
                throw new RuntimeException("Entrada cancelada pelo usuário.");
            }
            dadosOut[i] = input;
        }

        return dadosOut;
    }

    public Livro leLivro() { // metodo para ler livro

        String[] valores = new String[4]; // cria um array de string com tamanho 4
        String[] nomeVal = { "Titulo", "Autor", "Editora", "Ano" }; // cria um array de string com tamanho 4
        valores = leValores(nomeVal); // chama o metodo leValores e armazena no array valores

        int ano = this.retornaInteiro(valores[3]); // chama o metodo retornaInteiro e armazena no inteiro ano

        Livro livro = new Livro(valores[0], valores[1], valores[2], ano); // cria um objeto livro com os valores do
                                                                          // array valores
        return livro; // retorna o objeto livro
    }

    public LivrosInfantis leLivroInfantil() { // metodo para ler livro infantil

        String[] valores = new String[4]; // cria um array de string com tamanho 4
        String[] nomeVal = { "Titulo", "Autor", "Editora", "Ano" }; // cria um array de string com tamanho 4
        valores = leValores(nomeVal); // chama o metodo leValores e armazena no array valores

        int ano = this.retornaInteiro(valores[3]); // chama o metodo retornaInteiro e armazena no inteiro ano

        LivrosInfantis livroInfantil = new LivrosInfantis(valores[0], valores[1], valores[2], ano); // cria um objeto
                                                                                                    // livroInfantil
                                                                                                    // com os valores
                                                                                                    // do array
                                                                                                    // valores
        return livroInfantil; // retorna o objeto livroInfantil
    }

    public LivrosCulinaria leLivroCulinaria() { // metodo para ler livro de culinaria

        String[] valores = new String[4]; // cria um array de string com tamanho 4
        String[] nomeVal = { "Titulo", "Autor", "Editora", "Ano" }; // cria um array de string com tamanho 4
        valores = leValores(nomeVal); // chama o metodo leValores e armazena no array valores

        int ano = this.retornaInteiro(valores[3]); // chama o metodo retornaInteiro e armazena no inteiro ano

        LivrosCulinaria livroCulinaria = new LivrosCulinaria(valores[0], valores[1], valores[2], ano); // cria um
                                                                                                       // objeto
                                                                                                       // livroCulinaria
                                                                                                       // com os
                                                                                                       // valores do
                                                                                                       // array
                                                                                                       // valores
        return livroCulinaria; // retorna o objeto livroCulinaria
    }

    private boolean intValido(String s) { // metodo para verificar se o valor e inteiro
        try { // tenta executar o codigo
            Integer.parseInt(s); // converte o valor para inteiro
            return true; // retorna verdadeiro
        } catch (NumberFormatException e) { // caso nao consiga executar o codigo
            return false; // retorna falso
        }
    }

    public int retornaInteiro(String entrada) { // metodo para retornar inteiro
        int numInt; // cria um inteiro

        while (!this.intValido(entrada)) { // enquanto o valor nao for inteiro
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro."); // le o valor
                                                                                                          // novamente
        }

        numInt = Integer.parseInt(entrada); // converte o valor para inteiro
        return (numInt); // retorna o inteiro
    }

    public void salvaLivros(ArrayList<Livro> livros) { // metodo para salvar livros
        ObjectOutputStream outputStream = null; // cria um objeto ObjectOutputStream
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("livros.dat")); // cria um arquivo para salvar
            for (int i = 0; i < livros.size(); i++) // percorre o ArrayList
                outputStream.writeObject(livros.get(i)); // escreve o objeto no arquivo
        } catch (FileNotFoundException ex) { // caso nao encontre o arquivo
            JOptionPane.showMessageDialog(null, "Não foi possível salvar no banco de dados.");
            ex.printStackTrace();
        } catch (IOException ex) { // caso nao consiga executar o codigo
            JOptionPane.showMessageDialog(null, "Não foi possível salvar no banco de dados.");
            ex.printStackTrace();
        } finally { // executa o codigo
            try { // tenta executar o codigo
                if (outputStream != null) { // caso o objeto nao seja nulo
                    outputStream.flush(); // limpa o objeto
                    outputStream.close(); // fecha o objeto
                }
            }

        }
    }

    /**
     * @return
     */
    @SuppressWarnings("finally") // suprime o aviso de retorno de metodo
    public ArrayList<Livro> recupeArrayList() {
        ArrayList<Livro> livrosTemp = new ArrayList<Livro>(); // cria um ArrayList de livros

        ObjectInputStream inputStream = null; // cria um objeto ObjectInputStream

        try {
            inputStream = new ObjectInputStream(new FileInputStream("livros.dat")); // cria um arquivo para ler
            Object obj = null; // cria um objeto nulo
            while ((obj = inputStream.readObject()) != null) { // enquanto o objeto nao for nulo
                if (obj instanceof Livro) { // se o objeto for do tipo Livro
                    livrosTemp.add((Livro) obj); // adiciona o objeto no ArrayList
                }
            }
        } catch (EOFException ex) { // caso nao encontre o arquivo
            System.out.println("Fim de arquivo.");
        } catch (ClassNotFoundException ex) { // caso nao consiga executar o codigo
            ex.printStackTrace();
        } catch (FileNotFoundException ex) { // caso nao encontre o arquivo
            JOptionPane.showMessageDialog(null, "Arquivo com livros não existe!");
            ex.printStackTrace();
        } catch (IOException ex) { // caso nao consiga executar o codigo
            JOptionPane.showMessageDialog(null, "Impossível ler arquivo!");
            ex.printStackTrace();
        } finally { // executa o codigo
            try { // tenta executar o codigo
                if (inputStream != null) { // caso o objeto nao seja nulo
                    inputStream.close(); // fecha o objeto
                }
            } catch (final IOException ex) { // caso nao consiga executar o codigo
                ex.printStackTrace();
            }
            return livrosTemp;
        } // retorna o ArrayList
    }

    public void menuBiblioteca() { // metodo para mostrar o menu da biblioteca

        String menu = "";
        String entrada;
        int opc1, opc2;

        do {
            menu = "Biblioteca PUCPR\n" +

                    "1. Entrar Livros\n" +
                    "2. Exibir Livros\n" +
                    "3. Excluir Livros\n" +
                    "4. Salvar Livros\n" +
                    "5. Recuperar Livros\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog(menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1) {
                case 1:
                    menu = "Escolha a sessão:\n" +

                            "1. Livro Infantil\n" +
                            "2. Livro de Culinária\n" +
                            "9. Voltar";

                    entrada = JOptionPane.showInputDialog(menu + "\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2) {
                        case 1:
                            LivrosInfantis livroInfantil = this.leLivroInfantil();
                            livros.add(livroInfantil);
                            break;

                        case 2:
                            LivrosCulinaria livroCulinaria = this.leLivroCulinaria();
                            livros.add(livroCulinaria);
                            break;

                        case 9:
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }

                    break;

                case 2:
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há livros cadastrados!");
                    } else {
                        String dados = "";
                        for (int i = 0; i < livros.size(); i++) {
                            dados += livros.get(i).toString() + "-----------------------------\n";
                        }
                        JOptionPane.showMessageDialog(null, dados);
                    }
                    break;

                case 3:
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há livros cadastrados!");
                    } else {
                        String dados = "";
                        for (int i = 0; i < livros.size(); i++) {
                            dados += i + " - " + livros.get(i).toString() + "\n";
                        }
                        int posicao = Integer.parseInt(
                                JOptionPane.showInputDialog("Digite a posição do livro a ser excluído:\n\n" + dados));
                        livros.remove(posicao);
                    }
                    break;

                case 4:
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há livros cadastrados!");
                    } else {
                        this.salvaLivros(livros);
                    }
                    break;

                case 5:
                    livros = this.recupeArrayList();
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Não há livros cadastrados!");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Dados RECUPERADOS com sucesso!");
                    break;

                case 9:
                    JOptionPane.showMessageDialog(null, "Fim do aplicativo Biblioteca!");
                    break;

            }
        } while (opc1 != 9);
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.menuBiblioteca();
    }

}
