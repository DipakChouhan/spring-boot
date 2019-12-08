package com.owneroftime.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owneroftime.repository.ExcelDatabaseRepository;

@Service
public class ExcelDatabaseServiceImpl implements ExcelDatabaseService {
	@Autowired
	ExcelDatabaseRepository excelDbRepository;
	
	@Override
	public List<Object> getAllRecords() {
		excelDbRepository.getAllRecords("TODO", "TODO");
		return null;
	}

}
