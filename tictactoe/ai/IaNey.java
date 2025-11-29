package tictactoe.ai;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import tictactoe.core.Board;
import tictactoe.core.Move;

public class IaNey implements AIPlayer {
    private final String name;
    private Strategie strategie;
    public Board tabuleiroPrevio = null;
    int index = 0;

    public IaNey(String name) {
        this.name = name;
    }

    @Override
    public Move chooseMove(Board board, char mySymbol) {
        index++;
        return new Move(1, 2);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void updateProfile(Board board, char mySymbol) {
        if (tabuleiroPrevio == null) {
            tabuleiroPrevio = board.copy();
            return;
        }

    }

    private void whosFirst() {
        if (emptyPlaces() == 9) {
            strategie = new StrategieX();
            return;
        }
        strategie = new StrategieO();

    }

    protected int emptyPlaces() {
        int size = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                char c = tabuleiroPrevio.getCell(i, j);
                if (c == ' ') {
                    size++;
                }
            }
        }
        return size;

    }

    protected List<Move> casasVazias(Board tabuleiro) {
        List<Move> movimentos = new ArrayList<>();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {

                // Se a célula está vazia, adiciona
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

                // Se NÃO estiver vazio e NÃO for meu símbolo → é do oponente
                if (cell != ' ' && cell != mySymbol) {
                    movimentos.add(new Move(i, j));
                }
            }
        }

        return movimentos;
    }

    private interface Strategie {

    }

    private class StrategieX implements Strategie {

        public Move jogadaInicio() {

            if (conerIsAble()) {

                Move[] jogadas = {
                        new Move(0, 0),
                        new Move(0, 2),
                        new Move(2, 2),
                        new Move(2, 0)
                };

                List<Move> lista = casasVazias(tabuleiroPrevio);

                // comparação manual — funciona sem mexer em Move
                for (Move canto : jogadas) {
                    for (Move livre : lista) {
                        if (livre.getLinha() == canto.getLinha() &&
                                livre.getColuna() == canto.getColuna()) {

                            return canto;
                        }
                    }
                }
            }

            // fallback caso nenhum canto esteja disponível
            return casasVazias(tabuleiroPrevio).get(0);
        }

        private Move jogadaMedia() {
            if (middleIsAble()) {
                return new Move(1, 1);
            }
            return jogadaInicio();
        }

        private Boolean middleIsAble() {

            Move desejado = new Move(1, 1);

            for (Move livre : casasVazias(tabuleiroPrevio)) {
                if (livre.getLinha() == 1 && livre.getColuna() == 1) {
                    return true;
                }
            }

            return false;
        }

        private Boolean conerIsAble() {

            Move[] cantos = {
                    new Move(0, 0),
                    new Move(0, 2),
                    new Move(2, 2),
                    new Move(2, 0)
            };

            List<Move> lista = casasVazias(tabuleiroPrevio);

            for (Move canto : cantos) {
                for (Move livre : lista) {
                    if (livre.getLinha() == canto.getLinha() &&
                            livre.getColuna() == canto.getColuna()) {

                        return true;
                    }
                }
            }

            return false;
        }
    }

    public Move jogadaInicio() {

        if (conerIsAblle()) {

            Move[] jogadas = {
                    new Move(0, 0),
                    new Move(0, 2),
                    new Move(2, 2),
                    new Move(2, 0)
            };

            List<Move> lista = casasVazias(tabuleiroPrevio);

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i) == (jogadas[i])) {
                    return jogadas[i];
                }
            }

        }
        return casasVazias(tabuleiroPrevio).get(0);
    }

    private Move jogadaMedia() {
        if (middleIsAbble()) {
            return new Move(1, 1);
        } else {
            jogadaInicio();
        }
        return new Move(index, index);

    }

    private Move defense(Board tabuleiro, char mySymbol) {
        Move[][] perigo = {
                { new Move(0, 0), new Move(0, 2) },
                { new Move(1, 0), new Move(0, 2) },
                { new Move(2, 0), new Move(0, 2) },
                { new Move(0, 0), new Move(0, 2) },
                { new Move(0, 0), new Move(2, 2) }
        };

        List<Move> oponente = mapOponente(tabuleiro, mySymbol);
        for (Move[] listaMove : perigo) {
            for (Move elemento : listaMove) {
                if (oponente.contains(elemento)) {
                }
            }
        }

        return new Move(index, index);
    }

    private Boolean middleIsAbble() {
        List<Move> lista = casasVazias(tabuleiroPrevio);
        Move middle = new Move(1, 1);
        boolean gatilho = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.contains(middle)) {
                gatilho = true;
                break;
            }
        }
        return gatilho;

    }

    private Boolean conerIsAblle() {
        Boolean gatilho = false;

        Move[] jogadas = {
                new Move(0, 0),
                new Move(0, 2),
                new Move(2, 2),
                new Move(2, 0)
        };

        List<Move> lista = casasVazias(tabuleiroPrevio);

        for (int i = 0; i < jogadas.length; i++) {
            if (lista.contains(jogadas[i])) {
                gatilho = true;
            }
        }

        return gatilho;

    }

    private class StrategieO implements Strategie {


    }
}
