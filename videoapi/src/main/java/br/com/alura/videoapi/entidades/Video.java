package br.com.alura.videoapi.entidades;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Video {

    @Id @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 80)
    private String titulo;

    @Column(nullable = false, length = 400)
    private String descricao;

    @Column(nullable = false)
    @URL
    private String url;

    @Deprecated
    public Video(){}

    public Video(@NotBlank String titulo, @NotBlank String descricao, @NotBlank @URL String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(titulo, video.titulo) && Objects.equals(descricao, video.descricao) && Objects.equals(url, video.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, url);
    }
}
