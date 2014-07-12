package com.bedrosians.bedlogic.domain.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.solr.analysis.LetterTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.stereotype.Component;

import com.bedrosians.bedlogic.domain.account.embeddable.Address;
import com.bedrosians.bedlogic.domain.account.embeddable.Contact;
import com.bedrosians.bedlogic.domain.account.embeddable.Contract;
import com.bedrosians.bedlogic.domain.account.embeddable.Credit;
import com.bedrosians.bedlogic.domain.account.embeddable.HighBalance;
import com.bedrosians.bedlogic.domain.account.embeddable.Payment;
import com.bedrosians.bedlogic.domain.account.embeddable.Owner;
import com.bedrosians.bedlogic.domain.account.embeddable.Statement;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "account")
@Entity
@Table(name = "arm", schema = "public")
@DynamicUpdate
@DynamicInsert
@Indexed
@Analyzer(impl = StandardAnalyzer.class)
//@AnalyzerDef(name ="colorCategoryAnalyzer", tokenizer = @org.hibernate.search.annotations.TokenizerDef(factory=LetterTokenizerFactory.class))
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Account {

	//--------- Basic Info ---------//
	private String customerCode;
	private String customerName;
	private String inactiveCode;
	private String ownershipType;
	private String isVendor;
	private String isSlabStore;
	private String treatAsStore;
	private Date bizEstablishedDate;
	private String accountManager;
	private String defaultBranchCode;
	private String buyingGroup;
	private String cashDiscAllowed;
    private String jointCheck;
	private String guaranteeSigned;
	private Date  guaranteeExpdDate;
	private String isVoucherAccount;
	private Date judgementDate;        	
	private String lockHold;
	private Date obepeDate;	
	private Integer frtRateCwt; 
	private String obNotes;
	
	//---------- Components ---------//	
	private Contact apContact;
	private Contact ceoContact;
	private Contact cfoContact;
	private Contact purchaserContact;
	private Contact salesContact;
	protected Address address;
	protected Address physicalAddress;
    private Credit credit;
	private Statement statement;
	private Payment payment;
	private HighBalance highBalance;
	private Owner owner;
    private Contract contract;
	
	private Set<BranchCode> branches = new HashSet<>(0);
	
	@OneToMany(mappedBy = "branchPK.customerCode", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<BranchCode> getBranches() {
       return branches;
    }

    public void setBranches(Set<BranchCode> branches) {
       this.branches = branches;
    }

    //---------- Associations ---------//
   	//private List<Branch> branches = new ArrayList<>(0);
	//@OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//public List<Branch> getBranches() {
	//	return branches;
	//}
	//public void setBranches(List<Branch> branches) {
	//	this.branches = branches;
	//}
	
	
	// private String bankcruptcyCaseNo;
	// private List<CheckPayment> checkPayments = new ArrayList<CheckPayment>(0);
	// private Set<AccountPhone> accountPhones = new HashSet<AccountPhone>(0);
	// private Set<User> accountUsers = new HashSet<User>(0);
	// private Set<BranchPK> brancheIds = new HashSet<>(0);

	@Id
	@DocumentId
	@GeneratedValue(generator = "account_id_generator")
	@GenericGenerator(name = "account_id_generator", strategy = "com.bedrosians.bedlogic.util.account.AccountIdGenerator")
	@Column(name = "custcd")
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Boost(2.0f)
	@Column(name = "coname")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Column(name="ownershiptype")
	public String getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}

	/*
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Column(name="Dba")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name="b2Fax")
    public Long getB2Fax() {
		return b2Fax;
	}
	public void setB2Fax(Long b2Fax) {
		this.b2Fax = b2Fax;
	}

	@Column(name="b2Email")
	public String getB2Email() {
		return b2Email;
	}

	public void setB2Email(String b2Email) {
		this.b2Email = b2Email;
	}
    */
	@Temporal(TemporalType.DATE)
	@Column(name="busestablishdate", columnDefinition = "date default current_date")

	public Date getBizEstablishedDate() {
		return bizEstablishedDate;
	}

	public void setBizEstablishedDate(Date bizEstablishedDate) {
		this.bizEstablishedDate = bizEstablishedDate;
	}
	
	@Column(name="defbranchcd")
	public String getDefaultBranchCode() {
		return defaultBranchCode;
	}

	public void setDefaultBranchCode(String defaultBranchCode) {
		this.defaultBranchCode = defaultBranchCode;
	}
	
	@Column(name="BuyingGroup")
	public String getBuyingGroup() {
		return buyingGroup;
	}

	public void setBuyingGroup(String buyingGroup) {
		this.buyingGroup = buyingGroup;
	}

	@Column(name="cashdiscallowed")
	public String getCashDiscAllowed() {
		return cashDiscAllowed;
	}

	public void setCashDiscAllowed(String cashDiscAllowed) {
		this.cashDiscAllowed = cashDiscAllowed;
	}
	
	@Column(name="JointCheck")
	public String getJointCheck() {
		return jointCheck;
	}

	public void setJointCheck(String jointCheck) {
		this.jointCheck = jointCheck;
	}
	
	 @Column(name="obnotes")
	public String getObNotes() {
		return obNotes;
	}
	public void setObNotes(String obNotes) {
		this.obNotes = obNotes;
	}

	@Column(name="Voucher")
	public String getIsVoucherAccount() {
		return isVoucherAccount;
	}
	public void setIsVoucherAccount(String isVoucherAccount) {
		this.isVoucherAccount = isVoucherAccount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="Judment_Date")
	public Date getJudgementDate() {
		return judgementDate;
	}
	public void setJudgementDate(Date judgementDate) {
		this.judgementDate = judgementDate;
	}

	@Column(name="Lock_Hold")
	public String getLockHold() {
		return lockHold;
	}
	public void setLockHold(String lockHold) {
		this.lockHold = lockHold;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="obepedate")
	public Date getObepeDate() {
		return obepeDate;
	}

	public void setObepeDate(Date obepeDate) {
		this.obepeDate = obepeDate;
	}
	@Column(name="guaranteesigned")
    public String getGuaranteeSigned() {
		return guaranteeSigned;
	}
	public void setGuaranteeSigned(String guaranteeSigned) {
		this.guaranteeSigned = guaranteeSigned;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="guaranteeexpdate")
	public Date getGuaranteeExpdDate() {
		return guaranteeExpdDate;
	}
	public void setGuaranteeExpdDate(Date guaranteeExpdDate) {
		this.guaranteeExpdDate = guaranteeExpdDate;
	}

	@Column(name="frt_rate_cwt", columnDefinition = "numeric default 0")
	public Integer getFrtRateCwt() {
		return frtRateCwt;
	}
	public void setFrtRateCwt(Integer frtRateCwt) {
		this.frtRateCwt = frtRateCwt;
	}
	
	@Embedded
	public Credit getCredit() {
		return credit;
	}
	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	
	@Embedded
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	@Embedded
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Embedded
	public HighBalance getHighBalance() {
		return highBalance;
	}
	public void setHighBalance(HighBalance highBalance) {
		this.highBalance = highBalance;
	}

	@Embedded
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="apContact")),
		@AttributeOverride(name="email", column=@Column(name="apEmail")),
		@AttributeOverride(name="fax", column=@Column(name="apFax")),
		@AttributeOverride(name="notes", column=@Column(name="apNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="apPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="apExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="apCellPhone"))
	})
	public Contact getApContact() {
		return apContact;
	}

	public void setApContact(Contact apContact) {
		this.apContact = apContact;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="ceoContact")),
		@AttributeOverride(name="email", column=@Column(name="ceoEmail")),
		@AttributeOverride(name="fax", column=@Column(name="ceoFax")),
		@AttributeOverride(name="notes", column=@Column(name="ceoNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="ceoPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="ceoExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="ceoCellPhone"))
	})
	public Contact getCeoContact() {
		return ceoContact;
	}

	public void setCeoContact(Contact ceoContact) {
		this.ceoContact = ceoContact;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="cfoContact")),
		@AttributeOverride(name="email", column=@Column(name="cfoEmail")),
		@AttributeOverride(name="fax", column=@Column(name="cfoFax")),
		@AttributeOverride(name="notes", column=@Column(name="cfoNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="cfoPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="cfoExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="cfoCellPhone"))
	})
	public Contact getCfoContact() {
		return cfoContact;
	}

	public void setCfoContact(Contact cfoContact) {
		this.cfoContact = cfoContact;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="purContact")),
		@AttributeOverride(name="email", column=@Column(name="purEmail")),
		@AttributeOverride(name="fax", column=@Column(name="purFax")),
		@AttributeOverride(name="notes", column=@Column(name="purNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="purPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="purExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="purCellPhone"))
	})
	public Contact getPurchaserContact() {
		return purchaserContact;
	}

	public void setPurchaserContact(Contact purchaserContact) {
		this.purchaserContact = purchaserContact;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="slsContact")),
		@AttributeOverride(name="email", column=@Column(name="slsEmail")),
		@AttributeOverride(name="fax", column=@Column(name="slsFax")),
		@AttributeOverride(name="notes", column=@Column(name="slsNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="slsPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="slsExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="slsCellPhone"))
	})
	public Contact getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(Contact salesContact) {
		this.salesContact = salesContact;
	}

	@Embedded
	@IndexedEmbedded(depth=1)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="streeLine1", column=@Column(name="PhysAddr1")),
		@AttributeOverride(name="streeLine2", column=@Column(name="PhysAddr2")),
		@AttributeOverride(name="city", column=@Column(name="PhysCity")),
		@AttributeOverride(name="state", column=@Column(name="PhysStateCd")),
		@AttributeOverride(name="zip", column=@Column(name="PhysZip")),
		@AttributeOverride(name="country", column=@Column(name="PhyCountryCd"))
	})
	public Address getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(Address physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	//@Column(name = "CreditStatus")
	//public String getCreditStatus() {
	//	return creditStatus);
	//}

	//public void setCreditStatus(String creditStatus) {
	//	this.creditStatus = creditStatus;
	//}

	@Column(name = "InactiveCd")
	public String getInactiveCode() {
	//	if (inactiveCode == null || inactiveCode.trim().length() < 1)
	//		inactiveCode = inactiveCode.ACTIVE.getName();
	//	else if("Y".equalsIgnoreCase(inactiveCode.trim()))
	//		inactiveCode = inactiveCode.INACTIVE.getName();
	//	else if("D".equalsIgnoreCase(inactiveCode.trim()))
	//		inactiveCode = inactiveCode.DELETED.getName();	
		return inactiveCode;
	}

	public void setInactiveCode(String inactiveCode) {
		this.inactiveCode = inactiveCode;
	}
  
	@Column(name = "TreatAsStore")
	public String getTreatAsStore() {
		return treatAsStore;
	}

	public void setTreatAsStore(String treatAsStore) {
		this.treatAsStore = treatAsStore;
	}

	@Column(name="Slab")
	public String getIsSlabStore() {
		return isSlabStore;
	}

	public void setIsSlabStore(String isSlabStore) {
		this.isSlabStore = isSlabStore;
	}

	@Column(name = "vendor", columnDefinition = "char default 'N'")
	public String getIsVendor() {
		return isVendor;
	}

	public void setIsVendor(String isVendor) {
		this.isVendor = isVendor;
	}

	@Column(name = "OurArContact")
	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	@Embedded
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	/*
	@OneToMany(mappedBy = "branchPK.customerCode", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<BranchCode> getAccountBranches() {
		return accountBranches;
	}

	public void setAccountBranches(Set<BranchCode> accountBranches) {
		this.accountBranches = accountBranches;
	}
	*/
	
	/*
	 public Set<User> getAccountUsers() { return accountUsers; }
	  
	 public void setAccountUsers(Set<User> accountUsers) { this.accountUsers =
	 accountUsers; }
	  
	 @Transient public List<CheckPayment> getCheckPayments() { return
	 checkPayments; }
	 
	 public void setCheckPayments(List<CheckPayment> checkPayments) {
	 this.checkPayments = checkPayments; }
	 
		
	 @Column(name="caseNo")
	 public String getBankcruptcyCaseNo() {
	 return bankcruptcyCaseNo;
	 }

	 public void setBankcruptcyCaseNo(String bankcruptcyCaseNo) {
	 this.bankcruptcyCaseNo = bankcruptcyCaseNo;
	 }
	*/	 
	
	public Account() {
	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerCode == null) ? 0 : customerCode.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} 
		else if (!customerCode.equals(other.customerCode))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} 
		else if (!customerName.equals(other.customerName))
			return false;
		return true;
	}
	
