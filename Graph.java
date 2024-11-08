package bfs_dfs;

import java.util.*;

public class Graph {
    private int numeroTotalVertices;
    private LinkedList<Character>[] listaAdyacenciaVertices;
    
    
    @SuppressWarnings("unchecked")
    public Graph(int[][] matrizAdyacenciaEntrada) {
        if (matrizAdyacenciaEntrada == null) {
            throw new IllegalArgumentException("La matriz de adyacencia no puede ser nula");
        }
        if (matrizAdyacenciaEntrada.length == 0) {
            throw new IllegalArgumentException("La matriz de adyacencia no puede estar vacía");
        }
        if (matrizAdyacenciaEntrada.length != matrizAdyacenciaEntrada[0].length) {
            throw new IllegalArgumentException("La matriz de adyacencia debe ser cuadrada");
        }
        
        this.numeroTotalVertices = matrizAdyacenciaEntrada.length;
        listaAdyacenciaVertices = new LinkedList[numeroTotalVertices];
        
        for (int indiceVertice = 0; indiceVertice < numeroTotalVertices; indiceVertice++) {
            listaAdyacenciaVertices[indiceVertice] = new LinkedList<>();
        }
        
        for (int verticeOrigen = 0; verticeOrigen < matrizAdyacenciaEntrada.length; verticeOrigen++) {
            for (int verticeDestino = 0; verticeDestino < matrizAdyacenciaEntrada[verticeOrigen].length; verticeDestino++) {
                if (matrizAdyacenciaEntrada[verticeOrigen][verticeDestino] == 1) {
                    listaAdyacenciaVertices[verticeOrigen].add((char)('A' + verticeDestino));
                }
            }
        }
    }
    
    private void validarVerticeEntrada(char verticeEntrada) {
        int indiceVertice = verticeEntrada - 'A';
        if (indiceVertice < 0 || indiceVertice >= numeroTotalVertices) {
            throw new IllegalArgumentException(
                "El vértice " + verticeEntrada + " está fuera del rango válido");
        }
    }
    
    public List<Character> recorridoEnAnchura(char verticeInicial) {
        validarVerticeEntrada(verticeInicial);
        int indiceVerticeInicial = verticeInicial - 'A';
        
        List<Character> caminoRecorrido = new ArrayList<>();
        boolean[] verticesVisitados = new boolean[numeroTotalVertices];
        
        LinkedList<Integer> colaVerticesPendientes = new LinkedList<>();
        
        verticesVisitados[indiceVerticeInicial] = true;
        colaVerticesPendientes.add(indiceVerticeInicial);
        
        while (!colaVerticesPendientes.isEmpty()) {
            int verticeActual = colaVerticesPendientes.poll();
            caminoRecorrido.add((char)('A' + verticeActual));
            
            for (char verticeAdyacente : listaAdyacenciaVertices[verticeActual]) {
                int indiceVerticeAdyacente = verticeAdyacente - 'A';
                if (!verticesVisitados[indiceVerticeAdyacente]) {
                    verticesVisitados[indiceVerticeAdyacente] = true;
                    colaVerticesPendientes.add(indiceVerticeAdyacente);
                }
            }
        }
        
        return caminoRecorrido;
    }
    
    public List<Character> recorridoEnProfundidad(char verticeInicial) {
        validarVerticeEntrada(verticeInicial);
        
        List<Character> caminoRecorrido = new ArrayList<>();
        boolean[] verticesVisitados = new boolean[numeroTotalVertices];
        try {
            realizarRecorridoEnProfundidad(verticeInicial - 'A', verticesVisitados, caminoRecorrido);
        } catch (Exception excepcion) {
            throw new RuntimeException("Error durante el recorrido en profundidad: " + excepcion.getMessage());
        }
        return caminoRecorrido;
    }
    
    private void realizarRecorridoEnProfundidad(int indiceVerticeActual, boolean[] verticesVisitados, 
                                              List<Character> caminoRecorrido) {
        verticesVisitados[indiceVerticeActual] = true;
        caminoRecorrido.add((char)('A' + indiceVerticeActual));
        
        for (char verticeAdyacente : listaAdyacenciaVertices[indiceVerticeActual]) {
            int indiceVerticeAdyacente = verticeAdyacente - 'A';
            if (!verticesVisitados[indiceVerticeAdyacente]) {
                realizarRecorridoEnProfundidad(indiceVerticeAdyacente, verticesVisitados, caminoRecorrido);
            }
        }
    }
    
    public void imprimirListaAdyacencia() {
        System.out.println("Representación del Grafo - Lista de Adyacencia:");
        for (int indiceVertice = 0; indiceVertice < numeroTotalVertices; indiceVertice++) {
            System.out.print("Vértice " + (char)('A' + indiceVertice) + " está conectado a: ");
            for (char verticeAdyacente : listaAdyacenciaVertices[indiceVertice]) {
                System.out.print(verticeAdyacente + " ");
            }
            System.out.println();
        }
    }
}
