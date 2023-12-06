import javax.swing.*;
import java.util.ArrayList;

/*
1 Класс конструкторов
2) Overridden Methods
*/

public class Vizar extends Piece{

    private ImageIcon icon;

    /*
    Класс конструкторов
     */

    public Vizar(COLOUR colour, Coordinate OGcoord) {
        super(ID.VIZAR, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BVizar.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WVizar.png");
    }

    public Vizar(Vizar original) {
        super(original);
    }

    /*
    Overridden методы
     */

    @Override
    public Vizar makeCopy() {
        return new Vizar(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {

        ArrayList<Coordinate> knightMoves = Move.frontKnight(pieces, this);
        knightMoves.addAll(Move.backKnight(pieces, this));
        knightMoves.addAll(Move.rightKnight(pieces, this));
        knightMoves.addAll(Move.leftKnight(pieces, this));

        ArrayList<Coordinate> bishopMoves = Move.frontRDigFree(pieces, this, dimension);
        bishopMoves.addAll(Move.backRDigFree(pieces, this, dimension));
        bishopMoves.addAll(Move.backLDigFree(pieces, this, dimension));
        bishopMoves.addAll(Move.frontLDigFree(pieces, this, dimension));

        ArrayList<Coordinate> giraffeMoves = new ArrayList<>();
        giraffeMoves.addAll(knightMoves);
        giraffeMoves.addAll(bishopMoves);

        return giraffeMoves;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

}