import javax.swing.*;
import java.util.ArrayList;

/*
1 Классы конструкторов
2 Overridden метод
*/


public class Giraffe extends Piece {

    private ImageIcon icon;

    /*
    Классы конструкторов
     */

    public Giraffe(COLOUR colour, Coordinate OGcoord) {
        super(ID.GIRAFFE, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BGiraffe.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WGiraffe.png");
    }

    public Giraffe(Giraffe original) {
        super(original);
    }

    @Override
    public Giraffe makeCopy() {
        return new Giraffe(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> rookMoves = Move.frontFree(pieces, this, dimension);
        rookMoves.addAll(Move.rightFree(pieces, this, dimension));
        rookMoves.addAll(Move.backFree(pieces, this, dimension));
        rookMoves.addAll(Move.leftFree(pieces, this, dimension));

        ArrayList<Coordinate> knightMoves = Move.frontKnight(pieces, this);
        knightMoves.addAll(Move.backKnight(pieces, this));
        knightMoves.addAll(Move.rightKnight(pieces, this));
        knightMoves.addAll(Move.leftKnight(pieces, this));

        ArrayList<Coordinate> bishopMoves = Move.frontRDigFree(pieces, this, dimension);
        bishopMoves.addAll(Move.backRDigFree(pieces, this, dimension));
        bishopMoves.addAll(Move.backLDigFree(pieces, this, dimension));
        bishopMoves.addAll(Move.frontLDigFree(pieces, this, dimension));

        ArrayList<Coordinate> giraffeMoves = new ArrayList<>();
        giraffeMoves.addAll(rookMoves);
        giraffeMoves.addAll(knightMoves);
        giraffeMoves.addAll(bishopMoves);

        return giraffeMoves;
    }

    /*
    Override метод
     */

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }
}