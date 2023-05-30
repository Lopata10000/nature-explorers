package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.ExcursionParticipantDao;
import com.fanta.natureexplorers.entity.ExcursionParticipant;
import java.util.List;

public class ExcursionParticipantService implements ServiceInterface<ExcursionParticipant> {
    private ExcursionParticipantDao excursionParticipantDao;

    public ExcursionParticipantService() {
        excursionParticipantDao = new ExcursionParticipantDao();
    }

    @Override
    public ExcursionParticipant getById(Integer id) {
        return excursionParticipantDao.getById(id);
    }

    @Override
    public List<ExcursionParticipant> getAll() {
        return excursionParticipantDao.getAll();
    }

    @Override
    public void save(ExcursionParticipant entity) {
        validateAndSave(entity);
        excursionParticipantDao.save(entity);
    }

    @Override
    public void update(Integer id, ExcursionParticipant entity) {
        validateAndUpdate(id, entity);
        ExcursionParticipant existingExcursionParticipant = excursionParticipantDao.getById(id);
        if (existingExcursionParticipant != null) {
            entity.setId(existingExcursionParticipant.getId());
            excursionParticipantDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        ExcursionParticipant excursionParticipant = excursionParticipantDao.getById(id);
        if (excursionParticipant != null) {
            excursionParticipantDao.delete(excursionParticipant);
        }
    }
}
