public class RadixSort {
    private static int getMaior(int[] arr, int n) {
        int maior = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > maior) {
                maior = arr[i];
            }
        }
        return maior;
    }

    private static void countingSort(int[] arr, int n, int exp, MetricasRadixSort metricas) {
        int[] output = new int[n];
        int i;
        int[] count = new int[10];

        for(i = 0; i < 10; i++) {
            count[i] = 0;
        }

        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = n - 1; i >= 0; i--) {
            int digitIndex = (arr[i] / exp) % 10;
            output[count[digitIndex] - 1] = arr[i];
            count[digitIndex]--;
            metricas.trocas++;
        }

        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static MetricasRadixSort sort(int[] arr, int n) {
        MetricasRadixSort metricas = new MetricasRadixSort();
        metricas.trocas = 0;
        metricas.iteracoes = 0;

        int maior = getMaior(arr, n);

        for (int exp = 1; maior / exp > 0; exp *= 10) {
            metricas.iteracoes++;
            countingSort(arr, n, exp, metricas);
        }

        return metricas;
    }
}