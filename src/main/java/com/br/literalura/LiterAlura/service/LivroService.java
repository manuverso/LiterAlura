package com.br.literalura.LiterAlura.service;

import com.br.literalura.LiterAlura.model.Autor;
import com.br.literalura.LiterAlura.model.DadosLivro;
import com.br.literalura.LiterAlura.model.Idiomas;
import com.br.literalura.LiterAlura.model.Livro;
import com.br.literalura.LiterAlura.repository.AutorRepository;
import com.br.literalura.LiterAlura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void salvarLivro(DadosLivro dadosLivro) {

        Optional<Livro> livroOptional = livroRepository.findByTitulo(dadosLivro.titulo());

        if (livroOptional.isPresent()){
            System.out.println("Livro já cadastrado");
            return;
        }

        Livro livro = new Livro();

        livro.setTitulo(dadosLivro.titulo());
        livro.setDownloads(dadosLivro.numeroDownloads());

        livro.setIdioma(Idiomas.valueOf(dadosLivro.idioma().isEmpty() ? "Unknown" : dadosLivro.idioma().get(0)));

        List<Autor> autores = dadosLivro.autor().stream()
                .map(dadosAutor -> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(dadosAutor.nome());
                    novoAutor.setNascimento(Integer.valueOf(dadosAutor.nascimento()));
                    novoAutor.setFalecimento(Integer.valueOf(dadosAutor.falecimento())));
                    return novoAutor;
                }).collect(Collectors.toList());

        autorRepository.saveAll(autores.stream().filter(autor -> autor.getId() == null).collect(Collectors.toList()));
        livro.setAutores(autores);

        livroRepository.save(livro);
    }

    @Transactional(readOnly = true)
    public List<String> listarLivros() {
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().map(livro -> {
            String autores = livro.getAutores().stream()
                    .map(Autor::getNome)
                    .collect(Collectors.joining(", "));

            return "-------------- Livro ----------------\n" +
                    "Título: " + livro.getTitulo() + "\n" +
                    "Autores: " + autores + "\n" +
                    "Idioma: " + livro.getIdioma() + "\n" +
                    "Número de Downloads: " + livro.getDownloads() + "\n" +
                    "--------------------------------------";
        }).collect(Collectors.toList());
    }

    public Optional<Livro> buscarLivroPeloTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    @Transactional(readOnly = true)
    public List<String> listarLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.listarLivrosPorIdiomas(idioma);

        return livros.stream().map(livro -> {
            String autores = livro.getAutores().stream()
                    .map(Autor::getNome)
                    .collect(Collectors.joining(", "));

            return "-------------- Livro ----------------\n" +
                    "Título: " + livro.getTitulo() + "\n" +
                    "Autores: " + autores + "\n" +
                    "Idioma: " + livro.getIdioma() + "\n" +
                    "Número de Downloads: " + livro.getDownloads() + "\n" +
                    "--------------------------------------";
        }).collect(Collectors.toList());
    }

}
