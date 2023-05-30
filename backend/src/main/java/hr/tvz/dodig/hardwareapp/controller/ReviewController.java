package hr.tvz.dodig.hardwareapp.controller;


import hr.tvz.dodig.hardwareapp.dto.ReviewDTO;
import hr.tvz.dodig.hardwareapp.service.HardwareService;
import hr.tvz.dodig.hardwareapp.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{code}")
    public List<ReviewDTO> getReviewByHardwareCode(@PathVariable final String code) {
        return reviewService.getReviewsByHardwareCode(code);
    }

}
