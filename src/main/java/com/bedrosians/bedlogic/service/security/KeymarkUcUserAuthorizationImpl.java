package com.bedrosians.bedlogic.service.security;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;

@Service("keymarkUcUserAuthorization")
@Scope("singleton")
public class KeymarkUcUserAuthorizationImpl implements KeymarkUcUserAuthorization, Serializable{

	public boolean authorize(KeymarkUcUser user, ApiName apiName, DBOperation dBOperation) {
		switch(dBOperation) {
		   case SEARCH:
			   return true;
		   case CREATE:
			   if(ApiName.IMS.equals(apiName))
			       return user.getImallowcreateitem() == 'Y';
			   else if(ApiName.ACCOUNT.equals(apiName))
			       return user.getArcreatecustcd() == 'Y';
		   case UPDATE:	
			   if(ApiName.IMS.equals(apiName))
			      return user.getImallowchgims() == 'Y';
			   else if(ApiName.ACCOUNT.equals(apiName))
			       return user.getArcreatecustcd() == 'Y';
		   case DELETE:	
			   return user.getImallowchgims() == 'Y';	   
		   default:
			   throw new InputParamException ("Unsupported user type");	   
		}	
	}
}
