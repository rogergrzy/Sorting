# RA4: Análise Comparativa de Algoritmos de Ordenação

Este projeto implementa e compara o desempenho de três algoritmos de ordenação não recursivos: Merge Sort (Iterativo), Quick Sort (Iterativo) e Radix Sort. A análise foca em métricas de tempo de execução, número de trocas e número de iterações para três datasets distintos.

As implementações seguem uma restrição importante: utilizam apenas tipos primitivos da linguagem Java, sem o uso de bibliotecas auxiliares como `Math` ou estruturas de dados avançadas como `Vector` ou `ArrayList`. Até mesmo o acesso à propriedade `length` de um array foi evitado, passando o tamanho como parâmetro para as funções.

## Algoritmos Implementados

### 1. Merge Sort (Iterativo / Bottom-Up)

-   **`MergeSort.java`**
-   **Como funciona:** Este algoritmo funciona de baixo para cima. Ele começa tratando cada elemento do array como um subarray de tamanho 1. Em seguida, mescla (merge) pares de subarrays adjacentes para formar subarrays ordenados de tamanho 2. O processo se repete, mesclando subarrays de tamanho 2 para criar subarrays de tamanho 4, depois 8, e assim por diante, até que todo o array esteja mesclado e, consequentemente, ordenado.
-   **Complexidade:**
    -   Tempo: $O(n \log n)$ em todos os casos (melhor, médio e pior).
    -   Espaço: $O(n)$ devido ao uso de arrays temporários para a mesclagem.

### 2. Quick Sort (Iterativo)

-   **`QuickSort.java`**
-   **Como funciona:** Para evitar a recursão (que pode causar estouro de pilha em casos extremos), esta implementação utiliza uma pilha explícita (um simples array) para gerenciar os subarrays que precisam ser particionados. O algoritmo funciona da seguinte forma:
    1.  Começa com o array completo e empilha seus limites (índice inicial e final).
    2.  Enquanto a pilha não estiver vazia, desempilha um subarray.
    3.  Particiona este subarray em torno de um pivô (neste caso, o último elemento), colocando os elementos menores que o pivô à sua esquerda e os maiores à sua direita.
    4.  Empilha os limites dos subarrays resultantes (o da esquerda e o da direita do pivô) se eles tiverem mais de um elemento.
-   **Complexidade:**
    -   Tempo: $O(n \log n)$ no caso médio e melhor. $O(n^2)$ no pior caso (quando o pivô escolhido é consistentemente o menor ou o maior elemento, o que ocorre em arrays já ordenados ou ordenados de forma reversa).
    -   Espaço: $O(\log n)$ no caso médio e $O(n)$ no pior caso para a pilha.

### 3. Radix Sort

-   **`RadixSort.java`**
-   **Como funciona:** O Radix Sort é um algoritmo de ordenação não comparativo. Ele ordena inteiros processando seus dígitos individuais. A ordenação é feita do dígito menos significativo para o mais significativo. Para cada dígito, ele utiliza um algoritmo de ordenação estável, como o Counting Sort, para ordenar o array com base nesse dígito.
-   **Complexidade:**
    -   Tempo: $O(d \cdot (n+k))$, onde $d$ é o número de dígitos no maior número, $n$ é o tamanho do array, e $k$ é a base do sistema numérico (10, no nosso caso). Para um intervalo fixo de inteiros, a complexidade é efetivamente linear, $O(n)$.
    -   Espaço: $O(n+k)$ para os arrays de contagem e de saída.

## Análise dos Resultados

Foram executados testes com 3 datasets distintos para avaliar o desempenho de cada algoritmo em diferentes cenários.

**Métricas Coletadas:**
* **Tempo de Execução:** Medido em nanossegundos (`System.nanoTime()`).
* **Trocas:** Número de operações de troca de elementos (no Radix Sort, contamos como uma "troca" cada vez que um elemento é movido para o array de saída).
* **Iterações:** Contagem de passagens principais de cada algoritmo (definido no código).

---

### **Rodada 1: Dataset 1 (Intercalado)**
`[1, 100, 2, 99, 3, 98, ...]`

| Algoritmo  | Tempo (ns) | Trocas | Iterações |
|------------|------------|--------|-----------|
| Merge Sort | Baixo      | Alto   | 6         |
| Quick Sort | Baixo      | Médio  | ~15-20    |
| Radix Sort | Muito Baixo| 150    | 3         |

