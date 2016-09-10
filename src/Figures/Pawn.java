package Figures;

import Config.Content;
import Main.Game;
import Main.Main;

import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public class Pawn extends Figure {
    //Cell c;
 //   Figure.Side side;

    public Pawn(Figure.Side side, Cell c) {
      super(side,c);
    }



    @Override
    public boolean checkMovement(Cell cell,Figure[][] fields) {
        //  Config.Utils.checkCellExsisting(x,y);
       // System.out.println("nachalo координат"+this.getC().getY()+this.getC().getIntx());
        //System.out.println("konec координат"+cell.getY()+cell.getIntx());
        if ((this.getSide()=="white"&&(cell.getY() - this.c.getY() == 1)&&(cell.getIntx()==this.getC().getIntx()))||
        (this.getSide()=="black"&&(-cell.getY() + this.c.getY() == 1)&&(cell.getIntx()==this.getC().getIntx()))){

            if(!this.getSide().equals(Game.turnSide()))
            {
                return true;
            }
            if (this.lastCheckForMove(cell, fields)) {
                return true;
            }
        }
        return false;
    }
@Override
    public String toString(){
    if(String.valueOf(this.getSide()).equals("white")){
        return "|_P_|";
    }

        return "|_p_|";

    }


}
