package com.nextask.nextask_app.api.controller;

import com.nextask.nextask_app.api.DTO.ColumnDTO;
import com.nextask.nextask_app.api.entity.ColumnEntity;
import com.nextask.nextask_app.api.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/columns")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ColumnController {
  @Autowired
    private ColumnService columnService;
    
    // GET /api/columns - Récupérer toutes les colonnes
    @GetMapping
    public ResponseEntity<List<ColumnDTO>> getAllColumns() {
        List<ColumnDTO> columns = columnService.getUserColumns()
        .stream().map(ColumnDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(columns);
    }

    // // GET /api/columns/{id} - Récupérer une colonne par ID
    // @GetMapping("/{id}")
    // public ResponseEntity<ColumnEntity> getColumnById(@PathVariable String id) {
    //     Optional<ColumnEntity> column = columnService.getColumnById(id);
    //     return column.map(ResponseEntity::ok)
    //                  .orElse(ResponseEntity.notFound().build());
    // }

    // POST /api/columns - Créer une nouvelle colonne
    @PostMapping
    public ResponseEntity<ColumnDTO> createColumn(@RequestBody ColumnDTO column) {
        try {
            ColumnEntity createdColumn = columnService.createColumn(column.getName(), column.getColor());
            ColumnDTO responseColumn = new ColumnDTO(createdColumn);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseColumn);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/columns/{id} - Mettre à jour une colonne
    @PutMapping("/{id}")
    public ResponseEntity<ColumnEntity> updateColumn(@PathVariable String id, @RequestBody ColumnEntity columnDetails) {
        try {
            ColumnEntity updatedColumn = columnService.updateColumn(id, columnDetails.getName(), columnDetails.getColor());
            return ResponseEntity.ok(updatedColumn);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE /api/columns/{id} - Supprimer une colonne
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable String id) {
        try {
            columnService.deleteColumn(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
