package com.chenx.service.impl;

import com.chenx.mapper.StreetMapper;
import com.chenx.model.Street;
import com.chenx.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenX on 2017/8/7.
 */
@Service
@Transactional
public class StreetServiceImpl implements StreetService {
	@Autowired
	private StreetMapper streetMapper;
	@Override
	public void save(Street street) {
		streetMapper.save(street);
	}
}
