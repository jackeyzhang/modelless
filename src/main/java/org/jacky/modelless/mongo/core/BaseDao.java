package org.jacky.modelless.mongo.core;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface BaseDao<T extends Entity> {

  public void _test();

  public void createCollection(T entity);

  public List<T> findList( int skip, int limit , Class<T> cls);

  public T findOne( String id , Class<T> cls);

  public void insert( T entity );

  public void update( T entity );
  
  public void clear( Class<T> cls);

}
