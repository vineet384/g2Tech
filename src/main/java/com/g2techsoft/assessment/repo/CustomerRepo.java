package com.g2techsoft.assessment.repo;

import com.g2techsoft.assessment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
