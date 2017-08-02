# startup-alimentos
Aplicação web para gerir o negócio de uma startup do ramo de alimentos

**Pré-requisitos:** <br/>
Java 8, MongoDB e angular/cli

**No MongoDB criar os documentos como valor de entreda:**<br/>
db.ingrediente.insertMany( [{tipo: "Alface", quantidade: 1, valor: "0.40"},<br/>
{tipo: "Bacon", quantidade: 1, valor: "2.00"},<br/>
{tipo: "Hambúrguer de carne", quantidade: 1, valor: "3.00"},<br/>
{tipo: "Ovo", quantidade: 1, valor: "0.80"},<br/>
{tipo: "Queijo", quantidade: 1, valor: "1.50"}<br/>
] );

db.lanche.insertMany( [{ nome: "X-Bacon", quantidade: 1, ingredientes: [{tipo: "Bacon", quantidade: 1},{tipo: "Hambúrguer de carne", quantidade: 1},{tipo: "Queijo", quantidade: 1}], valor: "6.50"},<br/>
{ nome: "X-Burger", quantidade: 1, ingredientes: [{tipo: "Hambúrguer de carne", quantidade: 1},{tipo: "Queijo", quantidade: 1}], valor: "4.50"},<br/>
{ nome: "X-Egg", quantidade: 1, ingredientes: [{tipo: "Ovo", quantidade: 1},{tipo: "Hambúrguer de carne", quantidade: 1},{tipo: "Queijo", quantidade: 1}], valor: "5.30"},<br/>
{ nome: "X-Egg Bacon", quantidade: 1, ingredientes: [{tipo: "Ovo", quantidade: 1},{tipo: "Bacon", quantidade: 1},{tipo: "Hambúrguer de carne", quantidade: 1},{tipo: "Queijo", quantidade: 1}], valor: "7.30"}<br/>
] );

**Build:**
1. *Instale e configure os pré-requisitos.*<br/>
2. Faça um *Maven install* do projeto frontend.<br/>
3. Faça um *Maven install* do projeto backend. Nesta ordem porque este tem dependência do projeto frontend.<br/>
4. *Inicializa* o MongoDB.<br/>
5. Rode o projeto backend com: *Spring Boot App*.<br/>
6. Rode o projeto frontend com: *npm start* no diretório do projeto frontend. <br/>
7. Abra o *localhost:4200* no Chrome.
