package learning.java.game.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Field {

    private int size;

    @JsonIgnore
    private int counterFigure = 0;

    private Figure[][] figures;

    public Field() {
    }

    public Field(int size) {
        this.size = size;
        figures = new Figure[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        if (figures == null)
            figures = new Figure[size][size];
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

    public int getCounterFigure() {
        return counterFigure;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (Figure [] i : figures) {
            str.append("\n");
            for (Figure j : i)
                str.append(j).append(" ");
        }
        return str.toString();

    }
}
