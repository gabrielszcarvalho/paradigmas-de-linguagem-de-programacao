// Prática 5b - Um canal sem ninguém do outro lado
// Cole em https://onecompiler.com/go
package main

import "fmt"

func main() {
    ch := make(chan int)                     // canal SEM espaço (sem buffer)
    ch <- 1                                  // envia... e espera alguém receber
    fmt.Println(<-ch)
}
