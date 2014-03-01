package com.bedrosians.bedlogic.domain.product;
// Generated Feb 27, 2014 2:40:59 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ProdSurfaceFinish generated by hbm2java
 */
@Entity
@Table(name = "prod_surface_finish", schema = "public")
public class ProdSurfaceFinish implements java.io.Serializable {

	private String description;
	private Set products = new HashSet(0);

	public ProdSurfaceFinish() {
	}

	public ProdSurfaceFinish(String description) {
		this.description = description;
	}

	public ProdSurfaceFinish(String description, Set products) {
		this.description = description;
		this.products = products;
	}

	@Id
	@Column(name = "description", unique = true, nullable = false, length = 30)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}
