package com.chenx.controller;

import com.chenx.model.*;
import com.chenx.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ChenX on 2017/8/6.
 */
@Controller
public class HouseController {
	@Autowired
	private HouseService houseService;
	@Autowired
	private HouseTypeService houseTypeService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private StreetService streetService;
	@Autowired
	private HousePhotoService housePhotoService;

	@RequestMapping("/index")
	public ModelAndView skip(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<HouseType> allHouseType = houseTypeService.getAllHouseType();
		List<District> allDistrict = districtService.getAllDistrict();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		servletContext.setAttribute("houseType",allHouseType);
		servletContext.setAttribute("district",allDistrict);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/preAdd")
	public ModelAndView preAddHouse(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<HouseType> allHouseType = houseTypeService.getAllHouseType();
		List<District> allDistrict = districtService.getAllDistrict();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		servletContext.setAttribute("houseType",allHouseType);
		servletContext.setAttribute("district",allDistrict);
		mav.setViewName("pub");
		return mav;
	}
	@RequestMapping(value = "/add")
	public ModelAndView addHouse(House house, @RequestParam(value = "primaryPhoto",required = false) MultipartFile[] mfs, Errors errors,
								 HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		if (errors.hasErrors()){
			mav.addObject("hint","请输入正确的房屋信息");
			mav.setViewName("pub");
		}else {

			List<String> newFileNames = new ArrayList<>();
			for (MultipartFile mf:
				 mfs) {
				String uuid = UUID.randomUUID().toString().replaceAll("-","");
				String fileName = mf.getOriginalFilename();
				String[] fileNames = fileName.split("\\.");
				String newFileName = uuid + "." + fileNames[1];
				newFileNames.add(newFileName);
				String realPath = request.getServletContext().getRealPath("upload");
				File path = new File(realPath);
				if (!path.exists()){
					path.mkdir();
				}
				File file = new File(realPath+"\\"+newFileName);
				mf.transferTo(file);
			}
			house.setMainPhoto(newFileNames.get(0));
			Street street = new Street();
			street.setName(house.getStreet());
			street.setDistrict(house.getDistrict());
			streetService.save(street);
			System.out.println(house.getId());
			houseService.save(house);
			System.out.println(house.getId());
			for (String newFileName:
				 newFileNames) {
				HousePhoto housePhoto = new HousePhoto();
				housePhoto.setName(newFileName);
				housePhoto.setHouse(house);
				housePhotoService.save(housePhoto);
			}
			mav.setViewName("index");
		}
		return mav;
	}
	@RequestMapping("/search")
	public ModelAndView searchHouse(SearchDTO searchDTO,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		List<House> houses = houseService.getHouseBySearch(searchDTO);
		mav.addObject("houses",houses);
		mav.setViewName("index");
		return mav;
	}
}
