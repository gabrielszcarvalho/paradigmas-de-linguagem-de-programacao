// Prática 6a - Rust: tentando compartilhar uma variável entre threads
// Cole em https://onecompiler.com/rust
use std::thread;

fn main() {
    let mut contador = 0;
    let t = thread::spawn(|| {
        contador += 1;                       // a thread altera a variável de main
    });
    t.join().unwrap();
    println!("{}", contador);
}
