# Prática 2 - Todas as respostas possíveis da Figura 13.1 (Sebesta, p. 548)
# Cole em https://onecompiler.com/python
# TOTAL começa em 3. A tarefa A faz TOTAL + 1 e a tarefa B faz TOTAL * 2.
# Cada tarefa tem 3 passos: 1) ler TOTAL  2) calcular  3) gravar TOTAL.
from itertools import combinations


def executa(ordem):                      # ordem: por exemplo "AABABB"
    total = 3
    reg = {"A": 0, "B": 0}               # o "registrador" de cada tarefa
    passo = {"A": 0, "B": 0}
    for t in ordem:
        if passo[t] == 0:
            reg[t] = total                                    # 1) ler
        elif passo[t] == 1:
            reg[t] = reg[t] + 1 if t == "A" else reg[t] * 2   # 2) calcular
        else:
            total = reg[t]                                    # 3) gravar
        passo[t] += 1
    return total


print("AAABBB ->", executa("AAABBB"))   # A inteira, depois B inteira
print("BBBAAA ->", executa("BBBAAA"))   # B inteira, depois A inteira
print("ABABAB ->", executa("ABABAB"))   # alternando passo a passo

resultados = {}
for posicoes in combinations(range(6), 3):        # onde ficam os 3 passos de A
    ordem = "".join("A" if i in posicoes else "B" for i in range(6))
    resultados.setdefault(executa(ordem), []).append(ordem)

print("Ordens possíveis:", sum(len(v) for v in resultados.values()))
for valor in sorted(resultados):
    ordens = resultados[valor]
    print(f"TOTAL = {valor}: {len(ordens):2d} ordens, por exemplo {ordens[0]}")
