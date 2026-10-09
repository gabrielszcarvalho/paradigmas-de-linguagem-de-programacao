// Prática 5a - Passagem de mensagens com canais: grupo de trabalhadores (Sebesta 13.5, p. 559-560)
// Cole em https://onecompiler.com/go
package main

import (
    "fmt"
    "sync"
    "time"
)

const trabalhadores = 3                      // mude para 1 e depois para 6

func trabalhador(id int, tarefas <-chan int, wg *sync.WaitGroup) {
    defer wg.Done()
    for t := range tarefas {                 // recebe tarefas até o canal ser fechado
        time.Sleep(100 * time.Millisecond)   // simula um trabalho de 100 ms
        fmt.Printf("trabalhador %d fez a tarefa %d\n", id, t)
    }
}

func main() {
    tarefas := make(chan int, 6)             // canal com espaço para 6 mensagens
    var wg sync.WaitGroup
    inicio := time.Now()
    for id := 1; id <= trabalhadores; id++ {
        wg.Add(1)
        go trabalhador(id, tarefas, &wg)     // goroutine: uma tarefa concorrente leve
    }
    for t := 1; t <= 6; t++ {
        tarefas <- t                         // envia uma mensagem
    }
    close(tarefas)
    wg.Wait()                                // espera todos os trabalhadores
    fmt.Println(trabalhadores, "trabalhadores, 6 tarefas de 100 ms: levou",
        time.Since(inicio).Round(10*time.Millisecond))
}
