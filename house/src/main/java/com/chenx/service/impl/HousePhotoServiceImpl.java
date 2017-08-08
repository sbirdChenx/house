package com.chenx.service.impl;

import com.chenx.mapper.HousePhotoMapper;
import com.chenx.model.HousePhoto;
import com.chenx.service.HousePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenX on 2017/8/7.
 */
@Service
@Transactional
public class HousePhotoServiceImpl implements HousePhotoService {
	@Autowired
	private HousePhotoMapper housePhotoMapper;
	@Override
	public void save(HousePhoto housePhoto) {
		housePhotoMapper.save(housePhoto);
	}
}
