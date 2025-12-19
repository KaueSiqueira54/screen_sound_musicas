package com.example.screen_sound_musicas.models;

public enum Categoria {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipoArtista;

    Categoria(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }
}
