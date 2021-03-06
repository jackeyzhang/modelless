/**
 * 
 */
package org.jacky.modelless.mongo.business.data;

import java.util.Date;

import org.jacky.modelless.mongo.core.Entity;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author jzhang12
 *
 */

@Document(collection = "user")
public class User extends Entity {
  
  private static final long serialVersionUID = -1581215666113058977L;
  
  private String identity;
  
  private String name;
  
  private Date birthday;
  
  private String sexy;
  
  private Role role;
  
  public String getIdentity() {
    return identity;
  }


  
  public void setIdentity( String identity ) {
    this.identity = identity;
  }


  
  public String getName() {
    return name;
  }


  
  public void setName( String name ) {
    this.name = name;
  }


  
  public Date getBirthday() {
    return birthday;
  }


  
  public void setBirthday( Date birthday ) {
    this.birthday = birthday;
  }


  
  public String getSexy() {
    return sexy;
  }


  
  public void setSexy( String sexy ) {
    this.sexy = sexy;
  }

  
  public Role getRole() {
    return role;
  }

  public void setRole( Role role ) {
    this.role = role;
  }



  @Override
  public String toString() {
    return "User [name=" + name + ", birthday=" + birthday + ", sexy=" + sexy + ", role=" + (null == role? "" : role.toString()) + "]";
  }

}
