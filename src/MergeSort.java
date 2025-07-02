public class MergeSort {
    public static MetricasMergeSort sort(int[] arr, int n) {
        MetricasMergeSort metricas = new MetricasMergeSort();
        metricas.trocas = 0;
        metricas.iteracoes = 0;

        int tamanhoAtual;

        int inicioEsquerda;

        for (tamanhoAtual = 1; tamanhoAtual <= n - 1; tamanhoAtual = 2 * tamanhoAtual) {
            metricas.iteracoes++;
            for (inicioEsquerda = 0; inicioEsquerda < n - 1; inicioEsquerda += 2 * tamanhoAtual) {
                int meio = inicioEsquerda + tamanhoAtual - 1;

                int fimDireita = (inicioEsquerda + 2 * tamanhoAtual - 1 < n - 1)
                        ? inicioEsquerda + 2 * tamanhoAtual - 1
                        : n - 1;

                metricas.trocas += merge(arr, inicioEsquerda, meio, fimDireita, n);
            }
        }
        return metricas;
    }

    private static long merge(int[] arr, int inicio, int meio, int fim, int n) {
        long trocasLocais = 0;

        if (meio >= n-1) return 0;

        int i, j, k;
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (i = 0; i < n1; i++)
            L[i] = arr[inicio + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[meio + 1 + j];

        i = 0;
        j = 0;
        k = inicio;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
                trocasLocais += (n1 - i);
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        return trocasLocais;
    }
}