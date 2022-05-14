package br.com.alura.videoapi.funcionalidades.deletavideo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "${br.com.alura.videoapi.uri}")
public class DeletaVideoController {

    @Autowired
    private DeletaVideoRepository videoRepository;

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletaVideoPorId(@PathVariable UUID id){
        try{
            videoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("video removido");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("video não encontrado");
        }
    }
}
