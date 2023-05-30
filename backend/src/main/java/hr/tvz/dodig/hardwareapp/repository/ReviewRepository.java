package hr.tvz.dodig.hardwareapp.repository;


import hr.tvz.dodig.hardwareapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAll();

    public List<Review> findByHardware_Code(String hardwareCode);

    public List<Review> findByNaslovStartingWithIgnoreCase(String input);


}
