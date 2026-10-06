# Exercício 2/3 — Qual é a saída?

Para cada trecho: (a) qual é a saída? (b) qual conceito da aula explica?

## 3. Python

```python
fs = [lambda: i for i in range(3)]
print([f() for f in fs])
```

### (a) Saída

```
[2, 2, 2]
```

![Execução no OneCompiler](q3_original_onecompiler.png)

### (b) Conceito: fechamento (closure) e ligação tardia

As `lambda` não guardam o valor de `i` de cada volta do laço. Todas guardam uma referência para a mesma variável `i`.

Quando as funções são chamadas, o laço já terminou e `i` vale 2. Por isso as três devolvem 2.

### Correção

Capturar o valor de `i` na hora em que cada `lambda` é criada, usando um parâmetro com valor padrão:

```python
fs = [lambda i=i: i for i in range(3)]
print([f() for f in fs])
```

```
[0, 1, 2]
```

![Execução corrigida no OneCompiler](q3_corrigido_onecompiler.png)

## 4. C

```c
#include <stdio.h>

int contador(void) {
    static int n = 0;
    return ++n;
}

int main(void) {
    contador();
    contador();
    printf("%d\n", contador());
    return 0;
}
```

### (a) Saída

```
3
```

![Execução no OneCompiler](q4_onecompiler.png)

### (b) Conceito: variável local `static`

A variável `n` é local (só `contador` enxerga), mas por ser `static` ela não fica na pilha: é inicializada uma única vez e mantém o valor entre as chamadas.

| Chamada | `n` antes | `n` depois |
|---|---|---|
| 1ª | 0 | 1 |
| 2ª | 1 | 2 |
| 3ª | 2 | 3 |

O `printf` mostra o retorno da terceira chamada, que é 3.

Esse código não tem erro: o `static` foi usado de propósito, então não precisa de correção.

---

Arquivos: [q3_original.py](q3_original.py), [q3_corrigido.py](q3_corrigido.py), [q4.c](q4.c)
