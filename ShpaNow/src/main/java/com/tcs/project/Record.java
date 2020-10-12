package com.tcs.project;

public class Record {
	private int uid;
	private int pid;
	private String policyName;
	private String description;
	private String state;
	private String status;
	private String type;
	private String jurisdiction;
	private String source;
	private int year;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Record [uid=" + uid + ", pid=" + pid + ", policyName=" + policyName + ", description=" + description
				+ ", state=" + state + ", status=" + status + ", type=" + type + ", jurisdiction=" + jurisdiction
				+ ", source=" + source + ", year=" + year + "]";
	}
	public Record() {
		// TODO Auto-generated constructor stub
	}
	public Record(int uid, int pid, String policyName, String description, String state, String status, String type,
			String jurisdiction, String source, int year) {
		this.uid = uid;
		this.pid = pid;
		this.policyName = policyName;
		this.description = description;
		this.state = state;
		this.status = status;
		this.type = type;
		this.jurisdiction = jurisdiction;
		this.source = source;
		this.year = year;
	}
	
}
