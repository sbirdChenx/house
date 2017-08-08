package com.chenx.mapper;

import com.chenx.model.District;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenX on 2017/8/3.
 */
@Repository
public interface DistrictMapper {
	List<District> findAll();
}
