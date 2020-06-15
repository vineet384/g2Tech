package com.daimler.travelbook.roadtrip.repo;

import com.daimler.travelbook.roadtrip.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
