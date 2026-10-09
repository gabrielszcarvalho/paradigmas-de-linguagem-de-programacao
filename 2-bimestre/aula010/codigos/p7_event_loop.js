// Prática 7 - JavaScript: uma única thread e um laço de eventos
// Cole em https://onecompiler.com/nodejs
console.log("A");
setTimeout(() => console.log("B"), 0);
Promise.resolve().then(() => console.log("C"));
console.log("D");

function ocupado(ms) {                       // laço que NÃO devolve o controle
  const fim = Date.now() + ms;
  while (Date.now() < fim) { }
}

setTimeout(() => {
  const t0 = Date.now();
  setTimeout(() => console.log("E: timer de 10 ms rodou depois de " + (Date.now() - t0) + " ms"), 10);
  ocupado(300);                              // trava a única thread por 300 ms
}, 0);
