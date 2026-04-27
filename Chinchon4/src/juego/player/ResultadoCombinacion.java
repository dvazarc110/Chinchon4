package juego.player;

import java.util.*;

import juego.deck.Carta;

public class ResultadoCombinacion {

    private List<List<Carta>> combinaciones;
    private List<Carta> sobrantes;

    public ResultadoCombinacion(List<List<Carta>> combinaciones, List<Carta> sobrantes) {
        this.combinaciones = combinaciones;
        this.sobrantes = sobrantes;
    }

    public boolean puedeCerrar() {
        return sobrantes.size() <= 1;
    }

    public boolean esChinchon() {
        return combinaciones.stream()
                .anyMatch(g -> g.size() == 7);
    }

    public int calcularPuntos() {
        return sobrantes.stream()
                .mapToInt(Carta::getValor)
                .sum();
    }
	public List<List<Carta>> getCombinaciones() {
		return combinaciones;
	}
}
