package com.daimler.travelbook.roadtrip.repo;

import com.daimler.travelbook.roadtrip.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
}
