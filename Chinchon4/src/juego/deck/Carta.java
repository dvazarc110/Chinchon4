package juego.deck;

/**
 * Representa una carta de la baraja utilizada en el juego Blackjack.
 * 
 * Cada carta está formada por:
 * <ul>
 * <li>Un {@link TipoCarta}</li>
 * <li>Un {@link Palo}</li>
 * </ul>
 * 
 * La carta también permite obtener su valor numérico, el cual
 * se utiliza para calcular la puntuación de los jugadores.
 * 
 * @author Daniel Vazquez
 * @version 1.0
 */
public class Carta {

    private Palo palo;
    
    private TipoCarta tipo;
    
    public Carta(Palo palo, TipoCarta tipo) {
        this.palo = palo;
        this.tipo = tipo;
    }

    public int getValor() {
        return tipo.getValor();
    }

    public Palo getPalo() {
        return palo;
    }
    
    public TipoCarta getTipo(){
    	return tipo;
    }
    
    public String toString() {
        return tipo.getValor() + " " + palo.getPalo();
    }
}
