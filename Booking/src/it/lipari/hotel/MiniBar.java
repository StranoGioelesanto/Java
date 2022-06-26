package it.lipari.hotel;

import java.util.HashMap;
import java.util.Set;

public class MiniBar {
	int roomNumer;
	int drinksCapacity=10;
	float waterPrice=1.50f;
	float otherDrinksPrice=2.50f;
	HashMap<String, Integer> drinkList = new HashMap<String, Integer>();
	float amountPriceDrinks;
	public int getRoomNumer() {
		return roomNumer;
	}
	public void setRoomNumer(int roomNumer) {
		this.roomNumer = roomNumer;
	}
	public int getDrinksCapacity() {
		return drinksCapacity;
	}
	public void setDrinksCapacity(int drinksCapacity) {
		this.drinksCapacity = drinksCapacity;
	}
	public float getWaterPrice() {
		return waterPrice;
	}
	public void setWaterPrice(float waterPrice) {
		this.waterPrice = waterPrice;
	}
	public float getOtherDrinksPrice() {
		return otherDrinksPrice;
	}
	public void setOtherDrinksPrice(float otherDrinksPrice) {
		this.otherDrinksPrice = otherDrinksPrice;
	}
	public HashMap<String, Integer> getDrinkList() {
		return drinkList;
	}
	public void setDrinkList(HashMap<String, Integer> drinkList) {
		this.drinkList = drinkList;
	}
	public float getAmountPriceDrinks() {
		return amountPriceDrinks;
	}
	public void setAmountPriceDrinks() {
		//setto il prezzo totale del minibar
		Set<String> key = drinkList.keySet();
		for(String k:key) {
			if(k.equals("Water")) {
				
				//Se per chiave abbiamo Water aggiungo al prezzo totale il prodotto tra il prezzo per ogni bott acqua e il numero di bottiglie (Dato da drinkList.get(k))
				this.amountPriceDrinks = drinkList.get(k)*this.waterPrice;
			}
			else {
				
				//Se non è acqua, stesso procedimento ma il il prezzo di ogni bevanda
				this.amountPriceDrinks += drinkList.get(k)*otherDrinksPrice;
			}
			
		}
	}
	
	
}
