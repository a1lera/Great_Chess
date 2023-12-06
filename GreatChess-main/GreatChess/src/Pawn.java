import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
1 Класс конструкторов
2 Геттеры и сеттеры
3 Специальные методы для ходов пешки
4 Методы продвижения пешки
5 Overridden методы
*/

public class Pawn extends Piece {

    private boolean hasMovedTwo = false;
    private boolean enPassantLeft = false;
    private boolean enPassantRight = false;
    private Coordinate previousCoordinate = new Coordinate();
    private Piece promotedPiece;
    private ImageIcon icon;

    /*
    Класс конструкторов
     */

    public Pawn(COLOUR colour, Coordinate OGcoord) {
        super(ID.PAWN, colour, OGcoord);
        if (getColour() == COLOUR.B)
            icon = new ImageIcon("BPawn.png");
        else if (getColour() == COLOUR.W)
            icon = new ImageIcon("WPawn.png");
    }

    public Pawn(Pawn original) {
        super(original);
    }

    /*
    Геттеры и сеттеры
     */

    public void setPreviousCoordinate(Coordinate previousCoordinate) {
        this.previousCoordinate = previousCoordinate;
    }

    public Coordinate getPreviousCoordinate() {
        return previousCoordinate;
    }

    public void setHasMovedTwo() {
        this.hasMovedTwo = true;
    }

    public boolean getHasMovedTwo() {
        return hasMovedTwo;
    }

    public boolean getEnPassantLeft() {
        return enPassantLeft;
    }

    public boolean getEnPassantRight() {
        return enPassantRight;
    }

    /*
    Специальные методы для ходов пешки
     */

    private boolean canEatLeftDig(Pieces pieces) {

        int factorV;
        int factorH;

        if (getColour().equals(COLOUR.B)) {
            factorV = -1;
            factorH = 1;
        } else {
            factorV = 1;
            factorH = -1;
        }

        char newFile = (char) (getFile() + factorH);
        int newRank = getRank() + factorV;
        Coordinate leftDig = new Coordinate(newFile, newRank);

        return Move.tileFull(pieces, leftDig) && Move.isNotTileColour(pieces, leftDig, getColour());
    }

    private boolean canEatRightDig(Pieces pieces) {

        int factorV;
        int factorH;

        if (getColour().equals(COLOUR.B)) {
            factorV = -1;
            factorH = -1;
        } else {
            factorV = 1;
            factorH = 1;
        }

        char newFile = (char) (getFile() + factorH);
        int newRank = getRank() + factorV;
        Coordinate rightDig = new Coordinate(newFile, newRank);

        return Move.tileFull(pieces, rightDig) && Move.isNotTileColour(pieces, rightDig, getColour());
    }

    private ArrayList<Coordinate> pawnForward(Pieces pieces) {

        ArrayList<Coordinate> potentialForward = Move.frontFree(pieces, this, 2);
        ArrayList<Coordinate> actualForward = new ArrayList<>();

        if (potentialForward.size() > 0) {

            Coordinate front1 = potentialForward.get(0);
            Coordinate front2 = Coordinate.emptyCoordinate;

            if (potentialForward.size() == 2) {
                front2 = potentialForward.get(1);
            }

            if (Move.tileFull(pieces, front1))
                return actualForward;
            else {
                actualForward.add(front1);
            }

            if (pieces.findPiece(this).equals(getOGcoord()) && front2 != Coordinate.emptyCoordinate) {
                if (Move.tileFull(pieces, front2))
                    return actualForward;
                else
                    actualForward.add(front2);
            }
        }
        return actualForward;
    }

    /* public ArrayList <Coordinate> enPassant (Pieces pieces) {
        ArrayList<Coordinate> enPassantMoves = new ArrayList<>();
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,1);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,1);

        if (left.size() == 1) {
            Coordinate leftTile = left.get(0);
            boolean correctPiece = Move.tileFull(pieces,leftTile)
                                 && pieces.getPiece(leftTile).getName() == ID.PAWN
                                 && pieces.getPiece(leftTile).getColour() == COLOUR.not(getColour());
            if (correctPiece) {
                Pawn potentialPawn = (Pawn) pieces.getPiece(leftTile);
                boolean correctPassantContext = potentialPawn.getHasMovedTwo()
                                                && potentialPawn.getPreviousCoordinate().equals(potentialPawn.getOGcoord())
                                                && !potentialPawn.getPreviousCoordinate().equals(potentialPawn.getCoords());
                if (correctPassantContext) {
                    enPassantMoves.addAll(Move.frontLDigFree(pieces, this, 1));
                    enPassantLeft = true;
                }
            }
        }

        if (right.size() == 1) {
            Coordinate rightTile = right.get(0);
            boolean correctPiece = Move.tileFull(pieces,rightTile)
                    && pieces.getPiece(rightTile).getName() == ID.PAWN
                    && pieces.getPiece(rightTile).getColour() == COLOUR.not(getColour());
            if (correctPiece) {
                Pawn potentialPawn = (Pawn) pieces.getPiece(rightTile);
                boolean correctPassantContext = potentialPawn.getHasMovedTwo()
                        && potentialPawn.getPreviousCoordinate().equals(potentialPawn.getOGcoord())
                        && !potentialPawn.getPreviousCoordinate().equals(potentialPawn.getCoords());
                if (correctPassantContext) {
                    enPassantMoves.addAll(Move.frontRDigFree(pieces, this, 1));
                    enPassantRight = true;
                }
            }
        }

        return enPassantMoves;
    } */

    /*
    Методы продвижения пешки
     */

    public Piece promotionQuery(Coordinate promotionSquare) {
        Piece promotee = new Queen(getColour(), promotionSquare);
        return promotee;
    }

    public void GUIPromotionQuery(Coordinate promotionSquare) {
        ImageIcon icon;

        if (this.getColour() == COLOUR.B) {
            icon = new ImageIcon("BQueen.png");
            promotedPiece = new Queen(COLOUR.B, promotionSquare);
        } else {
            icon = new ImageIcon("WQueen.png");
            promotedPiece = new Queen(COLOUR.W, promotionSquare);
        }

        UIManager.put("OptionPane.background", GUIBoard.infoColour);
        UIManager.put("Panel.background", GUIBoard.infoColour);
        UIManager.put("OptionPane.messageForeground", Color.white);

        JOptionPane.showOptionDialog(
                null,
                "Ваша пешка стала ферзём.",
                "Повышение ранга",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                null,
                null
        );
    }

    public boolean canPromoteBlack (Coordinate coordinate) {
        return this.getColour() == COLOUR.B && coordinate.getRank() == BOARD.FIRST_RANK.getRankVal();
    }

    public boolean canPromoteWhite (Coordinate coordinate) {
        return this.getColour() == COLOUR.W && coordinate.getRank() == BOARD.LAST_RANK.getRankVal();
    }

    public Piece getPromotedPiece() {
        return promotedPiece;
    }

    /*
    Overridden методы
     */

    @Override
    public Pawn makeCopy() {
        return new Pawn(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {

        ArrayList<Coordinate> pawnMoves = new ArrayList<>();

        if (canEatLeftDig(pieces))
            pawnMoves.addAll(Move.frontLDigFree(pieces, this, 1));

        pawnMoves.addAll(pawnForward(pieces));

        if (canEatRightDig(pieces))
            pawnMoves.addAll(Move.frontRDigFree(pieces, this, 1));

        /*pawnMoves.addAll(enPassant(pieces));*/

        return pawnMoves;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }


}
