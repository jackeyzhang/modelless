package org.jacky.modelless.mongo.core;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.query.Update;


public class Entity extends Update implements Serializable{

  private static final long serialVersionUID = 7086391090823506098L;
  
  @Id
  private String id;

  public Entity( ) {
    this.id = UUID.randomUUID().toString();
  }
  
  public Entity( String id ) {
    super();
    this.id = id;
  }


  public String getId() {
    return id;
  }

  
  public void setId( String id ) {
    this.id = id;
  }
}