/*
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="cfoContact")),
		@AttributeOverride(name="email", column=@Column(name="cfoEmail")),
		@AttributeOverride(name="fax", column=@Column(name="cfoFax")),
		@AttributeOverride(name="notes", column=@Column(name="cfoNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="cfoPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="cfoExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="cfoCellPhone"))
	})
	private Contact cfoContact;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="purContact")),
		@AttributeOverride(name="email", column=@Column(name="purEmail")),
		@AttributeOverride(name="fax", column=@Column(name="purFax")),
		@AttributeOverride(name="notes", column=@Column(name="purNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="purPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="purExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="purCellPhone"))
	})
	private Contact purchaseContact;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name", column=@Column(name="slsContact")),
		@AttributeOverride(name="email", column=@Column(name="slsEmail")),
		@AttributeOverride(name="fax", column=@Column(name="slsFax")),
		@AttributeOverride(name="notes", column=@Column(name="slsNotes")),
		@AttributeOverride(name="phone.phoneNumber", column=@Column(name="slsPhone")),
		@AttributeOverride(name="phone.extension", column=@Column(name="slsExt")),
		@AttributeOverride(name="phone.cellPhoneNumber", column=@Column(name="slsCellPhone"))
	})
	private Contact salesContact;

	
	@Column(name="Dba")
	private String companyName;
	@Column(name="b2Fax")
	private Integer b2Fax;
	@Column(name="b2Email")
	private String b2Email;
	@Temporal(TemporalType.DATE)
	@Column(name="busestablishdate", columnDefinition = "date default current_date")
	private Date bizEstablishedDate;
	@Column(name="BuyingGroup")
	private String buyingGroup;
	
	@Column(name="cashdiscallowed")
	private String cashDiscAllowed;
 	@Column(name="ConsolidStmt")
	private String isConsolidatedPayment;
	@Column(name="CreditLimit")
	private Float creditLimit;
	//@Column(name="CreditStatus")
	//private String creditStatus;
	@Temporal(TemporalType.DATE)
	@Column(name="credstatchgdate")
	private Date credStatChgDate;
	@Column(name="credstatnote")
	private String  credStatNote ;
	@Column(name="daystopay")
	private Integer daysToPay;
	@Column(name="defbranchcd")
	private String defBranchId;
	@Column(name="finchgcd")
	private String latePaymentChargeId;
	@Column(name="FinChgRate")
	private Integer latePaymentChargeRate;
	@Column(name="guaranteesigned")
	private String guaranteeSigned;
	@Temporal(TemporalType.DATE)
	@Column(name="guaranteeexpdate")
	private Date  guaranteeExpdDate;
	@Column(name="highbalamt")
	private Float highBalanceAmount;
	@Temporal(TemporalType.DATE)
	@Column(name="highbaldate")
	private Date highBalanceDate;
	@Column(name="invtype")
	private String invoiceType;
	@Column(name="JointCheck")
	private String jointCheck;
	@Temporal(TemporalType.DATE)
	@Column(name="LastCredApRecvdDate")
	private Date lastCreditApprovalDate;
	@Column(name="laststmtamt")
	private Float lastStatementAmount;
	@Temporal(TemporalType.DATE)
	@Column(name="laststmtdate")
	private Date lastStatementDate;
	//@Column(name="OurArContact")
	//private String accountManager;
	@Column(name="ownershiptype")
	private String ownershipType;
	@Column(name="PmtTermsCd")
	private String paymentTerm;
	@Column(name="PoRequired")
	private String PoRequired;    
	@Column(name="PreLienLimit")
	private Float preLienLimit;
	@Column(name="PreLienReq")
	private String preLienRequired;
	@Column(name="PrintPricingOnInv")
	private String priceOnInvoice;
	@Column(name="RequireJobInfo")
	private String requireJobInfo;
	@Column(name="stmtnbr")
	private Integer statementNo;
	@Column(name="stmttype")
	private String statementType;             
	//@Column(name = "TreatAsStore")
	//private String treatAsStore;
	@Column(name="totbalamt", columnDefinition = "numeric default 0")
	private Float totalBalanceAmount;
	@Column(name = "crarnote")
	private String crarNote;
	@Column(name="StmtFreq_M")
	private String paperStatementMailingFrequency;
	@Column(name="StmtFreq_E")
	private String emailStatementMailingFrequency;
	@Column(name="InvFreq_M")
	private String paperInvoiceMailingFrequency;
	@Column(name="InvFreq_E")
	private String emailInvoiceMailingFrequency;
	@Temporal(TemporalType.DATE)
	@Column(name="stmtdate")
	private Date statementDate;
	@Column(name="stmtamt")
	private Float statementAmount;    
	//@Column(name = "vendor")
	//private String vendor;
	@Column(name="wdaystopay")
	private Integer wDaysToPay; 
	@Column(name = "setupby")
	private String setupBy;
	@Column(name="edi_receiverid")
	private Integer ediReceiverId; 
	@Column(name = "email_server")
	private String emailServer;
	
	@Column(name="cg_credit")
	private String cgCredit;
	@Column(name="nsf_count", columnDefinition = "numeric default 0")
	private Integer nonSufficientFundChecks; 
	@Column(name="highbalance6amt", columnDefinition = "numeric default 0")
	private Float highBalance6Amt; 
	@Temporal(TemporalType.DATE)
	@Column(name="highbalance6date")
	private Date highBalance6Date;        
	@Column(name="obnotes")
	private String obNotes;
	@Column(name="Voucher")
	private String isVoucherAccount;
	@Temporal(TemporalType.DATE)
	@Column(name="Judment_Date")
	private Date judgementDate;        
	@Column(name="Lock_Hold")
	private String lockHold;
	@Temporal(TemporalType.DATE)
	@Column(name="obepedate")
	private Date obepeDate;	
	@Temporal(TemporalType.DATE)
	@Column(name="contract_sdate")
	private Date contractStartDate;
	@Temporal(TemporalType.DATE)
	@Column(name="contract_edate")
	private Date contractEndDate;
	@Column(name="frt_rate_cwt", columnDefinition = "numeric default 0")
	private Integer frtRateCwt; 
	@Column(name="print_alt_forms")
	private String printAltForms; 
	@Column(name="last_pmt_amt", columnDefinition = "numeric default 0")
	private Float lastPaymentAmount; 
	@Temporal(TemporalType.DATE)
	@Column(name="last_pmt_date")
	private Date lastPaymentDate;
	*/

	

}
