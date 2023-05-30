package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.TripParticipantDao;
import com.fanta.natureexplorers.entity.TripParticipant;
import java.util.List;

public class TripParticipantService implements ServiceInterface<TripParticipant> {
    private TripParticipantDao tripParticipantDao;

    public TripParticipantService() {
        tripParticipantDao = new TripParticipantDao();
    }

    @Override
    public TripParticipant getById(Integer id) {
        return tripParticipantDao.getById(id);
    }

    @Override
    public List<TripParticipant> getAll() {
        return tripParticipantDao.getAll();
    }

    @Override
    public void save(TripParticipant entity) {
        validateAndSave(entity);
        tripParticipantDao.save(entity);
    }

    @Override
    public void update(Integer id, TripParticipant entity) {
        validateAndUpdate(id, entity);
        TripParticipant existingTripParticipant = tripParticipantDao.getById(id);
        if (existingTripParticipant != null) {
            entity.setId(existingTripParticipant.getId());
            tripParticipantDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        TripParticipant tripParticipant = tripParticipantDao.getById(id);
        if (tripParticipant != null) {
            tripParticipantDao.delete(tripParticipant);
        }
    }
}
