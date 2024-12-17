package com.br.literalura.LiterAlura.repository;

import com.br.literalura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);


    @Query("SELECT l FROM Livro l JOIN FETCH l.autores WHERE l.idioma = :idioma")
    List<Livro> listarLivrosPorIdiomas(@Param("idioma") String idioma);

    //selecionar todos os livros de um determinado autor
    @Query("SELECT l FROM Livro l JOIN FETCH l.autores a WHERE a.nome = :nome")
    List<Livro> listarLivrosPorAutor(@Param("nome") String nome);


}
