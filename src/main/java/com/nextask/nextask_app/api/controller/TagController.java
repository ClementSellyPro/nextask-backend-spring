package com.nextask.nextask_app.api.controller;

import com.nextask.nextask_app.api.DTO.TagDTO;
import com.nextask.nextask_app.api.entity.Tag;
import com.nextask.nextask_app.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "http://localhost:4200")
public class TagController {
	@Autowired
	private TagService tagService;

	// GET /api/tags - Récupérer tous les tags
	@GetMapping
	public ResponseEntity<List<TagDTO>> getAllTags(Authentication authentication) {
		try {
			List<Tag> tags = tagService.getAllTags(authentication.getName());
			List<TagDTO> tagDTOs = tags.stream()
					.map(TagDTO::new)
					.collect(Collectors.toList());
			return ResponseEntity.ok(tagDTOs);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	// GET /api/tags/{id} - Récupérer un tag par ID
	@GetMapping("/{id}")
	public ResponseEntity<Tag> getTagById(@PathVariable String id) {
		Optional<Tag> tag = tagService.getTagById(id);
		return tag.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	// POST /api/tags - Créer un nouveau tag
	@PostMapping
	public ResponseEntity<TagDTO> createTag(@RequestBody Tag tag, Authentication authentication) {
		try {
			Tag createdTag = tagService.createTag(tag, authentication.getName());
			return ResponseEntity.status(HttpStatus.CREATED).body(new TagDTO(createdTag));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	// PUT /api/tags/{id} - Mettre à jour un tag
	@PutMapping("/{id}")
	public ResponseEntity<TagDTO> updateTag(@PathVariable String id, @RequestBody TagDTO tagDetails, Authentication authentication) {
		try {
			TagDTO updatedTag = tagService.updateTag(id, tagDetails, authentication.getName());
			return ResponseEntity.ok(updatedTag);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
