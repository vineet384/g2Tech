package com.daimler.travelbook.roadtrip.repo;

import com.daimler.travelbook.roadtrip.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
    /**
     * while adding customer
     * @param name
     * @param regNo
     * @return
     */
    @Query("SELECT c FROM Company c where c.name =:name and c.registration_number=:registration_number")
    public Optional<Company> findCompanyByCustomer(@Param("name") String name,@Param("registration_number") String regNo);

    @Query("SELECT c FROM Company c where c.name =:name and c.registration_number=:registration_number ")
    public Optional<Company> findCompanyBySupplierID(@Param("name") String name,@Param("registration_number") String regNo);
}
