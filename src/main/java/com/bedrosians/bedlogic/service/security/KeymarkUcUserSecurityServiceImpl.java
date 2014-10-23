package com.bedrosians.bedlogic.service.security;

import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;
import com.bedrosians.bedlogic.usercode.UserCodeParser;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;

@Service("keymarkUcUserSecurityService")
@Scope(value = "singleton")
public class KeymarkUcUserSecurityServiceImpl implements KeymarkUcUserSecurityService{

	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	
	@Autowired
	KeymarkUcUserAuthentication keymarkUcUserAuthentication;
	
	@Autowired
	KeymarkUcUserAuthorization keymarkUcUserAuthorization;
	
	KeymarkUcUser keymarkUcUser = null;
	
	@Override
	public void doSecurityCheck(String userType, String userCode, String password, boolean isPasswordBasedAuth, ApiName apiName, DBOperation permission) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(userType) {
		   case "guest": case "Guest":
			   return;
		   case "keymark":	
			   validateUserInfo(userCode, password, isPasswordBasedAuth, apiName, permission);
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}
	}
	 
	@Override
    public void doUserSecurityCheck(HttpHeaders requestHeaders, ApiName apName, DBOperation operation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException {
		 //Check usercode
       UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
       if (!userCodeParser.isValidFormat())
       {
           throw new BedResUnAuthorizedException();
       }
       String userType = userCodeParser.getUserType();
       String userCode = userCodeParser.getUserCode();
       
       //Authenticate the user
       doSecurityCheck(userType, userCode, apName, operation);
	 }
	 
	@Override
	public void doSecurityCheck(String userType, String userCode, ApiName apiName, DBOperation operation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(userType) {
		   case "guest": case "Guest":
			   if(DBOperation.SEARCH.equals(operation))
			      break;
			   else
				   throw new BedResUnAuthorizedException("Guest user is not allowed for " + operation);	
		   case "keymark":	
			   validateUserInfo(userCode, "", false, apiName, operation);
			   break;
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");				      
		}
	}
	
	private void validateUserInfo(String userCode, String password, boolean isPasswordBased, ApiName apiName, DBOperation permission) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		keymarkUcUser = getUser(userCode);        
		keymarkUcUserAuthentication.authenticate(keymarkUcUser, password, isPasswordBased);
		keymarkUcUserAuthorization.authorize(keymarkUcUser, apiName, permission);
	}
	
	private KeymarkUcUser getUser(String userCode) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		if(userCode == null || userCode.length() == 0)
		   throw new BedDAOBadParamException("User code should not be empty with \"keymark\" as user type");	
		KeymarkUcUser user = keymarkUcUserService.getUserByUserCode(userCode);  
		if(user == null)
			throw new BedResUnAuthorizedException("No user found for the given user code " + userCode);
		return user;
	}
	
}
