package com.lastminute.model;

/**
 * FlightPrice entity
 * @author Sergio Candel
 *
 */
public class FlightPrice {

	private String code;
	private Double price;

	public FlightPrice(String code, Double price) {
		this.code = code;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "FlightPrice{" +
				"code='" + code + '\'' +
				", price=" + price +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FlightPrice)) return false;

		FlightPrice that = (FlightPrice) o;

		if (code != null ? !code.equals(that.code) : that.code != null) return false;
		return price != null ? price.equals(that.price) : that.price == null;
	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
	}
}
