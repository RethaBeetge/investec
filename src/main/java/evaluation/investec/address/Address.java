package evaluation.investec.address;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	@JsonProperty("type")
	private AddressType addressType;
	@JsonProperty("addressLineDetail")
	private AddressDetail addressDetail;
	@JsonProperty("provinceOrState")
	private ProvinceState provinceOrState;
	private String cityOrTown;
	private String suburbOrDistrict;
	@JsonProperty("country")
	private Country country;
	// Although sample provides numeric values some internation codes are alpha numeric
	private String postalCode;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private LocalDateTime lastUpdated;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public AddressDetail getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(AddressDetail addressDetail) {
		this.addressDetail = addressDetail;
	}

	public ProvinceState getProvinceState() {
		return provinceOrState;
	}

	public void setProvinceState(ProvinceState provinceState) {
		this.provinceOrState = provinceState;
	}

	public String getCityOrTown() {
		return cityOrTown;
	}

	public void setCityOrTown(String cityOrTown) {
		this.cityOrTown = cityOrTown;
	}

	public Country getCountry() {
		return country;
	}

	public String getSuburbOrDistrict() {
		return suburbOrDistrict;
	}
	
	public void setSuburbOrDistrict(String value) {
		suburbOrDistrict = value;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public class AddressType {
		private int code;
		private String name;
		
		public int getCode() {
			return code;
		}
		public void setCode(int value) {
			code = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String value) {
			name = value;
		}
	}

	public class AddressDetail {
		private String line1;
		private String line2;
		
		public String getLine1() {
			return line1;
		}
		public void setLine1(String value) {
			line1 = value;
		}
		
		public String getLine2() {
			return line2;
		}
		public void setLine2(String value) {
			line2 = value;
		}
	}
	
	public class ProvinceState {
		private int code;
		private String name;
		
		public int getCode() {
			return code;
		}
		public void setCode(int value) {
			code = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String value) {
			name = value;
		}
		
		public boolean isEmpty() {
			return this.getCode() == 0 && (this.getName() == null || this.getName().isEmpty());
		}
	}
	
	public class Country {
		private String code;
		private String name;
		
		public String getCode() {
			return code;
		}
		public void setCode(String value) {
			code = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String value) {
			name = value;
		}
		
		public boolean isEmpty() {
			return (this.getCode() == null || this.getCode().isEmpty()) && (this.getName() == null || this.getName().isEmpty());
		}
	}
}
