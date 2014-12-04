package at.erp.light.view.model;

// Generated 22.11.2014 18:02:50 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Organisation generated by hbm2java
 */
@Entity
@Table(name = "organisation", schema = "public")
public class Organisation implements java.io.Serializable {

	private int organisationId;
	private Address address;
	private City city;
	private Person person;
	private Country country;
	private String name;
	private String comment;
	private Date updateTimestamp;
	private int active;
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Person> persons = new HashSet<Person>(0);
	private Set<IncomingDelivery> incomingDeliveries = new HashSet<IncomingDelivery>(
			0);
	private Set<Type> types = new HashSet<Type>(0);
	private Set<OutgoingDelivery> outgoingDeliveries = new HashSet<OutgoingDelivery>(
			0);

	public Organisation() {
	}

	public Organisation(int organisationId, Address address, City city,
			Person person, Country country, String name, String comment,
			Date updateTimestamp, int active) {
		this.organisationId = organisationId;
		this.address = address;
		this.city = city;
		this.person = person;
		this.country = country;
		this.name = name;
		this.comment = comment;
		this.updateTimestamp = updateTimestamp;
		this.active = active;
	}

	public Organisation(int organisationId, Address address, City city,
			Person person, Country country, String name, String comment,
			Date updateTimestamp, int active, Set<Category> categories,
			Set<Person> persons, Set<IncomingDelivery> incomingDeliveries,
			Set<Type> types, Set<OutgoingDelivery> outgoingDeliveries) {
		this.organisationId = organisationId;
		this.address = address;
		this.city = city;
		this.person = person;
		this.country = country;
		this.name = name;
		this.comment = comment;
		this.updateTimestamp = updateTimestamp;
		this.active = active;
		this.categories = categories;
		this.persons = persons;
		this.incomingDeliveries = incomingDeliveries;
		this.types = types;
		this.outgoingDeliveries = outgoingDeliveries;
	}

	@Id
	@Column(name = "organisation_id", unique = true, nullable = false)
	public int getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = false)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relorgcat", schema = "public", joinColumns = { @JoinColumn(name = "organisation_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "category_id", nullable = false, updatable = false) })
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
	public Set<IncomingDelivery> getIncomingDeliveries() {
		return this.incomingDeliveries;
	}

	public void setIncomingDeliveries(Set<IncomingDelivery> incomingDeliveries) {
		this.incomingDeliveries = incomingDeliveries;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "organisations")
	public Set<Type> getTypes() {
		return this.types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
	public Set<OutgoingDelivery> getOutgoingDeliveries() {
		return this.outgoingDeliveries;
	}

	public void setOutgoingDeliveries(Set<OutgoingDelivery> outgoingDeliveries) {
		this.outgoingDeliveries = outgoingDeliveries;
	}

}
