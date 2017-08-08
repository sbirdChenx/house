package com.chenx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private HouseType houseType;
	private Integer area;
	private Integer floor;
	private Integer totalFloor;
	private Double price;
	private District district;
	private String street;
	private Contacter contacter;
	private String detail;
	private List<HousePhoto> photos;
	private String mainPhoto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HouseType getHouseType() {
		return houseType;
	}

	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getTotalFloor() {
		return totalFloor;
	}

	public void setTotalFloor(Integer totalFloor) {
		this.totalFloor = totalFloor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Contacter getContacter() {
		return contacter;
	}

	public void setContacter(Contacter contacter) {
		this.contacter = contacter;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<HousePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<HousePhoto> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(HousePhoto photo) {
		if(photos == null) {
			photos = new ArrayList<HousePhoto>();
		}
		photos.add(photo);
	}

	public int getPhotoCount() {
		return photos.size();
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

}
