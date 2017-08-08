package com.chenx.mapper;

import com.chenx.model.House;
import com.chenx.model.SearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenX on 2017/8/3.
 */
@Repository
public interface HouseMapper {
	int save(House house);
	List<House> findAll();
	List<House> findByDynamic(SearchDTO searchDTO);
}
