package juego.player;

import java.util.List;

import juego.deck.Carta;

public class SemiResultadoCombinacion {

    private List<List<Carta>> combinaciones;
    private List<Carta> sobrantes;

    public SemiResultadoCombinacion(List<List<Carta>> combinaciones, List<Carta> sobrantes) {
        this.combinaciones = combinaciones;
        this.sobrantes = sobrantes;
    }

    public boolean puedeCerrar() {
    	if(sobrantes.size() == 0) {
    		return true;
    	}else if(sobrantes.size() == 1) {
    		if(sobrantes.get(0).getValor() > 5) {
    			return false;
    		}else {
    			return true;
    		}
    	}else {
    		return false;
    	}
    }

    public boolean esChinchon() {
        return combinaciones.stream()
                .anyMatch(g -> g.size() == 7);
    }

    public int calcularPuntos() {
    	if(sobrantes.size() > 0) {
    		return sobrantes.stream()
    						.mapToInt(Carta::getValor)
    						.sum();
    	}else {
    		return (-10);
    	}
    }
	public List<List<Carta>> getCombinaciones() {
		return combinaciones;
	}
}
