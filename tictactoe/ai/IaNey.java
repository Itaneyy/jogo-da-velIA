package tictactoe.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tictactoe.core.Board;
import tictactoe.core.Move;

public class IaNey implements AIPlayer {

    private final String name;
    private final Strategie strategie;

    private int index = -1;
    private char mySymbol;
    private char opponent;

    public IaNey(String name) {
        this.name = name;
        this.strategie = new StrategieX();
    }

    @Override
    public Move chooseMove(Board board, char mySymbol) {

        this.mySymbol = mySymbol;
        this.opponent = (mySymbol == 'x') ? 'o' : 'x';

        index++;

        return strategie.minhaJogada(board, index);
    }

    @Override
    public String getName() {
        return this.name;
    }

   
    protected List<Move> casasVazias(Board tabuleiro) {
        List<Move> movimentos = new ArrayList<>();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (tabuleiro.getCell(i, j) == ' ') {
                    movimentos.add(new Move(i, j));
                }
            }
        }
        return movimentos;
    }

    protected List<Move> mapOponente(Board tabuleiro, char mySymbol) {
        List<Move> movimentos = new ArrayList<>();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                char cell = tabuleiro.getCell(i, j);

                if (cell != ' ' && cell != mySymbol) {
                    movimentos.add(new Move(i, j));
                }
            }
        }
        return movimentos;
    }

  
    private interface Strategie {
        Move minhaJogada(Board board, int jogadaIndex);
    }

    private class StrategieX implements Strategie {

        @Override
        public Move minhaJogada(Board board, int i) {

            switch (i) {
                case 0:
                    return jogadaInicio(board);

                case 1:
                    return jogadaGeral(board);

                default:
                    return defense(board);
            }
        }

    
        private Move jogadaInicio(Board board) {

            // tenta centro
            if (middleIsFree(board) && mySymbol == 'O') {
                return new Move(1, 1);
            }

            // tenta canto
            if (cornerIsFree(board)) {

                Move[] cantos = {
                        new Move(0, 0),
                        new Move(0, 2),
                        new Move(2, 2),
                        new Move(2, 0)
                };

                List<Move> livres = casasVazias(board);

                for (Move canto : cantos) {
                    for (Move livre : livres) {
                        if (livre.getLinha() == canto.getLinha() &&
                                livre.getColuna() == canto.getColuna()) {
                            return canto;
                        }
                    }
                }
            }

            // fallback
            return casasVazias(board).get(0);
        }

        private Move jogadaGeral(Board board) {

            if (middleIsFree(board)) {
                return new Move(1, 1);
            }

            return defense(board);
        }

        private boolean middleIsFree(Board board) {
            return board.getCell(1, 1) == ' ';
        }

        private boolean cornerIsFree(Board board) {

            Move[] cantos = {
                    new Move(0, 0),
                    new Move(0, 2),
                    new Move(2, 2),
                    new Move(2, 0)
            };

            List<Move> livres = casasVazias(board);

            for (Move canto : cantos) {
                for (Move livre : livres) {
                    if (livre.getLinha() == canto.getLinha() &&
                            livre.getColuna() == canto.getColuna()) {
                        return true;
                    }
                }
            }
            return false;
        }

        private Move defense(Board board) {

            List<Move> vazias = casasVazias(board);

         
            for (Move m : vazias) {
                if (isWinningMove(board, m.getLinha(), m.getColuna(), opponent)) {
                    return m;
                }
            }

            return atk(board);
        }

        private Move atk(Board board) {

            List<Move> vazias = casasVazias(board);

            for (Move m : vazias) {
                if (isWinningMove(board, m.getLinha(), m.getColuna(), mySymbol)) {
                    return m;
                }
            }

            return chute(board);
        }

        private Move chute(Board board) {
            List<Move> casas = casasVazias(board);

            return casas.get(new Random().nextInt(casas.size()));

        }

        private boolean isWinningMove(Board board, int linha, int coluna, char symb) {

          
            if (!board.isEmptyAt(linha, coluna))
                return false;

            Board hipot = board.withMove(new Move(linha, coluna), symb);

            boolean linhaVence = hipot.getCell(linha, 0) == symb &&
                    hipot.getCell(linha, 1) == symb &&
                    hipot.getCell(linha, 2) == symb;

            boolean colunaVence = hipot.getCell(0, coluna) == symb &&
                    hipot.getCell(1, coluna) == symb &&
                    hipot.getCell(2, coluna) == symb;

            boolean diagPrincipal = (linha == coluna) &&
                    hipot.getCell(0, 0) == symb &&
                    hipot.getCell(1, 1) == symb &&
                    hipot.getCell(2, 2) == symb;

            boolean diagSecundaria = (linha + coluna == 2) &&
                    hipot.getCell(0, 2) == symb &&
                    hipot.getCell(1, 1) == symb &&
                    hipot.getCell(2, 0) == symb;

            return linhaVence || colunaVence || diagPrincipal || diagSecundaria;
        }

    }
}
