# Exercício 3/3 — Qual é a saída?

Para cada trecho: (a) qual é a saída? (b) qual conceito da aula explica?

## 5. Rust

```rust
fn dobra(v: Vec<i32>) -> Vec<i32> {
    v.iter().map(|x| x * 2).collect()
}

fn main() {
    let v = vec![1, 2, 3];
    let d = dobra(v);
    println!("{:?} {:?}", v, d);
}
```

### (a) Saída

Não compila:

```
error[E0382]: borrow of moved value: `v`
```

![Execução no OneCompiler](q5_original_onecompiler.png)

### (b) Conceito: posse (ownership) e move

`Vec<i32>` não é copiado automaticamente. Quando o código chama `dobra(v)`, o vetor é movido para a função, que passa a ser a dona dele. Depois disso, `v` não pode mais ser usado no `main`, e o `println!` que tenta usar `v` gera o erro.

### Correção

Emprestar o vetor com uma referência (`&`) em vez de movê-lo. Assim o `main` continua sendo o dono de `v`:

```rust
fn dobra(v: &Vec<i32>) -> Vec<i32> {
    v.iter().map(|x| x * 2).collect()
}

fn main() {
    let v = vec![1, 2, 3];
    let d = dobra(&v);
    println!("{:?} {:?}", v, d);
}
```

```
[1, 2, 3] [2, 4, 6]
```

![Execução corrigida no OneCompiler](q5_corrigido_onecompiler.png)

Também funcionaria `dobra(v.clone())`, mas isso copia o vetor inteiro sem necessidade.

## 6. Python

```python
total = 0

def adiciona(x):
    total = total + x
    return total

print(adiciona(5))
```

### (a) Saída

Erro em tempo de execução:

```
UnboundLocalError: cannot access local variable 'total' where it is not associated with a value
```

![Execução no OneCompiler](q6_original_onecompiler.png)

### (b) Conceito: escopo de variáveis (regra LEGB)

Como existe uma atribuição a `total` dentro da função, o Python trata `total` como variável local de `adiciona`. Na hora de calcular `total + x`, ele tenta ler essa variável local, que ainda não tem valor. O `total = 0` de fora é global e nem chega a ser consultado.

### Correção

Declarar `global total` para a função usar e alterar a variável global:

```python
total = 0

def adiciona(x):
    global total
    total = total + x
    return total

print(adiciona(5))
```

```
5
```

![Execução corrigida no OneCompiler](q6_corrigido_onecompiler.png)

---

Arquivos: [q5_original.rs](q5_original.rs), [q5_corrigido.rs](q5_corrigido.rs), [q6_original.py](q6_original.py), [q6_corrigido.py](q6_corrigido.py)
