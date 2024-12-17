package com.br.literalura.LiterAlura.model;

public enum Idiomas {
    en ("Inglês"),
    fr ("Francês"),
    de ("Alemão"),
    fi ("Finlandês"),
    es ("Espanhol"),
    it ("Italiano"),
    nl ("Holandês"),
    pt ("Português"),
    sv ("Sueco"),
    da ("Dinamarquês");



    private String idioma;

    Idiomas(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.idioma.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }


}
