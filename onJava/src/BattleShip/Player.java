package BattleShip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Player {
  public String myName;
  public Number myType;
  public Number myMode;
  public Board myBoard;
  public Board hisBoard;
  public VDMSeq myMoves;
  public VDMSeq myShips;
  public static VDMSeq shipsSize = SeqUtil.seq(2L, 2L, 2L, 2L, 3L, 3L, 3L, 4L, 4L, 5L);

  public void cg_init_Player_1(final Number type, final String name, final Number player_mode) {

    VDMSeq shipsInitpos = null;
    String directions = null;
    myName = name;
    myType = type;
    myMode = player_mode;
    myBoard = new Board(0L);
    hisBoard = new Board(1L);
    shipsInitpos = getMyShipsPositions();
    myMoves = getMyMoves();
    initMyShips(Utils.copy(shipsInitpos));
  }

  public Player(final Number type, final String name, final Number player_mode) {

    cg_init_Player_1(type, name, player_mode);
  }

  public VDMSeq move() {

    VDMSeq mymove = null;
    mymove = Utils.copy(((VDMSeq) myMoves.get(0)));
    myMoves = SeqUtil.tail(Utils.copy(myMoves));
    return Utils.copy(mymove);
  }

  public void initMyShips(final VDMSeq shipsInitpos) {

    Ship shipTemp = null;
    VDMSeq sizes = null;
    VDMSeq temp_pos = null;
    VDMSeq temp_shipsInitpos = null;
    sizes = Utils.copy(Player.shipsSize);
    myShips = SeqUtil.seq();
    temp_shipsInitpos = Utils.copy(shipsInitpos);
    long toVar_6 = Player.shipsSize.size();

    for (Long i = 1L; i <= toVar_6; i++) {
      temp_pos = SeqUtil.seq();
      long toVar_5 = ((Number) Utils.get(shipsSize, i)).longValue();

      for (Long j = 1L; j <= toVar_5; j++) {
        temp_pos =
            SeqUtil.conc(
                Utils.copy(temp_pos), SeqUtil.seq(Utils.copy(((VDMSeq) temp_shipsInitpos.get(0)))));
        temp_shipsInitpos = SeqUtil.tail(Utils.copy(temp_shipsInitpos));
      }
      shipTemp = new Ship(Utils.copy(temp_pos), ((Number) Utils.get(shipsSize, i)), myBoard);
      myShips = SeqUtil.conc(Utils.copy(myShips), SeqUtil.seq(shipTemp));
    }
  }

  public VDMSeq getMyShipsPositions() {

    if (Utils.equals(myType, 0L)) {
      return SeqUtil.toSeq(ReadFile("resources/player1_shipsPositions.txt"));

    } else {
      return SeqUtil.toSeq(ReadFile("resources/player2_shipsPositions.txt"));
    }
  }

  public VDMSeq getMyMoves() {

    if (Utils.equals(myMode, 0L)) {
      if (Utils.equals(myType, 0L)) {
        return SeqUtil.toSeq(ReadFile("resources/player1_moves_winner.txt"));

      } else {
        return SeqUtil.toSeq(ReadFile("resources/player2_moves_loser.txt"));
      }

    } else {
      if (Utils.equals(myMode, 1L)) {
        if (Utils.equals(myType, 0L)) {
          return SeqUtil.toSeq(ReadFile("resources/player1_moves_loser.txt"));

        } else {
          return SeqUtil.toSeq(ReadFile("resources/player2_moves_winner.txt"));
        }

      } else {
        if (Utils.equals(myMode, 2L)) {
          if (Utils.equals(myType, 0L)) {
            return SeqUtil.toSeq(ReadFile("resources/player1_moves_outofmoves.txt"));

          } else {
            return SeqUtil.toSeq(ReadFile("resources/player2_moves_outofmoves.txt"));
          }

        } else {
          if (Utils.equals(myMode, 3L)) {
            if (Utils.equals(myType, 0L)) {
              return SeqUtil.toSeq(ReadFile("resources/player1_moves_lessmoves.txt"));

            } else {
              return SeqUtil.toSeq(ReadFile("resources/player2_moves_winner.txt"));
            }

          } else {
            if (Utils.equals(myType, 0L)) {
              return SeqUtil.toSeq(ReadFile("resources/player1_moves_winner.txt"));

            } else {
              return SeqUtil.toSeq(ReadFile("resources/player2_moves_lessmoves.txt"));
            }
          }
        }
      }
    }
  }

  public Player() {}

  public String toString() {

    return "Player{"
        + "myName := "
        + Utils.toString(myName)
        + ", myType := "
        + Utils.toString(myType)
        + ", myMode := "
        + Utils.toString(myMode)
        + ", myBoard := "
        + Utils.toString(myBoard)
        + ", hisBoard := "
        + Utils.toString(hisBoard)
        + ", myMoves := "
        + Utils.toString(myMoves)
        + ", myShips := "
        + Utils.toString(myShips)
        + ", shipsSize := "
        + Utils.toString(shipsSize)
        + "}";
  }
  
  public VDMSeq ReadFile(String file)
  {
	  VDMSeq coordsSeq = new VDMSeq();
	  VDMSeq coords = new VDMSeq();

	  String line = null;
      String[] details = null;
	  try (
	      InputStream fis = new FileInputStream(file);
	      InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	      BufferedReader br = new BufferedReader(isr);
	  ) {
	      while ((line = br.readLine()) != null) {
	    	  details = line.split(",");
	    	  coords.add(Integer.parseInt(details[0]));
	    	  coords.add(Integer.parseInt(details[1]));
	    	  coordsSeq.add(coords);
	    	  coords = new VDMSeq();
	      }
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return coordsSeq;
  }
}
