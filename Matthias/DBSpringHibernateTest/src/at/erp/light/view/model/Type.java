package at.erp.light.view.model;

// Generated 22.11.2014 18:02:50 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Type generated by hbm2java
 */
@Entity
@Table(name = "type", schema = "public")
public class Type implements java.io.Serializable {

	private int typeId;
	private String name;
	private Set<Organisation> organisations = new HashSet<Organisation>(0);
	private Set<Person> persons = new HashSet<Person>(0);

	public Type() {
	}

	public Type(int typeId, String name) {
		this.typeId = typeId;
		this.name = name;
	}

	@Id
	@Column(name = "type_id", unique = true, nullable = false)
	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relorgtype", schema = "public", joinColumns = { @JoinColumn(name = "type_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "organisation_id", nullable = false, updatable = false) })
	public Set<Organisation> getOrganisations() {
		return this.organisations;
	}

	public void setOrganisations(Set<Organisation> organisations) {
		this.organisations = organisations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relpersontype", schema = "public", joinColumns = { @JoinColumn(name = "type_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "person_id", nullable = false, updatable = false) })
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
