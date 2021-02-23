package com.priceCalculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="carton_price")
	private float cartonPrice;
	
	@Column(name="unit_price")
	private float unitPrice;
	
	@Column(name="unit_for_carton")
	private int unitForCarton;
	
	
	public Product() {
		super();
	}
	
	public Product(String name, float cartonPrice, float unitPrice, int unitForCarton) {
		super();
		this.name = name;
		this.cartonPrice = cartonPrice;
		this.unitPrice = unitPrice;
		this.unitForCarton = unitForCarton;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCartonPrice() {
		return cartonPrice;
	}
	public void setCartonPrice(float cartonPrice) {
		this.cartonPrice = cartonPrice;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getUnitForCarton() {
		return unitForCarton;
	}
	public void setUnitForCarton(int unitForCarton) {
		this.unitForCarton = unitForCarton;
	}

}
