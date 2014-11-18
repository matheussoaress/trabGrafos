//package etag;

import console.JPromptPane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author insira aqui o nome de cada integrante do grupo em cada tag @author
 */
public class PrincipalSecond extends JanelaETAG {

    /**
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.pack();
        programa.setVisible(true);
    }

    private void vericaCliclo(ArrayList<Item> arestas){

        int tam = arestas.size();
        int[][] a = new int[tam][tam];
        Item aux;

        for(int c = 0; c < tam; c++){
            aux = arestas.get(c);

        }

    }

    @Override
    public void algoritmoAGMPrim() {
        this.limparBufferCores();
        Map<String, Item> arestasMapa = grafo.getArestasGraficas();

        ArrayList<Item> arestas = new ArrayList<Item>();
        for(Item aresta : arestasMapa.values()){
//            JPromptPane.print("ahjsf", aresta.getItem().getSource().getId());
            System.out.printf("De %s  para %s\n", aresta.getItem().getSource().getId(), aresta.getItem().getTarget().getId());
            arestas.add(aresta);
        }
//        arestas.sort(Comparator.<Item>comparing());

//        for (Item vertice : vertivesMapa.values()){
//            vertice.getItem().
//        }

//        Map<String, Item> vertivesMapa  = grafo.getMapaVertices();
//        for(Item vertice: vertivesMapa.values()){
//            this.explorados.add(vertice);
//        }

    }

    @Override
    public void algoritmoAGMKruskal() {

    }

    @Override
    public void algoritmoBuscaProfundidade() {

    }

    @Override
    public void algoritmoBuscaLargura() {

    }
    
    @Override
    public void algoritmoDijkstra() {
        
    }

    @Override
    public void animacaoColorirVertices() {
        this.limparBufferCores();
        for (Item vertice : grafo.getMapaVertices().values()) {
            explorados.add(vertice);
        }
        JPromptPane.print("Coloração de Grafos", "Para ver a animação passo-a-passo, clique em Next!");
    }

    @Override
    public void animacaoColorirArestas() {
        this.limparBufferCores();
        for (Item aresta : grafo.getArestasGraficas().values()) {
            this.explorados.add(aresta);
        }
        JPromptPane.print("Coloração de Grafos", "Para ver a animação passo-a-passo, clique em Next!");
    }

    @Override
    public void animacaoColorirVerticesArestas() {
        this.limparBufferCores();
        for (Item aresta : grafo.getArestasGraficas().values()) {
            if (!(explorados.contains(new Item(aresta.getItem().getSource())))) {
                this.explorados.add(new Item(aresta.getItem().getSource()));
            }
            this.explorados.add(aresta);
            if (!(explorados.contains(new Item(aresta.getItem().getTarget())))) {
                this.explorados.add(new Item(aresta.getItem().getTarget()));
            }
        }
        for (Item vertice : grafo.getMapaVertices().values()) {
            if (!(explorados.contains(vertice))) {
                this.explorados.add(vertice);
            }
        }
        JPromptPane.print("Coloração de Grafos", "Para ver a animação passo-a-passo, clique em Next!");
    }
}