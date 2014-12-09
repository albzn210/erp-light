package at.erp.light.view.model;

// Generated 22.11.2014 18:02:50 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Person generated by hbm2java
 */
@Entity
@Table(name = "person", schema = "public")
public class Person implements java.io.Serializable {

	// Fetch Flags
	public static int FETCH_ADDRESS = 1;							// 0000 0000 0000 0001
	public static int FETCH_CITY = 2;								// 0000 0000 0000 0010
	public static int FETCH_COUNTRY = 4;							// 0000 0000 0000 0100
	
	public static int FETCH_PLATFORMUSER = 8;						// 0000 0000 0000 1000
	public static int FETCH_TYPES = 16;								// 0000 0000 0001 0000
	public static int FETCH_EMAILS = 32;							// 0000 0000 0010 0000
	public static int FETCH_TELEPHONES = 64;						// 0000 0000 0100 0000
	
	public static int FETCH_UPDATED_BY_PERSON = 128;				// 0000 0000 1000 0000
	public static int FETCH_ORGANISATION_CONTACT = 256;				// 0000 0001 0000 0000
	public static int FETCH_ALL = 511;
	
	
	private int personId;
	private Address address;
	private City city;
	private Country country;
	private String salutation;
	private String title;
	private String firstName;
	private String lastName;
	private String comment;
	private Date updateTimestamp;
	private int active;
	private Platformuser platformuser;
	private Person person;				// last updated by
//	private Organisation organisation;	
	private Set<Type> types = new HashSet<Type>(0);
	private Set<Email> emails = new HashSet<Email>(0);
	private Set<Telephone> telephones = new HashSet<Telephone>(0);

	public Person() {
	}

	public Person(int personId, String salutation, String title,
			String firstName, String lastName, String comment,
			Date updateTimestamp, int active) {
		this.personId = personId;
		this.salutation = salutation;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.updateTimestamp = updateTimestamp;
		this.active = active;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_person_id")
	@SequenceGenerator(name="gen_person_id", sequenceName="person_person_id_seq", allocationSize=1)
	@Column(name = "person_id", unique = true, nullable = false)
	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "per_person_id")
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", nullable = true)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "organisation_id")
//	public Organisation getOrganisation() {
//		return this.organisation;
//	}
//
//	public void setOrganisation(Organisation organisation) {
//		this.organisation = organisation;
//	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id", nullable = true)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", nullable = true)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "salutation", nullable = false, length = 10)
	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	@Column(name = "title", nullable = false, length = 10)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "first_name", nullable = false, length = 100)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 100)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "comment", nullable = false, length = 1000)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "update_timestamp", nullable = false, length = 13)
	public Date getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@Column(name = "active", nullable = false)
	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "person")
	public Platformuser getPlatformuser() {
		return this.platformuser;
	}

	public void setPlatformuser(Platformuser platformuser) {
		this.platformuser = platformuser;
	}

	// @ManyToMany(fetch = FetchType.EAGER, mappedBy = "persons")	// commented
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "relpersontype", schema = "public", inverseJoinColumns = { @JoinColumn(name = "type_id", nullable = false, updatable = false) }, joinColumns = { @JoinColumn(name = "person_id", nullable = false, updatable = false) })
	public Set<Type> getTypes() {
		return this.types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	@OneToMany(fetch=FetchType.EAGER)
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	@JoinColumn(name="person_id", referencedColumnName="person_id")	// added this for unidirectional OneToMany
	public Set<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	@OneToMany(fetch=FetchType.EAGER)
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	@JoinColumn(name="person_id", referencedColumnName="person_id")	// added this for unidirectional OneToMany
	public Set<Telephone> getTelephones() {
		return this.telephones;
	}

	public void setTelephones(Set<Telephone> telephones) {
		this.telephones = telephones;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(personId);
		sb.append(", Salutation: ").append(salutation);
		sb.append(", Title: ").append(title);
		sb.append(", FirstName: ").append(firstName);
		sb.append(", LastName: ").append(lastName);
		sb.append(", Comment: ").append(comment);
		sb.append(", UpdateTimeStamp: ").append(updateTimestamp);
		sb.append(", active: ").append(active);
		return sb.toString();
	}

}
