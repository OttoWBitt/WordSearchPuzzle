/**
 * Processo de seleção Studio Sol - Suporte Tecnico
 * Candidato: Otto Wilke Diniz Mariani Bittencourt
 * Email: ottowbittencourt@gmail.com
 *
 * Para se solucinar o problema dado, usei 2 matrizes, uma com a entrada de referencia, e outra para marcar com 0 ou 1 uma ocorrencia da palavra buscada.
 * O metodo de verificação de ocorrencia percorre toda a matriz de acordo com sua função(Horizontal,Vertical,DiagonalEsquerda ou Diaagonal Direita),
 * e quando acha uma correspondencia, que e marcado pelo numero de letras iguais da palavra em conjunto com a matriz de referencia, 
 * as coordenadas das letras são colocadas como 0 ou 1 na matriz resposta. 
 * Apos o final das verificaçoes, sao mostrados na tela somente a posição das palavras correspondentes, que são "traduzidas" por comparação entres 
 * as matrizes, quando na matrizResposta se tem o numero 1, a mesma posiçao da matriz referencia e mostrada, senão um * e mostrado.
 *
 * Para se executar o programa, deixe os arquivos findPuzzle.java e Ipunt.java na mesma pasta, pois o findPuzzle.java faz a leitra da matriz
 * de referencia do Input.txt. A execução do codigo se da como outro qualquer, se as condiçoes forem atendidas.
 * javac findPuzzle.java
 * java findPuzzle
 *
 * Apos se executar as pesquisas, digite FIM para finalizar a execuçao do programa.
 *
 * Se for nescessario o teste com outras matrizes, a nova matriz de referencia dever ser inserida no Input.txt de forma em que as linha virem colunas 
 * com um e somente um caracter por linha:
 * a b c d 
 * e f g h 
 * 	 |
 * 	 |
 * 	 |
 * 	\ /
 *
 *a
 *b
 *c
 *d
 *e
 *f
 *g
 *h
 * 
 */

import java.io.*;
import java.util.ArrayList;

class puzzle {

	char[][] matriz = new char[15][15];
	int matrixLimit = 15;
	int [][] matrizResposta = new int[15][15];
	int achouHorizontal = 0;
	int achouVertical = 0;
	int achouDiagonal = 0;
	ArrayList<Integer> respx = new ArrayList<Integer>();
	ArrayList<Integer> respy = new ArrayList<Integer>();
	int numeroDeOcorrencias = 0;

	/**
	 * Metodo de leitura do Input.txt para a inicialização das matrizes de referencia e resposta;
	 * @throws Exception Retorno de Erro;
	 */
	public void start () throws Exception {

		String fileName = "Input.txt";
		String line = null;

		FileReader fileReader = new FileReader(fileName);

		BufferedReader bufferedReader = new BufferedReader(fileReader);

		line = bufferedReader.readLine();

		while (line != null) {

			for (int linha = 0; linha < 15; linha++ ) {
				for (int coluna = 0; coluna < 15 ; coluna ++ ) {
					matriz[linha][coluna] = line.charAt(0);
					line = bufferedReader.readLine();
				}
			}
		}
		bufferedReader.close();

		for (int linha = 0; linha < 15; linha++ ) {
			for (int coluna = 0; coluna < 15 ; coluna ++ ) {
				matrizResposta[linha][coluna] = 0;
			}
		}
	}

