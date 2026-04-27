package juego.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

    private List<Carta> cartas = new ArrayList<>();

    public void inicializar() {
        for (Palo p : Palo.values()) {
            for (TipoCarta t : TipoCarta.values()) {
                cartas.add(new Carta(p, t));
            }
        }
    }

    public void barajar() {
    	inicializar();
        Collections.shuffle(cartas);
    }

    public List<Carta> repartir(int n) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mano.add(cartas.remove(0));
        }
        return mano;
    }
    
    public boolean estaVacia() {
    	if(cartas.isEmpty()) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
