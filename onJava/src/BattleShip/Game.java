package BattleShip;

import java.util.*;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Game {
  public static final Number PlayerNum1 = 2L;
  public static final Number PlayerNum2 = 3L;
  public static final VDMSet playersSet = SetUtil.set(Game.PlayerNum1, Game.PlayerNum2);
  public Player player1;
  public Board myBoard1;
  public Board hisBoard1;
  public Player player2;
  public Board myBoard2;
  public Board hisBoard2;
  public Boolean endGame = false;
  public Number currPlayer;

  public void cg_init_Game_1(
      final String player1_name, final String player2_name, final Number player_mode) {

    player1 = new Player(0L, player1_name, player_mode);
    myBoard1 = player1.myBoard;
    hisBoard1 = player1.hisBoard;
    player2 = new Player(1L, player2_name, player_mode);
    myBoard2 = player2.myBoard;
    hisBoard2 = player2.hisBoard;
    currPlayer = Game.PlayerNum1;
  }

  public Game(final String player1_name, final String player2_name, final Number player_mode) {

    cg_init_Game_1(player1_name, player2_name, player_mode);
  }

  public Number play() {

    if (Utils.equals(currPlayer, Game.PlayerNum1)) {
      if (!(Utils.equals(player1.myMoves.size(), 0L))) {
        {
          final VDMSeq move = player1.move();
          {
            IO.println("Player:" + player1.myName + " attacked Player: " + player2.myName);
            hisBoard1.save(Utils.copy(move), myBoard2.strike(Utils.copy(move)));
            endGame = verifyEndGame(player2.myShips);
            if (endGame) {
              return currPlayer;
            }

            changePlayer();
          }
        }

      } else {
        IO.println("Player:" + player1.myName + " is out of moves!!");
        changePlayer();
      }

    } else if (Utils.equals(currPlayer, Game.PlayerNum2)) {
      if (!(Utils.equals(player2.myMoves.size(), 0L))) {
        {
          final VDMSeq move2 = player2.move();
          {
            IO.println("Player:" + player2.myName + " attacked Player: " + player1.myName);
            hisBoard2.save(Utils.copy(move2), myBoard1.strike(Utils.copy(move2)));
            endGame = verifyEndGame(player1.myShips);
            if (endGame) {
              return currPlayer;
            }

            changePlayer();
          }
        }

      } else {
        IO.println("Player:" + player2.myName + " is out of moves!!");
        changePlayer();
      }
    }

    Boolean andResult_7 = false;

    if (Utils.equals(player1.myMoves.size(), 0L)) {
      if (Utils.equals(player2.myMoves.size(), 0L)) {
        andResult_7 = true;
      }
    }

    if (andResult_7) {
      endGame = true;
      return 4L;
    }

    return 1L;
  }

  public Number startGame() {

    Number winner = -1L;
    Number loser = 30L;
    if (player1.myMoves.size() > player2.myMoves.size()) {
      IO.println("Player " + player1.myName + " has more moves than player " + player2.myName);
      IO.println("Both players must have the same number of move");
      endGame = true;

    } else if (player1.myMoves.size() < player2.myMoves.size()) {
      IO.println("Player " + player2.myName + " has more moves than player " + player1.myName);
      IO.println("Both players must have the same number of move");
      endGame = true;

    } else {
      Boolean whileCond_1 = true;
      while (whileCond_1) {
        whileCond_1 = !(endGame);
        if (!(whileCond_1)) {
          break;
        }

        {
          winner = play();
        }
      }

      if (Utils.equals(winner, 2L)) {
        loser = 3L;

      } else if (Utils.equals(winner, 3L)) {
        loser = 2L;
      }

      if (Utils.equals(winner, 4L)) {
        IO.println("No one won the game. Both players ran out of moves!");
      } else {
        ShowBoards(winner, loser);
      }
    }

    return winner;
  }

  public Player getPlayer(final Number player_num) {

    if (Utils.equals(player_num, 2L)) {
      return player1;
    }

    return player2;
  }

  public void changePlayer() {

    if (Utils.equals(currPlayer, Game.PlayerNum1)) {
      currPlayer = Game.PlayerNum2;

    } else if (Utils.equals(currPlayer, Game.PlayerNum2)) {
      currPlayer = Game.PlayerNum1;
    }
  }

  public void ShowBoards(final Number winner, final Number loser) {

    IO.print("The winner is: ");
    IO.println(getPlayer(winner).myName);
    IO.println("");
    IO.println("Player " + getPlayer(winner).myName + " Board:");
    Interfaces.printMyBoard(getPlayer(winner).myBoard, getPlayer(winner).myBoard.battleShipBoard);
    IO.println("");
    IO.println("Player " + getPlayer(winner).myName + " shot's on enemy's Board:");
    Interfaces.printMyBoard(getPlayer(winner).hisBoard, getPlayer(winner).hisBoard.battleShipBoard);
    IO.println("");
    IO.println("");
    IO.println("Player " + getPlayer(loser).myName + " Board:");
    Interfaces.printMyBoard(getPlayer(loser).myBoard, getPlayer(loser).myBoard.battleShipBoard);
    IO.println("");
    IO.println("Player " + getPlayer(loser).myName + " shot's on enemy's Board:");
    Interfaces.printMyBoard(getPlayer(loser).hisBoard, getPlayer(loser).hisBoard.battleShipBoard);
  }

  public Boolean verifyEndGame(final VDMSeq ships) {

    Boolean forAllExpResult_1 = true;
    VDMSet set_5 = SeqUtil.elems(Utils.copy(ships));
    for (Iterator iterator_5 = set_5.iterator(); iterator_5.hasNext() && forAllExpResult_1; ) {
      Ship ship = ((Ship) iterator_5.next());
      forAllExpResult_1 = ship.getShipStatus();
    }
    return forAllExpResult_1;
  }

  public Game() {}

  public String toString() {

    return "Game{"
        + "PlayerNum1 = "
        + Utils.toString(PlayerNum1)
        + ", PlayerNum2 = "
        + Utils.toString(PlayerNum2)
        + ", playersSet = "
        + Utils.toString(playersSet)
        + ", player1 := "
        + Utils.toString(player1)
        + ", myBoard1 := "
        + Utils.toString(myBoard1)
        + ", hisBoard1 := "
        + Utils.toString(hisBoard1)
        + ", player2 := "
        + Utils.toString(player2)
        + ", myBoard2 := "
        + Utils.toString(myBoard2)
        + ", hisBoard2 := "
        + Utils.toString(hisBoard2)
        + ", endGame := "
        + Utils.toString(endGame)
        + ", currPlayer := "
        + Utils.toString(currPlayer)
        + "}";
  }
  public static void main(String[] args) {
	 
	  Interfaces.printGameInit();
	  System.out.println("");
	  System.out.print("Please enter the name of the player 1: ");
	  
	  Scanner sc = new Scanner(System.in);
	  String player = null;

	  do {  
		  player = sc.nextLine();
		  if(player.isEmpty())
			  System.out.print("Please enter a valid name for the player 1: " );
	  } while(player.isEmpty());
	  System.out.println("Player 1 name -> " + player);
	  System.out.println("");
	  System.out.print("Please enter the name of the player 2: ");
	  
	  Scanner sc2 = new Scanner(System.in);
	  String player2 = null;
	  
	  do {
		  player2 = sc2.nextLine();  
		  if(player2.isEmpty())
			  System.out.print("Please enter a valid name for the player 2: " );  
	  } while(player2.isEmpty());
	  System.out.println("Player 2 name -> " + player2);
	  System.out.println("");
	  
	  System.out.println("Now, let's select the moves mode:");
	  System.out.println("If 0: player 1 wins against player 2.");
	  System.out.println("If 1: player 2 wins against player 1.");
	  System.out.println("If 2: no one wins the game because both players ran out of moves.");
	  System.out.println("If 3: no one wins the game because player 1 has less moves than player 2.");
	  System.out.println("If 4: no one wins the game because player 2 has less moves than player 1.");
	  System.out.println("");
	  
	  Scanner sc3 = new Scanner(System.in);
	  int movesmode = -1;
	  System.out.print("Please enter the moves mode: ");
	  
	  do {
		  movesmode = sc3.nextInt();  
		  if(movesmode != 0 && movesmode != 1 && movesmode != 2 && movesmode != 3 && movesmode != 4)
			  System.out.print("Please enter a valid moves mode: " );  
	  } while(movesmode != 0 && movesmode != 1 && movesmode != 2 && movesmode != 3 && movesmode != 4);
	  System.out.println("Moves mode selected -> " + movesmode);
	  System.out.println("");
	  
	  System.out.println("Let's see the result of the game:");
	  System.out.println("");
	 
	 // Game game = new Game(player, player2, movesmode);
	 // game.startGame();
  }
}
