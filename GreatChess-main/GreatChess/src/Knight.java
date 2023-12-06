import javax.swing.*;
import java.util.ArrayList;

/*
1 ����� �������������
2 Overridden ������
*/

public class Knight extends Piece{

    private ImageIcon icon;

    /*
    ����� �������������
     */

    public Knight(COLOUR colour, Coordinate OGcoord) {
        super(ID.KNIGHT, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BKnight.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WKnight.png");
    }

    public Knight(Knight original) {
        super(original);
    }

    /*
    Overridden ������
     */

    @Override
    public Knight makeCopy() {
        return new Knight(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontKnight(pieces,this);
        ArrayList<Coordinate> right = Move.backKnight(pieces,this);
        ArrayList<Coordinate> back = Move.rightKnight(pieces,this);
        ArrayList<Coordinate> left = Move.leftKnight(pieces,this);

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
