package juego.table;

import java.util.ArrayList;
import java.util.List;

import juego.deck.Baraja;
import juego.deck.Carta;
import juego.deck.MazoDescartes;
import juego.deck.MazoRobar;
import juego.player.IPlayer;

public class GestorRonda {

    private MazoRobar mazoRobar = new MazoRobar();
    private MazoDescartes mazoDescartes = new MazoDescartes();

    public void iniciarRonda(List<IPlayer> jugadores) {

        Baraja baraja = new Baraja();
        baraja.barajar();

        List<Carta> todas = new ArrayList<>();

        while (!baraja.estaVacia()) {
            todas.addAll(baraja.repartir(1));
        }

        mazoRobar.cargar(todas);

        for (IPlayer p : jugadores) {
            for (int i = 0; i < 7; i++) {
                p.recibirCarta(mazoRobar.robar());
            }
        }
        mazoDescartes.descartar(mazoRobar.robar());
    }
    
    public void finalizarRonda(List<IPlayer> jugadores) {

        for (IPlayer p : jugadores) {
            for (int i = 0; i < 7; i++) {
                p.eliminarCarta((p.getMano()).get(0));
            }
        }
        mazoRobar.deleteCards();
        mazoDescartes.deleteCards();
    }
    
    
    public Carta robarCarta() {

        if (mazoRobar.estaVacio()) {
            reciclarDescartes();
        }

        Carta c = mazoRobar.robar();

        // 🔴 CONTROL CRÍTICO
        if (c == null) {
            throw new IllegalStateException("No hay cartas disponibles para robar");
        }

        return c;
    }

    public Carta verDescartes() {
        return mazoDescartes.verUltima();
    }

    public Carta robarDescartes() {
        return mazoDescartes.robarUltima();
    }
    
    public void descartar(Carta c) {
        mazoDescartes.descartar(c);
    }

    private void reciclarDescartes() {

        List<Carta> recicladas = mazoDescartes.vaciarMenosUltima();

        if (recicladas.isEmpty()) {
            return;
        }

        mazoRobar.agregarCartas(recicladas);
    }

}

