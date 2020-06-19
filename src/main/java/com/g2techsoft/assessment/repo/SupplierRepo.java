package com.g2techsoft.assessment.repo;

import com.g2techsoft.assessment.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
}
