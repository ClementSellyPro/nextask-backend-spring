package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.DTO.CardResponse;
import com.nextask.nextask_app.api.DTO.CreatedCardRequest;
import com.nextask.nextask_app.api.DTO.UpdateCardRequest;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public List<CardResponse> getCardsByUser(String username) {
    Project userProject = projectRepository.findByUserUsername(username)
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + username));

    List<Card> cards = cardRepository.findByProjectId(userProject.getId());

    return cards.stream()
        .map(this::convertToCardResponse)
        .collect(Collectors.toList());
  }

  private CardResponse convertToCardResponse(Card card) {
        CardResponse response = new CardResponse(card);
        
        if (card.getProject() != null) {
            response.setProjectId(card.getProject().getId());
            response.setProjectName(card.getProject().getName());
        }
        
        if (card.getColumn() != null) {
            response.setColumnId(card.getColumn().getId());
            response.setColumnName(card.getColumn().getName());
        }
        
        return response;
    }

  public List<Card> getCardsByColumn(String columnId) {
        // Vérifier que la colonne appartient à l'utilisateur
        ColumnEntity column = columnRepository.findById(columnId)
            .orElseThrow(() -> new RuntimeException("Colonne non trouvé"));
        
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
            .orElseThrow(() -> new RuntimeException("Colonne non trouvé"));
        
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
    
    public Card updateCard(UpdateCardRequest updateCardRequest, String username) {
        Card card = cardRepository.findById(updateCardRequest.getId())
            .orElseThrow(() -> new RuntimeException("Card non trouvé"));
        
        // Vérifier que la carte appartient à l'utilisateur
        Project userProject = projectRepository.findByUserUsername(username)
            .orElseThrow(() -> new RuntimeException("Projet de l'utilisateur non trouvé"));
        if (!card.getProject().getId().equals(userProject.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        card.setTitle(updateCardRequest.getTitle());
        card.setDescription(updateCardRequest.getDescription());
        card.setLimitDate(updateCardRequest.getLimitDate());
        card.setStoryPoints(updateCardRequest.getStoryPoints());

        if(updateCardRequest.getTags() != null) {
            Set<Tag> tags = new HashSet<>();
            for(String tagId : updateCardRequest.getTags()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag non trouvé"));
                tags.add(tag);
            }
            card.setTags(tags);
        }
        return cardRepository.save(card);
    }
    
    public void deleteCard(String cardId, String username) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new RuntimeException("Card non trouvé"));
        
        // Vérifier que la carte appartient à l'utilisateur
        Project userProject = projectRepository.findByUserUsername(username)
            .orElseThrow(() -> new RuntimeException("Project non trouvé"));
        if (!card.getProject().getId().equals(userProject.getId())) {
            throw new RuntimeException("Access denied");
        }
        cardRepository.delete(card);
    }

  public List<CardResponse> getAllCards() {
    List<Card> cards = cardRepository.findAll();
    return cards.stream()
        .map(CardResponse::new)
        .collect(Collectors.toList());
  }
}
