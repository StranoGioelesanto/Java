package it.lipari.hotel;

import java.util.ArrayList;

public class Hotel {
	
	String name;
	String address;
	String phone;
	
	float[] roomPrice;
	float suitePrice;
	
	int flat;
	int roomsPerFlat;
	ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
	
	
	public Hotel(String name, String address, String phone, int flat, int roomsPerFlat) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.flat = flat;
		this.roomsPerFlat = roomsPerFlat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float[] getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(float[] roomPrice) {
		this.roomPrice = roomPrice;
	}
	public float getSuitePrice() {
		return suitePrice;
	}
	public void setSuitePrice(float suitePrice) {
		this.suitePrice = suitePrice;
	}
	public int getRoomsPerFlat() {
		return roomsPerFlat;
	}
	public void setRoomsPerFlat(int roomsPerFlat) {
		this.roomsPerFlat = roomsPerFlat;
	}
	public ArrayList<ArrayList<Room>> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<ArrayList<Room>> rooms) {
		this.rooms = rooms;
	}
	public int getFlat() {
		return flat;
	}
	public void setFlat(int flat) {
		this.flat = flat;
	}
	public void createNewHotel() {
		for(int i=0; i<flat; i++) {
			ArrayList<Room> flat = new ArrayList <Room>();
			this.rooms.add(flat);
			for (int j=0; j<roomsPerFlat; j++) {
				if (j != 13 && j != 17) {
					int number = (i+1)*100 +j;
					if (Math.random() < 0.1) {
						SuiteRoom r = new SuiteRoom(number);
						this.addRoom(r);
					}
					else {
						Room r = new Room(number);
						this.addRoom(r);
					}
						
					}
				}
			}
		}
		
	
	
	public void addRoom (Room r) {
		int number = r.getNumber();
		int flatRoom = number / 100;
		System.out.println(number);
		ArrayList<Room> flat = rooms.get(flatRoom-1);
		flat.add(r);
		r.setRandomFeatures();
		
	}
	
	public Room getRoom (int roomNumber) {
		if (roomNumber % 100 > 13 && roomNumber % 100 < 17) {
			return this.rooms.get((roomNumber / 100) - 1).get((roomNumber % 100) - 1);
		} else if (roomNumber % 100 > 17) {
			return this.rooms.get((roomNumber / 100) - 1).get((roomNumber % 100) - 2);
		} else {
			return this.rooms.get((roomNumber / 100) - 1).get((roomNumber % 100));
		}
	}
	

}
