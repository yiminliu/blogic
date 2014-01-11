package com.bedrosians.bedlogic.dao.account;

import java.util.List;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.account.AccountBranch;
import com.bedrosians.bedlogic.domain.account.BranchPK;

public interface AccountBranchDao extends GenericDao<AccountBranch, BranchPK> {
	
	public AccountBranch getAccountBranchById(String accountId, String branchId);  
    public List<AccountBranch> getAccountBranchByName(String name);

}
