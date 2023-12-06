import javax.swing.*;
import java.util.ArrayList;

/*
1 ����� �������������
2 ������� � �������
3) Overridden Methods
*/

public class Rook extends Piece{

    private Coordinate castleCoordRook;

    private ImageIcon icon;

    /*
    ����� �������������
     */

    public Rook(COLOUR colour, Coordinate OGcoord) {
        super(ID.ROOK, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BRook.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WRook.png");
    }

    public Rook (Rook original) {
        super(original);
    }

    /*
    ������� � �������
     */

    public Coordinate getCastleCoordRook() {
        return castleCoordRook;
    }

    public void setCastleCoordRook(Coordinate castleCoordRook) {
        this.castleCoordRook = castleCoordRook;
    }

    /*
    Overridden ������
     */

    @Override
    public Rook makeCopy() {
        return new Rook(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontFree(pieces,this,dimension);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,dimension);
        ArrayList<Coordinate> back = Move.backFree(pieces,this,dimension);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,dimension);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }


}
