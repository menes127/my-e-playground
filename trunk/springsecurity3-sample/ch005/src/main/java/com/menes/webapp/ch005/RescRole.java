package com.menes.webapp.ch005;

public class RescRole implements java.io.Serializable {
	private static final long serialVersionUID = 0L;
	private String resc;
	private String role;
	
	public RescRole(){}
	
	public String getResc() {
		return resc;
	}
	public void setResc(String resc) {
		this.resc = resc;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
