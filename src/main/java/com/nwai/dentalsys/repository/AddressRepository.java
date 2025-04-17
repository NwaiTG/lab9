package com.nwai.dentalsys.repository;

import com.nwai.dentalsys.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByOrderByCityAsc();
}
