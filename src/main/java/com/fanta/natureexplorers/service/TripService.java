package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.TripDao;
import com.fanta.natureexplorers.entity.Trip;
import java.util.List;

public class TripService implements ServiceInterface<Trip> {
    private TripDao tripDao;

    public TripService() {
        tripDao = new TripDao();
    }

    @Override
    public Trip getById(Integer id) {
        return tripDao.getById(id);
    }

    @Override
    public List<Trip> getAll() {
        return tripDao.getAll();
    }

    @Override
    public void save(Trip entity) {
        validateAndSave(entity);
        tripDao.save(entity);
    }

    @Override
    public void update(Integer id, Trip entity) {
        validateAndUpdate(id, entity);
        Trip existingTrip = tripDao.getById(id);
        if (existingTrip != null) {
            entity.setTripId(existingTrip.getTripId());
            tripDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        Trip trip = tripDao.getById(id);
        if (trip != null) {
            tripDao.delete(trip);
        }
    }
}
