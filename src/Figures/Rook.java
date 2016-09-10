package Figures;

import Main.Game;

import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public class Rook extends Figure {
    public Rook(Figure.Side side, Cell c) {
        super(side,c);
    }
    @Override
    public boolean checkMovement(Cell cell,Figure[][]fields) {
        if ((cell.getIntx() == this.getC().getIntx()) || (this.getC().getY() == cell.getY())) {
            if (!this.getSide().equals(Game.turnSide())) {
                return true;
            }
            if (this.lastCheckForMove(cell, fields)) {
                return true;
            }
        }
        for (int i = 0; i < findCriticalCells(cell).size(); i++) {
            if (findCriticalCells(cell) != null && Game.getFigure(findCriticalCells(cell).get(i).getIntx(), findCriticalCells(cell).get(i).getY()) != null) {

                return false;
            }

        }
        return false;
    }
    @Override
    public ArrayList<Cell> findCriticalCells(Cell king)
    {
        ArrayList<Cell> CriticalCells = new ArrayList<Cell>();
        if((this.getC().getIntx()<king.getIntx())&&(this.getC().getY()==king.getY())) {
            for (int i = 0; -i + king.getIntx() < this.getC().getIntx(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()-i), king.getY()));
        }
        if((this.getC().getIntx()>king.getIntx())&&(this.getC().getY()==king.getY())) {
            for (int i = 0; i + king.getIntx() < this.getC().getIntx(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()+i), king.getY()));
        }
        if((this.getC().getIntx()==king.getIntx())&&(this.getC().getY()<king.getY())) {
            for (int i = 0; -i + king.getY() <this.getC().getY(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()), king.getY()-i));
        }
        if((this.getC().getIntx()==king.getIntx())&&(this.getC().getY()>king.getY())) {
            for (int i = 0; i + king.getY() < this.getC().getY(); i++)
                CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()), king.getY()+i));
        }
        return CriticalCells;
    }
    @Override
    public String toString(){
        if(String.valueOf(this.getSide()).equals("white")){
            return "|_R_|";
        }
else {
            return "|_r_|";
        }

    }
}
