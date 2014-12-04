package at.erp.light.view.dto;

import java.util.List;

public class PersonDTO {

	private int personId;
	
	private String salutation;
	private String title;
	
	private String firstName;
	private String lastName;
	
	private String comment;
	
	private String updateTimestamp;
	private int active;
	
	private String address;
	private String city;
	private String zip;
	private String country;
	
	//Platformuser 
	private String loginEmail;
	private String password;
	private String permission;
	
	//Mitglied Sponsor Mitarbeiter Gast
	private List<String> types;
	private List<String> emails;
	private List<String> telephones;

	
	public PersonDTO(){}

	

	public PersonDTO(int personId, String salutation, String title,
			String firstName, String lastName, String comment,
			String updateTimestamp, int active, String address, String city,
			String zip, String country, String loginEmail, String password,
			String permission, List<String> types, List<String> emails,
			List<String> telephones) {
		super();
		this.personId = personId;
		this.salutation = salutation;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.updateTimestamp = updateTimestamp;
		this.active = active;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.country = country;
		this.loginEmail = loginEmail;
		this.password = password;
		this.permission = permission;
		this.types = types;
		this.emails = emails;
		this.telephones = telephones;
	}



	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(String updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<String> telephones) {
		this.telephones = telephones;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
