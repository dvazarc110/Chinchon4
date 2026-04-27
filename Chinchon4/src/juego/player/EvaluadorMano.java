package juego.player;

import java.util.List;

import juego.deck.Carta;

public class EvaluadorMano {

    private final CombinadorMano combinador = new CombinadorMano();

    public int evaluar(List<Carta> mano, IPlayer jugador) {

        ResultadoCombinacion r = combinador.analizar(mano, jugador);

        int puntosSobrantes = r.calcularPuntos();

        int bonusCombinaciones = r.getCombinaciones().size() * 10;

        int bonusChinchon = r.esChinchon() ? 100 : 0;

        return bonusCombinaciones + bonusChinchon - puntosSobrantes;
    }
}
