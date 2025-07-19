package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.entity.Card;
import com.nextask.nextask_app.api.entity.ColumnEntity;
import com.nextask.nextask_app.api.entity.Tag;
import com.nextask.nextask_app.api.repository.CardRepository;
import com.nextask.nextask_app.api.repository.ColumnRepository;
import com.nextask.nextask_app.api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CardService {
  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private ColumnRepository columnRepository;

  @Autowired
  private TagRepository tagRepository;

  public List<Card> getAllCards() {
    return cardRepository.findAll();
  }

  public Optional<Card> getCardById(String id) {
    return cardRepository.findById(id);
  }

  public List<Card> getCardsByColumn(String columnId) {
    return cardRepository.findByColumnId(columnId);
  }

  public Card createCard(Card card) {
    if (card.getColumn() != null) {
      ColumnEntity column = columnRepository.findById(card.getColumn().getId()).orElseThrow(() -> new RuntimeException("Colonne non trouvée"));
      card.setColumn(column);
    }

    if (card.getTags() != null && !card.getTags().isEmpty()) {
      Set<Tag> validTags = card.getTags();
      for(Tag tag : validTags) {
        if (!tagRepository.existsById(tag.getId())) {
          throw new RuntimeException("Tag non trouvé avec l'ID: " + tag.getId());
        }
      }
    }
    return cardRepository.save(card);
  }

  public Card updateCard(String id, Card cardDetails) {
    Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Carte non trouvée avec l'ID: " + id));
    
    card.setTitle(cardDetails.getTitle());
    card.setDescription(cardDetails.getDescription());
    card.setLimitDate(cardDetails.getLimitDate());
    card.setStoryPoints(cardDetails.getStoryPoints());
    
    if (cardDetails.getColumn() != null) {
      ColumnEntity column = columnRepository.findById(cardDetails.getColumn().getId()).orElseThrow(() -> new RuntimeException("Colonne non trouvée"));
      card.setColumn(column);
    }
    
    if (cardDetails.getTags() != null) {
      card.setTags(cardDetails.getTags());
    }
    
    return cardRepository.save(card);
  }

  public void deleteCard(String id) {
    if (!cardRepository.existsById(id)) {
      throw new RuntimeException("Carte non trouvée avec l'ID: " + id);
    }
    cardRepository.deleteById(id);
  }

  public List<Card> getCardsByTag(String tagId) {
        return cardRepository.findByTagId(tagId);
    }
}
