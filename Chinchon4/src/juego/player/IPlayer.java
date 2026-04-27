package juego.player;

import java.util.List;

import juego.deck.Carta;

public interface IPlayer {

    void recibirCarta(Carta carta);

    List<Carta> getMano();

    Carta elegirDescartar(int sel);

    void eliminarCarta(Carta carta);

    String getNombre();
}
