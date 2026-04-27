package juego.table;

import juego.player.PlayerFactory;

public class Menu {

    public static void mostrar() {

    	ConsoleInput input = new ConsoleInput();
        String tipo = "";
        String nombre = "";
        System.out.println("Número de jugadores (2-4): ");
        int n = input.readInt();

        GestorPartida gp = GestorPartida.getInstancia();

        for (int i = 0; i < n; i++) {

            System.out.println("Jugador " + (i+1) + " tipo (humano/ia): ");
            tipo = input.readString();
            
            if(!(tipo.equalsIgnoreCase("ia"))) {
            	System.out.println("Nombre: ");
            	nombre = input.readString();
            }else {
            	nombre = String.format("ia%d", i+1);
            }
            
            gp.agregarJugador(PlayerFactory.crearJugador(tipo, nombre));
        }
    }
}
