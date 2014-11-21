package etag;

import console.JPromptPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

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
        arestas.addAll(this.grafo.getArestasGraficas().values());
        Collections.sort(arestas, this.comparadorItens);
        String partida;
        String partida2;
        String destino;
        String destino2;
        veritices.add(arestas.get(0).getDestino().getID());
        veritices.add(veritices.size(), arestas.get(0).getPartida().getID());
        this.explorados.add(arestas.get(0).getPartida());
        this.explorados.add(arestas.get(0).getDestino());
        for (int c = 0; c < arestas.size(); c++) {
            for (int d = 0; d < veritices.size(); d++) {
                partida = veritices.get(d);
                partida2 = arestas.get(c).getPartida().getID();
                destino = veritices.get(d);
                destino2 = arestas.get(c).getDestino().getID();
                if ((partida.equals(partida2)) || (destino.equals(destino2)) || (partida.equals(destino2)) || (destino.equals(partida2)) || (destino.equals(partida))) {
                    if (!(verificados.contains(arestas.get(c).getPartida()) & verificados.contains(arestas.get(c).getDestino()))) {
                        this.explorados.add(arestas.get(c));
                        if (!verificados.contains(arestas.get(c).getPartida())) {
                            verificados.add(arestas.get(c).getPartida());
                        }
                        if (!verificados.contains(arestas.get(c).getDestino())) {
                            verificados.add(arestas.get(c).getDestino());
                        }
                        if (!veritices.contains(partida2)) {
                            veritices.add(partida2);
                            this.explorados.add(arestas.get(c).getPartida());
                        }
                        if (!veritices.contains(destino2)) {
                            veritices.add(destino2);
                            this.explorados.add(arestas.get(c).getDestino());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void algoritmoAGMKruskal() {
        // Limpando processamento anteriores, se houver.
        this.limparBufferCores();
        // Adicionando todos os vÃ©rtices grÃ¡ficos como explorados para iniciar Kruskal:
        // Todos os vÃ©rtices de borda preta e cor cinza.
        for (Item vertice : grafo.getMapaVertices().values()) {
            vertice.setCores("black,gray");
            this.explorados.add(vertice);
        }

        // Pegando o primeiro vÃ©rtice e colorindo todo de vermelho.
        this.explorados.add(new Item("Kruskal", "Colocando o primeiro vÃ©rtice todo de vermelho!"));
        Item primeiro = new Item(explorados.get(0).getItem());
        primeiro.setCores("red");
        this.explorados.add(primeiro);

        // Pegando o Ãºltimo vÃ©rtice e colorindo com borda vermelha e cor laranja.
        this.explorados.add(new Item("Kruskal", "Colocando o Ãºltimo vÃ©rtice de borda vermelha e cor laranja!"));
        Item ultimo = new Item(explorados.get(grafo.getMapaVertices().keySet().size() - 1).getItem());
        ultimo.setCores("red,orange");
        this.explorados.add(ultimo);

        // Essa linha coloca uma mensagem no meio da animaÃ§ao passo-a-passo.
        this.explorados.add(new Item("Kruskal", "Floresta iniciada com os vÃ©rtices marcados!"));

        // Criando um ArrayList vazio para usar o sort do Java depois.
        List<Item> listaArestas = new ArrayList<>();
        // Adicionando todas as arestas grÃ¡ficas nesse ArrayList.
        listaArestas.addAll(this.grafo.getArestasGraficas().values());
        // Ordenando o ArrayList usando o comparador de itens da e-TAG: no caso
        // de arestas, ele ordena pelo valor numÃ©rico dos pesos.
        Collections.sort(listaArestas, this.comparadorItens);

        // Criando um ArrayList para verificar vÃ©rtices e controlar a existÃªncia de ciclo.
        List<Item> verificados = new ArrayList<>();

        // Para cada aresta do ArrayList ordenado, FaÃ§a:
        for (Item aresta : listaArestas) {
            // Se ambos os vÃ©rtices de partida e de destino da aresta jÃ¡ foram 
            // verificados, entÃ£o a aresta forma ciclo: nÃ£o pode ser explorada!
            // (Forma errada de detectar ciclo... tem que arrumar isso!)
            if (verificados.contains(aresta.getPartida()) & verificados.contains(aresta.getDestino())) {
                // Essa linha coloca uma mensagem no meio da animaÃ§ao passo-a-passo.
                this.explorados.add(new Item("Ciclo Detectado!", "Aresta " + aresta.getID() + " de peso " + aresta.getValor() + " nÃ£o incluÃ­da!"));
                // Ou se preferir a mensagem ocorrer antes da animaÃ§ao passo-a-passo, descomente a linha abaixo e comente acima:
                // JPromptPane.print("Ciclo Detectado!", "Aresta " + aresta.getID() + " de peso " + aresta.getValor() + " nÃ£o incluÃ­da!");
            } else {
                // Incluir a aresta como explorada para coloraÃ§Ã£o.
                this.explorados.add(aresta);

                // Se o vÃ©rtice de partida nÃ£o foi verificado, inclua na lista de verificados.
                if (!verificados.contains(aresta.getPartida())) {
                    verificados.add(aresta.getPartida());
                }
                // Se o vÃ©rtice de destino nÃ£o foi verificado, inclua na lista de verificados.
                if (!verificados.contains(aresta.getDestino())) {
                    verificados.add(aresta.getDestino());
                }
            }
            // Se todos os vÃ©rtices do grafo jÃ¡ foram verificados, interrompa o algoritmo.
            if (verificados.containsAll(this.grafo.getMapaVertices().values())) {
                break;
            }
        }
    }

    @Override
    public void algoritmoBuscaProfundidade() {
        ArrayList<Item> arestas = new ArrayList<>();
        this.limparBufferCores();
        arestas.addAll(this.grafo.getArestasGraficas().values());
        this.dfs(arestas, 0);
        this.explorados.add(new Item("Fim do processo!", "Os vertices que possuem ligação, foram encontrados"));
    }

    public void dfs(ArrayList<Item> arestas, int index) {
        if (arestas.size() > index) {

            //Verifica se esta no sexplorados
            if (!this.explorados.contains(arestas.get(index).getPartida())) {
                arestas.get(index).getPartida().setCores("red");
                this.explorados.add(arestas.get(index).getPartida());
            } else {
                Item i = arestas.get(index).getPartida();
                i.setCores("black,gray");
                this.explorados.add(i);
            }

            
            if (!this.explorados.contains(arestas.get(index))) {
                arestas.get(index).setCores("red");
                this.explorados.add(arestas.get(index));
            } else {
                Item i = arestas.get(index);
                i.setCores("black,gray");
                this.explorados.add(i);
            }

            if (!this.explorados.contains(arestas.get(index).getDestino())) {
                arestas.get(index).getDestino().setCores("red");
                this.explorados.add(arestas.get(index).getDestino());
            } else {
                Item i = arestas.get(index).getDestino();
                i.setCores("black,gray");
                this.explorados.add(i);
            }

            if (!this.explorados.contains(arestas.get(index))) {
                this.explorados.add(arestas.get(index));
            }
            this.dfs(arestas, index + 1);
        }
    }

    @Override
    public void algoritmoBuscaLargura() {
        int infinit = 0;

        this.limparBufferCores();
        for (Item aresta : this.grafo.getArestasGraficas().values()) {
            if (Integer.parseInt(aresta.getValor()) > infinit) {
                infinit = Integer.parseInt(aresta.getValor());
            }
        }

        ArrayList<Item> arestas = new ArrayList<>();
        ArrayList<Item> vertices = new ArrayList<>();

        arestas.addAll(this.grafo.getArestasGraficas().values());
        vertices.addAll(this.grafo.getMapaVertices().values());

        Collections.sort(arestas, this.comparadorItens);
        Collections.sort(vertices, this.comparadorItens);

        for (Item vertice : vertices) {
            for (Item aresta : arestas) {
                if (aresta.getPartida().equals(vertice)) {
                    if (!this.explorados.contains(vertice)) {
                        this.explorados.add(vertice);
                    }
                    if (!this.explorados.contains(aresta)) {
                        this.explorados.add(aresta);
                    }
                }
            }
        }
        if (this.explorados.size() > 0) {
            this.explorados.add(new Item("FIM !!!", "Fim do caminho de Dijkstra!"));
        } else {
            this.explorados.add(new Item("FIM !!!", "Não foi possível rodar o algoritmo de Dijkstra!"));
        }

    }

    /*@Override
     public void algoritmoBuscaLargura() {
     ArrayList<Item> arestas = new ArrayList<>();
     this.limparBufferCores();
     this.explorados.addAll(this.grafo.getMapaVertices().values());
     this.explorados.add(new Item("Busca em Largura", "Busca iniciada com os vértices marcados!"));
     arestas.addAll(this.grafo.getArestasGraficas().values());
     Collections.sort(arestas, this.comparadorItens);

     for (Item aresta : arestas) {
     Iterator<Item> vertices = this.grafo.getVerticesAdjacentes(aresta.getPartida().getItem().getId()).iterator();
     while (vertices.hasNext()) {
     Item item = vertices.next();
     if (!this.explorados.contains(item)) {
     this.explorados.add(item);
     this.explorados.add(aresta);
     }

     }
     }
     }*/
    @Override
    public void algoritmoDijkstra() {

        int infinit = 0;

        this.limparBufferCores();
        for (Item aresta : this.grafo.getArestasGraficas().values()) {
            if (Integer.parseInt(aresta.getValor()) > infinit) {
                infinit = Integer.parseInt(aresta.getValor());
            }
        }

        ArrayList<Item> arestas = new ArrayList<>();
        ArrayList<Item> vertices = new ArrayList<>();

        arestas.addAll(this.grafo.getArestasGraficas().values());
        vertices.addAll(this.grafo.getMapaVertices().values());

        Collections.sort(arestas, this.comparadorItens);
        Collections.sort(vertices, this.comparadorItens);

        int[][] matriz = new int[vertices.size()][vertices.size()];
        for (Item aresta : arestas) {
            matriz[Integer.parseInt(aresta.getPartida().getValor())-1][Integer.parseInt(aresta.getDestino().getValor())-1] = Integer.parseInt(aresta.getValor());
        }

        String linha1 = "";
        for (int[] vetor : matriz) {
            String linha = "";
            for (int valor : vetor) {
                linha += valor + "     ";
            }
            linha1 +=linha+"\n\r";
        }
        JPromptPane.print("Matriz de arestas", linha1);

        Item next = vertices.get(0);
        while (next != null) {
            Item best = null;
            for (Item aresta : arestas) {
                if (aresta.getPartida().equals(next)) {
                    //System.out.println("Vertice:" + aresta.getPartida().getValor() + "->" + aresta.getDestino().getValor() + " :" + aresta.getValor());
                    if (best == null) {
                        best = aresta;
                    } else {
                        if (Integer.parseInt(best.getValor()) > Integer.parseInt(aresta.getValor())) {
                            best = aresta;
                        }
                    }
                }
            }

            //System.out.println("Melhor aresta: " + best.getValor());

            if (!this.explorados.contains(next)) {
                this.explorados.add(next);
            }

            if (best.getDestino() != null && !this.explorados.contains(best.getDestino())) {
                this.explorados.add(best);
                next = best.getDestino();
            } else {
                next = null;
            }
        }
        if (this.explorados.size() > 0) {
            this.explorados.add(new Item("FIM !!!", "Fim do caminho de Dijkstra!"));
        } else {
            this.explorados.add(new Item("FIM !!!", "Não foi possível rodar o algoritmo de Dijkstra!"));
        }

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
