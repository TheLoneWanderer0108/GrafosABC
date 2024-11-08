package bfs_dfs;

import java.util.List;

public class Main {
    public static void main(String[] argumentosEntrada) {
        try {
            int[][] matrizAdyacenciaGrafo = {
                {0, 2, 1, 0},
                {1, 2, 1, 1},
                {1, 1, 0, 1},
                {0, 5, 1, 3}
            };
            
            /* Creación de la instancia del grafo con la matriz de adyacencia, Visualización de la estructura del grafo
             * Ejecución y visualización del recorrido en anchura, Ejecución y visualización del recorrido en profundidad */
            Graph grafoEjemplo = new Graph(matrizAdyacenciaGrafo);
            
            grafoEjemplo.imprimirListaAdyacencia();
            
            System.out.println("\nDemostración de Recorridos del Grafo:");
            
            System.out.println("Recorrido en Anchura (BFS) iniciando desde el vértice A:");
            List<Character> resultadoRecorridoAnchura = grafoEjemplo.recorridoEnAnchura('A');
            System.out.println(resultadoRecorridoAnchura);
            
            System.out.println("\nRecorrido en Profundidad (DFS) iniciando desde el vértice A:");
            List<Character> resultadoRecorridoProfundidad = grafoEjemplo.recorridoEnProfundidad('A');
            System.out.println(resultadoRecorridoProfundidad);
            
        } catch (IllegalArgumentException excepcionArgumentos) {
            System.err.println("Error de validación en los argumentos: " + excepcionArgumentos.getMessage());
        } catch (Exception excepcionGeneral) {
            System.err.println("Error inesperado durante la ejecución: " + excepcionGeneral.getMessage());
        }
    }
}
