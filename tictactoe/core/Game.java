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

import tictactoe.ai.AIPlayer;

public class Game {

    private Board board;
    private final AIPlayer playerX;
    private final AIPlayer playerO;

    private static final char X = 'X';
    private static final char O = 'O';

    public Game(AIPlayer playerX, AIPlayer playerO) {
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
    }

    public void run() {
        printInitialIntro();

        AIPlayer current = playerX;
        char symbol = X;
        GameResult result = GameResult.ANDAMENTO;

        while (result == GameResult.ANDAMENTO) {

            Move move = obterJogadaDaIA(current, symbol);

            if (!isValidMove(move)) {
                anunciarJogadaInvalida(current, move);
                result = (symbol == X) ? GameResult.ILLEGAL_MOVE_BY_X : GameResult.ILLEGAL_MOVE_BY_O;
                break;
            }

            aplicarJogada(move, symbol);
            imprimirTabuleiro(current, symbol);

            result = verificarEstadoDaPartida(symbol);
            if (result != GameResult.ANDAMENTO)
                break;

            esperarProximaJogada();
            current = trocarJogador(current);
            symbol = (symbol == X ? O : X);
        }

        imprimirResultadoFinal(result);
    }

    /** Obtém a jogada da IA com proteção de exceções */
    private Move obterJogadaDaIA(AIPlayer player, char symbol) {
        try {
            return player.chooseMove(board.copy(), symbol);
        } catch (Exception e) {
            System.out.println("Exceção na IA " + player.getName() + ": " + e.getMessage());
            return null;
        }
    }

    private void aplicarJogada(Move move, char symbol) {
        board = board.withMove(move, symbol);
    }

    private GameResult verificarEstadoDaPartida(char symbol) {
        if (hasWon(symbol))
            return (symbol == X ? GameResult.X_WINS : GameResult.O_WINS);

        if (boardIsFull())
            return GameResult.EMPATE;

        return GameResult.ANDAMENTO;
    }

    /** Troca player X ↔ O */
    private AIPlayer trocarJogador(AIPlayer atual) {
        return (atual == playerX) ? playerO : playerX;
    }

    private void printInitialIntro() {
        System.out.println("Iniciando combate:");
        System.out.println("IA 1 (X): " + playerX.getName());
        System.out.println("IA 2 (O): " + playerO.getName());
        System.out.println("O jogo começará em 3 segundos...");
        sleep3s();
    }

    private void anunciarJogadaInvalida(AIPlayer ia, Move move) {
        System.out.println("Jogada inválida da IA " + ia.getName() + " -> " + move);
    }

    private void imprimirTabuleiro(AIPlayer ia, char symbol) {
        System.out.println();
        System.out.println("Jogada de " + ia.getName() + " (" + symbol + "):");
        System.out.println(board);
    }

    private void imprimirResultadoFinal(GameResult result) {
        System.out.println();
        switch (result) {
            case X_WINS:
                System.out.println("Resultado: X venceu. IA: " + playerX.getName());
                break;
            case O_WINS:
                System.out.println("Resultado: O venceu. IA: " + playerO.getName());
                break;
            case EMPATE:
                System.out.println("Resultado: Empate.");
                break;
            case ILLEGAL_MOVE_BY_X:
                System.out.println("Resultado: Jogada ilegal por X. Vencedor: O (" + playerO.getName() + ")");
                break;
            case ILLEGAL_MOVE_BY_O:
                System.out.println("Resultado: Jogada ilegal por O. Vencedor: X (" + playerX.getName() + ")");
                break;
            default:
                System.out.println("Resultado: Partida finalizada.");
        }
    }

    private void esperarProximaJogada() {
        System.out.println("\nPróxima jogada em 3 segundos...");
        sleep3s();
    }

    private void sleep3s() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // validações

    private boolean isValidMove(Move m) {
        if (m == null)
            return false;
        int r = m.getLinha();
        int c = m.getColuna();

        return r >= 0 && r < Board.SIZE &&
                c >= 0 && c < Board.SIZE &&
                board.isEmptyAt(r, c);
    }

    private boolean boardIsFull() {
        for (int i = 0; i < Board.SIZE; i++)
            for (int j = 0; j < Board.SIZE; j++)
                if (board.isEmptyAt(i, j))
                    return false;
        return true;
    }

    private boolean hasWon(char symbol) {
        return venceuLinha(symbol) || venceuColuna(symbol) || venceuDiagonal(symbol);
    }

    private boolean venceuLinha(char s) {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean ok = true;
            for (int j = 0; j < Board.SIZE; j++)
                if (board.getCell(i, j) != s) {
                    ok = false;
                    break;
                }
            if (ok)
                return true;
        }
        return false;
    }

    private boolean venceuColuna(char s) {
        for (int j = 0; j < Board.SIZE; j++) {
            boolean ok = true;
            for (int i = 0; i < Board.SIZE; i++)
                if (board.getCell(i, j) != s) {
                    ok = false;
                    break;
                }
            if (ok)
                return true;
        }
        return false;
    }

    private boolean venceuDiagonal(char s) {
        boolean ok = true;
        for (int i = 0; i < Board.SIZE; i++)
            if (board.getCell(i, i) != s) {
                ok = false;
                break;
            }
        if (ok)
            return true;

        ok = true;
        for (int i = 0; i < Board.SIZE; i++)
            if (board.getCell(i, Board.SIZE - 1 - i) != s) {
                ok = false;
                break;
            }

        return ok;
    }
}
