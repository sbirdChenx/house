package com.chenx.service;

import com.chenx.model.HousePhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenX on 2017/8/7.
 */
public interface HousePhotoService {
	void save(HousePhoto housePhoto);
}
