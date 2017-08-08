package com.chenx.service.impl;

import com.chenx.mapper.DistrictMapper;
import com.chenx.model.District;
import com.chenx.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ChenX on 2017/8/6.
 */
@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	private DistrictMapper districtMapper;
	@Override
	public List<District> getAllDistrict() {

		return districtMapper.findAll();
	}
}
