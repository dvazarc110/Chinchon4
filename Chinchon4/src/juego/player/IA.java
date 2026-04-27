package juego.player;

import java.util.ArrayList;
import java.util.List;

import juego.deck.Carta;

public class IA extends Player {

    private final EvaluadorMano evaluador = new EvaluadorMano();

    public IA(String nombre) {
        super(nombre);
    }

    // ---------------------------
    // DECISIÓN DE DESCARTE
    // ---------------------------
    @Override
    public Carta elegirDescartar(int sel) {

        Carta peor = null;
        int peorValor = Integer.MAX_VALUE;

        for (Carta c : mano) {

            List<Carta> simulacion = new ArrayList<>(mano);
            simulacion.remove(c);

            int valor = evaluador.evaluar(simulacion, this);

            if (valor < peorValor) {
                peorValor = valor;
                peor = c;
            }
        }

        return peor;
    }

    // ---------------------------
    // DECISIÓN DE JUEGO
    // ---------------------------
    public boolean quiereRoboDescarte(Carta visible) {

        List<Carta> simulacion = new ArrayList<>(getMano());
        simulacion.add(visible);

        int valorActual = evaluador.evaluar(getMano(), this);
        int valorNuevo = evaluador.evaluar(simulacion, this);

        return valorNuevo > valorActual;
    }

    // ---------------------------
    // DECISIÓN DE CIERRE
    // ---------------------------
    public boolean puedeCerrarRonda() {

        ResultadoCombinacion r = new CombinadorMano().analizar(mano, this);

        return r.puedeCerrar() || r.esChinchon();
    }
}