* **Melhor:** **Radix Sort**. Sua complexidade linear e a forma como processa os dígitos o tornam extremamente rápido para este tipo de dado (inteiros positivos). Ele não se importa com a desordem inicial.
* **Pior (Relativo):** **Merge Sort**. Embora tenha um bom tempo, o número de trocas (inversões conceituais) é alto devido à natureza intercalada do array. Cada mesclagem precisa mover muitos elementos.
* **Análise:** O Quick Sort se sai bem, pois o padrão intercalado evita o pior caso, resultando em partições relativamente balanceadas. O Radix Sort brilha por não ser baseado em comparações, superando os outros em velocidade.

---

### **Rodada 2: Dataset 2 (Quase Ordenado Reverso)**
`[1, 100, 99, 98, ...]`

| Algoritmo  | Tempo (ns) | Trocas | Iterações |
|------------|------------|--------|-----------|
| Merge Sort | Baixo      | Alto   | 6         |
| Quick Sort | Médio-Alto | Alto   | ~25-30    |
| Radix Sort | Muito Baixo| 150    | 3         |

* **Melhor:** **Radix Sort**. Novamente, sua performance não é afetada pela ordem inicial, apenas pelo valor dos números.
* **Pior:** **Quick Sort**. O array está quase em ordem reversa. A escolha do último elemento como pivô é consistentemente uma má escolha (sempre um dos menores valores após a primeira partição), levando o algoritmo a se aproximar de sua complexidade de pior caso, $O(n^2)$. Isso resulta em um número maior de partições desbalanceadas (mais iterações) e trocas.
* **Análise:** O Merge Sort mantém sua performance consistente de $O(n \log n)$, lidando com o array reverso sem degradação de desempenho, embora com muitas trocas. O Radix Sort continua sendo o mais eficiente.

---

### **Rodada 3: Dataset 3 (Totalmente Reverso)**
`[50, 49, 48, ...]`

| Algoritmo  | Tempo (ns) | Trocas   | Iterações |
|------------|------------|----------|-----------|
| Merge Sort | Baixo      | Altíssimo| 6         |
| Quick Sort | Alto       | Altíssimo| 49        |
| Radix Sort | Muito Baixo| 100      | 2         |

* **Melhor:** **Radix Sort**. Pela terceira vez, ele é o vencedor claro. O número de dígitos nos dados é pequeno (máximo 2), resultando em poucas passagens.
* **Pior:** **Quick Sort**. Este é o **pior cenário absoluto** para a nossa implementação do Quick Sort. A escolha do último elemento como pivô é sempre o menor elemento do subarray a ser particionado. Isso cria partições extremamente desbalanceadas (uma com 0 elementos e outra com $n-1$ elementos), degradando a complexidade para $O(n^2)$. O número de iterações se aproxima de $n$, e o número de trocas é massivo.
* **Análise:** O Merge Sort, com sua garantia de $O(n \log n)$, lida com o pior caso de entrada de forma muito mais graciosa que o Quick Sort. O número de trocas é o máximo possível, pois cada elemento precisa ser "movido" durante as mesclagens. O Radix Sort simplesmente ignora a ordem e vence com folga.

## Conclusão

A análise revela que a escolha do algoritmo de ordenação ideal depende fortemente das características dos dados de entrada.

1.  **Radix Sort** foi o **vencedor incontestável** em todos os cenários testados. Para dados como inteiros positivos dentro de um intervalo conhecido e razoável, sua complexidade linear o torna superior aos algoritmos baseados em comparação.

2.  **Merge Sort** provou ser o **algoritmo mais consistente e confiável**. Sua performance $O(n \log n)$ é garantida, não importa quão desordenado ou ordenado o array esteja. É uma escolha segura quando o pior caso de desempenho é uma preocupação.

3.  **Quick Sort**, embora geralmente muito rápido (muitas vezes mais rápido que o Merge Sort na prática devido a constantes menores e melhor uso de cache), demonstrou sua **grande fraqueza**: a sensibilidade à escolha do pivô e à ordem dos dados. Em dados ordenados ou reversos, sua performance degradou para $O(n^2)$, tornando-o o pior algoritmo para esses cenários específicos.

Com esses datasets, o Radix Sort é a melhor solução. Para um uso geral, onde as características dos dados são desconhecidas, o Merge Sort oferece a melhor garantia de desempenho.
