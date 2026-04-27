package juego.player;

import juego.deck.Carta;

public class PlayerHumano extends Player {

    public PlayerHumano(String nombre) {
        super(nombre);
    }

    @Override
    public Carta elegirDescartar(int sel) {
        return mano.get(sel);
    }
    
    public void showMano() {
    	System.out.println(getMano());
    }
}
