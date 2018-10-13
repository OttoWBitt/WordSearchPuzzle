Processo de seleção Studio Sol - Suporte Tecnico
Candidato: Otto Wilke Diniz Mariani Bittencourt
Email: ottowbittencourt@gmail.com

Para se solucinar o problema dado, usei 2 matrizes, uma com a entrada de referencia, e outra para marcar com 0 ou 1 uma ocorrencia da palavra buscada.
O metodo de verificação de ocorrencia percorre toda a matriz de acordo com sua função(Horizontal,Vertical,DiagonalEsquerda ou Diaagonal Direita),
e quando acha uma correspondencia, que e marcado pelo numero de letras iguais da palavra em conjunto com a matriz de referencia, 
as coordenadas das letras são colocadas como 0 ou 1 na matriz resposta. 
Apos o final das verificaçoes, sao mostrados na tela somente a posição das palavras correspondentes, que são "traduzidas" por comparação entres 
as matrizes, quando na matrizResposta se tem o numero 1, a mesma posiçao da matriz referencia e mostrada, senão um * e mostrado.

Para se executar o programa, deixe os arquivos findPuzzle.java e Ipunt.java na mesma pasta, pois o findPuzzle.java faz a leitra da matriz
de referencia do Input.txt. A execução do codigo se da como outro qualquer, se as condiçoes forem atendidas.
javac findPuzzle.java
java findPuzzle

Apos se executar as pesquisas, digite FIM para finalizar a execuçao do programa.
 
Se for nescessario o teste com outras matrizes, a nova matriz de referencia dever ser inserida no Input.txt de forma em que as linha virem colunas 
com um e somente um caracter por linha:
a b c d 
e f g h 
   |
   |
   |
  \ /
 
   b
   c
   d
   e
   f
   g
   h
  
 