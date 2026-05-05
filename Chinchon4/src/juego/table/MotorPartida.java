package juego.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import juego.player.CombinadorMano;
import juego.player.IPlayer;
import juego.player.ResultadoCombinacion;

public class MotorPartida {

    private final GestorPartida gestor = GestorPartida.getInstancia();
    private final GestorRonda gestorRonda = new GestorRonda();
    private final TurnoController turnoController;

    private final Map<IPlayer, Integer> puntuaciones = new HashMap<>();

    public MotorPartida() {
        this.turnoController = new TurnoController(gestorRonda);
    }

    // ---------------------------
    // INICIO DE PARTIDA
    // ---------------------------
    public void iniciar() {
    	
    	ConsoleInput input = new ConsoleInput();
    	String siguiente = "";
    	int turncont;
    	
        inicializarPuntuaciones();

        while (!finPartida()) {

            System.out.println("\n🔵 NUEVA RONDA");

            gestorRonda.iniciarRonda(gestor.getJugadores());

            boolean rondaTerminada = false;

            turncont = 0;
            
            while (!rondaTerminada) {

                for (IPlayer jugador : gestor.getJugadores()) {

                    System.out.println("\nTurno de: " + jugador.getNombre());
                    
                    turncont++;
                    
                    boolean haCerrado = turnoController.jugarTurno(jugador, turncont, (gestor.getJugadores()).size());

                    if (haCerrado) {

                        System.out.println("🏁 RONDA CERRADA por " + jugador.getNombre());

                        procesarFinRonda(jugador);

                        rondaTerminada = true;
                        break;
                    }
                }
            }
            
            eliminarJugadores();
            mostrarPuntuaciones();
            
            if(!finPartida()) {
            	System.out.println("Introduzca cualquier tecla para pasar a la siguiente ronda: ");
            	siguiente = input.readString();
            }
            gestorRonda.finalizarRonda(gestor.getJugadores());
            
            
        }

        declararGanador();
    }
    
    private void inicializarPuntuaciones() {
        for (IPlayer p : gestor.getJugadores()) {
            puntuaciones.put(p, 0);
        }
    }

    private void procesarFinRonda(IPlayer ganador) {

        CombinadorMano combinador = new CombinadorMano();

        for (IPlayer p : gestor.getJugadores()) {

            ResultadoCombinacion r = combinador.analizar(p.getMano(), p);

            int puntos = r.calcularPuntos();

            puntuaciones.put(p, puntuaciones.get(p) + puntos);

            System.out.println(p.getNombre() + " suma " + puntos + " puntos");
        }

        // bonus chinchón
        ResultadoCombinacion rGanador = combinador.analizar(ganador.getMano(), ganador);

        if (rGanador.esChinchon()) {
            System.out.println("🔥 CHINCHÓN! Victoria directa de " + ganador.getNombre());
            puntuaciones.put(ganador, 0);
            eliminarTodosExcepto(ganador);
        }
    }

    private void eliminarJugadores() {

        Iterator<IPlayer> it = gestor.getJugadores().iterator();

        while (it.hasNext()) {

            IPlayer p = it.next();

            if (puntuaciones.get(p) >= 100) {

                System.out.println("❌ Eliminado: " + p.getNombre());

                it.remove();
                puntuaciones.remove(p);
            }
        }
    }

    private boolean finPartida() {
        return gestor.getJugadores().size() <= 1;
    }

    private void declararGanador() {

        IPlayer ganador = gestor.getJugadores().get(0);

        System.out.println("\n🏆 GANADOR FINAL: " + ganador.getNombre());
    }

    private void mostrarPuntuaciones() {

        System.out.println("\n📊 PUNTUACIONES:");

        for (Map.Entry<IPlayer, Integer> e : puntuaciones.entrySet()) {
            System.out.println(e.getKey().getNombre() + ": " + e.getValue());
        }
    }

    private void eliminarTodosExcepto(IPlayer ganador) {

        List<IPlayer> copia = new ArrayList<>(gestor.getJugadores());

        for (IPlayer p : copia) {
            if (!p.equals(ganador)) {
                gestor.getJugadores().remove(p);
            }
        }
    }
}
