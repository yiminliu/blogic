package com.bedrosians.bedlogic.domain.product;
// Generated Feb 27, 2014 2:40:59 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ProdDetail generated by hbm2java
 */
@Entity
@Table(name = "prod_detail", schema = "public")
public class ProdDetail implements java.io.Serializable {

	private String productId;
	private Product product;
	private String productManager;
	private String buyer;
	private String priorVendorPriceUnit;
	private BigDecimal priorVendorCost;
	private BigDecimal priorVendorFreightRateCwt;
	private Character offShade;
	private Character showOnWebsite;
	private Character showOnAlysedwards;
	private Date createdDate;
	private Date lastModifiedDate;

	public ProdDetail() {
	}

	public ProdDetail(Product product) {
		this.product = product;
	}

	public ProdDetail(Product product, String productManager, String buyer,
			String priorVendorPriceUnit, BigDecimal priorVendorCost,
			BigDecimal priorVendorFreightRateCwt, Character offShade,
			Character showOnWebsite, Character showOnAlysedwards,
			Date createdDate, Date lastModifiedDate) {
		this.product = product;
		this.productManager = productManager;
		this.buyer = buyer;
		this.priorVendorPriceUnit = priorVendorPriceUnit;
		this.priorVendorCost = priorVendorCost;
		this.priorVendorFreightRateCwt = priorVendorFreightRateCwt;
		this.offShade = offShade;
		this.showOnWebsite = showOnWebsite;
		this.showOnAlysedwards = showOnAlysedwards;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "product"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "product_id", unique = true, nullable = false, length = 30)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "product_manager", length = 25)
	public String getProductManager() {
		return this.productManager;
	}

	public void setProductManager(String productManager) {
		this.productManager = productManager;
	}

	@Column(name = "buyer", length = 25)
	public String getBuyer() {
		return this.buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	@Column(name = "prior_vendor_price_unit", length = 4)
	public String getPriorVendorPriceUnit() {
		return this.priorVendorPriceUnit;
	}

	public void setPriorVendorPriceUnit(String priorVendorPriceUnit) {
		this.priorVendorPriceUnit = priorVendorPriceUnit;
	}

	@Column(name = "prior_vendor_cost", precision = 9, scale = 4)
	public BigDecimal getPriorVendorCost() {
		return this.priorVendorCost;
	}

	public void setPriorVendorCost(BigDecimal priorVendorCost) {
		this.priorVendorCost = priorVendorCost;
	}

	@Column(name = "prior_vendor_freight_rate_cwt", precision = 9, scale = 4)
	public BigDecimal getPriorVendorFreightRateCwt() {
		return this.priorVendorFreightRateCwt;
	}

	public void setPriorVendorFreightRateCwt(
			BigDecimal priorVendorFreightRateCwt) {
		this.priorVendorFreightRateCwt = priorVendorFreightRateCwt;
	}

	@Column(name = "off_shade", length = 1)
	public Character getOffShade() {
		return this.offShade;
	}

	public void setOffShade(Character offShade) {
		this.offShade = offShade;
	}

	@Column(name = "show_on_website", length = 1)
	public Character getShowOnWebsite() {
		return this.showOnWebsite;
	}

	public void setShowOnWebsite(Character showOnWebsite) {
		this.showOnWebsite = showOnWebsite;
	}

	@Column(name = "show_on_alysedwards", length = 1)
	public Character getShowOnAlysedwards() {
		return this.showOnAlysedwards;
	}

	public void setShowOnAlysedwards(Character showOnAlysedwards) {
		this.showOnAlysedwards = showOnAlysedwards;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 13)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_modified_date", length = 13)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "ProdDetail [productId=" + productId + ", productManager="
				+ productManager + ", buyer=" + buyer
				+ ", priorVendorPriceUnit=" + priorVendorPriceUnit
				+ ", priorVendorCost=" + priorVendorCost
				+ ", priorVendorFreightRateCwt=" + priorVendorFreightRateCwt
				+ ", offShade=" + offShade + ", showOnWebsite=" + showOnWebsite
				+ ", showOnAlysedwards=" + showOnAlysedwards + ", createdDate="
				+ createdDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	

}
