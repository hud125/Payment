package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;

public class AddressBook extends TextualIDModel {

	private Country country;
	private StateProv state;
	private City city;
	private String zipCode;
	private String streetName1;
	private String streetName2;
	private String string;
	private String fullName;
	private String phoneNumber;
	private String ownerId;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public StateProv getState() {
		return state;
	}

	public void setState(StateProv state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetName1() {
		return streetName1;
	}

	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}

	public String getStreetName2() {
		return streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressBook [country=");
		builder.append(country);
		builder.append(", state=");
		builder.append(state);
		builder.append(", city=");
		builder.append(city);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", streetName1=");
		builder.append(streetName1);
		builder.append(", streetName2=");
		builder.append(streetName2);
		builder.append(", string=");
		builder.append(string);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetName1 == null) ? 0 : streetName1.hashCode());
		result = prime * result + ((streetName2 == null) ? 0 : streetName2.hashCode());
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBook other = (AddressBook) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetName1 == null) {
			if (other.streetName1 != null)
				return false;
		} else if (!streetName1.equals(other.streetName1))
			return false;
		if (streetName2 == null) {
			if (other.streetName2 != null)
				return false;
		} else if (!streetName2.equals(other.streetName2))
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
