package org.jacky.modelless;

import java.util.Date;

import org.jacky.modelless.mongo.business.Resource;
import org.jacky.modelless.mongo.business.ResourceDao;
import org.jacky.modelless.mongo.business.Role;
import org.jacky.modelless.mongo.business.RoleDao;
import org.jacky.modelless.mongo.business.User;
import org.jacky.modelless.mongo.business.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class App {

  public static void main( String[] args ) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext( "applicationContext.xml" );

    UserDao userDao = context.getBean( UserDao.class );
    
    User user = new User();
    user.setBirthday( new Date() );
    user.setIdentity( "jzhang12" );
    user.setSexy( "男" );
    user.setName( "Jacky Zhang" );
    
//    userDao.insert( user );
    
    User u = userDao.findOne( "identity", "jzhang12", User.class );
    System.out.println(u.toString());
    
    Resource indexVisit = new Resource();
    indexVisit.setName( "indexvisit" );
    indexVisit.setValue( 1l );
    
    Resource indexModify = new Resource();
    indexModify.setName( "indexmodify" );
    indexModify.setValue( 1l << 1 );
    
    Role role = new Role();
    role.setName( "commonuser" );
    role.addResource( indexVisit );
    role.addResource( indexModify );
    
    ResourceDao resourceDao = context.getBean( ResourceDao.class );
    resourceDao.insert( indexVisit );
    resourceDao.insert( indexModify );
    
    RoleDao roleDao = context.getBean( RoleDao.class );
    roleDao.insert( role );
    
    System.out.println(roleDao.findOne( "name", "commonuser", Role.class ));
    
    user.setRole( role );
    userDao.update( user );
    
    u = userDao.findOne( "identity", "jzhang12", User.class );
    System.out.println(u.toString());
    
    context.close();
  }
}
