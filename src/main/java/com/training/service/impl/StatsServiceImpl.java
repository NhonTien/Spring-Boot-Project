package com.training.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.dao.IStatsDao;
import com.training.service.StatsService;

@Service
@Transactional
public class StatsServiceImpl implements StatsService {

	@Autowired
	IStatsDao statsDao;

	@Override
	public List<Object[]> productStats(String keyword, Date fromDate, Date toDate){
		return statsDao.productStarts(keyword, fromDate, toDate);
	}

	@Override
	public List<Object[]> productMonthStats(String keyword, Date fromDate, Date toDate){
		return statsDao.productMonthStarts(keyword, fromDate, toDate);
	}

	@Override
	public List<Object[]> brandStats(){
		return statsDao.brandStarts();
	}

	@Override
	public List<Object[]> categoryStats(){
		return statsDao.categoryStarts();
	}
	
	@Override
	public List<Object[]> commentStats(){
		return statsDao.commentStarts();
	}
	
	@Override
	public List<Object[]> hotProducts(Long categoryId, int number){
		return statsDao.hotProducts(categoryId, number);
	}
}
