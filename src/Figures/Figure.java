package Figures;

import Config.Content;
import Config.Utils;
import Main.Game;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by User on 20.06.2016.
 */
public abstract class Figure {
    public enum Side {black, white}

    public Cell getC() {
        return c;
    }

    Cell c;
    Figure.Side side;

    public void setC(Cell c) {
        this.c = c;
    }

    public Figure(Figure.Side side, Cell c) {
        this.c = c;
        this.side = side;
    }

    public enum Coordinate {
        A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

        public int getIntValue() {
            return intValue;
        }

        private final int intValue;

        private Coordinate(int intValue) {
            this.intValue = intValue;
        }


    }

    public static Coordinate findCoordinate(int number) {
        for (int i = 0; i < Coordinate.values().length; i++) {
            if (Coordinate.values()[i].getIntValue() == number) {
                return Coordinate.values()[i];
            }
        }
        return null;
    }

    public String   getSide() {
        return String.valueOf(side);
    }

    public boolean lastCheckForMove(Cell cell, Figure[][] field) {
        Cell oldcell = this.c;
        boolean result;
        field[8][0] = Game.getField()[8-cell.getY()][cell.getIntx()];
        Game.getField()[8-cell.getY()][cell.getIntx()] = null;
        Game.getField()[8-cell.getY()][cell.getIntx()] = Game.getField()[8-this.getC().getY()][this.getC().getIntx()];
        Game.getField()[8-this.getC().getY()][this.getC().getIntx()] = null;
        Game.getField()[8-cell.getY()][cell.getIntx()].setC(cell);
        result = !Utils.checkforCheck(Utils.kingFounding());
        Game.getField()[8-oldcell.getY()][oldcell.getIntx()] = Game.getField()[8-cell.getY()][cell.getIntx()];
        Game.getField()[8-oldcell.getY()][oldcell.getIntx()].setC(oldcell);
        Game.getField()[8-cell.getY()][cell.getIntx()] = null;
        Game.getField()[8-cell.getY()][cell.getIntx()] = field[8][0];
        field[8][0] = null;

        return result;
    }

    public boolean move(Cell cell, Figure[][] field) {

   //     System.out.println("Наща ячейка в муве" +this.getC());
     //   System.out.println(" ячейка куда ходим в муве" +cell);
        if (checkMovement(cell, field)) {
            //     int a = Utils.numberOFCheckFigures(Utils.kingFounding());
            // Cell oldcell = this.c;
            // в муве будет только это

            field[8][0] = Game.getField()[8-cell.getY()][cell.getIntx()];
            Game.getField()[8-cell.getY()][cell.getIntx()] = null;

            Game.getField()[8-cell.getY()][cell.getIntx()] = Game.getField()[8-this.getC().getY()][this.getC().getIntx()];
            Game.getField()[8-this.getC().getY()][this.getC().getIntx()] = null;
            Game.getField()[8-cell.getY()][cell.getIntx()].setC(cell);
            return true;
        } else {
            System.out.println(Content.illigalMove);
            return false;
        }

    }

    public abstract boolean checkMovement(Cell cell, Figure[][] fields);


    public ArrayList<Cell> findCriticalCells(Cell king) {
        ArrayList<Cell> CriticalCells = new ArrayList<>();

        CriticalCells.add(this.getC());
        return CriticalCells;
    }

    public String toString() {

        return " ___\n|_F_|";

    }
}
