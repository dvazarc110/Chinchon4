package juego.table;

import juego.player.IPlayer;
import java.util.*;

public class GestorPartida {

    private static GestorPartida instancia;

    private List<IPlayer> jugadores = new ArrayList<>();
    private Map<IPlayer, Integer> puntuaciones = new HashMap<>();

    private GestorPartida() {}

    public static GestorPartida getInstancia() {
        if (instancia == null) {
            instancia = new GestorPartida();
        }
        return instancia;
    }

    public void agregarJugador(IPlayer p) {
        jugadores.add(p);
        puntuaciones.put(p, 0);
    }

    public List<IPlayer> getJugadores() {
        return jugadores;
    }
}
