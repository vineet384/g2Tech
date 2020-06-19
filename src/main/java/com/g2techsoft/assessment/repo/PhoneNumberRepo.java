package com.g2techsoft.assessment.repo;

import com.g2techsoft.assessment.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, Long> {
}
