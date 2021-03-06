package com.bedrosians.bedlogic.domain.product;
// Generated Feb 27, 2014 2:40:59 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProdMaterialStyle generated by hbm2java
 */
@Entity
@Table(name = "prod_material_style", schema = "public")
public class ProdMaterialStyle implements java.io.Serializable {

	private String description;

	public ProdMaterialStyle() {
	}

	public ProdMaterialStyle(String description) {
		this.description = description;
	}

	@Id
	@Column(name = "description", unique = true, nullable = false, length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
