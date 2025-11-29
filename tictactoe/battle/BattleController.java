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
package tictactoe.battle;

import tictactoe.ai.AIPlayer;
import tictactoe.core.Game;

public class BattleController {
    private final AIPlayer ia1;
    private final AIPlayer ia2;

    public BattleController(AIPlayer ia1, AIPlayer ia2) {
        this.ia1 = ia1;
        this.ia2 = ia2;
    }

    public void startBattle() {
        Game game = new Game(ia1, ia2);
        game.run();
    }
}
