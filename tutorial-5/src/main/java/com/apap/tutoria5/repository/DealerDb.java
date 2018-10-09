package com.apap.tutoria5.repository;

import com.apap.tutoria5.model.DealerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * DealerDb
 * @author rico.putra
 * @version 2/10/18
 */
@Repository
public interface DealerDb extends JpaRepository<DealerModel, Long> {
	List<DealerModel> findAll();
	
	void delete(DealerModel dealer);
}
