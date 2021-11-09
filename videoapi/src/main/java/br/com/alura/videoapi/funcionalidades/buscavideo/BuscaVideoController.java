package br.com.alura.videoapi.funcionalidades.buscavideo;

import br.com.alura.videoapi.entidades.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "${br.com.alura.videoapi.uri}")
public class BuscaVideoController {

    @Autowired
    private BuscaVideoRepository videoRepository;

    @GetMapping
    public Page<BuscavideoDTOResponse> buscaTodosOsVideos(Pageable page){
        return videoRepository.findAll(page).map(BuscavideoDTOResponse::converterListaDeVideos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Video> buscaVideoPorId(@PathVariable UUID id){
        Optional<Video> opt  = videoRepository.findById(id);
        return opt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
