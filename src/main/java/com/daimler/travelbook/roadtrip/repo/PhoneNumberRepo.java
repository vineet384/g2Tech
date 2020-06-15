package com.daimler.travelbook.roadtrip.repo;

import com.daimler.travelbook.roadtrip.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, Long> {
}
