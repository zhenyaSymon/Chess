package Figures;

import Config.Utils;
import Main.Game;


import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public class Elephant extends Figure {
    public Elephant(Figure.Side side, Cell c) {
        super(side,c);
    }
    @Override
    public boolean checkMovement(Cell cell, Figure[][] fields) {

        if (Math.abs(cell.getIntx() - this.getC().getIntx()) == Math.abs(cell.getY() - this.getC().getY())) {
            if(!this.getSide().equals(Game.turnSide()))
            {
                return true;
            }
            if (this.lastCheckForMove(cell, fields)) {
                return true;
            }

        }
        for(int i = 0 ; i<findCriticalCells(cell).size();i++){
            if(findCriticalCells(cell)!=null&&Game.getFigure(findCriticalCells(cell).get(i).getIntx(),findCriticalCells(cell).get(i).getY())!=null){

                return false;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Cell> findCriticalCells(Cell king) {
        ArrayList<Cell> CriticalCells = new ArrayList<Cell>();
        if((this.getC().getIntx()-king.getIntx()>0)&&(this.getC().getY()-king.getY()<0)) {
            System.out.println();
            for (int i = 0; i + king.getIntx() < this.getC().getIntx(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() + i), king.getY() - i));
        }
        if((this.getC().getIntx()-king.getIntx()<0)&&(this.getC().getY()-king.getY()>0)) {
            for (int i = 0; -i + king.getIntx() < this.getC().getIntx(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() - i), king.getY() + i));
        }
        if((this.getC().getIntx()-king.getIntx()>0)&&(this.getC().getY()-king.getY()>0)) {
            for (int i = 0; i + king.getIntx() < this.getC().getIntx(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() + i), king.getY() + i));
        }
        if((this.getC().getIntx()-king.getIntx()<0)&&(this.getC().getY()-king.getY()<0)) {
            for (int i = 0; -i + king.getIntx() < this.getC().getIntx(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() - i), king.getY() - i));
        }
for(int i =0;i<CriticalCells.size();i++) {
}
        return CriticalCells;
    }
    @Override
    public String toString(){
        if(String.valueOf(this.getSide()).equals("white")){
            return "|_E_|";
        }

        return "|_e_|";

    }
}
