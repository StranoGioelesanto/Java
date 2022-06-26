package it.lipari.booking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import it.lipari.hotel.Hotel;
import it.lipari.hotel.MiniBar;
import it.lipari.hotel.Room;
import it.lipari.hotel.SuiteRoom;


public class BookingManager {
	
	public void printDate (GregorianCalendar d) {
		System.out.println(d.get(Calendar.DATE) + "/" 
				+ d.get(Calendar.MONTH) + "/" 
				+ d.get(Calendar.YEAR));
	}
	
	HashMap<String,ArrayList<Booking>> bookingsPerClient = new HashMap<String,ArrayList<Booking>>();
	HashMap<Integer,HashMap<GregorianCalendar,Booking>> bookingsPerRoomNumber = new HashMap<Integer,HashMap<GregorianCalendar,Booking>>();
	
	
	public void addBooking(Booking b) {
	// bookingsPerClient.get(b.getClientEmail--> ritorna un ArrayList<Booking> se esiste per l'email del cliente (key) altrimenti null
		ArrayList<Booking> list = bookingsPerClient.get(b.getClientEmail());
		// Se ritorna null devo creare una lista di prenotazione con chiave l'email del cliente
		if(list == null) {
			list = new ArrayList<Booking>();
		}
		// Aggiungo la prenotazione alla lista esistente o creata
		list.add(b);
		// Il metodo put associa alla chiave (emailCliente) un nuovo valore (lista prenotazioni). Il valore precedente verrà sostituito.
		bookingsPerClient.put(b.getClientEmail(), list);
		
		Room r = b.getRoom();
		Integer rn = r.getNumber();
		// stesso procedimento di prima.
		// bookingsPerRoomNumber.get(rn) torna il valore corrispondente alla chiave (integer rn).Se non presente restituisce null.
		HashMap<GregorianCalendar,Booking> bookingsPerDate = bookingsPerRoomNumber.get(rn);
		if(bookingsPerDate == null) {
			bookingsPerDate = new HashMap<GregorianCalendar,Booking>();
		}
		bookingsPerDate.put(b.getCheckin(), b);
		bookingsPerRoomNumber.put(rn, bookingsPerDate);
	}
	
	public ArrayList<Integer> printAvailableDatePerRoom (Hotel hotel, int capacity, String typeRoom) {
		
		System.out.println("Stampa stanze e date disponibili");
		/*Creo una lista di interi che conterrà il numero delle stanze che corrispondono al tipo e alla capienza scelta
		Questa lista mi sarà utile nel main per controllare l'input dell'utente nella scelta della stanza*/
		ArrayList<Integer> listNumberRoom = new ArrayList<Integer>();
		
		
		
		// Attraverso tutte le stanze dell'hotel controllando la capacita e il tipo di oggetto 
		for(int i=0; i<hotel.getFlat()-1; i++) {
			for(int j=0; j<(hotel.getRoomsPerFlat()-2); j++) {
				if(j != 13 && j != 17) {
					if ((((((hotel.getRooms()).get(i)).get(j)).getClass()).getSimpleName().equals(typeRoom))
							&& (((((hotel.getRooms()).get(i)).get(j)).getClass()).getSimpleName().equals(typeRoom))){
						// Mi salvo il numero della stanza
						Integer keyNumber=((((hotel.getRooms()).get(i)).get(j)).getNumber());
						// Aggiungo il numero alla lista che ritornerò
						listNumberRoom.add(keyNumber);
						
						/*Creo una lista di date che conterrà le date di checkin e checkout di tutte le prenotazione 
						 * per una stanza. mettendo la creazione all'interno del ciclo ogni volta svuoto la lista cosi che
						 * possa contenere le date di checkin e checkout della stanza successiva
						 */
						ArrayList<GregorianCalendar> listaDate= new ArrayList<GregorianCalendar>();


						// Creo HashMap cosi da poter lavorare sul HashMap corrispondente al numero stanza (chiave)
						HashMap<GregorianCalendar, Booking> dateBookingPerRoom = new HashMap<GregorianCalendar, Booking>();
						dateBookingPerRoom = bookingsPerRoomNumber.get(keyNumber);
						if (dateBookingPerRoom != null) {

							//Creo un Set che contiene tutte le chiavi del HashMap dateBookingPerRoom. Le chiavi sono le Date di prenotazione.
							Set<GregorianCalendar> DatesRoom = dateBookingPerRoom.keySet();
							//Attraverso il set
							for (GregorianCalendar keyDate:DatesRoom) {
								//Aggiungo per ogni prenotazione la data di checkin e la data di checkout
								listaDate.add(keyDate);
								listaDate.add((dateBookingPerRoom.get(keyDate)).getCheckout());
							}
							//Ordino l'array di date
							Collections.sort(listaDate);
							if (listaDate.size() == 2) {
								System.out.println("La stanza numero " + keyNumber + " è disponibile ");
								System.out.println(" Dal " + listaDate.get(1).get(Calendar.DATE) + "/" + listaDate.get(1).get(Calendar.MONTH) + "/" + listaDate.get(1).get(Calendar.YEAR));
							}
							/* Stampo sempre la data di check out e quella di checkin tra due prenotazioni. Suppongo di 
							 * non dover avere problemi perchè ordinando le date si dovrebbe presentare da logica:
							 * IOIOIOIOIOIOIO 
							 * I=checkin
							 * O=checkout
							 */
							else {

								ArrayList<GregorianCalendar> list = new ArrayList<GregorianCalendar>();
								for(int k = 1; k<listaDate.size(); k = k+2) {
									list.add(listaDate.get(k));
								}
								System.out.println("La stanza numero " + keyNumber + " e' disponibile ");
								for (int k=0; k<list.size(); k++) {
									if ((k % 2) == 0) {
										System.out.print("Dal " + list.get(k).get(Calendar.DATE) + "/" + list.get(k).get(Calendar.MONTH) + "/" + list.get(k).get(Calendar.YEAR));
									}
									else {
										System.out.println("al " + list.get(k).get(Calendar.DATE) + "/" + list.get(k).get(Calendar.MONTH) + "/" + list.get(k).get(Calendar.YEAR));
									}

									
									
								}
							}

						}
						else {
							System.out.println("La stanza " + keyNumber + " non ha prenotazioni");
						}



					}
					
				}
				
			}
		}
		return listNumberRoom;
		
	}
	
