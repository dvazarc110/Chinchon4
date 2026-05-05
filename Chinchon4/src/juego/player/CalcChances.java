package juego.player;

import java.util.ArrayList;
import java.util.List;

import juego.deck.Carta;

public class CalcChances {
	
	private List<Carta> cardsneeded;
	private double posibilities;
	
	public CalcChances(List<Carta> cardsneeded, double posibilities) {
		this.cardsneeded = cardsneeded;
		this.posibilities = posibilities;
	}
	
	public double CalcPosibilities(List<Carta> descart, List<Carta> draw, int playersquant) {
		List<Carta> notposibles = new ArrayList<>();
		int interest, notinterest;
		int oppcards = playersquant*7;
		int totalcards = draw.size() + oppcards;
		
		for(Carta cdesc : descart) {
			for(Carta cneed : cardsneeded) {
				if(cdesc == cneed) {
					notposibles.add(cneed);
				}
			}
		}
		
		if(!(notposibles.isEmpty())) {
			cardsneeded.removeAll(notposibles);
		}
		
		interest = cardsneeded.size();
		notinterest = (totalcards) - interest;
		
		
		
		return 0.5;
	}
	
	public void setcardsneedes(List<List<Carta>> mazo, List<Carta> needed) {
		this.cardsneeded = needed;
	}
	
}
