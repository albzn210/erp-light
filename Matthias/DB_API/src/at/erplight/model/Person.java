package at.erplight.model;

// Generated 21.11.2014 00:35:55 by Hibernate Tools 3.4.0.CR1

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Person generated by hbm2java
 */
@Entity
@Table(name = "person", schema = "public")
public class Person implements java.io.Serializable {

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
	private Set<Email> emails = new HashSet<Email>(0);
	private Set<Type> types = new HashSet<Type>(0);
	private Set<Person> persons = new HashSet<Person>(0);
	private Set<Platformuser> platformusers = new HashSet<Platformuser>(0);
	private Set<IncomingDelivery> incomingDeliveries = new HashSet<IncomingDelivery>(
			0);
	private Set<OutgoingDelivery> outgoingDeliveries = new HashSet<OutgoingDelivery>(
			0);
	private Set<DeliveryList> deliveryLists = new HashSet<DeliveryList>(0);
	private Set<Telefone> telefones = new HashSet<Telefone>(0);

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
			Set<Organisation> organisations, Set<Email> emails,
			Set<Type> types, Set<Person> persons,
			Set<Platformuser> platformusers,
			Set<IncomingDelivery> incomingDeliveries,
			Set<OutgoingDelivery> outgoingDeliveries,
			Set<DeliveryList> deliveryLists, Set<Telefone> telefones) {
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
		this.emails = emails;
		this.types = types;
		this.persons = persons;
		this.platformusers = platformusers;
		this.incomingDeliveries = incomingDeliveries;
		this.outgoingDeliveries = outgoingDeliveries;
		this.deliveryLists = deliveryLists;
		this.telefones = telefones;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator_person_id")
	@SequenceGenerator(name="generator_person_id", sequenceName="person_person_id_seq", allocationSize=1)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Organisation> getOrganisations() {
		return this.organisations;
	}

	public void setOrganisations(Set<Organisation> organisations) {
		this.organisations = organisations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "person_type", schema = "public", joinColumns = { @JoinColumn(name = "person_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "type_id", nullable = false, updatable = false) })
	public Set<Type> getTypes() {
		return this.types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Platformuser> getPlatformusers() {
		return this.platformusers;
	}

	public void setPlatformusers(Set<Platformuser> platformusers) {
		this.platformusers = platformusers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<IncomingDelivery> getIncomingDeliveries() {
		return this.incomingDeliveries;
	}

	public void setIncomingDeliveries(Set<IncomingDelivery> incomingDeliveries) {
		this.incomingDeliveries = incomingDeliveries;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<OutgoingDelivery> getOutgoingDeliveries() {
		return this.outgoingDeliveries;
	}

	public void setOutgoingDeliveries(Set<OutgoingDelivery> outgoingDeliveries) {
		this.outgoingDeliveries = outgoingDeliveries;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<DeliveryList> getDeliveryLists() {
		return this.deliveryLists;
	}

	public void setDeliveryLists(Set<DeliveryList> deliveryLists) {
		this.deliveryLists = deliveryLists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Telefone> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

}
