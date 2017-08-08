package com.chenx.service.impl;

import com.chenx.mapper.HouseMapper;
import com.chenx.mapper.HouseTypeMapper;
import com.chenx.model.HouseType;
import com.chenx.service.HouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ChenX on 2017/8/6.
 */
@Service
@Transactional
public class HouseTypeServiceImpl implements HouseTypeService {
	@Autowired
	private HouseTypeMapper houseTypeMapper;
	@Override
	public List<HouseType> getAllHouseType() {
		return houseTypeMapper.findAll();
	}
}
