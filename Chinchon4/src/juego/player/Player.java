package juego.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import juego.deck.Carta;

public abstract class Player implements IPlayer {

    protected String nombre;
    protected List<Carta> mano = new ArrayList<>();

    public Player(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirCarta(Carta carta) {

        if (carta == null) {
            throw new IllegalArgumentException("No se puede añadir una carta null");
        }

        mano.add(carta);
    }

    @Override
    public List<Carta> getMano() {
    	mano.sort(Comparator.comparing(Carta::getValor));
        return mano;
    }

    @Override
    public void eliminarCarta(Carta carta) {
        mano.remove(carta);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    // cada tipo de jugador decide su estrategia
    @Override
    public abstract Carta elegirDescartar(int sel);
}
