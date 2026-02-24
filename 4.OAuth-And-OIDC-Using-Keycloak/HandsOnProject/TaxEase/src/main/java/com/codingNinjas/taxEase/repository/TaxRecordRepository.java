package com.codingNinjas.taxEase.repository;

import com.codingNinjas.taxEase.model.TaxRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRecordRepository extends JpaRepository<TaxRecord, Long>{

}
