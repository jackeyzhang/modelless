/**
 * 
 */
package org.jacky.modelless.mongo.business;

import java.util.ArrayList;
import java.util.List;

import org.jacky.modelless.mongo.core.Entity;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author jzhang12
 *
 */

@Document(collection = "role")
public class Role extends Entity {

  private static final long serialVersionUID = -8531212228871703732L;
  
  private String name;
  
  private List<Resource> resources = new ArrayList<Resource>();
  
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

  
  public List<Resource> getResources() {
    return resources;
  }

  
  public void setResources( List<Resource> resources ) {
    this.resources = resources;
  }

  public void addResource(Resource resource){
    this.resources.add( resource );
  }

  @Override
  public String toString() {
    return "Role [name=" + name + ", value=" + value + ", enable=" + enable + "]";
  }
}
