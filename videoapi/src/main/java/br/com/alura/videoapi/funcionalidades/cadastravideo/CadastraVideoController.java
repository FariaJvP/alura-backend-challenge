package br.com.alura.videoapi.funcionalidades.cadastravideo;

import br.com.alura.videoapi.entidades.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "${br.com.alura.videoapi.uri}")
public class CadastraVideoController {

    @Value("${novovideo.headerlocation}")
    private String locationNovoVideoUri;

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<NovoVideoResponse> criaNovoVideo(@RequestBody @Valid NovoVideoRequest novoVideoRequest,
                                                           UriComponentsBuilder uriComponentsBuilder){
        Video novoVideo = novoVideoRequest.toEntidadeVideo();
        entityManager.persist(novoVideo);

        URI uri = uriComponentsBuilder.path(locationNovoVideoUri).buildAndExpand(novoVideo.getId()).toUri();
        return ResponseEntity.created(uri).body(new NovoVideoResponse(novoVideo));
    }
}
