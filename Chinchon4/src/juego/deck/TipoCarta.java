package juego.deck;

public enum TipoCarta {

    UNO(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    SOTA(10),
    CABALLO(11),
    REY(12);

    private int valor;
    
    TipoCarta(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
