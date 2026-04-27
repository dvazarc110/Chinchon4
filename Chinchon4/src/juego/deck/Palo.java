package juego.deck;

public enum Palo {

    ORO("🟡"),
    COPA("🏆"),
    BASTOS("🥬"),
    ESPADAS("🗡️");

    private String simbolo;

    Palo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getPalo() {
        return simbolo;
    }
}
