import java.util.*;

public class BattleFieldBoard extends FightBoard{

    private String name;
    private int id;
    private int[][] board;
    private Set<Integer> aureoleList = new HashSet<>();

    private static List<Ship> shipsListId1 = new ArrayList<>();
    private static List<Ship> shipsListId2 = new ArrayList<>();
    private static int playersCount;

    public BattleFieldBoard(String name) {
        this.name = name;
        this.board = new int[10][10];
        if(playersCount == 0){
            this.id = 1;
            playersCount ++;
        } else if(playersCount == 1){
            this.id = 2;
            playersCount ++;
        } else {
            System.out.println("Уже создано 2 игрока");
        }
    }

    public static List<Ship> getShipsList(int id) {
        if (id == 1) {
            return shipsListId1;
        } else return shipsListId2;
    }

    public static void instruction(){
        Scanner sc = new Scanner(System.in);
        System.out.print("ВНИМАНИЕ! Введи координаты в соответствии с\n" +
                "колличеством палуб. Каждая координата должна\n" +
                "состоять из цифры и буквы. Координаты должны\n" +
                "отделяться между собой пробелом. Пример\n" +
                "корректного ввода координат четырехпалубного\n" +
                "корабля: ");
        System.out.println("\u001B[32m" + "5d 5e 5f 5g" + "\u001B[0m\n");
        System.out.print("Нажми 'Enter', чтобы начать >>> ");
        sc.nextLine();
        System.out.println("================================================");
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[32m" + "1. Посмотреть расстановку своих кораблей,\n" +
                "после чего выстрелить\n" +
                "2. Произвести выстрел сейчас\n" +
                "Выбор >>> " + "\u001B[0m");
        int choice = sc.nextInt();

        if (choice == 1){
            printBoard();
        } //sc.close();
    }

    public void setShips(){
        System.out.println(name + ", размести корабли. Второй игрок не смотри!\n");
        setShip(4, 1);
        setShip(3, 2);
        setShip(2, 2);
        setShip(1, 4);
    }

    private void setShip(int decks, int ShipsCount){
        Scanner sc = new Scanner(System.in);
        int count = 0;


        while (count < ShipsCount){
            this.printBoard();
            System.out.print("\u001B[32m" + "Создай корабль с " + decks + " палубами(ой) >>> " + "\u001B[0m");
            String tempShip = sc.nextLine();
            List<Integer> shipsDots = CheckNewShip.checkShipFormat(tempShip, decks, aureoleList);
            if(shipsDots.size() == decks){
                Ship ship = new Ship(shipsDots);
                aureoleList.addAll(ship.getAureole());
                addShipAtList(ship);
                count++;
            }
        } // sc.close();
    }

    private void addShipAtList(Ship ship){
        if(this.id == 1){
            shipsListId1.add(ship);
            System.out.println();
        } else {
            shipsListId2.add(ship);
            System.out.println();
        }
    }

    public void printBoard(){
        List<Ship> shipsList;
        List<Integer> shipsSpaceList = new ArrayList<>();
        List<Integer> killedDots = new ArrayList<>();

        if (this.id == 1) shipsList = shipsListId1;
        else shipsList = shipsListId2;

        for (Ship ship : shipsList) {
            List<Integer> cord = ship.getCoordinates();
            shipsSpaceList.addAll(cord);
        }
        killedDots.addAll(Ship.getKnocked(id));
        killedDots.addAll(Ship.getKilled(id));

        String symbol;
        String shipSym = " # ";
        String knocked = "\u001B[31m" + " # " + "\u001B[0m";

        System.out.println("Мои корабли \uD83E\uDC66 \uD83E\uDC66 \uD83E\uDC66");
        System.out.print("   a  b  c  d  e  f  g  h  i  j ");

        for (int i = 0; i < board.length; i++) {
            System.out.println();
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                symbol = "\u001B[34m" + " ~ " + "\u001B[0m";
                int checkDot = Integer.parseInt(i
                        + String.valueOf(j));
                for(int dot : shipsSpaceList){
                    if(dot == checkDot){
                        symbol = shipSym;
                        break;
                    } else {
                        for(int killedDot : killedDots){
                            if(checkDot == killedDot){
                                symbol = knocked;
                                break;
                            }
                        }
                    }
                } System.out.print(symbol);
            }
        } System.out.println();
    }

    public String getName() {
        return name;
    }

}
