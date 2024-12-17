package com.br.literalura.LiterAlura.model;

import java.util.List;

public record DadosGutendex(Integer count,
                            String next,
                            String previous,
                            List<DadosLivro> results) {
}
