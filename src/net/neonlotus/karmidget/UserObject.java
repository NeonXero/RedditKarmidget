package net.neonlotus.karmidget;

/**
 * Created by IntelliJ IDEA.
 * User: RyanThomas
 * Date: 12/8/11
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserObject {
	private static UserObject instance;

	// Global variable
	private String comment;
	private String user;
	private String link;
	private String[] demUsers = new String[10];

	// Restrict the constructor from being instantiated
	private UserObject(){}

	public void setData(String c, String u, String l){
		this.comment = c;
		this.user = u;
		this.link = l;
	}

	public void setUsers (String[] dU) {
		this.demUsers = dU;
	}
	public String[] getDemUsers() {
		return this.demUsers;
	}

	public String getComment(){
		return this.comment;
	}
	public String getUser(){
		return this.user;
	}
	public String getLink(){
		return this.link;
	}

	public static synchronized UserObject getInstance(){
		if(instance==null){
			instance=new UserObject();
		}
		return instance;
	}
}

