import java.util.*;

public abstract class CheckNewShip {

    public static List<Integer> checkShipFormat(String coordinates, int decks, Set<Integer> aureoleList) {
        int coordinatesLengthCount = 0;
        int cordStrCount = 0;


        switch (decks){
            case 4 : coordinatesLengthCount = 11;
                cordStrCount = 8;
                break;
            case 3 : coordinatesLengthCount = 8;
                cordStrCount = 6;
                break;
            case 2 : coordinatesLengthCount = 5;
                cordStrCount = 4;
                break;
            case 1 : coordinatesLengthCount = 2;
                cordStrCount = 2;
        }
        String error = "\u001B[31m" + "Неправильный формат!" + "\u001B[0m";
        String error4 = "\u001B[31m" + "Корабль должен быть целым и находиться\n" +
                "на одной линии по горизонтали или вертикали." + "\u001B[0m";
        String error5 = "\u001B[31m" + "Наложение кораблей!" + "\u001B[0m";
        List<Integer> empty = new ArrayList<>();
        if (coordinates.length() == coordinatesLengthCount) {
            String[] cordStr = coordinates.replaceAll(" ", "").split("");
            if (cordStr.length == cordStrCount) {
                List<Integer> cord = new ArrayList<>();
                for (int i = 0; i < cordStrCount; i++) {
                    try {
                        int tempNum = Integer.parseInt(cordStr[i]);
                        if (tempNum >= 0 && tempNum <= 9) {
                            cord.add(Integer.parseInt(cordStr[i]));
                        } else break;

                    } catch (NumberFormatException e) {
                        int tempX = xCord(cordStr[i]);
                        if (tempX < 10)
                            cord.add(xCord(cordStr[i]));
                        else break;
                    }
                }
                if (cord.size() == cordStrCount) {
                    if (CheckNewShip.checkValidation(cord)){
                        List<Integer> shipsDots = makeShipsCordArray(cord);
                        if (CheckNewShip.checkAureoleList(shipsDots, aureoleList)){
                            return shipsDots;
                        } else System.out.println(error5); return empty;
                    } else System.out.println(error4); return empty;
                } else System.out.println(error); return empty;
            } else System.out.println(error); return empty;
        } else System.out.println(error); return empty;
    }

    private static boolean checkAureoleList(List<Integer> shipsDots, Set<Integer> aureoleList){
        Set<Integer> intersection = new HashSet<>(aureoleList);
        Set<Integer> cordSet = new HashSet<>(shipsDots);
        intersection.retainAll(cordSet);
        return intersection.size() == 0;
    }

    private static boolean checkValidation(List<Integer> cord){
        boolean validation = false;
        int intValidation = 0;

        if (cord.size() == 2) return true;

        for (int i = 0; i < cord.size()-2; i+=2) {
            if (cord.get(0).equals(cord.get(i+2))){
                intValidation = 1;
            } else if (cord.get(1).equals(cord.get(i+3))) {
                intValidation = 2;
            } else {
                intValidation = 0;
                break;
            }
        }
        if (intValidation == 1 || intValidation == 2){
            List<Integer> cordY = new ArrayList<>();
            if (intValidation == 1) {
                for (int i = 1; i < cord.size(); i += 2) {
                    cordY.add(cord.get(i));
                }
            } else {
                for (int i = 0; i < cord.size(); i += 2) {
                    cordY.add(cord.get(i));
                }
            }
            Collections.sort(cordY);
            for (int i = 0; i < cordY.size()-1; i++) {
                if (cordY.get(i)+1 == cordY.get(i+1))
                    validation = true;
                else {
                    validation = false;
                    break;
                }
            }
        }
        return validation;
    }

    public static int xCord(String xString){
        int x;
        switch (xString){
            case "A": case "a":
                x = 0; break;
            case "B": case "b":
                x = 1; break;
            case "C": case "c":
                x = 2; break;
            case "D": case "d":
                x = 3; break;
            case "E": case "e":
                x = 4; break;
            case "F": case "f":
                x = 5; break;
            case "G": case "g":
                x = 6; break;
            case "H": case "h":
                x = 7; break;
            case "I": case "i":
                x = 8; break;
            case "J": case "j":
                x = 9; break;
            default: x = 10;
        } return x;
    }

    private static List<Integer> makeShipsCordArray(List<Integer> cord) {
        List<Integer> shipsDots = new ArrayList<>();
        for (int i = 0; i < cord.size(); i++) {
            try{
                if(i == 0){
                    int dot1 = Integer.parseInt(cord.get(i)
                            + String.valueOf(cord.get(i+1)));
                    shipsDots.add(dot1);
                } else if (i == 2) {
                    int dot2 = Integer.parseInt(cord.get(i)
                            + String.valueOf(cord.get(i+1)));
                    shipsDots.add(dot2);
                } else if (i == 4) {
                    int dot3 = Integer.parseInt(cord.get(i)
                            + String.valueOf(cord.get(i+1)));
                    shipsDots.add(dot3);
                } else if (i == 6) {
                    int dot4 = Integer.parseInt(cord.get(i)
                            + String.valueOf(cord.get(i+1)));
                    shipsDots.add(dot4);
                }
            } catch (IndexOutOfBoundsException e){
                break;
            }

        }return shipsDots;
    }

}