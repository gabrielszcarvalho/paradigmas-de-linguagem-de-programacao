# Exercício 1/3 — Qual é a saída?

Para cada trecho: (a) qual é a saída? (b) qual conceito da aula explica?

## 1. Python

```python
def adicionar(item, lista=[]):
    lista.append(item)
    return lista

print(adicionar(1))
print(adicionar(2))
```

### (a) Saída

```
[1]
[1, 2]
```

![Execução no OneCompiler](q1_original_onecompiler.png)

### (b) Conceito: argumento padrão mutável

O valor padrão `lista=[]` é criado uma única vez, quando a função é definida, e não a cada chamada. Por isso a mesma lista é reaproveitada:

- `adicionar(1)` devolve `[1]`
- `adicionar(2)` usa a mesma lista, que vira `[1, 2]`

### Correção

Usar `None` como padrão e criar a lista dentro da função, assim cada chamada ganha uma lista nova:

```python
def adicionar(item, lista=None):
    if lista is None:
        lista = []
    lista.append(item)
    return lista

print(adicionar(1))
print(adicionar(2))
```

```
[1]
[2]
```

![Execução corrigida no OneCompiler](q1_corrigido_onecompiler.png)

## 2. Java

```java
public class Main {
    static void zera(int[] v, int n) {
        v[0] = 0;
        n = 0;
    }

    public static void main(String[] args) {
        int[] v = {5, 5};
        int n = 5;
        zera(v, n);
        System.out.println(v[0] + " " + n);
    }
}
```

### (a) Saída

```
0 5
```

![Execução no OneCompiler](Main_onecompiler.png)

### (b) Conceito: passagem por valor (inclusive de referências)

Em Java tudo é passado por valor. Como `n` é um `int`, a função recebe uma cópia do número, e mudar essa cópia não afeta o `n` do `main`.

Já `v` é uma referência para um array. A função recebe uma cópia da referência, mas ela aponta para o mesmo array, então `v[0] = 0` altera o array original.

No final, `v[0]` vale 0 e `n` continua 5.

Esse código não tem erro: o comportamento é exatamente o que a linguagem define, então não precisa de correção.

---

Arquivos: [q1_original.py](q1_original.py), [q1_corrigido.py](q1_corrigido.py), [Main.java](Main.java)
