package com.ishimura.gasService.MongoDB;

public class MongoFactory implements IMongoDBProvider {

	private String host;
	private String user;
	private String pass;
	private String dbName;
	private int port;

	@Override
	public MongoContext getContext() {
		// TODO Auto-generated method stub
		MongoContext mongo = new MongoContext();
		mongo.setHost(host);
		mongo.setUser(user);
		mongo.setPass(pass);
		mongo.setDbName(dbName);
		mongo.setPort(port);
		return mongo;
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

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
