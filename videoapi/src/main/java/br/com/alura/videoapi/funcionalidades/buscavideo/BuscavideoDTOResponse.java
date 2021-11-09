package br.com.alura.videoapi.funcionalidades.buscavideo;

import br.com.alura.videoapi.entidades.Video;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuscavideoDTOResponse {

    @JsonProperty String id;

    @JsonProperty String titulo;

    @JsonProperty String url;

    @Deprecated
    public BuscavideoDTOResponse() {}

    public BuscavideoDTOResponse(String id, String titulo, String url) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
    }

    public static BuscavideoDTOResponse converterListaDeVideos(Video video){
        return new BuscavideoDTOResponse(video.getId().toString(), video.getTitulo(), video.getUrl());
    }
}
