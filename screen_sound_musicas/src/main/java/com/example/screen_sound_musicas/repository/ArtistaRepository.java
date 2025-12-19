package com.example.screen_sound_musicas.repository;

import com.example.screen_sound_musicas.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Artista findByNomeContainingIgnoreCase(String nomeArtista);

}
