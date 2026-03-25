package com.grupo2.nexus.controller;

import com.grupo2.nexus.model.dto.RequestDto;
import com.grupo2.nexus.model.entity.Request;
import com.grupo2.nexus.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests") // Mantenemos el estándar de versionamiento
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAll() {
        return ResponseEntity.ok(requestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RequestDto> create(@RequestBody Request request) {
        // Al llamar a save, se dispara la lógica de reducción de cupos que hiciste
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.save(request));
    }

    // Nota: Generalmente las solicitudes no se borran, se cancelan cambiando el estado,
    // pero te dejo el Delete por si necesitas limpiar pruebas en desarrollo.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Si no tienes el método delete en el Service todavía, deberías crearlo.
        // requestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}