	/**
	 * Metodo percorre a matriz de referencia letra a letra, e chama os metodos de verificação;
	 * para se verificar alguma ocorrencia da palavra procurada;
	 * @param word Recebimento da palavra a ser procurada;
	 */
	public void searchWord(String word) {

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == word.charAt(0)) {
					checkPositionHorizontal(i, j, word);
					checkPositionVertical(i, j, word);
					checkPositionDiagonalDir(i, j, word);
					checkPositionDiagonalEsq(i, j, word);
				}
			}
		}
	}

	/**
	 * Metodo executa a checagem de ocorrencia da palavra na orientação vertical;
	 * @param linha  coordenada da linha inicial;
	 * @param coluna coordenada da coluna inicial;
	 * @param word   palavra a ser pesquisada;
	 */
	public void checkPositionVertical(int linha, int coluna, String word) {

		int wordLength = word.length();
		int xMax = linha + wordLength;
		ArrayList<Integer> auxX = new ArrayList<Integer>();
		ArrayList<Integer> auxY = new ArrayList<Integer>();
		int auxVertical = 0;

		if (matriz[linha][coluna] == word.charAt(0)) {

			//vertical
			//limite da matriz
			if (xMax <= 15) {
				for (int i = 0; i < wordLength ; i++ ) {
					if (matriz[linha][coluna] == word.charAt(i)) {
						//adiciona coordenadas em arrays auxiliares
						auxX.add(linha);
						auxY.add(coluna);
						linha++;
						auxVertical++;
					} else {
						i = wordLength;
						auxVertical = 0;
						//limpa array se nao houver correspondencia
						auxX.clear();
						auxY.clear();
					}
					if (auxVertical == wordLength) {
						achouVertical = auxVertical;
						//adiciona as coordenadas nos arrays de resposta
						for (int x = 0; x < auxX.size() ; x++ ) {
							respx.add(auxX.get(x));
							respy.add(auxY.get(x));
						}
						break;
					}
				}

				if (achouVertical == wordLength) {
					//transferencia das coordenadas do array de resposta para a matriz de resposta
					for (int j = 0; j < respx.size(); j++ ) {
						int aux = respx.get(j);
						int aux2 = respy.get(j);
						matrizResposta[aux][aux2] = 1;
						numeroDeOcorrencias++;
					}
					respx.clear();
					respy.clear();
				} else if (achouVertical != wordLength) {
					respx.clear();
					respy.clear();
				}
			}
			//fim vertical
		}
	}

	/**
	 * Metodo executa a checagem de ocorrencia da palavra na orientação horizontal;
	 * @param linha  coordenada da linha inicial;
	 * @param coluna coordenada da coluna inicial;
	 * @param word   palavra a ser pesquisada;
	 */
	public void checkPositionHorizontal(int linha, int coluna, String word) {

		int wordLength = word.length();
		int yMax = coluna + wordLength;
		ArrayList<Integer> auxX = new ArrayList<Integer>();
		ArrayList<Integer> auxY = new ArrayList<Integer>();
		int auxHorizontal = 0;

		if (matriz[linha][coluna] == word.charAt(0)) {

			//horizontal
			if (yMax <= 15) {
				for (int i = 0; i < wordLength ; i++ ) {
					if (matriz[linha][coluna] == word.charAt(i)) {
						auxX.add(linha);
						auxY.add(coluna);
						coluna++;
						auxHorizontal++;
					} else {
						i = wordLength;
						auxHorizontal = 0;
						auxX.clear();
						auxY.clear();
					}
					if (auxHorizontal == wordLength) {
						achouHorizontal = auxHorizontal;
						for (int x = 0; x < auxX.size() ; x++ ) {
							respx.add(auxX.get(x));
							respy.add(auxY.get(x));
						}
						break;
					}
				}

				if (achouHorizontal == wordLength) {

					for (int j = 0; j < respx.size(); j++ ) {
						int aux = respx.get(j);
						int aux2 = respy.get(j);
						matrizResposta[aux][aux2] = 1;
						numeroDeOcorrencias++;
					}
					respx.clear();
					respy.clear();
				} else if (achouHorizontal != wordLength) {
					respx.clear();
					respy.clear();
				}
			}
			//fim horizontal
		}
	}

	/**
	 * Metodo executa a checagem de ocorrencia da palavra na diagonal esquerda;
	 * @param linha  coordenada da linha inicial;
	 * @param coluna coordenada da coluna inicial;
	 * @param word   palavra a ser pesquisada;
	 */
	public void checkPositionDiagonalEsq(int linha, int coluna, String word) {

		int wordLength = word.length();
		int yMin = coluna - wordLength + 1;
		int xMin = linha + wordLength;
		ArrayList<Integer> auxX = new ArrayList<Integer>();
		ArrayList<Integer> auxY = new ArrayList<Integer>();
		int auxHorizontal = 0;
		int auxVertical = 0;

		if (matriz[linha][coluna] == word.charAt(0)) {
			//diagonal
			if (yMin >= 0 && xMin <= 15) {
				for (int i = 0; i < wordLength ; i++ ) {
					if (matriz[linha][coluna] == word.charAt(i)) {
						auxX.add(linha);
						auxY.add(coluna);
						coluna--;
						linha++;
						auxHorizontal++;
						auxVertical++;
					} else {
						i = wordLength;
						auxVertical = 0;
						auxHorizontal = 0;
						auxX.clear();
						auxY.clear();
					}
					if (auxHorizontal == wordLength) {
						achouDiagonal = auxHorizontal;
						for (int x = 0; x < auxX.size() ; x++ ) {
							respx.add(auxX.get(x));
							respy.add(auxY.get(x));
						}
						break;
					}
				}

				if (achouDiagonal == wordLength) {

					for (int j = 0; j < respx.size(); j++ ) {
						int aux = respx.get(j);
						int aux2 = respy.get(j);
						matrizResposta[aux][aux2] = 1;
						numeroDeOcorrencias++;
					}
					respx.clear();
					respy.clear();
				} else if (achouDiagonal != wordLength) {
					respx.clear();
					respy.clear();
				}
			}
			//fim diagonal
		}
	}

	/**
	 * Metodo executa a checagem de ocorrencia da palavra na diagonal direita;
	 * @param linha  coordenada da linha inicial;
	 * @param coluna coordenada da coluna inicial;
	 * @param word   palavra a ser pesquisada;
	 */
	public void checkPositionDiagonalDir(int linha, int coluna, String word) {

		int wordLength = word.length();
		int yMax = coluna + wordLength;
		int xMax = linha + wordLength;
		ArrayList<Integer> auxX = new ArrayList<Integer>();
		ArrayList<Integer> auxY = new ArrayList<Integer>();
		int auxHorizontal = 0;
		int auxVertical = 0;

		if (matriz[linha][coluna] == word.charAt(0)) {

			//diagonal
			if (yMax <= 15 && xMax <= 15) {
				for (int i = 0; i < wordLength ; i++ ) {
					if (matriz[linha][coluna] == word.charAt(i)) {
						auxX.add(linha);
						auxY.add(coluna);
						coluna++;
						linha++;
						auxHorizontal++;
						auxVertical++;
					} else {
						i = wordLength;
						auxVertical = 0;
						auxHorizontal = 0;
						auxX.clear();
						auxY.clear();
					}
					if (auxHorizontal == wordLength) {
						achouDiagonal = auxHorizontal;
						for (int x = 0; x < auxX.size() ; x++ ) {
							respx.add(auxX.get(x));
							respy.add(auxY.get(x));
						}
						break;
					}
				}

				if (achouDiagonal == wordLength) {
					for (int j = 0; j < respx.size(); j++ ) {
						int aux = respx.get(j);
						int aux2 = respy.get(j);
						matrizResposta[aux][aux2] = 1;
						numeroDeOcorrencias++;
					}
					respx.clear();
					respy.clear();
				} else if (achouDiagonal != wordLength) {
					respx.clear();
					respy.clear();
				}
			}
			//fim diagonal
		}
	}

	/**
	 * Metodo faz a impressao da matriz resposta final, com as palavras achadas e * no lugar das ignoradas;
	 * @param palavra palavra a ser impressa na tela como indicativo;
	 */
	public void gerarMatrizFinal(String palavra) {
		int ocorrencias = (numeroDeOcorrencias / palavra.length());

		if (ocorrencias > 0) {
			System.out.println( ocorrencias + " ocorrencias para a palavra " + palavra);
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					if (matrizResposta[i][j] == 1) {
						System.out.print(matriz[i][j] + " ");
					} else {
						System.out.print("*" + " ");
					}
				}
				System.out.println();
			}
		}
		else{
			System.out.println("Nenhuma ocorrencia para a palavra "+palavra);
		}
	}

	/**
	 * Metodo nao usado para se imprimir a matriz resposta de 0 ou 1;
	 */
	public void printMatrixResposta() {
		for (int i = 0; i < matrizResposta.length; i++) {
			for (int j = 0; j < matrizResposta[i].length; j++) {
				System.out.print(matrizResposta[i][j] + " ");
			}
			System.out.println();
		}
	}
  	
  	/**
  	 * Metodo nao usado para se imprimir a matriz de referencia;
  	 */
	public void printMatrixReferencia() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}
}
class findPuzzle {

