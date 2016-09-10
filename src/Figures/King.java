package Figures;

import Main.Game;

import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public class King extends Figure {
    public King(Figure.Side side, Cell c) {
        super(side,c);
    }


    @Override
    public boolean checkMovement(Cell cell,Figure[][] fields) {
        if((Math.abs(cell.getIntx()-this.getC().getIntx())==1)&&(Math.abs(cell.getY()-this.getC().getY())==1))
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


    public static Cell[] movesForTheKing(Figure king){
        Cell[] moves = new Cell[8];
        moves[0]= new Cell(Figure.findCoordinate(king.getC().getIntx()),king.getC().getY()+1);
        moves[1]= new Cell(Figure.findCoordinate(king.getC().getIntx()-1),king.getC().getY()+1);
        moves[2]= new Cell(Figure.findCoordinate(king.getC().getIntx()+1),king.getC().getY()+1); // проблема тут в y , надо написать,что б оно не віходиило за 9
        moves[3]= new Cell(Figure.findCoordinate(king.getC().getIntx()-1),king.getC().getY());
        moves[4]= new Cell(Figure.findCoordinate(king.getC().getIntx()+1),king.getC().getY());
        moves[5]= new Cell(Figure.findCoordinate(king.getC().getIntx()),king.getC().getY()-1);
        moves[6]= new Cell(Figure.findCoordinate(king.getC().getIntx()-1),king.getC().getY()-1);
        moves[7]= new Cell(Figure.findCoordinate(king.getC().getIntx()+1),king.getC().getY()-1);

        return moves;
    }
    @Override
    public String toString(){
        if(String.valueOf(this.getSide()).equals("white")){
            return "|_K_|";
        }

        return "|_k_|";

    }
}
