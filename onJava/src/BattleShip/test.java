package BattleShip;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class test {
  public static void assertTrue(final Boolean expectedTrue) {

    return;
  }

  public static void Player1Winner() {

    Game game = new Game("manuel", "tiago", 0L);
    Number winner = game.startGame();
    assertTrue(game.endGame);
    assertTrue(Utils.equals(winner, game.PlayerNum1));
  }

  public static void Player2Winner() {

    Game game = new Game("manuel", "tiago", 1L);
    Number winner = game.startGame();
    assertTrue(game.endGame);
    assertTrue(Utils.equals(winner, game.PlayerNum2));
  }

  public static void NoOneWon() {

    Game game = new Game("manuel", "tiago", 2L);
    Number winner = game.startGame();
    assertTrue(game.endGame);
    assertTrue(Utils.equals(winner, 4L));
  }

  public static void LessMovesPlayer1() {

    Game game = new Game("manuel", "tiago", 3L);
    Number winner = game.startGame();
    assertTrue(game.endGame);
    assertTrue(Utils.equals(winner, -1L));
  }

  public static void LessMovesPlayer2() {

    Game game = new Game("manuel", "tiago", 4L);
    Number winner = game.startGame();
    assertTrue(game.endGame);
    assertTrue(Utils.equals(winner, -1L));
  }

  public static void testBoardCells() {

    Board board = new Board(0L);
    assertTrue(
        Utils.equals(
            Board.BoardSize.longValue() * Board.BoardSize.longValue(), board.boardCells.size()));
  }

  public static void testNumberShips() {

    Game game = new Game("manuel", "tiago", 0L);
    VDMSeq player1ships = game.player1.myShips;
    VDMSeq player2ships = game.player2.myShips;
    assertTrue(Utils.equals(player1ships.size() + player2ships.size(), 20L));
  }

  public static void testCoordinates() {

    Game game = new Game("manuel", "tiago", 0L);
    Board player1myBoard = game.myBoard1;
    Board player1hisBoard = game.hisBoard1;
    Board player2myBoard = game.myBoard2;
    Board player2hisBoard = game.hisBoard2;
    Boolean forAllExpResult_2 = true;
    VDMSet set_6 = player1myBoard.boardCells;
    for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext() && forAllExpResult_2; ) {
      CellBoard cell = ((CellBoard) iterator_6.next());
      forAllExpResult_2 =
          CellBoard.verifyCoordinates(
              SeqUtil.conc(SeqUtil.seq(cell.x_coord), SeqUtil.seq(cell.y_coord)));
    }
    assertTrue(forAllExpResult_2);

    Boolean forAllExpResult_3 = true;
    VDMSet set_7 = player1hisBoard.boardCells;
    for (Iterator iterator_7 = set_7.iterator(); iterator_7.hasNext() && forAllExpResult_3; ) {
      CellBoard cell = ((CellBoard) iterator_7.next());
      forAllExpResult_3 =
          CellBoard.verifyCoordinates(
              SeqUtil.conc(SeqUtil.seq(cell.x_coord), SeqUtil.seq(cell.y_coord)));
    }
    assertTrue(forAllExpResult_3);

    Boolean forAllExpResult_4 = true;
    VDMSet set_8 = player2myBoard.boardCells;
    for (Iterator iterator_8 = set_8.iterator(); iterator_8.hasNext() && forAllExpResult_4; ) {
      CellBoard cell = ((CellBoard) iterator_8.next());
      forAllExpResult_4 =
          CellBoard.verifyCoordinates(
              SeqUtil.conc(SeqUtil.seq(cell.x_coord), SeqUtil.seq(cell.y_coord)));
    }
    assertTrue(forAllExpResult_4);

    Boolean forAllExpResult_5 = true;
    VDMSet set_9 = player2hisBoard.boardCells;
    for (Iterator iterator_9 = set_9.iterator(); iterator_9.hasNext() && forAllExpResult_5; ) {
      CellBoard cell = ((CellBoard) iterator_9.next());
      forAllExpResult_5 =
          CellBoard.verifyCoordinates(
              SeqUtil.conc(SeqUtil.seq(cell.x_coord), SeqUtil.seq(cell.y_coord)));
    }
    assertTrue(forAllExpResult_5);

    Boolean forAllExpResult_6 = true;
    VDMSet set_10 = SeqUtil.elems(SeqUtil.conc(game.player1.myShips, game.player2.myShips));
    for (Iterator iterator_10 = set_10.iterator(); iterator_10.hasNext() && forAllExpResult_6; ) {
      Ship ship = ((Ship) iterator_10.next());
      Boolean forAllExpResult_7 = true;
      VDMSet set_11 = SeqUtil.elems(ship.positions);
      for (Iterator iterator_11 = set_11.iterator(); iterator_11.hasNext() && forAllExpResult_7; ) {
        VDMSeq coord = ((VDMSeq) iterator_11.next());
        forAllExpResult_7 =
            CellBoard.verifyCoordinates(
                SeqUtil.conc(
                    SeqUtil.seq(((Number) Utils.get(coord, 1L))),
                    SeqUtil.seq(((Number) Utils.get(coord, 2L)))));
      }
      forAllExpResult_6 = forAllExpResult_7;
    }
    assertTrue(forAllExpResult_6);
  }

  public static void runTests() {

    Player1Winner();
    Player2Winner();
    NoOneWon();
    LessMovesPlayer1();
    LessMovesPlayer2();
    testBoardCells();
    testNumberShips();
    testCoordinates();
  }

  public test() {}

  public String toString() {

    return "test{}";
  }
}
