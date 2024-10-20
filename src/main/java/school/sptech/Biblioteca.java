package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nome;
    private List<Livro> livros = new ArrayList<>();

    public Biblioteca() {
    }

    public Biblioteca(String nome) {
        this.nome = nome;
        List<Livro> livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro){
        if (livro == null){
            throw new ArgumentoInvalidoException("Argumeto inválido");
        }
        if(livro.getAutor() == null || livro.getAutor().isBlank() || livro.getTitulo() == null || livro.getTitulo().isBlank() || (livro.getDataPublicacao() == null)){
            throw new ArgumentoInvalidoException("Argumento inválido");
        }
        livros.add(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo){
    if (titulo == null){
        throw new ArgumentoInvalidoException();
    }
    if (titulo.isBlank()){
        throw new ArgumentoInvalidoException();
    }
        for (Livro livro : livros){
            if (livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }
        throw new LivroNaoEncontradoException();
    }

    public void removerLivroPorTitulo(String titulo){
        Livro livro = buscarLivroPorTitulo(titulo);
        if(livro == null){
            throw new LivroNaoEncontradoException();
        }
        livros.remove(livro);
    }

    public Integer contarLivros(){
        Integer qtdLivros = 0;
        for (Livro livro : livros){
            qtdLivros += 1;
        }
        return qtdLivros;
    }

    public List<Livro> obterLivrosAteAno(Integer ano){
        List<Livro> livrosAteAno = new ArrayList<>();
        for (Livro livro : livros){
            if (livro.getDataPublicacao().getYear() <= ano){
                livrosAteAno.add(livro);
            }
        }
        return livrosAteAno;
    }

    public List<Livro> retornarTopCincoLivros(){
        List<Livro> top5 = new ArrayList<>();
        Integer indice = 0;

        livros.sort((livros1, livros2) -> livros2.calcularMediaAvaliacoes().compareTo(livros1.calcularMediaAvaliacoes()));

        if(livros.size() <= 5){
            return livros;
        }else{
            while (top5.size() < 5){
                top5.add(livros.get(indice));
                indice += 1;
            }
            return top5;
        }
    }
}
