package org.jacky.modelless.demo;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class PersonRepository{

	private MongoTemplate mongoTemplate;

	public List<Person> findAll() {
		return getMongoTemplate().find(new Query(), Person.class);
	}

	public void findAndModify(String id) { 
		getMongoTemplate().updateFirst(new Query(Criteria.where("id").is(id)), new Update().inc("age", 3),Person.class);
	}

	public List<Person> findByRegex(String regex) {
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Criteria criteria = new Criteria("name").regex(pattern.toString());
		return getMongoTemplate().find(new Query(criteria), Person.class);
	}

	public Person findOne(String id) {
		return getMongoTemplate().findOne(new Query(Criteria.where("id").is(id)), Person.class);
	}

	public void insert(Person person) {
		getMongoTemplate().insert(person);
	}

	public void removeAll() {
		List<Person> list = this.findAll();
		if(list != null){
			for(Person person : list){
				getMongoTemplate().remove(person);
			}
		}
	}

	public void removeOne(String id){
		Criteria criteria = Criteria.where("id").in(id);
		if(criteria == null){
			 Query query = new Query(criteria);
			 if(query != null && getMongoTemplate().findOne(query, Person.class) != null)
				 getMongoTemplate().remove(getMongoTemplate().findOne(query, Person.class));
		}
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}

