package com.dconsult.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ConsultationFormBean {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	private Date creationTime;
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	@Persistent
	private String financingNeeds;
	@Persistent
	private String percentageCoFund;
	@Persistent
	private String primaryCompanyActivity;
	@Persistent
	private String companyEarnings;
	@Persistent
	private String numberOfEmployees;
	@Persistent
	private String priorFinancing;
	@Persistent
	private String companyName;
	@Persistent
	private String contactName;
	@Persistent
	private String address;
	@Persistent
	private String telephone;
	@Persistent
	private String mobile;
	@Persistent
	private String email;
	@Persistent
	private String webAddress;
	
	public String getFinancingNeeds() {
		return financingNeeds;
	}
	public void setFinancingNeeds(String financingNeeds) {
		this.financingNeeds = financingNeeds;
	}
	public String getPercentageCoFund() {
		return percentageCoFund;
	}
	public void setPercentageCoFund(String percentageCoFund) {
		this.percentageCoFund = percentageCoFund;
	}
	public String getPrimaryCompanyActivity() {
		return primaryCompanyActivity;
	}
	public void setPrimaryCompanyActivity(String primaryCompanyActivity) {
		this.primaryCompanyActivity = primaryCompanyActivity;
	}
	public String getCompanyEarnings() {
		return companyEarnings;
	}
	public void setCompanyEarnings(String companyEarnings) {
		this.companyEarnings = companyEarnings;
	}
	public String getNumberOfEmployees() {
		return numberOfEmployees;
	}
	public void setNumberOfEmployees(String numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	public String getPriorFinancing() {
		return priorFinancing;
	}
	public void setPriorFinancing(String priorFinancing) {
		this.priorFinancing = priorFinancing;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
}
