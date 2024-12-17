package com.br.literalura.LiterAlura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
