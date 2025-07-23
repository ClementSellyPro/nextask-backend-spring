package com.nextask.nextask_app.api.controller;

import com.nextask.nextask_app.api.DTO.CreatedCardRequest;
import com.nextask.nextask_app.api.DTO.UpdateCardRequest;
import com.nextask.nextask_app.api.entity.Card;
import com.nextask.nextask_app.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {
  @Autowired
    private CardService cardService;
    
    // GET /api/cards - Récupérer toutes les cartes
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }
    
    // GET /api/cards/{id} - Récupérer une carte par ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable String id) {
        Optional<Card> card = cardService.getCardById(id);
        return card.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/cards/column/{columnId} - Récupérer les cartes d'une colonne
    @GetMapping("/column/{columnId}")
    public ResponseEntity<List<Card>> getCardsByColumn(@PathVariable String columnId) {
        List<Card> cards = cardService.getCardsByColumn(columnId);
        return ResponseEntity.ok(cards);
    }

    // POST /api/cards - Créer une nouvelle carte
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody CreatedCardRequest cardRequest) {
        try {
            Card createdCard = cardService.createCard(cardRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
        } catch (RuntimeException e) {
            System.err.println("ERROR CREATING CARD: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/cards/{id} - Mettre à jour une carte
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable String id, @RequestBody UpdateCardRequest cardRequest) {
        try {
            Card updatedCard = cardService.updateCard(id, cardRequest);
            return ResponseEntity.ok(updatedCard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/cards/{id} - Supprimer une carte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id) {
        try {
            cardService.deleteCard(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/cards/tag/{tagId} - Récupérer les cartes par tag
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<List<Card>> getCardsByTag(@PathVariable String tagId) {
        List<Card> cards = cardService.getCardsByTag(tagId);
        return ResponseEntity.ok(cards);
    }
}
