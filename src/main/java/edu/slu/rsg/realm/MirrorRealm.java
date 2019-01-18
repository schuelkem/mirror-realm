package com.endaemon.catalina.realm;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MirrorRealm extends RealmBase {

	private final Log log = LogFactory.getLog(this.getClass());

	private String username;
	private String password;

	@Override
	protected String getName() {
		return this.username;
	}

	@Override
	protected String getPassword(String s) {
		return this.password;
	}

	@Override
	public Principal authenticate(String username) {
		this.username = username;
		this.password = username;
		log.info("Authentication is taking place with userid: " + username);
		return getPrincipal(username);
	}

	@Override
	protected Principal getPrincipal(String string) {
		List<String> roles = new ArrayList<String>();
		roles.add("user");
		log.info("username is "+ this.username);
		log.info("Realm: " + this);
		Principal principal = new GenericPrincipal(this.username, this.password, roles);
		log.info("Principal: " + principal);
		return principal;
	}
	
}
