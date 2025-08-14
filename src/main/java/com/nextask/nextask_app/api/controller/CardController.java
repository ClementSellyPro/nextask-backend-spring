package com.nextask.nextask_app.api.controller;

import com.nextask.nextask_app.api.DTO.CardMoveRequest;
import com.nextask.nextask_app.api.DTO.CardResponse;
import com.nextask.nextask_app.api.DTO.CreatedCardRequest;
import com.nextask.nextask_app.api.DTO.PositionUpdateRequest;
import com.nextask.nextask_app.api.DTO.UpdateCardRequest;
import com.nextask.nextask_app.api.entity.Card;
import com.nextask.nextask_app.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {
  @Autowired
	private CardService cardService;
	
	// GET /api/cards - Récupérer toutes les cartes
	@GetMapping
	public ResponseEntity<List<CardResponse>> getAllCards(Authentication authentication) {
		List<CardResponse> cards = cardService.getCardsByUser(authentication.getName());
		return ResponseEntity.ok(cards);
	}

	// GET /api/cards/column/{columnId} - Récupérer les cartes d'une colonne
	@GetMapping("/column/{columnId}")
	public ResponseEntity<List<Card>> getCardsByColumn(@PathVariable String columnId) {
		List<Card> cards = cardService.getCardsByColumn(columnId);
		return ResponseEntity.ok(cards);
	}

	// POST /api/cards - Créer une nouvelle carte
	@PostMapping
	public ResponseEntity<CardResponse> createCard(
		@RequestBody CreatedCardRequest cardRequest, 
		Authentication authentication) {
		try {
			Card createdCard = cardService.createCard(
				cardRequest,
				authentication.getName()
			);
			return ResponseEntity.status(HttpStatus.CREATED).body(new CardResponse(createdCard));
		} catch (RuntimeException e) {
			System.err.println("ERROR CREATING CARD: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	// PUT /api/cards/{id} - Mettre à jour une carte
	@PutMapping("/{id}")
	public ResponseEntity<CardResponse> updateCard(
		@PathVariable String id, 
		@RequestBody UpdateCardRequest cardRequest, 
		Authentication authentication) {
		try {
			if(!id.equals(cardRequest.getId())) {
				return ResponseEntity.badRequest().build();
			}

			Card updatedCard = cardService.updateCard(cardRequest, authentication.getName());
			CardResponse cardResponse = new CardResponse(updatedCard);
			return ResponseEntity.ok(cardResponse);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// DELETE /api/cards/{id} - Supprimer une carte
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCard(
		@PathVariable String id, 
		Authentication authentication) {
		try {
			cardService.deleteCard(id, authentication.getName());
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// GET /api/cards/tag - Récupérer les cartes par tag
	@GetMapping("/tag")
	public ResponseEntity<List<CardResponse>> getCardsByTag(
		@RequestParam List<String> tagIds, 
		Authentication authentication) {
	    List<CardResponse> cards = cardService.getCardsByTag(tagIds, authentication.getName());
	    return ResponseEntity.ok(cards);
	}

	// PUT /api/cards/{id}/position - Changer une carte de position
	@PutMapping("/{id}/position")
	public ResponseEntity<Void> updatePosition(
		@PathVariable String id,
		@RequestBody PositionUpdateRequest request,
		Authentication authentication) {
			cardService.reorderCard(id, (request.getNewPosition() + 1) * 1000);
			return ResponseEntity.ok().build();
	}

	// PUT /api/cards/{id}/move - Changer une carte de colonne
	@PutMapping("/{id}/move")
	public ResponseEntity<Void> moveCard(
		@PathVariable String id, 
		@RequestBody CardMoveRequest request,
		Authentication authentication) {
			cardService.moveCardToColumn(id, request.getNewColumnId(), (request.getNewPosition() + 1) * 1000);
			return ResponseEntity.ok().build();
	}
}
