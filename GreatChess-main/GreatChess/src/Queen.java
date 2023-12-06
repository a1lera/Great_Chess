import javax.swing.*;
import java.util.ArrayList;

/*
1 Класс конструкторов
2) Overridden Methods
*/

public class Queen extends Piece{

    private ImageIcon icon;

    /*
    Класс конструкторов
     */

    public Queen(COLOUR colour, Coordinate OGcoord) {
        super(ID.QUEEN, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BQueen.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WQueen.png");
    }

    public Queen(Queen original) {
        super(original);
    }

    /*
    Overridden методы
     */

    @Override
    public Queen makeCopy() {
        return new Queen(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {

        ArrayList<Coordinate> front = Move.frontFree(pieces,this,dimension);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,dimension);
        ArrayList<Coordinate> back = Move.backFree(pieces,this,dimension);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,dimension);
        ArrayList<Coordinate> frontRDig = Move.frontRDigFree(pieces, this,dimension);
        ArrayList<Coordinate> backRDig = Move.backRDigFree(pieces, this, dimension);
        ArrayList<Coordinate> backLDig = Move.backLDigFree(pieces, this,dimension);
        ArrayList<Coordinate> frontLDig = Move.frontLDigFree(pieces, this, dimension);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        front.addAll(frontRDig);

        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

}
