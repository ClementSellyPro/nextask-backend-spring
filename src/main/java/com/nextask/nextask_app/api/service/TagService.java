package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.DTO.TagDTO;
import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.entity.Tag;
import com.nextask.nextask_app.api.repository.ProjectRepository;
import com.nextask.nextask_app.api.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private ProjectRepository projectRepository;

  public List<Tag> getAllTags(String username) {
    Project project = projectRepository.findByUserUsername(username)
      .orElseThrow(() -> new RuntimeException("Projet non trouvé"));

    return tagRepository.findByProject(project);
  }

  public Optional<Tag> getTagById(String id) {
    return tagRepository.findById(id);
  }

  public Tag createTag(Tag tag, String username) {
    Project project = projectRepository.findByUserUsername(username)
      .orElseThrow(() -> new RuntimeException("Projet de l'utilisateur non trouvé"));

    if (tagRepository.existsByNameAndProject(tag.getName(), project)) {
      throw new RuntimeException("Un tag avec ce nom existe déjà");
    }
    
    tag.setProject(project);
    return tagRepository.save(tag);
  }

  public TagDTO updateTag(String id, TagDTO tagDetails, String username) {
    Project project = projectRepository.findByUserUsername(username)
      .orElseThrow(() -> new RuntimeException());

    Tag tag = tagRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Tag non trouvé avec l'ID: " + id));

    String newName = tagDetails.getName().trim();
    if(!tag.getName().equals(newName)) {
      if(tagRepository.existsByNameAndProject(newName, project)) {
        throw new RuntimeException("Un tag avec ce nom existe déjà dans ce projet");
      }
    }

    tag.setName(newName);
    tag.setColor(tagDetails.getColor());
    
    Tag savedTag = tagRepository.save(tag);
    return new TagDTO(savedTag);
  }

  public void deleteTag(String id) {
    if (!tagRepository.existsById(id)) {
      throw new RuntimeException("Tag non trouvé avec l'ID: " + id);
    }
    tagRepository.deleteById(id);
  }
}
