4)	O que é Deadlock? Detalhe um pouco sobre o caso e como você poderia resolver isso. (5 pontos)

R: 

É sememlhante a fila que estamos acostumados a ver caso C.
Um processo de bloqueio, de espera ou de interrupção em processos diferentes.
Para evitar isso temos que nos orientar sobre LOOPS e tranzações remetidas em SGBD irregular. 


5)	Uma das grandes inclusões no Java 8 foi a API Stream. 
Com ela podemos fazer diversas operações de loop, filtros, maps, etc.
 Porém, existe uma variação bem interessante do Stream que é ParallelStreams. 
 Descreva com suas palavras quando qual é a diferença entre os dois e quando devemos utilizar cada um deles. (5 pontos)

R:

Maneira aleatória de separar o processo e dividir o trafego e o processo de dados e informações.
A API Stream é uma maneira de filtrar dados e processar e retornalas de acordo com a necessidade, trazendo assim
somente o necessário, fazendo o processo de API's um tanto mais rápido, algo que dependendo da estrutura e configuração, 
tornava o processo do Java na JVM um tanto demorado.
Resumindo o entendimento que ParallelStreams, quebra o processo em várias partes e usa um Thread para anexar os pedaços 
e excutar a palicção de modo mais eficiente.