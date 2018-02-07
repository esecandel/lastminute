package com.lastminute.model;

/**
 * Route entity
 * @author Sergio Candel
 *
 */
public class Route {

	private String originAirport;
	private String destinationAirport;
	private String code;

	public Route(String originAirport, String destinationAirport, String code) {
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.code = code;
	}

	public String getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Route{" +
				"originAirport='" + originAirport + '\'' +
				", destinationAirport='" + destinationAirport + '\'' +
				", code='" + code + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Route)) return false;

		Route route = (Route) o;

		if (originAirport != null ? !originAirport.equals(route.originAirport) : route.originAirport != null)
			return false;
		if (destinationAirport != null ? !destinationAirport.equals(route.destinationAirport) : route.destinationAirport != null)
			return false;
		return code != null ? code.equals(route.code) : route.code == null;
	}

	@Override
	public int hashCode() {
		int result = originAirport != null ? originAirport.hashCode() : 0;
		result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
		result = 31 * result + (code != null ? code.hashCode() : 0);
		return result;
	}
}
