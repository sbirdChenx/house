package com.chenx.mapper;

import com.chenx.model.HousePhoto;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenX on 2017/8/3.
 */
@Repository
public interface HousePhotoMapper {
	void save(HousePhoto housePhoto);
}
