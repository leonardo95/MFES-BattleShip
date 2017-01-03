package BattleShip;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Interfaces {
  public static Character ship = 'O';
  public static Character strikeShip = 'X';
  public static Character water = ' ';
  public static Character strikeWater = '+';
  public static VDMMap boardColumns =
      MapUtil.map(
          new Maplet(1L, 'A'),
          new Maplet(2L, 'B'),
          new Maplet(3L, 'C'),
          new Maplet(4L, 'D'),
          new Maplet(5L, 'E'),
          new Maplet(6L, 'F'),
          new Maplet(7L, 'G'),
          new Maplet(8L, 'H'),
          new Maplet(9L, 'I'),
          new Maplet(10L, 'J'));

  public static void printGameInit() {

    IO.println("");
    IO.println("\t////////////////////////////////////////////");
    IO.println("\t/***     Welcome to BATTLESHIP Game     ***/");
    IO.println("\t////////////////////////////////////////////");
    IO.println("");
  }

  public static void printMyBoard(final Board board, final Number type) {

    Character temp_cell = Interfaces.ship;
    IO.print("                     ");
    long toVar_1 = Board.BoardSize.longValue();

    for (Long i = 1L; i <= toVar_1; i++) {
      IO.print(((Character) Utils.get(boardColumns, i)));
      IO.print("   ");
    }
    IO.println("");
    long toVar_3 = Board.BoardSize.longValue();

    for (Long y = 1L; y <= toVar_3; y++) {
      if (!(Utils.equals(y, 10L))) {
        IO.print("                    ");

      } else {
        IO.print("                   ");
      }

      IO.print(y);
      long toVar_2 = Board.BoardSize.longValue();

      for (Long x = 1L; x <= toVar_2; x++) {
        {
          CellBoard cell = null;
          Boolean success_3 = false;
          VDMSet set_3 = board.boardCells;
          for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext() && !(success_3); ) {
            cell = ((CellBoard) iterator_3.next());
            Boolean andResult_8 = false;

            if (Utils.equals(cell.x_coord, x)) {
              if (Utils.equals(cell.y_coord, y)) {
                andResult_8 = true;
              }
            }

            success_3 = andResult_8;
          }
          if (!(success_3)) {
            throw new RuntimeException("Let Be St found no applicable bindings");
          }

          {
            if (Utils.equals(type, 0L)) {
              if (cell.withShip) {
                if (cell.striked) {
                  temp_cell = Interfaces.strikeShip;
                } else {
                  temp_cell = Interfaces.ship;
                }

              } else if (cell.striked) {
                temp_cell = Interfaces.strikeWater;
              }

              IO.print(temp_cell);
              IO.print(" | ");
              temp_cell = Interfaces.water;

            } else {
              if (cell.withShip) {
                if (cell.striked) {
                  temp_cell = Interfaces.strikeShip;
                } else {
                  temp_cell = Interfaces.ship;
                }

              } else {
                if (cell.striked) {
                  temp_cell = Interfaces.strikeWater;

                } else {
                  temp_cell = Interfaces.water;
                }
              }

              IO.print(temp_cell);
              IO.print(" | ");
            }
          }
        }
      }
      IO.println("");
    }
    IO.print("                     ");
    long toVar_4 = Board.BoardSize.longValue();

    for (Long i = 1L; i <= toVar_4; i++) {
      IO.print(((Character) Utils.get(boardColumns, i)));
      IO.print("   ");
    }
    IO.println(" ");
  }

  public Interfaces() {}

  public String toString() {

    return "Interfaces{"
        + "ship := "
        + Utils.toString(ship)
        + ", strikeShip := "
        + Utils.toString(strikeShip)
        + ", water := "
        + Utils.toString(water)
        + ", strikeWater := "
        + Utils.toString(strikeWater)
        + ", boardColumns := "
        + Utils.toString(boardColumns)
        + "}";
  }
}
