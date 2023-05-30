package hr.tvz.dodig.hardwareapp.service;

import hr.tvz.dodig.hardwareapp.dto.ReviewDTO;
import hr.tvz.dodig.hardwareapp.model.Review;
import hr.tvz.dodig.hardwareapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDTO mapReviewToDTO(Review review) {
        return new ReviewDTO(review.getNaslov(), review.getTekst(), review.getOcjena());
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(this::mapReviewToDTO).toList();
    }

    @Override
    public List<ReviewDTO> getReviewsByHardwareCode(String hardwareCode) {
        return reviewRepository.findByHardware_Code(hardwareCode).stream().map(this::mapReviewToDTO).toList();
    }

    @Override
    public void deleteByHardwareId(Integer hardware_id) {
        //reviewRepository.deletebyHardware_ID(hardware_id);
    }


}
