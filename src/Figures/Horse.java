package Figures;

import Main.Game;

import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public class Horse extends Figure {


    public Horse(Side side, Cell c) {
        super(side, c);
    }



    @Override
    public boolean checkMovement(Cell cell,Figure[][] fields) {
        if(((Math.abs(cell.getIntx()-this.getC().getIntx())==2)&&(Math.abs(cell.getY()-this.getC().getY())==1))
                ||((Math.abs(cell.getY()-this.getC().getY())==2)&&(Math.abs(cell.getIntx()-this.getC().getIntx())==1)))
        {
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
            return "|_H_|";
        }

        return "|_h_|";

    }
    }


