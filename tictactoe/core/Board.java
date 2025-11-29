/********************************************************************************************
 *  AVISO IMPORTANTE — NÃO ALTERAR ESTE ARQUIVO
 *
 *  Este arquivo faz parte da ENGINE OFICIAL do desafio de IA de Jogo da Velha.
 *  Os alunos NÃO estão autorizados a modificar:
 *
 *      ✔ Lógica interna
 *      ✔ Validações
 *      ✔ Alternância de jogadores
 *      ✔ Regras de vitória/empate
 *      ✔ Controle de sleep/tempo
 *
 *  Você só deve implementar classes dentro de:  tictactoe.ai
 *
 *  Qualquer alteração neste arquivo invalida o desafio.
 ***********/

package tictactoe.core;

import java.util.Arrays;

public final class Board {

    public static final int SIZE = 3;
    private final char[][] cells; // Matriz do tabuleiro ('X', 'O' ou ' ')

    public Board() {
        this.cells = tabuleiroVazio();
    }

    /**
     * Construtor privado usado para cópia profunda (deep copy).
     * Garante que o novo Board não compartilha a mesma matriz do original.
     */
    private Board(char[][] source) {
        this.cells = deepCopy(source);
    }

    /**
     * Retorna uma cópia independente do tabuleiro atual.
     *
     */
    public Board copy() {
        return new Board(this.cells);
    }

    public char getCell(int linha, int coluna) {
        if (linha < 0 || linha >= SIZE || coluna < 0 || coluna >= SIZE)
            return '?';
        return cells[linha][coluna];
    }

    public Board withMove(Move m, char symbol) {
        char[][] novo = deepCopy(this.cells);
        aplicarJogada(novo, m, symbol);
        return new Board(novo);
    }

    public boolean isEmptyAt(int linha, int coluna) {
        return getCell(linha, coluna) == ' ';
    }

    /**
     * Gera representação textual do tabuleiro,
     * usada para impressões e debugging.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(' ').append(cells[i][j]).append(' ');
                if (j < SIZE - 1)
                    sb.append('|');
            }
            if (i < SIZE - 1)
                sb.append('\n').append("-----------").append('\n');
        }
        return sb.toString();
    }

    // MÉTODOS UTILITÁRIOS

    /**
     * Cria e retorna um novo tabuleiro totalmente vazio.
     * Cada célula é preenchida com ' '.
     */
    private static char[][] tabuleiroVazio() {
        char[][] t = new char[SIZE][SIZE];
        for (int linha = 0; linha < SIZE; linha++) {
            Arrays.fill(t[linha], ' ');
        }
        return t;
    }

    /**
     * Cria uma cópia profunda (deep copy) da matriz origem.
     * Nenhuma linha ou célula é compartilhada com a matriz original.
     */
    private static char[][] deepCopy(char[][] origem) {
        char[][] copia = new char[SIZE][SIZE];
        for (int linha = 0; linha < SIZE; linha++) {
            for (int coluna = 0; coluna < SIZE; coluna++) {
                copia[linha][coluna] = origem[linha][coluna];
            }
        }
        return copia;
    }

    /**
     * Aplica a jogada na matriz fornecida.
     * Este método existe para isolar a lógica de escrita.
     */
    private static void aplicarJogada(char[][] matriz, Move m, char symbol) {
        matriz[m.getLinha()][m.getColuna()] = symbol;
    }
}
