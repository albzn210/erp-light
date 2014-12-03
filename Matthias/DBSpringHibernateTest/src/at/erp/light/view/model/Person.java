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

	// Updated by this Person
	public static int FETCH_ORGANISATIONS_UPDATED = 512;			// 0000 0010 0000 0000
	public static int FETCH_INCOMINGDELIVERIES_UPDATED = 1024;		// 0000 0100 0000 0000
	public static int FETCH_OUTGOINGDELIVERIES_UPDATED = 2048;		// 0000 1000 0000 0000
	public static int FETCH_DELIVERYLISTS_UPDATED = 4096;			// 0001 0000 0000 0000
	public static int FETCH_PERSONS_UPDATED = 8192;					// 0010 0000 0000 0000
	public static int FETCH_ALL = 16383;							// 0011 1111 1111 1111
	
	
	
	private int personId;
	private Person person;
	private Address address;
	private Organisation organisation;
	private City city;
	private Country country;
	private String salutation;
	private String title;
	private String firstName;
	private String lastName;
	private String comment;
	private Date updateTimestamp;
	private int active;
	private Set<Organisation> organisations = new HashSet<Organisation>(0);
	private Platformuser platformuser;
	private Set<Type> types = new HashSet<Type>(0);
	private Set<Email> emails = new HashSet<Email>(0);
	private Set<IncomingDelivery> incomingDeliveries = new HashSet<IncomingDelivery>(
			0);
	private Set<Telephone> telephones = new HashSet<Telephone>(0);
	private Set<OutgoingDelivery> outgoingDeliveries = new HashSet<OutgoingDelivery>(
			0);
	private Set<DeliveryList> deliveryLists = new HashSet<DeliveryList>(0);
	private Set<Person> persons = new HashSet<Person>(0);

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

	public Person(int personId, Person person, Address address,
			Organisation organisation, City city, Country country,
			String salutation, String title, String firstName, String lastName,
			String comment, Date updateTimestamp, int active,
			Set<Organisation> organisations, Platformuser platformuser,
			Set<Type> types, Set<Email> emails,
			Set<IncomingDelivery> incomingDeliveries, Set<Telephone> telephones,
			Set<OutgoingDelivery> outgoingDeliveries,
			Set<DeliveryList> deliveryLists, Set<Person> persons) {
		this.personId = personId;
		this.person = person;
		this.address = address;
		this.organisation = organisation;
		this.city = city;
		this.country = country;
		this.salutation = salutation;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.comment = comment;
		this.updateTimestamp = updateTimestamp;
		this.active = active;
		this.organisations = organisations;
		this.platformuser = platformuser;
		this.types = types;
		this.emails = emails;
		this.incomingDeliveries = incomingDeliveries;
		this.telephones = telephones;
		this.outgoingDeliveries = outgoingDeliveries;
		this.deliveryLists = deliveryLists;
		this.persons = persons;
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

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "per_person_id")
	public Person getPerson() {
		return this.person;
	}

	@JsonIgnore
	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = true)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return this.organisation;
	}

	@JsonIgnore
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = true)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Organisation> getOrganisations() {
		return this.organisations;
	}

	@JsonIgnore
	public void setOrganisations(Set<Organisation> organisations) {
		this.organisations = organisations;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
	public Platformuser getPlatformuser() {
		return this.platformuser;
	}

	public void setPlatformuser(Platformuser platformuser) {
		this.platformuser = platformuser;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "persons")
	public Set<Type> getTypes() {
		return this.types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	@OneToMany
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	@JoinColumn(name="person_id", referencedColumnName="person_id")	// added this for unidirectional OneToMany
	public Set<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<IncomingDelivery> getIncomingDeliveries() {
		return this.incomingDeliveries;
	}

	@JsonIgnore
	public void setIncomingDeliveries(Set<IncomingDelivery> incomingDeliveries) {
		this.incomingDeliveries = incomingDeliveries;
	}

	@OneToMany
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	@JoinColumn(name="person_id", referencedColumnName="person_id")	// added this for unidirectional OneToMany
	public Set<Telephone> getTelephones() {
		return this.telephones;
	}

	public void setTelephones(Set<Telephone> telephones) {
		this.telephones = telephones;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<OutgoingDelivery> getOutgoingDeliveries() {
		return this.outgoingDeliveries;
	}

	@JsonIgnore
	public void setOutgoingDeliveries(Set<OutgoingDelivery> outgoingDeliveries) {
		this.outgoingDeliveries = outgoingDeliveries;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<DeliveryList> getDeliveryLists() {
		return this.deliveryLists;
	}

	@JsonIgnore
	public void setDeliveryLists(Set<DeliveryList> deliveryLists) {
		this.deliveryLists = deliveryLists;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Person> getPersons() {
		return this.persons;
	}

	@JsonIgnore
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
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
