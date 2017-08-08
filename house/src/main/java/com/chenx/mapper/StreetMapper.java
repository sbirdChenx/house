package com.chenx.mapper;

import com.chenx.model.Street;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenX on 2017/8/3.
 */
@Repository
public interface StreetMapper {
	void save(Street street);
}