	public void printbookingsPerClient(String email) {
		ArrayList<Booking> listBookingClient = bookingsPerClient.get(email);
		for (int i=0; i<listBookingClient.size(); i++) {
			System.out.println("---------");
			printDate(listBookingClient.get(i).getCheckin());
			printDate(listBookingClient.get(i).getCheckout());
			System.out.println(listBookingClient.get(i).getRoom().getNumber());

		}
	}
	
	public void printBookings () {
		Set<String> listClient = bookingsPerClient.keySet();
		for(String key : listClient) {
			for(int i=0; i < bookingsPerClient.get(key).size(); i++) {
				System.out.println("Prenotazioni cliente : " + key);
				System.out.println("------------");
				System.out.println(bookingsPerClient.get(key).get(i).getClientEmail());
				printDate(bookingsPerClient.get(key).get(i).getCheckin());
				printDate(bookingsPerClient.get(key).get(i).getCheckout());
				System.out.println(bookingsPerClient.get(key).get(i).getRoom().getNumber());
				System.out.println(bookingsPerClient.get(key).get(i).getAmountPaid());
				
			}
			
		}
	}

	
	public Booking getBooking(String email, GregorianCalendar prenotazione) {
		Set<String> listaEmail = bookingsPerClient.keySet();
		for(String emailClient : listaEmail) {
			if (emailClient.equals(email)){
//				System.out.println("Imhere");
				for(Booking bg : bookingsPerClient.get(emailClient)) {
					if ((bg.getCheckin().get(Calendar.DATE) == prenotazione.get(Calendar.DATE)) 
						&& (bg.getCheckin().get(Calendar.MONTH) == prenotazione.get(Calendar.MONTH)) 
						&& (bg.getCheckin().get(Calendar.YEAR) == prenotazione.get(Calendar.YEAR))) {
//						System.out.println("Imhere2");
						return bg;
					}
				}
			}
		}
		return null;	
	}
	
	public void removeBooking(String email, GregorianCalendar Prenotazione, int numberRoom) {
		Booking bgDel= getBooking(email, Prenotazione);
		if(bgDel != null) {
			bookingsPerClient.get(email).remove(bgDel);
			if (bookingsPerClient.get(email).isEmpty()){
				bookingsPerClient.remove(email);
				}
			//recupero il numero della stanza della prenotazione e poi elimino l'HashMap che ha come chiave la data di prenotazione
			bookingsPerRoomNumber.get(bgDel.getRoom().getNumber()).remove(Prenotazione);
			}			
	}
	
	public int modificationDateBooking(String email, GregorianCalendar Prenotazione, GregorianCalendar newCheckin, GregorianCalendar newCheckout) {
		
		Booking bgMod= getBooking(email, Prenotazione);
		if (bgMod != null) {
			
			// controllo se la stanza della prenotazione è disponibile per la nuova data
			HashMap <GregorianCalendar, Booking> bookingsNewDate = bookingsPerRoomNumber.get(bgMod.getRoom().getNumber());
			Set <GregorianCalendar> date = bookingsNewDate.keySet();
			// Controllo se la nuova datacheckin e quella di checkout non sono in conflitto con le date delle altre prenotazioni
			for(GregorianCalendar d : date) {
				if ((newCheckin.after(d)) && (newCheckin.before(bookingsNewDate.get(d).getCheckout()))) {
					if ((newCheckout.after(d)) && (newCheckout.before(bookingsNewDate.get(d).getCheckout()))) {
						return -1;
					}
				}
			}
		
			//setto il checkin e il checkout con la nuova data
			bgMod.setCheckin(newCheckin);
			bgMod.setCheckout(newCheckout);
			
			//Inserisco nell'HashMap la prenotazione con chiave la nuova data
			bookingsNewDate.put(newCheckin, bgMod);
			
			//Inserisco l'inerno HashMap nel HashMap esterno
			bookingsPerRoomNumber.put(bgMod.getRoom().getNumber(), bookingsNewDate);
			
			//Rimuovo la vecchia prenotazione
			bookingsPerRoomNumber.get(bgMod.getRoom().getNumber()).remove(Prenotazione);	
			return 1;
			}	
		return 0;
		}	
	
