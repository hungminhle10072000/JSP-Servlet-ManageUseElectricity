package com.hdh.services;

import com.hdh.daos.BranchDao;
import com.hdh.dto.RequestAddBranch;
import com.hdh.models.Branch;

import java.util.List;

public class BranchService {

    private final BranchDao branchDao = new BranchDao();

    public List<Branch> listBranches() {
        return branchDao.getllListBranchs();
    }

    public void deleteBranch(Integer id) {
        branchDao.deleteBranch(id);
    }

    public void updateBranch(Branch branch) {
        branchDao.updateBranch(branch);
    }

    public Branch addBranch(RequestAddBranch requestAddBranch) {
        Branch branchAdd = new Branch();
        branchAdd.setNameBranch(requestAddBranch.getNameBranch());
        branchAdd.setAddress(requestAddBranch.getAddress());
        return branchDao.addBranch(branchAdd);
    }

}
