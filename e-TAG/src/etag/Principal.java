package etag;

import console.JPromptPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author insira aqui o nome de cada integrante do grupo em cada tag @author
 */
public class Principal extends JanelaETAG {

    /**
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.pack();
        programa.setVisible(true);
    }

    @Override
    public void algoritmoAGMPrim() {
        ArrayList<Item> arestas = new ArrayList<Item>();
        ArrayList<String> veritices = new ArrayList<String>();
        ArrayList<Item> sArestas = new ArrayList<Item>();
        List<Item> verificados = new ArrayList<Item>();

        this.limparBufferCores();
//        this.explorados.add(new Item("Prim", "Floresta iniciada com os vértices marcados!"));
        arestas.addAll(this.grafo.getArestasGraficas().values());
        Collections.sort(arestas, this.comparadorItens);

        String partida;
        String partida2;
        String destino;
        String destino2;

        //SELECIONAR A MELHOR ARESTA
        veritices.add(arestas.get(0).getDestino().getID());
        veritices.add(veritices.size(), arestas.get(0).getPartida().getID());


        //BUSCAR MELHOR VÉRTICE
        //CAPTURA OS VÉRTICES
        //VERIFICA SE O MENOR TEM LIGAÇÃO COM O VERTICE
        // PULA AQUELES QUE NÃO TEM LIGAÇÃO
        this.explorados.add(arestas.get(0).getPartida());
        this.explorados.add(arestas.get(0).getDestino());
        for(int c=0; c<arestas.size(); c++){
            for(int d=0; d<veritices.size();c++){
                partida = veritices.get(d);
                partida2 = arestas.get(c).getPartida().getID();
                destino = veritices.get(d);
                destino2 = arestas.get(c).getDestino().getID();
                if((partida.equals(partida2))||(destino.equals(destino2))||(partida.equals(destino2))||(destino.equals(partida2))||(destino.equals(partida))){
                    if (!(verificados.contains(arestas.get(c).getPartida()) & verificados.contains(arestas.get(c).getDestino()))) {

                        if(!this.explorados.contains(arestas.get(c).getPartida())){
                            System.out.println(arestas.get(c).getPartida());
                        }
                        this.explorados.add(arestas.get(c));

                        if (!verificados.contains(arestas.get(c).getPartida())) {
                            verificados.add(arestas.get(c).getPartida());
                        }
                        if (!verificados.contains(arestas.get(c).getDestino())) {
                            verificados.add(arestas.get(c).getDestino());
                        }

                        if(!veritices.contains(partida2)) {
                            veritices.add(partida2);
                            this.explorados.add(arestas.get(c).getPartida());
                        }

                        if(!veritices.contains(destino2)) {
                            veritices.add(destino2);
                            this.explorados.add(arestas.get(c).getDestino());
                        }
                    }
                }
            }
        }

        //SELECIONA CAMINHO SEM



    }

    @Override
    public void algoritmoAGMKruskal() {
        // Limpando processamento anteriores, se houver.
        this.limparBufferCores();
        // Adicionando todos os vértices gráficos como explorados para iniciar Kruskal.
        this.explorados.addAll(this.grafo.getMapaVertices().values());
        // Essa linha coloca uma mensagem no meio da animaçao passo-a-passo.
        this.explorados.add(new Item("Kruskal", "Floresta iniciada com os vértices marcados!"));
        
        // Criando um ArrayList vazio para usar o sort do Java depois.
        List<Item> listaArestas = new ArrayList<Item>();
        // Adicionando todas as arestas gráficas nesse ArrayList.
        listaArestas.addAll(this.grafo.getArestasGraficas().values());
        // Ordenando o ArrayList usando o comparador de itens da e-TAG: no caso
        // de arestas, ele ordena pelo valor numérico dos pesos.
        Collections.sort(listaArestas, this.comparadorItens);
        
        // Criando um ArrayList para verificar vértices e controlar a existência de ciclo.
        List<Item> verificados = new ArrayList<Item>();
        
        // Para cada aresta do ArrayList ordenado, Faça:
        for (Item aresta : listaArestas) {
            // Se ambos os vértices de partida e de destino da aresta já foram 
            // verificados, então a aresta forma ciclo: não pode ser explorada!
            if (verificados.contains(aresta.getPartida()) & verificados.contains(aresta.getDestino())) {
                // Essa linha coloca uma mensagem no meio da animaçao passo-a-passo.
                this.explorados.add(new Item("Ciclo Detectado!", "Aresta " + aresta.getID() + " de peso " + aresta.getValor() + " não incluída!"));
                // Ou se preferir a mensagem ocorrer antes da animaçao passo-a-passo, descomente a linha abaixo e comente acima:
                // JPromptPane.print("Ciclo Detectado!", "Aresta " + aresta.getID() + " de peso " + aresta.getValor() + " não incluída!");
            } else {
                // Incluir a aresta como explorada para coloração.
                this.explorados.add(aresta);

                // Se o vértice de partida não foi verificado, inclua na lista de verificados.
                if (!verificados.contains(aresta.getPartida())) {
                    verificados.add(aresta.getPartida());
                }
                // Se o vértice de destino não foi verificado, inclua na lista de verificados.
                if (!verificados.contains(aresta.getDestino())) {
                    verificados.add(aresta.getDestino());
                }
            }
            // Se todos os vértices do grafo já foram verificados, interrompa o algoritmo.
            if (verificados.containsAll(this.grafo.getMapaVertices().values())) break;
        }
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
        explorados.addAll(grafo.getMapaVertices().values());
        JPromptPane.print("Coloração de Grafos", "Para ver a animação passo-a-passo, clique em Next!");
    }

    @Override
    public void animacaoColorirArestas() {
        this.limparBufferCores();
        explorados.addAll(grafo.getArestasGraficas().values());
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