package tictactoe;

import tictactoe.ai.AIPlayer;

import tictactoe.ai.RandomAI;
import tictactoe.battle.BattleController;


public class Main {
    public static void main(String[] args) {
        // Instancie  aqui as IAs desejadas (ex.: IAJoao, IAMaria)
        AIPlayer ia1 = new RandomAI("Random-A");
        AIPlayer ia2 = new RandomAI("Random-B");
       


        // Inicia combate manual
        BattleController controller = new BattleController(ia1, ia2);
        controller.startBattle();
    }
}
