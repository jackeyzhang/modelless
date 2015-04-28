package org.jacky.modelless;

import java.util.Date;

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
    
    userDao.insert( user );
    
    User u = userDao.findOne( "123456", User.class );
    System.out.println(u.toString());
    
    context.close();
  }
}
