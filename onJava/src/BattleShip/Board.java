package BattleShip;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Board {
  public static final Number BoardSize = 10L;
  public static final VDMSet rangeCells = SetUtil.range(1L, Board.BoardSize);
  public static final Character Shot_Miss = 'M';
  public static final Character Shot_Strike = 'H';
  public static final Character Shot_DestroyedShip = 'D';
  public VDMSet boardCells;
  public Number battleShipBoard;

  public void cg_init_Board_1(final Number BoardType) {

    boardCells = SetUtil.set();
    battleShipBoard = BoardType;
    for (Iterator iterator_12 = Board.rangeCells.iterator(); iterator_12.hasNext(); ) {
      Number cells_x = (Number) iterator_12.next();
      for (Iterator iterator_13 = Board.rangeCells.iterator(); iterator_13.hasNext(); ) {
        Number cells_y = (Number) iterator_13.next();
        boardCells =
            SetUtil.union(Utils.copy(boardCells), SetUtil.set(new CellBoard(cells_x, cells_y)));
      }
    }
    for (Iterator iterator_14 = boardCells.iterator(); iterator_14.hasNext(); ) {
      CellBoard boardcells = (CellBoard) iterator_14.next();
      boardcells.setBoard(this);
    }
  }

  public Board(final Number BoardType) {

    cg_init_Board_1(BoardType);
  }

  public Character strike(final VDMSeq coordinates) {

    {
      CellBoard cell = null;
      Boolean success_1 = false;
      VDMSet set_1 = Utils.copy(boardCells);
      for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext() && !(success_1); ) {
        cell = ((CellBoard) iterator_1.next());
        Boolean andResult_1 = false;

        if (Utils.equals(cell.x_coord, ((Number) coordinates.get(0)))) {
          if (Utils.equals(cell.y_coord, ((Number) SeqUtil.tail(Utils.copy(coordinates)).get(0)))) {
            andResult_1 = true;
          }
        }

        success_1 = andResult_1;
      }
      if (!(success_1)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      {
        Boolean orResult_1 = false;

        if (!(cell.withShip)) {
          orResult_1 = true;
        } else {
          orResult_1 = cell.striked;
        }

        if (orResult_1) {
          return Board.Shot_Miss;
        }

        Boolean andResult_2 = false;

        if (cell.withShip) {
          if (!(cell.striked)) {
            andResult_2 = true;
          }
        }

        if (andResult_2) {
          cell.setstrike();
          cell.ship.incStrikes();
          IO.print("Ship id: ");
          IO.print(cell.ship.tempId);
          IO.println(" was hit!");
        }

        if (cell.ship.getShipStatus()) {
          IO.print("Ship id: ");
          IO.print(cell.ship.tempId);
          IO.println(" was destroyed!");
          IO.println("");
          return Board.Shot_DestroyedShip;
        }

        IO.println("");
        return Board.Shot_Strike;
      }
    }
  }

  public void save(final VDMSeq move, final Character strike) {

    {
      CellBoard cell = null;
      Boolean success_2 = false;
      VDMSet set_2 = Utils.copy(boardCells);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext() && !(success_2); ) {
        cell = ((CellBoard) iterator_2.next());
        Boolean andResult_3 = false;

        if (Utils.equals(cell.x_coord, ((Number) move.get(0)))) {
          if (Utils.equals(cell.y_coord, ((Number) SeqUtil.tail(Utils.copy(move)).get(0)))) {
            andResult_3 = true;
          }
        }

        success_2 = andResult_3;
      }
      if (!(success_2)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      {
        Boolean orResult_2 = false;

        if (Utils.equals(strike, Board.Shot_Strike)) {
          orResult_2 = true;
        } else {
          orResult_2 = Utils.equals(strike, Board.Shot_DestroyedShip);
        }

        if (orResult_2) {
          cell.sethasShip();
        }

        cell.setstrike();
      }
    }
  }

  public Board() {}

  public String toString() {

    return "Board{"
        + "BoardSize = "
        + Utils.toString(BoardSize)
        + ", rangeCells = "
        + Utils.toString(rangeCells)
        + ", Shot_Miss = "
        + Utils.toString(Shot_Miss)
        + ", Shot_Strike = "
        + Utils.toString(Shot_Strike)
        + ", Shot_DestroyedShip = "
        + Utils.toString(Shot_DestroyedShip)
        + ", boardCells := "
        + Utils.toString(boardCells)
        + ", battleShipBoard := "
        + Utils.toString(battleShipBoard)
        + "}";
  }
}
