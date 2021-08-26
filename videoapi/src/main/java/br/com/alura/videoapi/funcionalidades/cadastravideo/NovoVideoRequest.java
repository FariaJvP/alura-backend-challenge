package br.com.alura.videoapi.funcionalidades.cadastravideo;

import br.com.alura.videoapi.entidades.Video;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoVideoRequest {

    @NotBlank
    @Size(max = 80)
    private String titulo;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    @NotBlank
    @URL(message = "Precisamos de uma url válida para cotinuar. Ex: http://www.foo.com.br")
    @UniqueURL(fieldName = "url", domainClass = Video.class, message = "URL já cadastrada")
    private String url;

    public NovoVideoRequest(@NotBlank @Size(max = 40) String titulo,
                            @NotBlank @Size(max = 400)  String descricao,
                            @NotBlank @URL String url){
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Video toEntidadeVideo(){
        return new Video(this.titulo, this.descricao, this.url);
    }
}
