package br.com.alura.videoapi.funcionalidades.deletavideo;

import br.com.alura.videoapi.entidades.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface DeletaVideoRepository extends JpaRepository<Video, UUID> {

}

