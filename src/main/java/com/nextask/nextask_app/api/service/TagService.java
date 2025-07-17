package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.entity.Tag;
import com.nextask.nextask_app.api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
  @Autowired
  private TagRepository tagRepository;

  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  public Optional<Tag> getTagById(String id) {
    return tagRepository.findById(id);
  }

  public Tag createTag(Tag tag) {
    if (tagRepository.existsByName(tag.getName())) {
      throw new RuntimeException("Un tag avec ce nom existe déjà");
    }
    return tagRepository.save(tag);
  }

  public Tag updateTag(String id, Tag tagDetails) {
    Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag non trouvé avec l'ID: " + id));

    tag.setName(tagDetails.getName());
    tag.setColor(tagDetails.getColor());
    
    return tagRepository.save(tag);
  }

  public void deleteTag(String id) {
    if (!tagRepository.existsById(id)) {
      throw new RuntimeException("Tag non trouvé avec l'ID: " + id);
    }
    tagRepository.deleteById(id);
  }
}
