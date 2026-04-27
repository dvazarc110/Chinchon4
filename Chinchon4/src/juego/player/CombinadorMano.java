package juego.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import juego.deck.Carta;
import juego.deck.Palo;

public class CombinadorMano {

    public ResultadoCombinacion analizar(List<Carta> mano, IPlayer jugador) {

        List<Carta> sobrantes = new ArrayList<>(mano);
        List<List<Carta>> grupos = new ArrayList<>();
        Carta samecard;
        int groupsize;

        extraerTríosYCuartetos(sobrantes, grupos, mano);
        extraerEscaleras(sobrantes, grupos, mano);

        if (jugador instanceof IA ia) {

        	groupsize = grupos.size();

        	if(groupsize == 2) {
        		samecard = comprobarCombinaciones(grupos);
        		if(!(samecard == null)) {
        			if(!analizarCombinaciones(grupos, samecard, mano)) {
        				//llamar a funcion para quedarse con la combinacion que menos posibilidades tiene de completarse de otra forma o que te deje con menos puntos
        			}
        		}
        	}

        }
       
        
        return new ResultadoCombinacion(grupos, sobrantes);
    }

    private void extraerTríosYCuartetos(List<Carta> cartas, List<List<Carta>> grupos, List<Carta> mano) {

        Map<Integer, List<Carta>> map = new HashMap<>();

        for (Carta c : mano) {

            if (c == null) continue; // evita fallo

            map.computeIfAbsent(c.getValor(), k -> new ArrayList<>()).add(c);
        }

        for (List<Carta> grupo : map.values()) {
            if (grupo.size() == 3 || grupo.size() == 4) {
                grupos.add(new ArrayList<>(grupo));
                cartas.removeAll(grupo);
            }
        }
    }

    private void extraerEscaleras(List<Carta> cartas, List<List<Carta>> grupos, List<Carta> mano) {

        Map<Palo, List<Carta>> porPalo = new HashMap<>();

        for (Carta c : mano) {

            if (c == null) continue; // evita fallo

            porPalo.computeIfAbsent(c.getPalo(), k -> new ArrayList<>()).add(c);
        }


        for (List<Carta> lista : porPalo.values()) {

            lista.sort(Comparator.comparingInt(Carta::getValor));

            List<Carta> escalera = new ArrayList<>();

            for (int i = 0; i < lista.size(); i++) {

                if (escalera.isEmpty()) {
                    escalera.add(lista.get(i));
                } else {
                    Carta ant = escalera.get(escalera.size() - 1);

                    if (lista.get(i).getValor() == ant.getValor() + 1) {
                        escalera.add(lista.get(i));
                    } else {
                        if (escalera.size() >= 3) {
                            grupos.add(new ArrayList<>(escalera));
                            cartas.removeAll(escalera);
                        }
                        escalera.clear();
                        escalera.add(lista.get(i));
                    }
                }
            }

            if (escalera.size() >= 3) {
                grupos.add(new ArrayList<>(escalera));
                cartas.removeAll(escalera);
            }
        }
    }
    
    public Carta comprobarCombinaciones(List<List<Carta>> grupos) {
    	List<Carta> comb1 = grupos.get(0);
    	List<Carta> comb2 = grupos.get(1);
    	
    	for(Carta c1 : comb1) {
    		for(Carta c2 : comb2) {
        		if(c2 == c1) {
        			return c2;
        		}
        	}
    	}
    	return null;
    }
    
    public boolean analizarCombinaciones(List<List<Carta>> grupos, Carta samecard, List<Carta> mano) {
    	List<Carta> comb1 = grupos.get(0);
    	List<Carta> comb2 = grupos.get(1);
    	List<List<Carta>> group1 = new ArrayList<>();
    	List<List<Carta>> group2 = new ArrayList<>();
    	int group1size;
    	int group2size;
    	
    	comb1.remove(samecard);
    	comb2.remove(samecard);
    	
    	extraerTríosYCuartetos(comb1, group1, mano);
        extraerEscaleras(comb1, group1, mano);
    	
        group1size = group1.size();
        
        extraerTríosYCuartetos(comb2, group2, mano);
        extraerEscaleras(comb2, group2, mano);
        
        group2size = group2.size();
        
        if((group1size == 1) && (group2size == 1)) {
        	return true;
        }else if((group1size == 0) && (group2size == 1)) {
        	return true;
        }else if((group1size == 1) && (group2size == 0)) {
        	return true;
        }else {
        	return false;
        }
    }
}
