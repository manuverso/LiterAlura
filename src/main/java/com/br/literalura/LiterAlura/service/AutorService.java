package com.br.literalura.LiterAlura.service;

import com.br.literalura.LiterAlura.model.Autor;
import com.br.literalura.LiterAlura.model.Livro;
import com.br.literalura.LiterAlura.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroService livroService;

    @Transactional(readOnly = true)
    public List<String> listarAutores() {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .map( autor -> {
                    String livros = autor.getLivros().stream()
                            .map(Livro::getTitulo)
                            .collect(Collectors.joining(", "));

                    return "Autor: " + autor.getNome() + "\n" +
                            "Ano de nascimento: " + autor.getNascimento() + "\n" +
                            "Ano de falecimento: " + (autor.getFalecimento() != null ? autor.getFalecimento() : "N/A") + "\n" +
                            "Livros: [" + livros + "]\n";
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> listarAutoresVivosEmUmDeterminadoAno(int ano) {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .filter(autor -> autor.getFalecimento() == null || ( autor.getFalecimento() > ano && autor.getNascimento() <= ano))
                .map( autor -> {
                    String livros = autor.getLivros().stream()
                            .map(Livro::getTitulo)
                            .collect(Collectors.joining(", "));

                    return "Autor: " + autor.getNome() + "\n" +
                            "Ano de nascimento: " + autor.getNascimento() + "\n" +
                            "Ano de falecimento: " + (autor.getFalecimento() != null ? autor.getFalecimento() : "N/A") + "\n" +
                            "Livros: [" + livros + "]\n";
                }).collect(Collectors.toList());
    }



}
