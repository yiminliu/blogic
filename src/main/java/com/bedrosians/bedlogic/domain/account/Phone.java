package com.bedrosians.bedlogic.domain.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bedrosians.bedlogic.util.FormatUtil;

@Embeddable
public class Phone implements Serializable {

	private static final long serialVersionUID = 7982351854896047883L;
	
	@Column(name="apPhone")
	private Long phoneNumber;		
	@Column(name="apExt")
	private Integer extension;
	@Column(name="apCellPhone")
	private Long cellPhoneNumber;	
	
	public Long getPhoneNumber() {
		return FormatUtil.process(phoneNumber);
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Integer getextension() {
		return FormatUtil.process(extension);
	}

	public void setExtension(Integer extension) {
		this.extension = extension;
	}
	
	public Long getCellPhoneNumber() {
		return FormatUtil.process(cellPhoneNumber);
	}

	public void setCellPhoneNumber(Long cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	
}
