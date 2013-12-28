package com.bedrosians.bedlogic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.domain.FullAccount;
import com.bedrosians.bedlogic.domain.Account;


@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, String> implements AccountDao {
	

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {
	    //return sessionFactory.getCurrentSession();
		return sessionFactory.openSession();
	}
	
	
	public List<Account> getAllAccounts(){
		String queryString = "from Account";
		Session session = currentSession();
		Query query = session.createQuery(queryString);
		//query.setParameter("status", status);
		query.setMaxResults(10);
		List<Account> list = (List<Account>)query.list();
		
		return list;
	}
	
	public List<Account> getActiveAccounts(){
		String queryString = "from Account a where activityStatus = 'Y' or activityStatus = ''";
		Session session = currentSession();
		Query query = session.createQuery(queryString);
		query.setMaxResults(10);
		List<Account> list = (List<Account>)query.list();
		
		return list;
	}
			
	public List<Account> getAccountsByActivityStatus(String status){
		String queryString = "from Account a where activityStatus =: status";
		Session session = currentSession();
		Query query = session.createQuery(queryString);
		query.setParameter("status", status);
		query.setMaxResults(10);
		List<Account> list = (List<Account>)query.list();
		
		return list;
	}
	
	
	  //public List<Account> getAccountById(String accountId){
	//	  return null;
	 // }
	  
	  //public Account getAccountByName(String accountName){
		//  return null;
	  //}
	  public List<Account> getAccountsByParameter(String parameterName, String value){
		    String queryString = "from Account where ".concat(parameterName.concat(" = :")).concat(parameterName);
			Session session = currentSession();
			Query query = session.createQuery(queryString);
			query.setParameter(parameterName, value);
			query.setMaxResults(10);
			List<Account> list = (List<Account>)query.list();
			
			return list;
	  }
	  	  
	  
	  public List<Account> getAccountByParameter(String[] parameterNames, String[] values){
		    String condition = "";
		    List<Account> accountList = null;
		    for(int i = 0; i < parameterNames.length; i++){
		    	if(i < parameterNames.length - 1)
		    	  condition.concat(parameterNames[i].concat(" = :")).concat(parameterNames[i]).concat(" AND ");
		    	else
		    		condition.concat(parameterNames[i].concat(" = :")).concat(parameterNames[i]);	
		    }
		    String queryString = "from Account where ".concat(condition);
			Session session = currentSession();
			Query query = session.createQuery(queryString);
			for(int i = 0; i < parameterNames.length; i++){
			    query.setParameter(parameterNames[i], values[i]);
			}
			
			accountList = (List<Account>)query.list();
			
			return accountList;
	  }
	  	
	
	//@SuppressWarnings("unchecked")
	//@Override
	//@Transactional(readOnly=true)
/*	public List<SimpleAccount> getSimpleAccounts(
			String accountName,
			String addressStreetLine1,
			String addressCity,
			String addressState,
			String addressZip,
			String caseNo,
			String ownerFirstName,
			String ownerLastName,
			String ownerDriverLicenseNo,
			String phoneNo,
			String activityStatus){
		
		ProjectionList projList = Projections.projectionList()
				.add(Projections.property("accountId"), "accountId")
				.add(Projections.property("accountName"), "accountName")
				.add(Projections.property("creditStatus"), "creditStatus")
				.add(Projections.property("activityStatus"), "activityStatus")
				.add(Projections.property("addressCity"), "addressCity")
				.add(Projections.property("addressState"), "addressState")
				.add(Projections.property("addressZip"), "addressZip");
		Criteria crit = currentSession().createCriteria(Account.class)
			.setProjection(Projections.distinct(projList))
				.setResultTransformer(Transformers.aliasToBean(SimpleAccount.class));
		if (accountName != null) {
			crit.add(Restrictions.like("accountName", "%".concat(accountName).concat("%")).ignoreCase());
		}
		
		if (addressStreetLine1 != null){
			crit.add(Restrictions.like("addressStreetLine1", "%".concat(addressStreetLine1).concat("%")).ignoreCase());
		}

		if (addressCity != null){
			crit.add(Restrictions.eq("addressCity", addressCity).ignoreCase());
		}
		
		if (addressState != null){
			crit.add(Restrictions.eq("addressState", addressState).ignoreCase());
		}
		
		if (addressZip != null){
			crit.add(Restrictions.eq("addressZip", addressZip).ignoreCase());
		}
		
		if (caseNo != null){
			crit.add(Restrictions.eq("caseNo", caseNo).ignoreCase());
		}
		
		if (ownerFirstName != null){
			crit.add(Restrictions.eq("ownerFirstName", ownerDriverLicenseNo).ignoreCase());
		}
		
		if (ownerLastName != null){
			crit.add(Restrictions.eq("ownerLastName", ownerLastName).ignoreCase());
		}
		
		if (ownerDriverLicenseNo != null){
			crit.add(Restrictions.eq("ownerDriverLicenseNo", ownerLastName).ignoreCase());
		}
		
		if (activityStatus != null){
			activityStatus = activityStatus.toLowerCase();
			if (activityStatus.compareTo("all") != 0){
				if (activityStatus.equals("active")) {
					activityStatus = "Y";
				} else if (activityStatus.equals("inactive")){
					activityStatus = "D";
				}
				crit.add(Restrictions.eq("activityStatus", activityStatus).ignoreCase());
			}
		}
		
		if (phoneNo != null){
			StringBuilder phoneSb = new StringBuilder();
			for (int i = 0 ; i < phoneNo.length() ; i++){
				char c = phoneNo.charAt(i);
				if (c >= 0 && c <= '9'){
					phoneSb.append(c);
				}
			}
			crit.createCriteria("phoneNumbers")
				.add(Restrictions.eq("number", Long.valueOf(phoneSb.toString())));
		}

		return crit.list();
		*/
	   
	  
		@SuppressWarnings("unchecked")
		@Override
		@Transactional(readOnly=true)
		@Deprecated
		public List<Account> getAccounts(
				String accountName,
				String addressStreetLine1,
				String addressCity,
				String addressState,
				String addressZip,
				String caseNo,
				String ownerFirstName,
				String ownerLastName,
				String ownerDriverLicenseNo,
				String phoneNo,
				String activityStatus){
			String queryString = "from Account";
			Session session = currentSession();
			Query query = session.createQuery(queryString);
			query.setMaxResults(10);
			List<Account> list = (List<Account>)query.list();
	     	
			return list;
		}
		
}
