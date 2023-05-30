package hr.tvz.dodig.hardwareapp.service;

import hr.tvz.dodig.hardwareapp.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    public List<ReviewDTO> getAllReviews();

    public List<ReviewDTO> getReviewsByHardwareCode(String hardwareCode);

    public void            deleteByHardwareId(Integer hardware_id);

}
