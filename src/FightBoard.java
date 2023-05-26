import java.util.*;

public abstract class FightBoard {

    private int[][] board = new int[10][10];
    private List<Integer> offTarget = new ArrayList<>();

    public boolean setShot(int id){
        Scanner sc = new Scanner(System.in);
        int shot = parseShotToInt(sc.nextLine());
        for(Ship ship : BattleFieldBoard.getShipsList(id)){
            for (int i = 0; i < ship.getCoordinates().size(); i++) {
                if (shot == ship.getCoordinates().get(i)){
                    int total = 0;
                    for(int dot : ship.getCoordinates()){
                        if(dot == -1) total += dot;
                    }
                    if ((total * -1 + 1) == ship.getCoordinates().size()) {
                        System.out.println("\u001B[31m" + "Точное попадание! Убил!" + "\u001B[0m");
                        ship.changeCoordinates(id, i, shot);
                        Ship.knockedToKilled(id);
                    }
                    else {
                        System.out.println("\u001B[33m" + "Точное попадание! Ранил" + "\u001B[0m");
                        ship.changeCoordinates(id, i, shot);
                    }
                    return true;
                }
            }
        }
        System.out.println("Мимо!");
        System.out.println();
        addToOffTarget(shot);
        return false;
    }

    private int parseShotToInt(String shotStr){
        String[] shotArray = shotStr.split("");
        return Integer.parseInt(shotArray[0] + CheckNewShip.xCord(shotArray[1]));
    }

    private void addToOffTarget(int shot) {
        this.offTarget.add(shot);
    }

    public void printFightBoard(int id){
        System.out.println("Поле противника \uD83E\uDC66 \uD83E\uDC66 \uD83E\uDC66");
        List<Integer> knockedList;
        List<Integer> killedList;

        if(id == 1) { knockedList = Ship.getKnocked(2);
            killedList = Ship.getKilled(2);
        } else { knockedList = Ship.getKnocked(1);
            killedList = Ship.getKilled(1);
        }

        String symbol;
        String killed = "\u001B[31m" + " * " + "\u001B[0m";
        String knocked = "\u001B[33m" + " * " + "\u001B[0m";

        System.out.print("   a  b  c  d  e  f  g  h  i  j ");

        for (int i = 0; i < board.length; i++) {
            System.out.println();
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                symbol = "\u001B[34m" + " ~ " + "\u001B[0m";
                int checkDot = Integer.parseInt(i
                        + String.valueOf(j));
                if(killedList.size() > 0) {
                    for (int dot : killedList) {
                        if (dot == checkDot) {
                            symbol = killed;
                            break;
                        }
                    }
                } if(knockedList.size() > 0) {
                    for (int dot : knockedList) {
                        if (dot == checkDot) {
                            symbol = knocked;
                            break;
                        }
                    }
                } if (!symbol.equals(killed) && !symbol.equals(knocked)) {
                    for (int offDot : offTarget) {
                        if (offDot == checkDot) {
                            symbol = " o ";
                            break;
                        } else symbol = "\u001B[34m" + " ~ " + "\u001B[0m";
                    }
                }
                System.out.print(symbol);
            }
        }
        System.out.println();
    }

}