package BattleShip;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CellBoard {
  public Number x_coord;
  public Number y_coord;
  public Boolean striked = false;
  public Boolean withShip = false;
  public Board myBoard;
  public Ship ship;

  public void cg_init_CellBoard_1(final Number x, final Number y) {

    x_coord = x;
    y_coord = y;
  }

  public CellBoard(final Number x, final Number y) {

    cg_init_CellBoard_1(x, y);
  }

  public void setBoard(final Board board) {

    myBoard = board;
  }

  public void setShip(final Ship b) {

    ship = b;
  }

  public void setstrike() {

    striked = true;
  }

  public void sethasShip() {

    withShip = true;
  }

  public CellBoard() {}

  public static Boolean verifyCoordinates(final VDMSeq coordinates) {

    Boolean andResult_4 = false;

    if (((Number) coordinates.get(0)).longValue() >= 1L) {
      Boolean andResult_5 = false;

      if (((Number) coordinates.get(0)).longValue() <= Board.BoardSize.longValue()) {
        Boolean andResult_6 = false;

        if (((Number) SeqUtil.tail(Utils.copy(coordinates)).get(0)).longValue() >= 1L) {
          if (((Number) SeqUtil.tail(Utils.copy(coordinates)).get(0)).longValue()
              <= Board.BoardSize.longValue()) {
            andResult_6 = true;
          }
        }

        if (andResult_6) {
          andResult_5 = true;
        }
      }

      if (andResult_5) {
        andResult_4 = true;
      }
    }

    return andResult_4;
  }

  public String toString() {

    return "CellBoard{"
        + "x_coord := "
        + Utils.toString(x_coord)
        + ", y_coord := "
        + Utils.toString(y_coord)
        + ", striked := "
        + Utils.toString(striked)
        + ", withShip := "
        + Utils.toString(withShip)
        + ", myBoard := "
        + Utils.toString(myBoard)
        + ", ship := "
        + Utils.toString(ship)
        + "}";
  }
}
