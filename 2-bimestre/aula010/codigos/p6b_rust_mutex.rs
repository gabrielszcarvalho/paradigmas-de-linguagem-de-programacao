// Prática 6b - Rust: compartilhar com Arc (contagem de referências) e Mutex (exclusão mútua)
// Cole em https://onecompiler.com/rust
use std::sync::{Arc, Mutex};
use std::thread;

fn main() {
    let contador = Arc::new(Mutex::new(0));  // compartilhado e protegido
    let mut threads = Vec::new();
    for _ in 0..4 {
        let c = Arc::clone(&contador);
        threads.push(thread::spawn(move || {
            for _ in 0..100_000 {
                *c.lock().unwrap() += 1;     // trava, soma e destrava
            }
        }));
    }
    for t in threads { t.join().unwrap(); }
    println!("contador = {}", *contador.lock().unwrap());
}
