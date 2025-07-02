// AnalisadorDeAlgoritmos.java
public class AnalisadorDeAlgoritmos {

    public static void main(String[] args) {
        int[] dataset1 = {1, 100, 2, 99, 3, 98, 4, 97, 5, 96, 6, 95, 7, 94, 8, 93, 9, 92, 10, 91, 11, 90, 12, 89, 13, 88, 14, 87, 15, 86, 16, 85, 17, 84, 18, 83, 19, 82, 20, 81, 21, 80, 22, 79, 23, 78, 24, 77, 25, 76};
        int[] dataset2 = {1, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52};
        int[] dataset3 = {50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        System.out.println("--- Análise de Algoritmos de Ordenação ---");

        executarAnalise("Dataset 1 (Intercalado)", dataset1, 50);
        executarAnalise("Dataset 2 (Quase Ordenado Reverso)", dataset2, 50);
        executarAnalise("Dataset 3 (Totalmente Reverso)", dataset3, 50);
    }

    public static void executarAnalise(String nomeDataset, int[] original, int n) {
        System.out.println("\n==============================================");
        System.out.println("Analisando: " + nomeDataset);
        System.out.println("==============================================");

        // --- Merge Sort ---
        int[] copia1 = clonarArray(original, n);
        long startTime = System.nanoTime();
        MetricasMergeSort metricasMerge = MergeSort.sort(copia1, n);
        long endTime = System.nanoTime();

        Metricas resultadoMerge = new Metricas();
        resultadoMerge.tempoDeExecucao = endTime - startTime;
        resultadoMerge.trocas = metricasMerge.trocas;
        resultadoMerge.iteracoes = metricasMerge.iteracoes;

        System.out.println("\n--- Merge Sort (Iterativo) ---");
        imprimirResultados("MergeSort", resultadoMerge, copia1, n);

        // --- Quick Sort ---
        int[] copia2 = clonarArray(original, n);
        startTime = System.nanoTime();
        MetricasQuickSort metricasQuick = QuickSort.sort(copia2, n);
        endTime = System.nanoTime();

        Metricas resultadoQuick = new Metricas();
        resultadoQuick.tempoDeExecucao = endTime - startTime;
        resultadoQuick.trocas = metricasQuick.trocas;
        resultadoQuick.iteracoes = metricasQuick.iteracoes;

        System.out.println("\n--- Quick Sort (Iterativo) ---");
        imprimirResultados("QuickSort", resultadoQuick, copia2, n);

        // --- Radix Sort ---
        int[] copia3 = clonarArray(original, n);
        startTime = System.nanoTime();
        MetricasRadixSort metricasRadix = RadixSort.sort(copia3, n);
        endTime = System.nanoTime();

        Metricas resultadoRadix = new Metricas();
        resultadoRadix.tempoDeExecucao = endTime - startTime;
        resultadoRadix.trocas = metricasRadix.trocas;
        resultadoRadix.iteracoes = metricasRadix.iteracoes;

        System.out.println("\n--- Radix Sort ---");
        imprimirResultados("RadixSort", resultadoRadix, copia3, n);

        System.out.println("\n--- Dados para Gráficos  ---");
        System.out.println("Algoritmo | Tempo(ns) | Trocas | Iteracoes");
        System.out.println("MergeSort | " + resultadoMerge.tempoDeExecucao + " | " + resultadoMerge.trocas + " | " + resultadoMerge.iteracoes);
        System.out.println("QuickSort | " + resultadoQuick.tempoDeExecucao + " | " + resultadoQuick.trocas + " | " + resultadoQuick.iteracoes);
        System.out.println("RadixSort | " + resultadoRadix.tempoDeExecucao + " | " + resultadoRadix.trocas + " | " + resultadoRadix.iteracoes);
    }

    public static int[] clonarArray(int[] original, int n) {
        int[] copia = new int[n];
        for (int i = 0; i < n; i++) {
            copia[i] = original[i];
        }
        return copia;
    }

    public static void imprimirResultados(String nome, Metricas r, int[] arr, int n) {
        System.out.println("Resultados para: " + nome);
        System.out.println("  - Tempo de Execução: " + r.tempoDeExecucao + " nanossegundos");
        System.out.println("  - Número de Trocas/Movimentações: " + r.trocas);
        System.out.println("  - Número de Iterações: " + r.iteracoes);
        System.out.print("  - Array Ordenado: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }
}