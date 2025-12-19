package com.example.screen_sound_musicas.principal;

import com.example.screen_sound_musicas.models.Artista;
import com.example.screen_sound_musicas.models.DadosArtista;
import com.example.screen_sound_musicas.models.DadosMusica;
import com.example.screen_sound_musicas.models.Musica;
import com.example.screen_sound_musicas.repository.ArtistaRepository;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);
    private List<Artista> artistas = new ArrayList<>();
    private List<Musica> musicas = new ArrayList<>();

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    
                    *** Screen Sound Music ***
                    
                    1 - Cadastrar artista
                    2 - Cadastrar música
                    3 - Exibir dados cadastrados
                    4 - Buscar músicas por artista
                    5 - Remover música

                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    exibirDadosCadastrados();
                    break;
                case 4:
                    exibirMusicaPorArtista();
                    break;
                case 5:
                    removerMusica();
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void exibirDadosCadastrados() {
        artistas = repositorio.findAll();

        List<String> dadosUnificados = artistas.stream()
                .flatMap(artista -> {
                    List<String> linhas = new ArrayList<>();

                    linhas.add("Artista: " + artista.getNome() +
                            " | Categoria: " + artista.getCategoria());

                    artista.getMusicas().forEach(musica ->
                            linhas.add("   Música: " + musica.getNome() +
                                    " | Gênero: " + musica.getGenero())
                    );

                    return linhas.stream();
                })
                .toList();

        dadosUnificados.forEach(System.out::println);
    }

    private void cadastrarArtista() {
        System.out.println("Digite o nome do artista: \n");
        var nomeArtista = leitura.nextLine();
        System.out.println("Informe o tipo desse artista: (solo, dupla ou banda");
        var tipoArtista = leitura.nextLine();
        Artista artista = new Artista(nomeArtista, tipoArtista);
        repositorio.save(artista);
        System.out.println("Artista Salvo.");
    }

    private void exibirArtistas() {
        artistas = repositorio.findAll();
        artistas.stream()
                .map(a -> a.getNome())
                .forEach(System.out::println);
    }

    private void cadastrarMusica() {
        exibirArtistas();
        System.out.println("Digite o nome do artista: \n");
        var nomeArtista = leitura.nextLine();
        Optional<Artista> artista = Optional.ofNullable(repositorio.findByNomeContainingIgnoreCase(nomeArtista));

        if(artista.isPresent()) {
            Artista artistaEncontrado = artista.get();
            System.out.println("Digite o nome da música: \n");
            var nomeMusica = leitura.nextLine();
            System.out.println("Digite o gênero da música: \n");
            var generoMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica, generoMusica);
            musica.setArtista(artistaEncontrado);
            artistaEncontrado.getMusicas().add(musica);
            repositorio.save(artistaEncontrado);
            System.out.println("Música salva.");
        } else {
            System.out.println("Artista não cadastrado! Tente novamente.");
        }
    }

    private void exibirMusicaPorArtista() {
        System.out.println("Artistas disponíveis: \n");
        exibirArtistas();
        System.out.println("Digite o nome do artista: \n");
        var nomeArtista = leitura.nextLine();
        List<Artista> musicasArtista = Collections.singletonList(repositorio.findByNomeContainingIgnoreCase(nomeArtista));
        musicasArtista.stream()
                .map(a -> a.getMusicas())
                .forEach(System.out::println);
    }

    private void removerMusica() {
        exibirArtistas();
        System.out.println("Digite o nome do artista:");
        String nomeArtista = leitura.nextLine();

        Optional<Artista> artistaOpt =
                Optional.ofNullable(repositorio.findByNomeContainingIgnoreCase(nomeArtista));

        if (artistaOpt.isEmpty()) {
            System.out.println("Artista não encontrado.");
            return;
        }

        Artista artista = artistaOpt.get();

        if (artista.getMusicas().isEmpty()) {
            System.out.println("Este artista não possui músicas cadastradas.");
            return;
        }

        artista.getMusicas().forEach(m ->
                System.out.println(m.getNome())
        );

        System.out.println("Digite o nome da música para remover:");
        String nomeMusica = leitura.nextLine();

        boolean removida = artista.getMusicas()
                .removeIf(m -> m.getNome().equalsIgnoreCase(nomeMusica));

        if (removida) {
            repositorio.save(artista);
            System.out.println("Música removida com sucesso.");
        } else {
            System.out.println("Música não encontrada.");
        }
    }

}
