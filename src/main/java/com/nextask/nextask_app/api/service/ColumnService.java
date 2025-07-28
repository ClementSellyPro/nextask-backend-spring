package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.entity.ColumnEntity;
import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColumnService {

  @Autowired
  private ColumnRepository columnRepository;

  @Autowired
    private UserService userService;

    public List<ColumnEntity> getUserColumns() {
      Project userProject = userService.getCurrentUserProject();
      return columnRepository.findByProjectIdOrderByName(userProject.getId());
    }

    public ColumnEntity createColumn(String name, String color) {
      Project userProject = userService.getCurrentUserProject();
      
      ColumnEntity column = new ColumnEntity();
      column.setName(name);
      column.setColor(color);
      column.setProject(userProject);
      
      return columnRepository.save(column);
    }
    
    public ColumnEntity updateColumn(String columnId, String name, String color) {
      ColumnEntity column = columnRepository.findById(columnId)
              .orElseThrow(() -> new RuntimeException("Column not found"));
      
      // Vérifier que la colonne appartient à l'utilisateur
      Project userProject = userService.getCurrentUserProject();
      if (!column.getProject().getId().equals(userProject.getId())) {
          throw new RuntimeException("Access denied");
      }
      
      column.setName(name);
      column.setColor(color);
      return columnRepository.save(column);
    }
    
    public void deleteColumn(String columnId) {
      ColumnEntity column = columnRepository.findById(columnId)
              .orElseThrow(() -> new RuntimeException("Column not found"));
      
      // Vérifier que la colonne appartient à l'utilisateur
      Project userProject = userService.getCurrentUserProject();
      if (!column.getProject().getId().equals(userProject.getId())) {
          throw new RuntimeException("Access denied");
      }
      
      columnRepository.delete(column);
    }
  // public List<ColumnEntity> getAllColumns() {
  //   return columnRepository.findAll();
  // }

  // public Optional<ColumnEntity> getColumnById(String id) {
  //   return columnRepository.findById(id);
  // }

  // public ColumnEntity createColumn(ColumnEntity column) {
  //   if (columnRepository.existsByName(column.getName())) {
  //     throw new RuntimeException("Une colunne avec ce nom existe déjà");
  //   }
  //   return columnRepository.save(column);
  // }

  // public ColumnEntity updateColumn(String id, ColumnEntity columnDetails) {
  //   ColumnEntity column = columnRepository.findById(id).orElseThrow(() -> new RuntimeException("Colonne non trouvée avec l'ID : " + id));

  //   column.setName(columnDetails.getName());
  //   column.setColor(columnDetails.getColor());

  //   return columnRepository.save(column);
  // }

  // public void deleteColumn(String id) {
  //   if(!columnRepository.existsById(id)) {
  //     throw new RuntimeException("Colonne non trouvée avec l'ID: " + id);
  //   }
  //   columnRepository.deleteById(id);
  // }
}
