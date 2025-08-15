package com.nextask.nextask_app.api.DTO;

public class CompletedUpdateRequest {
  private boolean isCompleted;

  public boolean isCompleted() { return isCompleted; }
  public void setIsCompleted(boolean completed) { this.isCompleted = completed; }

  @Override
    public String toString() {
        return "CompletedUpdateRequest{ isCompleted=" + isCompleted + "}";
    }
}
