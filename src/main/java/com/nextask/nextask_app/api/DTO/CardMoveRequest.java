package com.nextask.nextask_app.api.DTO;

public class CardMoveRequest {
  private String newColumnId;
  private Integer newPosition;
  
  public String getNewColumnId() { return newColumnId; }
  public void setNewColumnId(String newColumnId) { this.newColumnId = newColumnId; }
  public Integer getNewPosition() { return newPosition; }
  public void setNewPosition(Integer newPosition) { this.newPosition = newPosition; }

  @Override 
  public String toString() {
    return "CardMoveRequest{" +
      "newColumnId='" + newColumnId + '\'' +
      ", newPosition='" + newPosition +
      '}';
  } 
}
