package com.example.screen_sound_musicas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSoundMusicasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoundMusicasApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testando");
    }
}
