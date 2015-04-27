/**
 * 
 */
package org.jacky.modelless.mongo.core;

import java.util.List;
import java.util.Set;

import org.jacky.modelless.demo.d1.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;


/**
 * default dao implementation
 * 
 * @author jzhang12
 * @param <T>
 *
 */

@Repository
public class DefaultDao<T extends Entity> implements BaseDao<T>{

  public static final Logger logger = LoggerFactory.getLogger( UserDaoImpl.class );

  @Autowired
  private MongoTemplate mongoTemplate;

  public void _test() {
    Set<String> colls = this.mongoTemplate.getCollectionNames();
    for ( String coll : colls ) {
      logger.info( "CollectionName=" + coll );
    }
    DB db = this.mongoTemplate.getDb();
    logger.info( "db=" + db.toString() );
  }

  public void insert( T entity ) {
    this.mongoTemplate.insert(entity);
  }

  public void update( T entity ) {
    Query query = new Query();
    query.addCriteria(new Criteria("_id").is(entity.getId()));
    this.mongoTemplate.updateFirst(query, entity, entity.getClass());
  }

  public void createCollection( T entity ) {
    if ( !this.mongoTemplate.collectionExists( entity.getClass()) ) {
      this.mongoTemplate.createCollection( entity.getClass() );
    }
  }


  public List<T> findList( int skip, int limit , Class<T> cls) {
    Query query = new Query();
    query.with(new Sort(new Order(Direction.ASC, "_id")));
    query.skip(skip).limit(limit);
    return this.mongoTemplate.find(query, cls);
  }


  public T findOne( String id, Class<T> cls ) {
    Query query = new Query();
    query.addCriteria(new Criteria("_id").is(id));
    return this.mongoTemplate.findOne(query, cls);
  }
  
}
