package com.hdh.services;

import com.hdh.daos.FormUseDao;
import com.hdh.models.FormUse;

import java.util.List;

public class FormUseService {

    private final FormUseDao formUseDao = new FormUseDao();

    public List<FormUse> listFormUses() {
        return formUseDao.getAllListFormUse();
    }
}
