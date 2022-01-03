package com.example.demo.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.model.users.Address;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="entities")
public class EntityClass {
	@Id
	@SequenceGenerator(name = "mySeqGenEntity", sequenceName = "mySeqEntity", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenEntity")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;
	
	@Column(name="promoDescription", unique=false, nullable=false)
	private String promoDescription; //promotivni opis
	
	@OneToMany(mappedBy = "entity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Image> image;
	
	@OneToMany(mappedBy = "entity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdditionalService> additionalServices;
	
	@Column(name="rules", unique=false, nullable=false)
	private String rules;
	
	@Column(name="price", unique=false, nullable=false)
	private int price; //je l ovo dovoljno za cenovnik
	//slobodni termini sa akcijama za rezervaciju OVO NEMAMO

	@Column(name="grade", unique=false, nullable=false)
	private double grade;
	
	public EntityClass() {
		super();
	}

	public EntityClass(int id, String name, Address address, String promoDescription, Set<Image> image,
			Set<AdditionalService> additionalServices, String rules, int price, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.promoDescription = promoDescription;
		this.image = image;
		this.additionalServices = additionalServices;
		this.rules = rules;
		this.price = price;
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public Set<Image> getImage() {
		return image;
	}

	public void setImage(Set<Image> image) {
		this.image = image;
	}

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}