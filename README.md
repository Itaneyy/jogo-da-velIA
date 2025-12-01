# Jogo da VelIA

O objetivo do jogo é apoiar o ensino de orientação a objetos. Este repositório contém a estrutura base do jogo e os alunos devem desenvolver seus módulos de "IA" para realizar o combate entre eles.

*Desenvolvido por: Filipe Fernandes e Itaney Filho*
    
---


##  Documentação Geral do Projeto


### 📁 Estrutura Geral do Projeto

```
tictactoe/
│
├── core/
│   ├── Board.java
│   ├── Game.java
│   ├── GameResult.java
│   └── Move.java
│
├── ai/
│   ├── AIPlayer.java
|   ├── IaNey.java (EXEMPLO DE IMPLEMENTAÇÃO)
│   └──RandomAI.java (EXEMPLO DE IMPLEMENTAÇÃO)
│  
│
├── battle/
│   └── BattleController.java
│
└── Main.java
```

---
#  📂 Pasta **core**

Contém **toda a engine oficial do jogo**, que **não pode ser alterada**.
Ela gerencia:

* Regras
* Turnos
* Validações
* Vitórias/empates
* Segurança contra jogadas inválidas

As IAs dependem dessa pasta para ler o tabuleiro e jogar corretamente.

---

## 📄  **Board.java**

Representa o tabuleiro oficial do jogo.

### Funções importantes:

| Função                   | Descrição                                                  |
| ------------------------ | ---------------------------------------------------------- |
| `getCell(linha, coluna)` | Retorna `'X'`, `'O'` ou `' '`                              |
| `isEmptyAt(l, c)`        | Verifica se a casa está vazia                              |
| `copy()`                 | Cria uma cópia do tabuleiro atual                          |
| `withMove(Move, symbol)` | Retorna uma versão NOVA do tabuleiro com a jogada aplicada |

A IA usa esse arquivo para **analisar e simular jogadas**.

---

## 📄  **Move.java**

Representa uma jogada.

```java
new Move(linha, coluna);
```

A IA **sempre** deve retornar um objeto `Move` válido.

---

## 📄 **Game.java**

Gerencia toda a partida:

* Alterna jogadores
* Valida jogadas
* Finaliza o jogo
* Mostra resultado
* Controla tempo entre jogadas

As IAs não mexem neste arquivo — apenas recebem dados dele.

---

##  📄 **GameResult.java**

Enum com possíveis resultados:

* ANDAMENTO
* X_WINS
* O_WINS
* EMPATE
* ILLEGAL_MOVE_BY_X
* ILLEGAL_MOVE_BY_O

---

#  📂 Pasta **ai**


Único local onde os alunos podem programar.
As IAs são criadas aqui.

---

##  📄 **AIPlayer.java**

Interface-base para todas as IAs.

Cada IA deve implementar:

```java
Move chooseMove(Board board, char mySymbol);
String getName();
```

---

##  📄 **RandomAI.java**

Exemplo simples de IA que joga aleatoriamente.
Serve como referência para aprender:
* como devolver jogadas válidas
* como ler o tabuleiro

---

##  📄 **IaNey.java** 

Exemplo de IA personalizada.

---

#  📂 Pasta **battle**

##  **BattleController.java**

Gerencia batalhas entre duas IAs.

* Recebe IA1 e IA2
* Cria um `Game`
* Executa a partida
* Exibe o resultado final


---

# 📄 **Main.java**

Arquivo principal do programa.

Usado para:

* Escolher quais IAs jogarão
* Iniciar a batalha
* Instanciar suas IA's


---

# O que aluno  o aluno precisa saber para criar sua própria IA

## 1. Entender como ler o tabuleiro
```java
board.getCell(i, j);
board.isEmptyAt(i, j);
```

##  2. Entender como simular jogadas 

```java
//Exemplo - cria uma simulação do tabuleiro com a jogada aplicada
Board futuro = board.withMove(new Move(i,j), mySymbol);

```
Para a IA tomar decisões inteligentes, ela precisa testar jogadas sem alterar o tabuleiro real. A simulação cria uma cópia do tabuleiro com a jogada aplicada, permitindo que a IA avalie cenários futuros, como possíveis vitórias, bloqueios ou estratégias, antes de escolher o movimento definitivo.

## 3. Sempre retornar uma jogada válida

```java
return new Move(linha, coluna);
```

Porém:

* Se a posição estiver fora do tabuleiro
* ou ocupada
* ou a IA retornar null

→ derrota automática.

##  4. Estratégia

O aluno é livre para criar sua estrátegia para o jogo, exeto  copiar as estrátegias  dos exemplos, você pode tentar fazer algo parecido, com sua propria estrátegia seguinto  as regras descritas.

##  5. Saber que o board enviado é uma CÓPIA

A IA não altera o tabuleiro real.
Ela recebe uma visão apenas para análise.

---

