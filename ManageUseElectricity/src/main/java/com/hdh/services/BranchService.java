package com.hdh.services;

import com.hdh.daos.BranchDao;
import com.hdh.models.Branch;

import java.util.List;

public class BranchService {

    private BranchDao branchDao = new BranchDao();

    public List<Branch> listBranches() {
        return branchDao.getllListBranchs();
    }

}
