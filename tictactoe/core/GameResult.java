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

public enum GameResult {
    ANDAMENTO,
    X_WINS,
    O_WINS,
    EMPATE,
    ILLEGAL_MOVE_BY_X,
    ILLEGAL_MOVE_BY_O
}
