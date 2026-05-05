package juego.deck;

import java.util.*;

public class MazoRobar {

    private Deque<Carta> cartas = new ArrayDeque<>();

    public void cargar(List<Carta> lista) {
        Collections.shuffle(lista);
        cartas.addAll(lista);
    }

    public Carta robar() {
        return cartas.isEmpty() ? null : cartas.poll();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public void agregarCartas(List<Carta> lista) {
        Collections.shuffle(lista);
        cartas.addAll(lista);
    }
    
    public void deleteCards() {
    	cartas.clear();
    }
}
