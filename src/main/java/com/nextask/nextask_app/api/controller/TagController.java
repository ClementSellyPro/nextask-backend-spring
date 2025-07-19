package com.nextask.nextask_app.api.controller;

import com.nextask.nextask_app.api.entity.Tag;
import com.nextask.nextask_app.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "http://localhost:4200")
public class TagController {
  @Autowired
  private TagService tagService;

  // GET /api/tags - Récupérer tous les tags
  @GetMapping
  public ResponseEntity<List<Tag>> getAllTags() {
    List<Tag> tags = tagService.getAllTags();
    return ResponseEntity.ok(tags);
  }
  
  // GET /api/tags/{id} - Récupérer un tag par ID
  @GetMapping("/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable String id) {
    Optional<Tag> tag = tagService.getTagById(id);
    return tag.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
  
  // POST /api/tags - Créer un nouveau tag
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        try {
            Tag createdTag = tagService.createTag(tag);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/tags/{id} - Mettre à jour un tag
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable String id, @RequestBody Tag tagDetails) {
        try {
            Tag updatedTag = tagService.updateTag(id, tagDetails);
            return ResponseEntity.ok(updatedTag);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
