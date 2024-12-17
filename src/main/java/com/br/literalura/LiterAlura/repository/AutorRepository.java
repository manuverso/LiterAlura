package com.br.literalura.LiterAlura.repository;

import com.br.literalura.LiterAlura.model.Autor;
import com.br.literalura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("select l from Autor a join a.livros b")
    List<Livro> findAllBook();

    @Query("select l from Autor a JOIN a.livros b WHERE l.titulo ILIKE %:titulo%")
    Optional<Autor> findByNomeIgnoreCase(String nome);


    @Query("SELECT a FROM Autor a JOIN FETCH a.livros")
    List<Autor> findAllWithLivros();

}
