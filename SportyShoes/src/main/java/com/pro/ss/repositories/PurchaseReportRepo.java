package com.pro.ss.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pro.ss.model.PurchaseReport;

@Repository
public interface PurchaseReportRepo extends JpaRepository<PurchaseReport, Integer> {
	
	@Query(value="SELECT * FROM purchase_reports WHERE timestamp >= ?1 AND timestamp <= ?2 ",nativeQuery = true)
	List<PurchaseReport> findByListDateRange(Date startDate, Date endDate);
	
	List<PurchaseReport> findByCategoryid(int cat);

}
