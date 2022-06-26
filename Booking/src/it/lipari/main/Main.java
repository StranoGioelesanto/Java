//ANCORA DA FINIRE
package it.lipari.main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

import it.lipari.booking.Booking;
import it.lipari.booking.BookingManager;
import it.lipari.hotel.Hotel;
import it.lipari.hotel.MiniBar;
import it.lipari.hotel.Room;
import it.lipari.hotel.SuiteRoom;

public class Main {
	

	
	public static void addDate(GregorianCalendar d, int day, int month, int year) {
		d.set(Calendar.DATE, day);
		d.set(Calendar.MONTH, month);
		d.set(Calendar.YEAR, year);
	}

	public static void main(String[] args) {

		int choice;
		Hotel h = new Hotel("LondonHotel", "Street 1", "324354523", 3, 20);
		ArrayList<ArrayList<Room>> rooms = new ArrayList<ArrayList<Room>>();
		ArrayList<Room> roomsPerFlat = new ArrayList<Room>();
		BookingManager bookingManager = new BookingManager();

		// setto il prezzo in base alla capienza di ogni stanza
		float priceForcapacity[] = { 50.0f, 75.0f, 100.0f, 150.0f, 200.0f };
		h.setRoomPrice(priceForcapacity);
		h.setSuitePrice(300.0f);
		h.createNewHotel();
		boolean stay = true;
		Scanner in = new Scanner(System.in);

		do {
			System.out.println("Menu");
			System.out.println("1) Aggiungi prenotazione");
			System.out.println("2) Elimina prenotazione");
			System.out.println("3) Stampa prenotazioni hotel");
			System.out.println("4) Modifica data prenotazione");
			System.out.println("5) Modifica stanza prenotazione");
			System.out.println("0) EXIT");
			choice = in.nextInt();

			switch (choice) {
			case 1:
				ArrayList<Integer> listNumberRoom = new ArrayList<Integer>();
				System.out.println("Inserire email");
				String emailClient = in.next();
				System.out.println("Scegli tipologia di stanza");
				System.out.println("1) Standard");
				System.out.println("2) Suite");
				int choice2 = in.nextInt();
				System.out.println("Inserire il numero di persone");
				int capacity = in.nextInt();
				if (choice2 == 1) {
					listNumberRoom = bookingManager.printAvailableDatePerRoom(h, capacity, "Room");
				} else {
					listNumberRoom = bookingManager.printAvailableDatePerRoom(h, capacity, "SuiteRoom");
				}
				System.out.println("Scegli la stanza");
				Integer roomNumber = in.nextInt();
				do {
					if (listNumberRoom.contains(roomNumber)){
						break;
					}
					else {
						System.out.println("La stanza inserita non è tra quelle elencate");
						System.out.println("Inserire numero stanza corretto");
						roomNumber = in.nextInt();
					}
					
				}while(true);
				System.out.println("Scegli data checkin dd/mm/yyyy");
				GregorianCalendar checkin = new GregorianCalendar();
				addDate(checkin, in.nextInt(), in.nextInt(), in.nextInt());
				System.out.println("Scegli data checkout dd/mm/yyyy");
				GregorianCalendar checkout = new GregorianCalendar();
				addDate(checkout, in.nextInt(), in.nextInt(), in.nextInt());
				MiniBar miniBarBooking = new MiniBar();
				bookingManager.setDrinkMiniBar(miniBarBooking, roomNumber);
				Room bookingRoom = h.getRoom(roomNumber);
//				System.out.println(capacity);
				Booking bookingClient = new Booking(bookingRoom, checkin, checkout, emailClient, capacity,
						miniBarBooking);
				bookingManager.addBooking(bookingClient);

				bookingManager.calculateTotPrice(h, emailClient, checkin, miniBarBooking);
				System.out.println("Prenotazione Inserita");
				System.out.println(
						"Il prezzo totale è di " + bookingManager.getBooking(emailClient, checkin).getAmountPaid());
				break;

			case 2:
				System.out.println("Inserire email cliente");
				String clientEmail = in.next();
				System.out.println("Inserire data di checkin dd/mm/yyyy");
				GregorianCalendar delCheckin = new GregorianCalendar();
				addDate(delCheckin, in.nextInt(), in.nextInt(), in.nextInt());
				System.out.println("Inserire numero stanza");
				int numRoom = in.nextInt();
				bookingManager.removeBooking(clientEmail, delCheckin, numRoom);
				break;
			case 3:
				bookingManager.printBookings();
				break;
				
			case 4:
				System.out.println("Inserire email e data della prenotazione dd/mm/yyyy");
				String email = in.next();
				GregorianCalendar dateBooking = new GregorianCalendar ();
				addDate(dateBooking, in.nextInt(), in.nextInt(), in.nextInt());
				Booking bg = bookingManager.getBooking(email, dateBooking);
				if (bg != null) {
					System.out.println("Inserire la nuova data di checkin");
					GregorianCalendar newCheckin = new GregorianCalendar();
					addDate(newCheckin, in.nextInt(), in.nextInt(), in.nextInt());
					System.out.println("Inserire la nuova data di checkout");
					GregorianCalendar newCheckout = new GregorianCalendar();
					addDate(newCheckout, in.nextInt(), in.nextInt(), in.nextInt());
					int controll = bookingManager.modificationDateBooking(email, dateBooking, newCheckin, newCheckout);
					if (controll == -1) {
						System.out.println("Le date non sono disponibili");
					}
					break;
				}
			case 5:
				System.out.println("Inserire l'email della prenotazione");
				String emailBooking = in.next();
				bookingManager.printbookingsPerClient(emailBooking);
				System.out.println("Inserire numero della camera da voler cambiare");
				int numberRoom = in.nextInt();
				System.out.println("Inserire la data di checkin");
				GregorianCalendar checkIn = new GregorianCalendar();
				addDate(checkIn, in.nextInt(), in.nextInt(), in.nextInt());
				System.out.println("Inserire la data di checkout");
				GregorianCalendar checkOut = new GregorianCalendar();
				addDate(checkOut, in.nextInt(), in.nextInt(), in.nextInt());
				System.out.println("Inserire la nuova stanza da voler prenotare");
				int newNumberRoom = in.nextInt();
				bookingManager.modificationRoomBooking(emailBooking, checkIn, checkOut, newNumberRoom);
				break;
				
			case 0:
				stay = false;
				break;

			}

		} while (stay);

	}

}



