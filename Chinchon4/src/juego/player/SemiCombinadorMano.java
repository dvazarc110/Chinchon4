package juego.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import juego.deck.Carta;
import juego.deck.Palo;

public class SemiCombinadorMano {

    public SemiResultadoCombinacion analizar(List<Carta> mano, IPlayer jugador) {

    	List<Carta> sobrantes = new ArrayList<>(mano);
    	List<List<Carta>> grupos = new ArrayList<>();
    	List<Carta> samecards = new ArrayList<>();
    	Carta samecard;
    	int groupsize;

    	extraerPosibTríosYCuartetos(sobrantes, grupos, mano);
    	extraerPosibEscaleras(sobrantes, grupos, mano);

    	groupsize = grupos.size();

    	if((groupsize == 2) && (sobrantes.size()>1)) {
    		samecards = comprobarCombinaciones(grupos);
    		if(!(samecards.isEmpty())) {
    			if(!analizarCombinaciones(grupos, samecards, mano)) {
    				selectCombination(grupos, sobrantes, samecards);
    			}
    		}
    	}else if(groupsize > 2) {
    		removeExtraCard(grupos, sobrantes);
    	}

    	return new SemiResultadoCombinacion(grupos, sobrantes);
    }

    private void extraerPosibTríosYCuartetos(List<Carta> cartas, List<List<Carta>> grupos, List<Carta> mano) {

        Map<Integer, List<Carta>> map = new HashMap<>();

        for (Carta c : mano) {

            if (c == null) continue; // evita fallo

            map.computeIfAbsent(c.getValor(), k -> new ArrayList<>()).add(c);
        }

        for (List<Carta> grupo : map.values()) {
            if (grupo.size() >= 2) {
                grupos.add(new ArrayList<>(grupo));
                cartas.removeAll(grupo);
            }
        }
    }

    private void extraerPosibEscaleras(List<Carta> cartas, List<List<Carta>> grupos, List<Carta> mano) {

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

                    if (lista.get(i).getTipo().ordinal() == ant.getTipo().ordinal() + 1) {
                        escalera.add(lista.get(i));
                    } else {
                        if (escalera.size() >= 2) {
                            grupos.add(new ArrayList<>(escalera));
                            cartas.removeAll(escalera);
                        }
                        escalera.clear();
                        escalera.add(lista.get(i));
                    }
                }
            }

            if (escalera.size() >= 2) {
                grupos.add(new ArrayList<>(escalera));
                cartas.removeAll(escalera);
            }
        }
    }
    
    public List<Carta> comprobarCombinaciones(List<List<Carta>> grupos) {
    	List<Carta> comb1 = grupos.get(0);
    	List<Carta> comb2 = grupos.get(1);
    	List<Carta> samecards = new ArrayList<>();
    	
    	for(Carta c1 : comb1) {
    		for(Carta c2 : comb2) {
        		if(c2 == c1) {
        			 samecards.add(c2);
        		}
        	}
    	}
    	return samecards;
    }
    
    public void removeExtraCard(List<List<Carta>> group, List<Carta> sobrantes) {
    	List<Carta> comb1 = group.get(0);
    	List<Carta> comb2 = group.get(1);
    	List<Carta> comb3 = group.get(2);
    	List<Carta> combRemoved = new ArrayList<>();
    	List<List<Carta>> extrac = new ArrayList<>(); 
    	Carta same1 = null;
    	Carta same2 = null;
    	
    	for(Carta c1 : comb1) {
    		for(Carta c2 : comb2) {
        		if(c2 == c1) {
        			if(!(same1 == null)) {
        				same2 = c2;
        			}else {
        				same1 = c2;
        			}
        		}
        	}
    	}
    	
    	for(Carta c2 : comb2) {
    		for(Carta c3 : comb3) {
        		if(c2 == c3) {
        			if(!(same1 == null)) {
        				same2 = c2;
        			}else {
        				same1 = c2;
        			}
        		}
        	}
    	}
    	
    	for(Carta c1 : comb1) {
    		for(Carta c3 : comb3) {
        		if(c3 == c1) {
        			if(!(same1 == null)) {
        				same2 = c3;
        			}else {
        				same1 = c3;
        			}
        		}
        	}
    	}
    	
    	for(List<Carta> grupo : group) {
    		if(grupo.contains(same1) && grupo.contains(same2)) {
    			for(Carta c : grupo) {
    				combRemoved.add(c);
    			}
    		}
    	}
    	
    	extrac.add(combRemoved);
    	group.removeAll(extrac);
    	combRemoved.remove(same1);
    	combRemoved.remove(same2);
    	sobrantes.add(combRemoved.get(0));
    }
    
    public boolean analizarCombinaciones(List<List<Carta>> group, List<Carta> samecards, List<Carta> mano) {
    	List<Carta> comb1 = group.get(0);
    	List<Carta> comb1_copy = group.get(0);
    	List<Carta> comb1_2 = new ArrayList<>();
    	for(Carta c : comb1) {
    		comb1_2.add(c);
    	}
    	List<Carta> comb2 = group.get(1);
    	List<Carta> comb2_copy = group.get(1);
    	List<Carta> comb2_2 = new ArrayList<>();
    	for(Carta c : comb2) {
    		comb2_2.add(c);
    	}
    	List<List<Carta>> group1 = new ArrayList<>();
    	List<List<Carta>> group2 = new ArrayList<>();
    	int group1size;
    	int group2size;
    	group.remove(comb1);
        group.remove(comb2);
        
    	comb1.removeAll(samecards);
    	comb2.removeAll(samecards);
    	
    	extraerPosibTríosYCuartetos(comb1_2, group1, comb1);
        extraerPosibEscaleras(comb1_2, group1, comb1);
    	
        group1size = group1.size();
        
        extraerPosibTríosYCuartetos(comb2_2, group2, comb2);
        extraerPosibEscaleras(comb2_2, group2, comb2);
        
        group2size = group2.size();
        
        if((group1size == 1) && (group2size == 1)) {
        	group.add(comb1);        	
        	group.add(comb2_copy);
        	return true;
        }else if((group1size == 0) && (group2size == 1)) {
        	group.add(comb1_copy);        	
        	group.add(comb2);
        	return true;
        }else if((group1size == 1) && (group2size == 0)) {       	
        	group.add(comb1);        	
        	group.add(comb2_copy);
        	return true;
        }else {
        	group.add(comb1_copy);        	
        	group.add(comb2_copy);
        	return false;
        }
    }
    
    public void selectCombination(List<List<Carta>> group, List<Carta> sobrantes, List<Carta> samecards) {
    	List<Carta> comb1 = group.get(0);
    	int group1points = 0;
    	for(Carta c : comb1) {
    		group1points += c.getValor();
    	}
    	
    	List<Carta> comb2 = group.get(1);
    	int group2points = 0;
    	for(Carta c : comb2) {
    		group2points += c.getValor();
    	}
        
    	group.remove(comb1);
        group.remove(comb2);
    	
        if(group1points > group2points) {
        	comb2.removeAll(samecards);
        	group.add(comb1); 
        	for(Carta c : comb2) {
        		sobrantes.add(c);
        	}
        }else if(group1points < group2points) {
        	comb1.removeAll(samecards);       	
        	group.add(comb2);
        	for(Carta c : comb1) {
        		sobrantes.add(c);
        	}
        }else {
        	comb1.removeAll(samecards);       	
        	group.add(comb2);
        	for(Carta c : comb1) {
        		sobrantes.add(c);
        	}
        }
    }
}
