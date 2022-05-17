package com.training.service;

import java.util.Date;
import java.util.List;

public interface StatsService {

	List<Object[]> productStats(String keyword, Date fromDate, Date toDate);

	List<Object[]> productMonthStats(String keyword, Date fromDate, Date toDate);

	List<Object[]> brandStats();

	List<Object[]> categoryStats();

	List<Object[]> commentStats();

	List<Object[]> hotProducts(Long categoryId, int number);

}
