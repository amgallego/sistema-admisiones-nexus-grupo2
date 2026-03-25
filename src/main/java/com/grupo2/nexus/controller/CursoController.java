package com.grupo2.nexus.controller;

import com.grupo2.nexus.model.dto.CursoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.grupo2.nexus.model.entity.Curso;
import com.grupo2.nexus.service.CursoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor

public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDto>> getAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }


   @PostMapping
    public ResponseEntity<CursoDto> create(@RequestBody Curso request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(request));
    }

      @PutMapping("/{id}")
    public ResponseEntity<CursoDto> update(
            @PathVariable Long id,
            @RequestBody Curso request) {

          request.setId(id);
        return ResponseEntity.ok(cursoService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)    {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
