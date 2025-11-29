package tictactoe.ai;

import tictactoe.core.Board;
import tictactoe.core.Move;

public interface AIPlayer {
    Move chooseMove(Board board, char mySymbol);

    String getName();
}
