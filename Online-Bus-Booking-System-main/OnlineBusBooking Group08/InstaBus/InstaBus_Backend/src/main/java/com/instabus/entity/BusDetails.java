package com.instabus.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/*
 *  change the schema attribute according to your schema
 */

@Entity
@Table(name = "bus_details", schema = "hr")
public class BusDetails {

	@Id
	private Integer busNumber;

	@NotNull(message = "Departure Busstop cannot be null")
	private String departureBusstop;

	@NotNull(message = "Source Busstop cannot be null")
	private String arrivalBusstop;

	private Integer availableSeats;

	@NotNull(message = "daparture date cannot be null")
	private String departureDate;

	@NotNull(message = "arrival date cannot b null")
	private String arrivalDate;

	@NotNull(message = "Arival Time cannot be null")
	private String arrivalTime;

	@NotNull(message = "Departure Time cannot be null")
	private String departureTime;

	@NotNull(message = "Bus Vendor cannot be null")
	private String busVendor;

	@NotNull(message = "cost cannot be null")
	private Double cost;

	public BusDetails() {
		super();
	}

	public BusDetails(
			@NotNull(message = "Departure Busstop cannot be null") String departureBusstop,
			@NotNull(message = "Source Busstop cannot be null") String arrivalBusstop, Integer availableSeats,
			@NotNull(message = "daparture date cannot be null") String departureDate,
			@NotNull(message = "arrival date cannot b null") String arrivalDate,
			@NotNull(message = "Arival Time cannot be null") String arrivalTime,
			@NotNull(message = "Departure Time cannot be null") String departureTime,
			@NotNull(message = "Bus Vendor cannot be null") String busVendor, Double cost) {
		super();
		this.departureBusstop = departureBusstop;
		this.arrivalBusstop = arrivalBusstop;
		this.availableSeats = availableSeats;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.busVendor = busVendor;
		this.cost = cost;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(Integer busNumber) {
		this.busNumber = busNumber;
	}

	public String getDepartureBusstop() {
		return departureBusstop;
	}

	public void setDepartureBusstop(String departureBusstop) {
		this.departureBusstop = departureBusstop.toLowerCase();
	}

	public String getArrivalBusstop() {
		return arrivalBusstop;
	}

	public void setArrivalBusstop(String arrivalBusstop) {
		this.arrivalBusstop = arrivalBusstop.toLowerCase();
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getBusVendor() {
		return busVendor;
	}

	public void setBusVendor(String busVendor) {
		this.busVendor = busVendor;
	}

}
