package juego.player;

public class PlayerFactory {

    public static IPlayer crearJugador(String tipo, String nombre) {

        if (tipo.equalsIgnoreCase("ia")) {
            return new IA(nombre);
        } else {
        	return new PlayerHumano(nombre);
        }
    }
}
