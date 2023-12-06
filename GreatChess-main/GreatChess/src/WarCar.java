import javax.swing.*;
import java.util.ArrayList;

/*
1 Класс конструкторов
2) Overridden метод
*/


public class WarCar extends Piece {

    private ImageIcon icon;

    /*
    Класс конструкторов
     */

    public WarCar(COLOUR colour, Coordinate OGcoord) {
        super(ID.WARCAR, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BWarCar.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WWarCar.png");
    }

    public WarCar(WarCar original) {
        super(original);
    }

    @Override
    public WarCar makeCopy() {
        return new WarCar(this);
    }

       /*
    Overridden метод
     */

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

        ArrayList<Coordinate> giraffeMoves = new ArrayList<>();
        giraffeMoves.addAll(rookMoves);
        giraffeMoves.addAll(knightMoves);

        return giraffeMoves;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }
}