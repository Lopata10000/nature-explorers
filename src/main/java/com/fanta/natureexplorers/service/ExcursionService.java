package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.ExcursionDao;
import com.fanta.natureexplorers.entity.Excursion;
import java.util.List;

public class ExcursionService implements ServiceInterface<Excursion> {
    private ExcursionDao excursionDao;

    public ExcursionService() {
        excursionDao = new ExcursionDao();
    }

    @Override
    public Excursion getById(Integer id) {
        return excursionDao.getById(id);
    }

    @Override
    public List<Excursion> getAll() {
        return excursionDao.getAll();
    }

    @Override
    public void save(Excursion entity) {
        validateAndSave(entity);
        excursionDao.save(entity);
    }

    @Override
    public void update(Integer id, Excursion entity) {
        validateAndUpdate(id, entity);
        Excursion existingExcursion = excursionDao.getById(id);
        if (existingExcursion != null) {
            entity.setExcursionId(existingExcursion.getExcursionId());
            excursionDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        Excursion excursion = excursionDao.getById(id);
        if (excursion != null) {
            excursionDao.delete(excursion);
        }
    }
}
