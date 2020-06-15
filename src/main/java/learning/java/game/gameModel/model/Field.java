package learning.java.game.gameModel.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Field {

    private int size;

    @JsonIgnore private int counterFigure = 0;

    private final Figure[][] figures;

    public Field(int size) {
        this.size = size;
        figures = new Figure[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setFigures(final Point point, final Figure figure) {

        figures[point.getX()][point.getY()] = figure;
        counterFigure++;
    }

    public Figure getFigure(final Point point) {
        return figures[point.getX()][point.getY()];

    }

    public Figure[][] getFigures() {
        return figures;
    }

    public int getCounterFigure(){
        return counterFigure;
    }


}
