package juego.table;

import java.util.ArrayList;

import juego.deck.Carta;
import juego.player.CalcChances;
import juego.player.CombinadorMano;
import juego.player.IA;
import juego.player.IPlayer;
import juego.player.ResultadoCombinacion;
import juego.player.SemiCombinadorMano;
import juego.player.SemiResultadoCombinacion;

public class TurnoController {

    private final GestorRonda ronda;
    private final CombinadorMano combinador = new CombinadorMano();

    public TurnoController(GestorRonda ronda) {
        this.ronda = ronda;
    }

    public boolean jugarTurno(IPlayer jugador, int turncont, int playeramount) {

    	ConsoleInput input = new ConsoleInput();
    	int choice;
    	int turn = turncont;
    	String siguiente = "";
        Carta cartaRobada, visible, descartada;
        visible = ronda.verDescartes();
        
        System.out.println("Carta de mazo de descartes: " + visible);
        
        if (jugador instanceof IA ia) {
        	
        	CalcChances c = new CalcChances(new ArrayList<>(), 0.0);
        	
        	SemiResultadoCombinacion sr = new SemiCombinadorMano().analizar(jugador.getMano(), jugador);
        	
        	
        	
            if (visible != null && ia.quiereRoboDescarte(visible)) {
                cartaRobada = ronda.robarDescartes();
            } else {
                cartaRobada = ronda.robarCarta();
            }

        } else {
        	System.out.println("Mano actual: " + jugador.getMano());
        	
        	System.out.println("¿Quiere robar del mazo de descartes (1) o de la baraja (2)?");
        	choice = input.readIntInRange(1, 2);
        	if(choice == 1) {
        		cartaRobada = ronda.robarDescartes();
        	} else {
        		cartaRobada = ronda.robarCarta(); // simplificado
        	}
        }

        jugador.recibirCarta(cartaRobada);
        
        System.out.println("Mano: " + jugador.getMano());
        
        if (jugador instanceof IA ia) {
        	descartada = jugador.elegirDescartar(0);
        } else {
        	System.out.println("¿Que carta de su mano quiere descartar (1 - 8)?");
        	choice = input.readIntInRange(1, 8);
        	descartada = jugador.elegirDescartar(choice-1);
        }

        jugador.eliminarCarta(descartada);
        ronda.descartar(descartada);

        System.out.println("Tras descarte: " + jugador.getMano());
        
        ResultadoCombinacion r = new CombinadorMano().analizar(jugador.getMano(), jugador);
        
        System.out.println("Introduzca cualquier tecla para terminar su turno: ");
    	input.cleanInput();
        return r.puedeCerrar() || r.esChinchon();
    }

}