//for (int i = 0; i < h.getFlat(); i++) {
//roomsPerFlat = new ArrayList<Room>();
//
//for (int j = 0; j < 20; j++) {
//	if (j != 13 && j != 17) {
//
//		int number = ((i + 1) * 100) + j;
//
//		// Math.random() da un valore da 0.0 a 1.0
//		Room r = Math.random() < 0.1 ? new SuiteRoom(number) : new Room(number);
//		if (r.getClass().getSimpleName() == Room.class.getSimpleName()) {
//			float mq = Math.random() < 0.5 ? 10 : 20;
//			r.setMq(mq);
//			if (mq == 10) {
//				int capacity = Math.random() < 0.5 ? 1 : 2;
//				r.setCapacity(capacity);
//				r.setHasBalcony(false);
//				r.setHasPrivateBathroom(false);
//			} else {
//				int capacity = Math.random() < 0.5 ? 3 : 4;
//				r.setCapacity(capacity);
//				r.setHasBalcony(true);
//				r.setHasPrivateBathroom(true);
//			}
//
//		} else {
//			float mq = Math.random() < 0.5 ? 25 : 35;
//			r.setMq(mq);
//			if (mq == 25) {
//				int capacity = Math.random() < 0.5 ? 3 : 4;
//				r.setCapacity(capacity);
//			} else {
//				int capacity = Math.random() < 0.5 ? 4 : 5;
//				r.setCapacity(capacity);
//			}
////			SuiteRoom sr = (SuiteRoom)r;
////			
////			((SuiteRoom)r).isHasCameraService()
//		}
//		roomsPerFlat.add(r);
//
//	}
//
//}
////ArrayList<Room> change = (ArrayList<Room>) roomsPerFlat.clone();
//rooms.add(roomsPerFlat);
//
//}
//
//h.setRooms(rooms);
//Stampa delle stanze dell'hotel prova
//for (int i=0; i<h.getFlat(); i++) {
//for(int j=0; j<h.getRoomsPerFlat(); j++) {
//	System.out.println(rooms.get(i).get(j).getNumber());
//
//}
//}
