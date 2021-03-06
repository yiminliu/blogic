package com.bedrosians.bedlogic.domain.product;// Generated Feb 27, 2014 2:40:59 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ProdTest generated by hbm2java
 */
@Entity
@Table(name = "prod_test", schema = "public")
public class ProdTest implements java.io.Serializable {

	private String productId;
	private Product product;
	private Float waterAbsorption;
	private Character frostResistance;
	private Character chemicalResistance;
	private Float peiAbrasion;
	private String scofWet;
	private String scofDry;
	private Integer breakingStrength;
	private Float preConsummer;
	private Float posConsummer;
	private String warpage;
	private String wedging;
	private String dcof;
	private String thermalShock;
	private String bondStrength;
	private Character greenFriendly;

	public ProdTest() {
	}

	public ProdTest(Product product) {
		this.product = product;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "product"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "product_id", unique = true, nullable = false, length = 20)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "water_absorption", precision = 4)
	public Float getWaterAbsorption() {
		return this.waterAbsorption;
	}

	public void setWaterAbsorption(Float waterAbsorption) {
		this.waterAbsorption = waterAbsorption;
	}

	@Column(name = "frost_resistance", length = 1)
	public Character getFrostResistance() {
		return this.frostResistance;
	}

	public void setFrostResistance(Character frostResistance) {
		this.frostResistance = frostResistance;
	}

	@Column(name = "chemical_resistance", length = 1)
	public Character getChemicalResistance() {
		return this.chemicalResistance;
	}

	public void setChemicalResistance(Character chemicalResistance) {
		this.chemicalResistance = chemicalResistance;
	}

	@Column(name = "pei_abrasion", precision = 4, scale = 1)
	public Float getPeiAbrasion() {
		return this.peiAbrasion;
	}

	public void setPeiAbrasion(Float peiAbrasion) {
		this.peiAbrasion = peiAbrasion;
	}

	@Column(name = "scof_wet", length = 6)
	public String getScofWet() {
		return this.scofWet;
	}

	public void setScofWet(String scofWet) {
		this.scofWet = scofWet;
	}

	@Column(name = "scof_dry", length = 6)
	public String getScofDry() {
		return this.scofDry;
	}

	public void setScofDry(String scofDry) {
		this.scofDry = scofDry;
	}

	@Column(name = "breaking_strength", precision = 5, scale = 0)
	public Integer getBreakingStrength() {
		return this.breakingStrength;
	}

	public void setBreakingStrength(Integer breakingStrength) {
		this.breakingStrength = breakingStrength;
	}

	@Column(name = "pre_consummer", precision = 5)
	public Float getPreConsummer() {
		return this.preConsummer;
	}

	public void setPreConsummer(Float preConsummer) {
		this.preConsummer = preConsummer;
	}

	@Column(name = "pos_consummer", precision = 5)
	public Float getPosConsummer() {
		return this.posConsummer;
	}

	public void setPosConsummer(Float posConsummer) {
		this.posConsummer = posConsummer;
	}

	@Column(name = "warpage", length = 5)
	public String getWarpage() {
		return this.warpage;
	}

	public void setWarpage(String warpage) {
		this.warpage = warpage;
	}

	@Column(name = "wedging", length = 5)
	public String getWedging() {
		return this.wedging;
	}

	public void setWedging(String wedging) {
		this.wedging = wedging;
	}

	@Column(name = "dcof", length = 6)
	public String getDcof() {
		return this.dcof;
	}

	public void setDcof(String dcof) {
		this.dcof = dcof;
	}

	@Column(name = "thermal_shock", length = 6)
	public String getThermalShock() {
		return this.thermalShock;
	}

	public void setThermalShock(String thermalShock) {
		this.thermalShock = thermalShock;
	}

	@Column(name = "bond_strength", length = 6)
	public String getBondStrength() {
		return this.bondStrength;
	}

	public void setBondStrength(String bondStrength) {
		this.bondStrength = bondStrength;
	}

	@Column(name = "green_friendly", length = 1)
	public Character getGreenFriendly() {
		return this.greenFriendly;
	}

	public void setGreenFriendly(Character greenFriendly) {
		this.greenFriendly = greenFriendly;
	}

	@Override
	public String toString() {
		return "ProdTest [productId=" + productId 
				//+ ", product=" + product
				+ ", waterAbsorption=" + waterAbsorption + ", frostResistance="
				+ frostResistance + ", chemicalResistance="
				+ chemicalResistance + ", peiAbrasion=" + peiAbrasion
				+ ", scofWet=" + scofWet + ", scofDry=" + scofDry
				+ ", breakingStrength=" + breakingStrength + ", preConsummer="
				+ preConsummer + ", posConsummer=" + posConsummer
				+ ", warpage=" + warpage + ", wedging=" + wedging + ", dcof="
				+ dcof + ", thermalShock=" + thermalShock + ", bondStrength="
				+ bondStrength + ", greenFriendly=" + greenFriendly + "]";
	}

	
}
