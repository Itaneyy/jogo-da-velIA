package tictactoe.ai;

//EXEMPLO DE IMPLEMENTAÇÃO DE IA

//Ela escolhe aleatoriamente uma célula vazia.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tictactoe.core.Board;
import tictactoe.core.Move;

public class RandomAI implements AIPlayer {

    private final Random rand = new Random();
    private final String name;

    public RandomAI() {
        this.name = "RandomAI";
    }

    public RandomAI(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move chooseMove(Board board, char mySymbol) {
        List<Move> empties = new ArrayList<>();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.isEmptyAt(i, j))
                    empties.add(new Move(i, j));
            }
        }
        if (empties.isEmpty())
            return null;
        return empties.get(rand.nextInt(empties.size()));
    }
}
