package hr.tvz.dodig.hardwareapp.repository;

import hr.tvz.dodig.hardwareapp.model.Hardware;
import hr.tvz.dodig.hardwareapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaHardwareRepository extends JpaRepository<Hardware, Long> {

    public List<Hardware> findByReviews(List<Review> reviews);

}
