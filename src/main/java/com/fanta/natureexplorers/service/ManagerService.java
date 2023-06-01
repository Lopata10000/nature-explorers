package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.ManagerDao;
import com.fanta.natureexplorers.entity.Manager;
import java.util.List;

public class ManagerService implements ServiceInterface<Manager> {
    private ManagerDao managerDao;

    public ManagerService() {
        managerDao = new ManagerDao();
    }

    @Override
    public Manager getById(Integer id) {
        return managerDao.getById(id);
    }

    @Override
    public List<Manager> getAll() {
        return managerDao.getAll();
    }

    @Override
    public void save(Manager entity) {
        validateAndSave(entity);
        managerDao.save(entity);
    }

    @Override
    public void update(Integer id, Manager entity) {
        validateAndUpdate(id, entity);
        Manager existingManager = managerDao.getById(id);
        if (existingManager != null) {
            entity.setManagerId(existingManager.getManagerId());
            managerDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        Manager manager = managerDao.getById(id);
        if (manager != null) {
            managerDao.delete(manager);
        }
    }
}
