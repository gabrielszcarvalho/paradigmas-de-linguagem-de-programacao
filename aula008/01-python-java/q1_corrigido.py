def adicionar(item, lista=None):
    if lista is None:
        lista = []
    lista.append(item)
    return lista

print(adicionar(1))
print(adicionar(2))
