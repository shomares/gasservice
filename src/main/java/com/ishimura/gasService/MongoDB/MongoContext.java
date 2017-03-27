package com.ishimura.gasService.MongoDB;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoContext {
	private String host;
	private String user;
	private String pass;
	private String dbName;
	private int port;

	private MongoClient mongoClient;

	public void close() {
		mongoClient.close();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public MongoCollection<Document> getCollection(String name) {
		// TODO Auto-generated method stub

		// To connect to mongodb server

		if (user != null && pass != null) {
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(MongoCredential.createCredential(user, dbName, pass.toCharArray()));
			mongoClient = new MongoClient(new ServerAddress(host, port), credentials);
		} else
			mongoClient = new MongoClient(this.host, this.port);
		// Now connect to your databases
		MongoDatabase db = mongoClient.getDatabase(dbName);

		MongoCollection<Document> collection = db.getCollection(name);

		if (collection == null) {
			db.createCollection(name);
		}
		collection = db.getCollection(name);
		return collection;

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
