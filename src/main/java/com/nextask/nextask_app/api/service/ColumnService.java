package com.nextask.nextask_app.api.service;

import com.nextask.nextask_app.api.entity.ColumnEntity;
import com.nextask.nextask_app.api.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ColumnService {
  @Autowired
  private ColumnRepository columnRepository;

  public List<ColumnEntity> getAllColumns() {
    return columnRepository.findAll();
  }

  public Optional<ColumnEntity> getColumnWithCards(String id) {
    return columnRepository.findByIdWithCards(id);
  }

  public Optional<ColumnEntity> getColumnById(String id) {
    return columnRepository.findById(id);
  }

  public ColumnEntity createColumn(ColumnEntity column) {
    if (columnRepository.existsByName(column.getName())) {
      throw new RuntimeException("Une colunne avec ce nom existe déjà");
    }
    return columnRepository.save(column);
  }

  public ColumnEntity updateColumn(String id, ColumnEntity columnDetails) {
    ColumnEntity column = columnRepository.findById(id).orElseThrow(() -> new RuntimeException("Colonne non trouvée avec l'ID : " + id));

    column.setName(columnDetails.getName());
    column.setColor(columnDetails.getColor());

    return columnRepository.save(column);
  }

  public void deleteColumn(String id) {
    if(!columnRepository.existsById(id)) {
      throw new RuntimeException("Colonne non trouvée avec l'ID: " + id);
    }
    columnRepository.deleteById(id);
  }
}
