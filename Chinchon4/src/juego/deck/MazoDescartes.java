package juego.deck;

import java.util.*;

public class MazoDescartes {

    private Deque<Carta> cartas = new ArrayDeque<>();

    public void descartar(Carta carta) {
        cartas.push(carta);
    }

    public Carta verUltima() {
        return cartas.isEmpty() ? null : cartas.peek();
    }

    public Carta robarUltima() {
        return cartas.isEmpty() ? null : cartas.pop();
    }

    public List<Carta> vaciarMenosUltima() {
        if (cartas.isEmpty()) return new ArrayList<>();

        Carta ultima = cartas.pop();
        List<Carta> resto = new ArrayList<>(cartas);
        cartas.clear();
        cartas.push(ultima);

        return resto;
    }
    
    public void deleteCards() {
    	cartas.clear();
    }
}
