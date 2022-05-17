package com.training.dao;

import java.util.Date;
import java.util.List;

public interface IStatsDao {

	List<Object[]> productStarts(String keyword, Date fromDate, Date toDate);

	List<Object[]> productMonthStarts(String keyword, Date fromDate, Date toDate);

	List<Object[]> brandStarts();

	List<Object[]> categoryStarts();

	List<Object[]> commentStarts();

	List<Object[]> hotProducts(Long categoryId, int number);
	
}
