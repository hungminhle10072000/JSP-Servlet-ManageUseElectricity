package com.hdh.services;

import com.hdh.daos.NoteBookDao;
import com.hdh.models.NoteBook;

import java.util.List;

public class NoteBookService {

    private final NoteBookDao noteBookDao = new NoteBookDao();

    public NoteBook findNoteBookByMonthYearCustomer(int month, int year, Long idCustomer) {
        return noteBookDao.findNoteBookByMonthYearCustomer(month, year, idCustomer);
    }

    public boolean addNoteBook(NoteBook noteBookAdd) {
        return noteBookDao.addNoteBook(noteBookAdd);
    }

    public List<NoteBook> getAllNoteBook() {
        return noteBookDao.getAllNoteBook();
    }

    public boolean deleteNoteBook(Long idDelete) {
        return noteBookDao.deleteNoteBook(idDelete);
    }

    public NoteBook findNoteBookById(Long idNoteBook) {
        return noteBookDao.findNoteBookById(idNoteBook);
    }

    public boolean updateNoteBookService(NoteBook noteBookUpdate) {
        return noteBookDao.updateNoteBook(noteBookUpdate);
    }

    public List<NoteBook> findNoteBook(int month, int year, Long idCustomer) {
        return noteBookDao.findNoteBook(month, year, idCustomer);
    }

}
