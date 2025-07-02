public class QuickSort {
    private static long partition(int[] arr, int baixo, int alto, MetricasQuickSort metricas) {
        long trocasParticao = 0;
        int pivo = arr[alto];
        int i = (baixo - 1);

        for (int j = baixo; j < alto; j++) {
            if (arr[j] <= pivo) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                trocasParticao++;
                metricas.trocas++;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        trocasParticao++;
        metricas.trocas++;

        return i + 1;
    }

    public static MetricasQuickSort sort(int[] arr, int n) {
        MetricasQuickSort metricas = new MetricasQuickSort();
        metricas.trocas = 0;
        metricas.iteracoes = 0;

        if (n <= 1) return metricas;

        int[] pilha = new int[n];

        int topo = -1;

        pilha[++topo] = 0;
        pilha[++topo] = n - 1;

        while (topo >= 0) {
            metricas.iteracoes++;
            int alto = pilha[topo--];
            int baixo = pilha[topo--];

            int pivoIndex = (int)partition(arr, baixo, alto, metricas);

            if (pivoIndex - 1 > baixo) {
                pilha[++topo] = baixo;
                pilha[++topo] = pivoIndex - 1;
            }

            if (pivoIndex + 1 < alto) {
                pilha[++topo] = pivoIndex + 1;
                pilha[++topo] = alto;
            }
        }
        return metricas;
    }
}