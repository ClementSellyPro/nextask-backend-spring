package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.DTO.CreatedCardRequest;
import com.nextask.nextask_app.api.entity.Card;
import com.nextask.nextask_app.api.entity.ColumnEntity;
import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.entity.Tag;
import com.nextask.nextask_app.api.repository.CardRepository;
import com.nextask.nextask_app.api.repository.ColumnRepository;
import com.nextask.nextask_app.api.repository.ProjectRepository;
import com.nextask.nextask_app.api.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CardService {
  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private ColumnRepository columnRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private ProjectRepository projectRepository;

  public List<Card> getCardsByColumn(String columnId) {
        // Vérifier que la colonne appartient à l'utilisateur
        ColumnEntity column = columnRepository.findById(columnId)
                .orElseThrow(() -> new RuntimeException("Column not found"));
        
        Project userProject = userService.getCurrentUserProject();
        if (!column.getProject().getId().equals(userProject.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        return cardRepository.findByColumnId(columnId);
    }
    
    public Card createCard(CreatedCardRequest cardRequest, String username) {
        
        // Vérifier que la colonne appartient à l'utilisateur
        Project userProject = projectRepository.findByUserUsername(username)
            .orElseThrow(() -> new RuntimeException("Project de l'utilisateur non trouvé"));

        ColumnEntity column = columnRepository.findById(cardRequest.getColumn_id())
            .orElseThrow(() -> new RuntimeException("Column not found"));
        
        if (!column.getProject().getId().equals(userProject.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        Card card = new Card();
        card.setTitle(cardRequest.getTitle());
        card.setDescription(cardRequest.getDescription());
        card.setLimitDate(cardRequest.getLimitDate());
        card.setStoryPoints(cardRequest.getStoryPoints());
        card.setColumn(column);
        card.setProject(userProject);

        if(cardRequest.getTags() != null) {
            Set<Tag> tags = new HashSet<>();
            for(String tagId : cardRequest.getTags()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag non trouvé"));

                    tags.add(tag);
            }
            card.setTags(tags);
        }
        return cardRepository.save(card);
    }
    
    public Card updateCard(String cardId, String title, String description, LocalDateTime limitDate, String storyPoints) {
        
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        
        // Vérifier que la carte appartient à l'utilisateur
        Project userProject = userService.getCurrentUserProject();
        if (!card.getProject().getId().equals(userProject.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        card.setTitle(title);
        card.setDescription(description);
        card.setLimitDate(limitDate);
        card.setStoryPoints(storyPoints);
        
        return cardRepository.save(card);
    }
    
    public void deleteCard(String cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        
        // Vérifier que la carte appartient à l'utilisateur
        Project userProject = userService.getCurrentUserProject();
        if (!card.getProject().getId().equals(userProject.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        cardRepository.delete(card);
    }

  // public List<Card> getAllCards() {
  //   return cardRepository.findAll();
  // }

  // public Optional<Card> getCardById(String id) {
  //   return cardRepository.findById(id);
  // }

  // public List<Card> getCardsByColumn(String columnId) {
  //   return cardRepository.findByColumnId(columnId);
  // }

  // public Card createCard(CreatedCardRequest cardRequest) {
  //   Card card = new Card();
  //   card.setTitle(cardRequest.getTitle());
  //   card.setDescription(cardRequest.getDescription());
  //   card.setLimitDate(cardRequest.getLimitDate());
  //   card.setStoryPoints(cardRequest.getStoryPoints());

  //   if (cardRequest.getColumn_id() != null) {
  //     ColumnEntity column = columnRepository.findById(cardRequest.getColumn_id()).orElseThrow(() -> new RuntimeException("Colonne non trouvée"));
  //     card.setColumn(column);
  //   }

  //   if (cardRequest.getTags() != null && !cardRequest.getTags().isEmpty()) {
  //     Set<String> validTags = cardRequest.getTags();

  //     for(String tag : validTags) {
  //       if (!tagRepository.existsById(tag)) {
  //         throw new RuntimeException("Tag non trouvé avec l'ID: " + tag);
  //       }
  //     }

  //     Set<Tag> tags = new HashSet<>();
  //     for(String tagId : validTags) {
  //       Tag tag = tagRepository.findById(tagId)
  //         .orElseThrow(() -> new RuntimeException("Tag non trouvé avec l'ID: " + tagId));
  //       tags.add(tag);
  //     }
  //     card.setTags(tags);
  //   }
  //   return cardRepository.save(card);
  // }

  // public Card updateCard(String id, UpdateCardRequest cardRequest) {
  //   Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Carte non trouvée avec l'ID: " + id));
    
  //   if (cardRequest.getTitle() != null) {
  //       card.setTitle(cardRequest.getTitle());
  //   }
  //   if (cardRequest.getDescription() != null) {
  //       card.setDescription(cardRequest.getDescription());
  //   }
  //   if (cardRequest.getLimitDate() != null) {
  //       card.setLimitDate(cardRequest.getLimitDate());
  //   }
  //   if (cardRequest.getStoryPoints() != null) {
  //       card.setStoryPoints(cardRequest.getStoryPoints());
  //   }
    
  //   if (cardRequest.getColumn_id() != null) {
  //     ColumnEntity column = columnRepository.findById(cardRequest.getColumn_id()).orElseThrow(() -> new RuntimeException("Colonne non trouvée"));
  //     card.setColumn(column);
  //   }
    
  //   if (cardRequest.getTags() != null) {
  //     Set<Tag> tags = new HashSet<>();
  //       for (String tagId : cardRequest.getTags()) {
  //         Tag tag = tagRepository.findById(tagId)
  //             .orElseThrow(() -> new RuntimeException("Tag non trouvé avec l'ID: " + tagId));
  //         tags.add(tag);
  //       }
  //       card.setTags(tags);
  //   }
  //   return cardRepository.save(card);
  // }

  // public void deleteCard(String id) {
  //   if (!cardRepository.existsById(id)) {
  //     throw new RuntimeException("Carte non trouvée avec l'ID: " + id);
  //   }
  //   cardRepository.deleteById(id);
  // }

  // public List<Card> getCardsByTag(String tagId) {
  //     return cardRepository.findByTagId(tagId);
  // }
}
