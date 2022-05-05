package com.hdh.services;

import com.hdh.daos.FormUseDao;
import com.hdh.dto.FormUseAdd;
import com.hdh.models.FormUse;

import java.util.List;

public class FormUseService {

    private final FormUseDao formUseDao = new FormUseDao();

    public List<FormUse> listFormUses() {
        return formUseDao.getAllListFormUse();
    }

    public void deleteFormUses(Integer id) {
        formUseDao.deleteFormUse(id);
    }

    public FormUse addFormUse(FormUseAdd formUseAdd) {
        FormUse formUse = new FormUse();
        formUse.setNameForm(formUseAdd.getNameForm());
        formUse.setUnitPrice(formUseAdd.getUnitPrice());
        return formUseDao.addFormUse(formUse);
    }

    public boolean updateFormUse(FormUse formUse) {
        return formUseDao.updateFormUse(formUse);
    }

    public FormUse findFormUseById(Integer id) {
        return formUseDao.findFormUseById(id);
    }

    public List<FormUse> findFormUse(String keyWord) {
        return formUseDao.findFormUse(keyWord);
    }
}
