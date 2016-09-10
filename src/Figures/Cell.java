package Figures;

import Figures.Figure;

/**
 * Created by User on 26.06.2016.
 */
public class Cell {
    Figure.Coordinate x;
    int y;
    public Cell(Figure.Coordinate x, int y){
        this.x = x;
        this.y = y;
    }

    public Figure.Coordinate getX() {
        return x;
    }
    public int getIntx(){
        return x.getIntValue();
    }

    public void setX(Figure.Coordinate x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public String toString(){
        return x+","+y;
    }

}
