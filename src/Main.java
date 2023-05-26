public class Main {
    public static void main(String[] args) {

        BattleFieldBoard.instruction();
        BattleFieldBoard player1 = new BattleFieldBoard("Олег");
        BattleFieldBoard player2 = new BattleFieldBoard("Таня");
        player1.setShips();
        player2.setShips();

        while ((Ship.getKilled(1).size() != 20) && (Ship.getKilled(2).size() != 20)) {
            System.out.println("\u001B[32m" + player1.getName() +
                    ", выбери действие и нажми 'Enter':" + "\u001B[0m");
            player1.menu();

            int count = 20 - Ship.getKilled(2).size();

            for (int i = 0; i < count; i++) {
                player1.printFightBoard(1);
                System.out.print("\u001B[32m" + player1.getName() + ", стреляй >>> " + "\u001B[0m");
                if (!player1.setShot(2)) break;
            }

            if (Ship.getKilled(2).size() != 20) {
                System.out.println("\u001B[32m" + player2.getName() +
                        ", выбери действие и нажми 'Enter':" + "\u001B[0m");
                player2.menu();

                count = 20 - Ship.getKilled(1).size();

                for (int i = 0; i < count; i++) {
                    player2.printFightBoard(2);
                    System.out.print("\u001B[32m" + player2.getName() + ", стреляй >>> " + "\u001B[0m");
                    if (!player2.setShot(1)) break;
                }
            }

            if (Ship.getKilled(2).size() == 20)
                System.out.println("\n\u001B[32m" + player1.getName().toUpperCase() +
                        " ПОБЕДИЛ! " + "\u001B[0m");
            else if(Ship.getKilled(1).size() == 20)
                System.out.println("\n\u001B[32m" + player2.getName().toUpperCase() +
                        " ПОБЕДИЛ! " + "\u001B[0m");
        }
    }
}
