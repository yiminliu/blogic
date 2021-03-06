package com.bedrosians.bedlogic.domain.product;
// Generated Feb 27, 2014 2:40:59 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProdUnit generated by hbm2java
 */
@Entity
@Table(name = "prod_unit", schema = "public")
public class ProdUnit implements java.io.Serializable {

	private int unitId;
	private Product product;
	private String name;
	private String unit;
	private BigDecimal unitRatio;
	private Character isStdSell;
	private Character isStdOrder;
	private Character isFractQty;
	private Character isPackUnit;
	private Long upc;
	private BigDecimal volumn;
	private BigDecimal weight;

	public ProdUnit() {
	}

	public ProdUnit(int unitId) {
		this.unitId = unitId;
	}


	@Id
	@Column(name = "unit_id", unique = true, nullable = false)
	public int getUnitId() {
		return this.unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "name", length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "unit", length = 4)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "unit_ratio", precision = 9, scale = 4)
	public BigDecimal getUnitRatio() {
		return this.unitRatio;
	}

	public void setUnitRatio(BigDecimal unitRatio) {
		this.unitRatio = unitRatio;
	}

	@Column(name = "is_std_sell", length = 1)
	public Character getIsStdSell() {
		return this.isStdSell;
	}

	public void setIsStdSell(Character isStdSell) {
		this.isStdSell = isStdSell;
	}

	@Column(name = "is_std_order", length = 1)
	public Character getIsStdOrder() {
		return this.isStdOrder;
	}

	public void setIsStdOrder(Character isStdOrder) {
		this.isStdOrder = isStdOrder;
	}

	@Column(name = "is_fract_qty", length = 1)
	public Character getIsFractQty() {
		return this.isFractQty;
	}

	public void setIsFractQty(Character isFractQty) {
		this.isFractQty = isFractQty;
	}

	@Column(name = "is_pack_unit", length = 1)
	public Character getIsPackUnit() {
		return this.isPackUnit;
	}

	public void setIsPackUnit(Character isPackUnit) {
		this.isPackUnit = isPackUnit;
	}

	@Column(name = "upc", precision = 17, scale = 0)
	public Long getUpc() {
		return this.upc;
	}

	public void setUpc(Long upc) {
		this.upc = upc;
	}

	@Column(name = "volumn", precision = 10, scale = 6)
	public BigDecimal getVolumn() {
		return this.volumn;
	}

	public void setVolumn(BigDecimal volumn) {
		this.volumn = volumn;
	}

	@Column(name = "weight", precision = 10, scale = 6)
	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