	public int modificationRoomBooking(String email, GregorianCalendar checkin, GregorianCalendar checkout, Integer newNumberRoom) {
		
		//Controllo se la nuova stanza selezionata è disponibile per la data della prenotazione
		HashMap <GregorianCalendar, Booking> bookingsNewRoom = bookingsPerRoomNumber.get(newNumberRoom);
		//Se non c'è nessuna prenotazione per la nuova stanza recupero il booking del cliente e cambio la stanza
		if (bookingsNewRoom == null) {
			Booking bgClient = getBooking(email, checkin);
			bgClient.getRoom().setNumber(newNumberRoom);
		}
		else {
			Set <GregorianCalendar> dateNewRoom = bookingsNewRoom.keySet();
			for(GregorianCalendar d : dateNewRoom) {
				if ((checkin.after(d)) && (checkin.before(bookingsNewRoom.get(d).getCheckout()) && checkout.after(d)) && checkout.before(bookingsNewRoom.get(d).getCheckout())) {
					return -1;
				}
			}
			//Recupero l'oggetto booking della prenotazione
			Booking bgMod=getBooking(email, checkin);
			
			//Elimino la prenotazione per la stanza precedente
			bookingsPerRoomNumber.get(bgMod.getRoom().getNumber()).remove(checkin);
			
			//Modifico il numero della stanza della prenotazione
			bgMod.getRoom().setNumber(newNumberRoom);
			
			//Modifico l'HashMap interno facendo in modo che punti al numero di stanza (Integer) nuovo
			HashMap<GregorianCalendar, Booking> bookingPerRoom = bookingsPerRoomNumber.get(newNumberRoom);
			bookingsPerRoomNumber.put(newNumberRoom, bookingPerRoom);
			return 1;
		}
		
		return 0;
		
	}

	
	public void calculateTotPrice(Hotel h, String email, GregorianCalendar Prenotazione, MiniBar miniBarBooking) {
		
		//Recupero l'oggetto booking della prenotazione
		Booking bg=getBooking(email, Prenotazione);
		
		// Metto a confronto il tipo dell'oggetto con la classe Room cosi da capire se ho la prenot di una stanza o suite
		if (bg.getRoom().getClass().getSimpleName() ==  Room.class.getSimpleName()) {
			
			//Creo un vettore float contenente tutti i prezzi delle stanze in base alla capacita
			float [] priceRoom = h.getRoomPrice();
			
			//Setto il prezzo totale del booking sommando il prezzo della stanza per la capacita corrispondente
			bg.setAmountPaid(priceRoom[bg.getRoom().getCapacity()]);
		}
		else {
			
			//La suite ha un prezzo unico indipendente dalla capacita
			bg.setAmountPaid(h.getSuitePrice());
		}
		
		//Sommo al prezzo totale anche il costo totale delle bevande del minibar
//		System.out.println("Im here3");
		System.out.println(miniBarBooking.getAmountPriceDrinks());
		bg.setAmountPaid(miniBarBooking.getAmountPriceDrinks());
		
	}
	
	public MiniBar setDrinkMiniBar (MiniBar m, int roomNumber) {
		Scanner in = new Scanner(System.in);
		m.setRoomNumer(roomNumber);
		HashMap<String, Integer> drinkList = new HashMap<String, Integer>();
		System.out.println("Bevande minibar (Capacita max 10 bevende)");
		System.out.println("Quante bottiglie d'acqua?");
		int numberWater = in.nextInt();
		if (numberWater > m.getDrinksCapacity()) {
			numberWater = 10;
		}
		drinkList.put("Water", numberWater);
		int restDrink = m.getDrinksCapacity() - numberWater;
		if (restDrink > 0) {
			do {
				System.out.println("Puoi aggiungere ancora " + restDrink + " bevande");
				System.out.println("Inserire nome bevanda");
				String drink = in.next();
				System.out.println("Quantità?");
				int numDrink = in.nextInt();
				restDrink = restDrink - numDrink;
				if (restDrink <= 0) {
					numDrink = numDrink + restDrink;
					drinkList.put(drink, numDrink);
					break;
				}
			} while (true);
		}
		m.setDrinkList(drinkList);
		m.setAmountPriceDrinks();
		return m;
	}
	

	
	

	}

		
	
	
	
	
	





