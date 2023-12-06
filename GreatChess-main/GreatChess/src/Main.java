import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("����� ���������� � ������� �������. ������� \"GUI\", ����� ������ ������");

        Pieces pieces = new Pieces();

        boolean correctType = false;

        while (!correctType) {

            String gameType = sc.next();

            if (gameType.equals("GUI")) {
                pieces.setGUIGame(true);
                new GUIBoard(pieces);
                correctType = true;
            }
            else
                System.out.println("������������ ������ �����. �������� �����.");
        }
    }

}
