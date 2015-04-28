/**
 * 
 */
package org.jacky.modelless.mongo.business;

import org.jacky.modelless.mongo.core.Entity;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author jzhang12
 *
 */

@Document(collection = "resource")
public class Resource extends Entity {

  private static final long serialVersionUID = -8531212228871703732L;
  
  private String name;
  
  private Long value;
  
  private boolean enable = true;

  
  public String getName() {
    return name;
  }

  
  public void setName( String name ) {
    this.name = name;
  }

  
  public Long getValue() {
    return value;
  }

  
  public void setValue( Long value ) {
    this.value = value;
  }

  
  public boolean isEnable() {
    return enable;
  }

  
  public void setEnable( boolean enable ) {
    this.enable = enable;
  }


  @Override
  public String toString() {
    return "Resource [name=" + name + ", value=" + value + ", enable=" + enable + "]";
  }
  
}
