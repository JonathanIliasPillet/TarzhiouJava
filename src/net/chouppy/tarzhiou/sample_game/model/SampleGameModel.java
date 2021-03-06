package net.chouppy.tarzhiou.sample_game.model;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.listeners.PlayerListener;

public class SampleGameModel extends Game implements PlayerListener
{
  public SampleGameModel()
  {
    super();

    setCellSpace(new SampleCellSpace());
  }

  public void createPlayer(String name)
  {
    Player p = new Player(name);

    addPlayer(p);
    p.addListener(this);
  }

  public void start ()
  {
    super.start ();
  }
  
  @Override
  protected void processBursts()
  {
    getCellSpace().doAllBursts();
  }

  @Override
  public void onLooseAPiece(Player me, Piece thisPiece)
  {
    assert me != null : "me cannot be null";

    if (!me.isAlive())
    {
      System.out.println(me.toString() + " died"); // FIXME: remove this

      if (countAlivePlayers() == 1)
      {
        getCellSpace().stopDoingAllBursts();
        win(getAnAlivePlayer());
      }
    }
  }

  @Override
  public void onNewPiece (Player me, Piece thisPiece) {}

  @Override
  public void onWinAPiece (Player me, Piece thisPiece) {}
}
