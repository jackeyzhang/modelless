package org.jacky.modelless.demo.d1;


import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;

@Repository
public class UserDaoImpl implements UserDao {

	public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	public void _test() {
		Set<String> colls = this.mongoTemplate.getCollectionNames();
		for (String coll : colls) {
			logger.info("CollectionName=" + coll);
		}
		DB db = this.mongoTemplate.getDb();
		logger.info("db=" + db.toString());
	}

	public void createCollection() {
		if (!this.mongoTemplate.collectionExists(UserEntity.class)) {
			this.mongoTemplate.createCollection(UserEntity.class);
		}
	}

	public List<UserEntity> findList(int skip, int limit) {
		Query query = new Query();
		query.with(new Sort(new Order(Direction.ASC, "_id")));
		query.skip(skip).limit(limit);
		return this.mongoTemplate.find(query, UserEntity.class);
	}

	public List<UserEntity> findListByAge(int age) {
		Query query = new Query();
		query.addCriteria(new Criteria("age").is(age));
		return this.mongoTemplate.find(query, UserEntity.class);
	}

	public UserEntity findOne(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(id));
		return this.mongoTemplate.findOne(query, UserEntity.class);
	}

	public UserEntity findOneByUsername(String username) {
		Query query = new Query();
		query.addCriteria(new Criteria("name.username").is(username));
		return this.mongoTemplate.findOne(query, UserEntity.class);
	}

	public void insert(UserEntity entity) {
		this.mongoTemplate.insert(entity);

	}

	public void update(UserEntity entity) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(entity.getId()));
		Update update = new Update();
		update.set("age", entity.getAge());
		update.set("password", entity.getPassword());
		update.set("regionName", entity.getRegionName());
		update.set("special", entity.getSpecial());
		update.set("works", entity.getWorks());
		update.set("name", entity.getName());
		this.mongoTemplate.updateFirst(query, update, UserEntity.class);

	}

}

