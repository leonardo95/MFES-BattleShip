package BattleShip;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Ship {
  public static Number id = 0L;
  public Number tempId;
  public Number sizeShip = 3L;
  public Number strikes = 0L;
  public VDMSeq positions = SeqUtil.seq();
  public Board board;

  public void cg_init_Ship_1(final VDMSeq pos, final Number size, final Board bd) {

    tempId = Ship.id.longValue() + 1L;
    id = tempId;
    sizeShip = size;
    positions = Utils.copy(pos);
    board = bd;
    initShipCells();
  }

  public Ship(final VDMSeq pos, final Number size, final Board bd) {

    cg_init_Ship_1(Utils.copy(pos), size, bd);
  }

  public void initShipCells() {

    long toVar_7 = positions.size();

    for (Long i = 1L; i <= toVar_7; i++) {
      {
        CellBoard h = null;
        Boolean success_4 = false;
        VDMSet set_4 = board.boardCells;
        for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext() && !(success_4); ) {
          h = ((CellBoard) iterator_4.next());
          Boolean andResult_9 = false;

          if (Utils.equals(
              h.x_coord, ((Number) Utils.get(((VDMSeq) Utils.get(positions, i)), 1L)))) {
            if (Utils.equals(
                h.y_coord, ((Number) Utils.get(((VDMSeq) Utils.get(positions, i)), 2L)))) {
              andResult_9 = true;
            }
          }

          success_4 = andResult_9;
        }
        if (!(success_4)) {
          throw new RuntimeException("Let Be St found no applicable bindings");
        }

        {
          h.sethasShip();
          h.setShip(this);
        }
      }
    }
  }

  public Boolean getShipStatus() {

    if (Utils.equals(strikes, sizeShip)) {
      return true;

    } else {
      return false;
    }
  }

  public void incStrikes() {

    strikes = strikes.longValue() + 1L;
  }

  public Ship() {}

  public String toString() {

    return "Ship{"
        + "id := "
        + Utils.toString(id)
        + ", tempId := "
        + Utils.toString(tempId)
        + ", sizeShip := "
        + Utils.toString(sizeShip)
        + ", strikes := "
        + Utils.toString(strikes)
        + ", positions := "
        + Utils.toString(positions)
        + ", board := "
        + Utils.toString(board)
        + "}";
  }
}
