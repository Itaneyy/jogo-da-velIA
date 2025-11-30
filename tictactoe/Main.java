package tictactoe;

import tictactoe.ai.AIPlayer;
import tictactoe.ai.IaNey;
import tictactoe.ai.RandomAI;
import tictactoe.battle.BattleController;


public class Main {
    public static void main(String[] args) {
        // Instancie  aqui as IAs desejadas (ex.: IAJoao, IAMaria)
        
        AIPlayer ia = new RandomAI("Random-A");
        AIPlayer ia2 = new RandomAI("Random-B");
        AIPlayer ita = new IaNey("IANey");

       


        // Inicia combate manual
        BattleController controller = new BattleController(ita,ia2);
        controller.startBattle();
    
    }
}
