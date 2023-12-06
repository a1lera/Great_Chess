import java.util.ArrayList;
import java.util.Objects;

/*
1 Пустые сообщения
2 Методы поддержки
3 Методы для ветрикальных ходов
4 Методы для горизонтальных ходов
5 Методы для диагональных ходов вправо
6 Методы для диагональных ходов влево
7 Методы для вертикальных ходов короля
8 Методы для горизонтальных ходов короля
*/

public class Move {

    /*
    Пустые сообщения
     */

    private static final String nullPieces = "Вы не можете играть с пустой доской.";
    private static final String nullCoord = "Координаты доски не могут быть нулевыми.";

    /*
    Методы поддержки
     */

    public static boolean isNotTileColour(Pieces pieces, Coordinate destination, COLOUR colour) {
        Objects.requireNonNull(pieces,nullPieces);
        Objects.requireNonNull(destination,nullCoord);

        return !(pieces.getPieces().get(destination).getColour() == (colour));
    }

    public static boolean tileFull (Pieces pieces, Coordinate destination) {

        Objects.requireNonNull(pieces,nullPieces);
        Objects.requireNonNull(destination,nullCoord);

        return pieces.getPieces().get(destination) != null;
    }

    /*
    Методы для ветрикальных ходов
     */

    public static ArrayList<Coordinate> frontFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = 1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate(piece.getFile(), piece.getRank() + change);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }

        return moves;
    }

    public static ArrayList<Coordinate> backFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = -1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate(piece.getFile(),piece.getRank()+change);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    /*
    Методы для горизонтальных ходов
     */

    public static ArrayList<Coordinate> rightFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = 1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+change),piece.getRank());
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    public static ArrayList<Coordinate> leftFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = -1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+change),piece.getRank());
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    /*
    Методы для диагональных ходов вправо
     */

    public static ArrayList<Coordinate> frontRDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = -1;
            factorH = -1;
        }
        else {
            factorV = 1;
            factorH = 1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else if (!tileFull(pieces, checkCoord))
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    public static ArrayList<Coordinate> backRDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = -1;
            factorH = 1;
        }
        else {
            factorV = 1;
            factorH = -1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    /*
    Методы для диагональных ходов влево
     */

    public static ArrayList<Coordinate> backLDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = 1;
            factorH = 1;
        }
        else {
            factorV = -1;
            factorH = -1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    public static ArrayList<Coordinate> frontLDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = 1;
            factorH = -1;
        }
        else {
            factorV = -1;
            factorH = 1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    /*
    Методы для вертикальных ходов короля
     */

    public static ArrayList<Coordinate> frontKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = -1;
        }
        else {
            factor = 1;
        }

        int changeV = 2*factor;
        int newRank = piece.getRank() + changeV;

        Coordinate frontRight = new Coordinate((char) (piece.getFile() + sideDistance),newRank);
        Coordinate frontLeft = new Coordinate((char) (piece.getFile() - sideDistance),newRank);

        if (Coordinate.inBoard(frontLeft)) {
            boolean occupiedTile = tileFull(pieces, frontLeft);
            if (!occupiedTile || isNotTileColour(pieces, frontLeft, piece.getColour()))
                moves.add(frontLeft);
        }

        if (Coordinate.inBoard(frontRight)) {
            boolean occupiedTile = tileFull(pieces, frontRight);
            if (!occupiedTile || isNotTileColour(pieces, frontRight, piece.getColour()))
                moves.add(frontRight);
        }
        return moves;
    }

    public static ArrayList<Coordinate> backKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = 1;
        }
        else {
            factor = -1;
        }

        int changeV = 2*factor;
        int newRank = piece.getRank() + changeV;

        Coordinate backRight = new Coordinate((char) (piece.getFile() + sideDistance),newRank);
        Coordinate backLeft = new Coordinate((char) (piece.getFile() - sideDistance),newRank);

        if (Coordinate.inBoard(backLeft)) {
            boolean occupiedTile = tileFull(pieces, backLeft);
            if (!occupiedTile || isNotTileColour(pieces, backLeft, piece.getColour()))
                moves.add(backLeft);
        }

        if (Coordinate.inBoard(backRight)) {
            boolean occupiedTile = tileFull(pieces, backRight);
            if (!occupiedTile || isNotTileColour(pieces, backRight, piece.getColour()))
                moves.add(backRight);
        }
        return moves;
    }

    /*
    Методы для горизонтальных ходов короля
     */

    public static ArrayList<Coordinate> rightKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = -1;
        }
        else {
            factor = 1;
        }

        int changeH = 2*factor;
        char newFile = (char) (piece.getFile() + changeH);

        Coordinate rightTop = new Coordinate(newFile,piece.getRank() + sideDistance);
        Coordinate rightBottom = new Coordinate(newFile, piece.getRank() - sideDistance);

        if (Coordinate.inBoard(rightTop)) {
            boolean occupiedTile = tileFull(pieces, rightTop);
            if (!occupiedTile || isNotTileColour(pieces, rightTop, piece.getColour()))
                moves.add(rightTop);
        }

        if (Coordinate.inBoard(rightBottom)) {
            boolean occupiedTile = tileFull(pieces, rightBottom);
            if (!occupiedTile || isNotTileColour(pieces, rightBottom, piece.getColour()))
                moves.add(rightBottom);
        }
        return moves;
    }

    public static ArrayList<Coordinate> leftKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = 1;
        }
        else {
            factor = -1;
        }

        int changeH = 2*factor;
        char newFile = (char) (piece.getFile() + changeH);

        Coordinate leftTop = new Coordinate(newFile,piece.getRank() + sideDistance);
        Coordinate leftBottom = new Coordinate(newFile, piece.getRank() - sideDistance);

        if (Coordinate.inBoard(leftBottom)) {
            boolean occupiedTile = tileFull(pieces, leftBottom);
            if (!occupiedTile || isNotTileColour(pieces, leftBottom, piece.getColour()))
                moves.add(leftBottom);
        }

        if (Coordinate.inBoard(leftTop)) {
            boolean occupiedTile = tileFull(pieces, leftTop);
            if (!occupiedTile || isNotTileColour(pieces, leftTop, piece.getColour()))
                moves.add(leftTop);
        }
        return moves;
    }

}
