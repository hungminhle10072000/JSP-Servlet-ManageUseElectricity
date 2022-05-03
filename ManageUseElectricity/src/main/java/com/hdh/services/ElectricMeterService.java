package com.hdh.services;

import com.hdh.daos.ElectricMeterDao;
import com.hdh.models.ElectricMeter;

import java.util.List;

public class ElectricMeterService {

    private final ElectricMeterDao electricMeterDao = new ElectricMeterDao();

    public boolean addElectricMeter(ElectricMeter electricMeter) {
        return electricMeterDao.addElectricMeter(electricMeter);
    }

    public ElectricMeter findElectricMeterByContract(Long idContract) {
        return electricMeterDao.findElectricMeterByContract(idContract);
    }

    public List<ElectricMeter> getAllElectricMeter() {
        return electricMeterDao.getAllElectricMeter();
    }

    public boolean deleteElectric(Long idDelete) {
        return electricMeterDao.deleteElectricMeter(idDelete);
    }

    public ElectricMeter findElectricMeterById(Long idDetail) {
        return electricMeterDao.findElectricMeterById(idDetail);
    }

    public boolean updateElectric(ElectricMeter electricMeter) {
        return electricMeterDao.updateElectric(electricMeter);
    }
}
