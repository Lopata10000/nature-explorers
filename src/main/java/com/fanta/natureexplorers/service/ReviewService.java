package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.ReviewDao;
import com.fanta.natureexplorers.entity.Review;
import java.util.List;

public class ReviewService implements ServiceInterface<Review> {
    private ReviewDao reviewDao;

    public ReviewService() {
        reviewDao = new ReviewDao();
    }

    @Override
    public Review getById(Integer id) {
        return reviewDao.getById(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewDao.getAll();
    }

    @Override
    public void save(Review entity) {
        validateAndSave(entity);
        reviewDao.save(entity);
    }

    @Override
    public void update(Integer id, Review entity) {
        validateAndUpdate(id, entity);
        Review existingReview = reviewDao.getById(id);
        if (existingReview != null) {
            entity.setReviewId(existingReview.getReviewId());
            reviewDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        Review review = reviewDao.getById(id);
        if (review != null) {
            reviewDao.delete(review);
        }
    }
}
