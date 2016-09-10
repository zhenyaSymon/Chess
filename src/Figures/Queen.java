package Figures;

import Main.Game;

import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public class Queen extends Figure {
    public Queen(Figure.Side side, Cell c) {
        super(side,c);
    }
    @Override
    public boolean checkMovement(Cell cell,Figure[][] fields) {
        if(((cell.getIntx()==this.getC().getIntx())||(this.getC().getY()==cell.getY()))
        ||(Math.abs(cell.getIntx()-this.getC().getIntx())==Math.abs(cell.getY()-this.getC().getY()))){
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
    public ArrayList<Cell> findCriticalCells(Cell king) {
        ArrayList<Cell> CriticalCells = new ArrayList<Cell>();
        if((this.getC().getIntx()==king.getIntx())||(this.getC().getY()==king.getY())){
            if((this.getC().getIntx()<king.getIntx())&&(this.getC().getY()==king.getY())) {
                for (int i = 0; -i + king.getIntx() <= this.getC().getIntx(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()-i), king.getY()));
            }
            if((this.getC().getIntx()>king.getIntx())&&(this.getC().getY()==king.getY())) {
                for (int i = 0; i + king.getIntx() <= this.getC().getIntx(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()+i), king.getY()));
            }
            if((this.getC().getIntx()==king.getIntx())&&(this.getC().getY()<king.getY())) {
                for (int i = 0; -i + king.getY() <= this.getC().getY(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()), king.getY()-i));
            }
            if((this.getC().getIntx()==king.getIntx())&&(this.getC().getY()>king.getY())) {
                for (int i = 0; i + king.getY() <= this.getC().getY(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx()), king.getY()+i));
            }
        }
        else {
            if((this.getC().getIntx()-king.getIntx()>0)&&(this.getC().getY()-king.getY()<0)) {
                for (int i = 0; i + king.getIntx() <= this.getC().getIntx(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() + i), king.getY() - i));
            }
            if((this.getC().getIntx()-king.getIntx()<0)&&(this.getC().getY()-king.getY()>0)) {
                for (int i = 0; -i + king.getIntx() <= this.getC().getIntx(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() - i), king.getY() + i));
            }
            if((this.getC().getIntx()-king.getIntx()>0)&&(this.getC().getY()-king.getY()>0)) {
                for (int i = 0; i + king.getIntx() <= this.getC().getIntx(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() + i), king.getY() + i));
            }
            if((this.getC().getIntx()-king.getIntx()<0)&&(this.getC().getY()-king.getY()<0)) {
                for (int i = 0; -i + king.getIntx() <= this.getC().getIntx(); i++)
                    CriticalCells.add(new Cell(Figure.findCoordinate(king.getIntx() - i), king.getY() - i));
            }
        }
        return CriticalCells;
    }
    @Override
    public String toString(){
        if(String.valueOf(this.getSide()).equals("white")){
            return "|_Q_|";
        }

        return "|_q_|";

    }
}
