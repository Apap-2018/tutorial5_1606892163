package com.apap.tutoria5.service;

import java.util.Optional;
import java.util.List;
import com.apap.tutoria5.model.DealerModel;

/**
 * DealerService
 * @author rico.putra
 * @version 2/10/18
 */
public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	
	void addDealer(DealerModel dealer);
	
	List<DealerModel> getListDealer();
	
	void deleteById(Long id);
	
	void updateById(Long id, DealerModel dealerNew);
}
