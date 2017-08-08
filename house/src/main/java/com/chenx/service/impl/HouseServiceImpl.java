package com.chenx.service.impl;

import com.chenx.mapper.HouseMapper;
import com.chenx.model.House;
import com.chenx.model.SearchDTO;
import com.chenx.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ChenX on 2017/8/6.
 */
@Service
@Transactional
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseMapper houseMapper;

	@Override
	public int save(House house) {

		return houseMapper.save(house);
	}

	@Override
	public List<House> getHouseBySearch(SearchDTO searchDTO) {
		String price = searchDTO.getPrice();
		if ("不限".equals(price)){
			searchDTO.setLowPrice(0);
			searchDTO.setHightPrice(0);
		}else {
			String[] split = price.split("-");
			searchDTO.setLowPrice(Integer.parseInt(split[0]));
			searchDTO.setHightPrice(Integer.parseInt(split[1]));
		}
		String area = searchDTO.getArea();
		if ("不限".equals(area)){
			searchDTO.setLowArea(0);
			searchDTO.setHightPrice(0);
		}else {
			String[] split = area.split("-");
			searchDTO.setLowArea(Integer.parseInt(split[0]));
			searchDTO.setHightArea(Integer.parseInt(split[1]));
		}
		return houseMapper.findByDynamic(searchDTO);
	}
}
