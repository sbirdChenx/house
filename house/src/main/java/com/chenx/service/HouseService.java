package com.chenx.service;

import com.chenx.model.House;
import com.chenx.model.SearchDTO;

import java.util.List;

/**
 * Created by ChenX on 2017/8/6.
 */
public interface HouseService {
	int save(House house);
	List<House> getHouseBySearch(SearchDTO searchDTO);
}
