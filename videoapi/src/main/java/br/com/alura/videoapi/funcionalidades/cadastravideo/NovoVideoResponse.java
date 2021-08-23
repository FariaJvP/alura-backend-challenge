package br.com.alura.videoapi.funcionalidades.cadastravideo;

import br.com.alura.videoapi.entidades.Video;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NovoVideoResponse {

    @JsonProperty String id;

    @JsonProperty String titulo;

    @JsonProperty String descricao;

    @JsonProperty String url;

    public NovoVideoResponse(Video novoVideo){
        this.id = novoVideo.getId().toString();
        this.titulo = novoVideo.getTitulo();
        this.descricao = novoVideo.getDescricao();
        this.url = novoVideo.getUrl();
    }

}
