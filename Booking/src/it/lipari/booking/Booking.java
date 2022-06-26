package it.lipari.booking;

import java.util.Date;
import java.util.GregorianCalendar;

import it.lipari.hotel.MiniBar;
import it.lipari.hotel.Room;

public class Booking {
	
	Room room;
	GregorianCalendar checkin;
	GregorianCalendar checkout;
	MiniBar miniBarBooking;
	
	String clientEmail;
	int capacity = 1;
	
	boolean confirmed = false;
	boolean cancelled = false;
	float amountPaid = 0.0f;
	
	public Booking(Room room, GregorianCalendar checkin, GregorianCalendar checkout, String clientEmail, int capacity, MiniBar miniBar) {
		this.room = room;
		this.checkin = checkin;
		this.checkout = checkout;
		this.clientEmail = clientEmail;
		this.capacity = capacity;
		this.miniBarBooking = miniBar;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = this.amountPaid + amountPaid;
	}

	public GregorianCalendar getCheckout() {
		return checkout;
	}

	public void setCheckout(GregorianCalendar checkout) {
		this.checkout = checkout;
	}

	public GregorianCalendar getCheckin() {
		return checkin;
	}

	public void setCheckin(GregorianCalendar checkin) {
		this.checkin = checkin;
	}

	public MiniBar getMiniBarBooking() {
		return miniBarBooking;
	}

	public void setMiniBarBooking(MiniBar miniBarBooking) {
		this.miniBarBooking = miniBarBooking;
	}
	
	

}
