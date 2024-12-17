package com.br.literalura.LiterAlura.principal;

import com.br.literalura.LiterAlura.model.DadosGutendex;
import com.br.literalura.LiterAlura.model.DadosLivro;
import com.br.literalura.LiterAlura.model.Idiomas;
import com.br.literalura.LiterAlura.model.Livro;
import com.br.literalura.LiterAlura.repository.LivroRepository;
import com.br.literalura.LiterAlura.service.AutorService;
import com.br.literalura.LiterAlura.service.ConsumoAPI;
import com.br.literalura.LiterAlura.service.ConverteDados;
import com.br.literalura.LiterAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private LivroRepository livroRepositorio;

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    private final String ENDERECO = "https://gutendex.com/books/?search=";


    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0){
            var menu = """
                    ----------------------------
                    Escolha o número de sua opção:
                    1- buscar livro pelo título
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    0- sair
                    ----------------------------
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarTodosAutores();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    livrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...\n" + " - Programa finalizado! -");
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String livro = leitura.nextLine();

        String dados = consumo.obterDados(ENDERECO + livro.replace(" ", "%20"));
        DadosGutendex dadosGutendex = conversor.obterDados(dados, DadosGutendex.class);

        if (dadosGutendex.results() != null && !dadosGutendex.results().isEmpty()) {
            for (DadosLivro dadosLivro : dadosGutendex.results()) {
                System.out.println(dadosLivro);

                if (livroService.buscarLivroPeloTitulo(dadosLivro.titulo()).isPresent()) {
                    System.out.println("Livro já cadastrado");
                } else {
                    livroService.salvarLivro(dadosLivro);
                    System.out.println("Livro cadastrado com sucesso");
                }
            }
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void listarLivros() {
        List<String> livrosRegistrados = livroService.listarLivros();

        if (livrosRegistrados.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        } else {
            livrosRegistrados.forEach(System.out::println);
        }
    }

    private void listarTodosAutores() {
        List<String> autoresRegistrados = autorService.listarAutores();

        if (autoresRegistrados.isEmpty()) {
            System.out.println("Nenhum autor cadastrado");
        } else {
            autoresRegistrados.forEach(System.out::println);
        }
    }

    private void listarAutoresPorAno() {
        System.out.println("Digite o ano que deseja buscar: ");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<String> autoresVivos = autorService.listarAutoresVivosEmUmDeterminadoAno(ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado");
        } else {
            autoresVivos.forEach(System.out::println);
        }
    }

    private void livrosPorIdioma() {
        System.out.println("Disponiveis:");
        for (Idiomas i : Idiomas.values()){
            System.out.println(i.getIdioma());
        }

        System.out.println("Digite o idioma:");
        var entrada = leitura.nextLine();

        var entrada2 = String.valueOf(Idiomas.fromString(entrada));

        if (entrada2.equals("null")){
            System.out.println(entrada + " não é um idioma disponível, desculpe-nos!");
        }else {
            List<String> livros = livroService.listarLivrosPorIdioma(entrada2);
            if (livros.isEmpty()) {
                System.out.println("Nada Encontrado");
            } else {
                livros.forEach(System.out::println);
            }
        }
    }
}