	/**
	 * Metodo realiza a verificação de uma String ser ou nao upperCase;
	 * @param  palavra String a ser verificada;
	 * @return         retorno da resposta boolean;
	 */
	private static boolean isStringUpperCase(String palavra) {

		char[] charArray = palavra.toCharArray();

		for (int i = 0; i < charArray.length; i++) {

			if ( !Character.isUpperCase( charArray[i] )) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Metodo faz a conversão de uma String para UpperCase;
	 * @param  palavra String a ser convertida;
	 * @return         Retorno da String convertida;
	 */
	public static String toUpperCase(String palavra) {

		String resposta = "";

		for (int i = 0; i < palavra.length(); i++) {
			char charAtual = palavra.charAt(i);
			char charAtualToUpperCase = Character.toUpperCase(charAtual);
			resposta = resposta + charAtualToUpperCase;
		}

		return resposta;
	}

	public static void main(String[] args) throws Exception {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader read = new BufferedReader(isr);


		System.out.println("Digite a palavra a ser buscada, ou FIM para finalizar: ");
		String line = line = read.readLine();
		while (!line.equals("FIM")) {

			if (isStringUpperCase(line) == true) {
				puzzle p = new puzzle();
				p.start();
				p.searchWord(line);
				p.gerarMatrizFinal(line);
			} else {
				line = toUpperCase(line);
				puzzle p = new puzzle();
				p.start();
				p.searchWord(line);
				p.gerarMatrizFinal(line);
			}

			System.out.println("Digite a palavra a ser buscada, ou FIM para finalizar: ");
			line = read.readLine();
		}
	}
}