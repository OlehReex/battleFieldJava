import java.util.*;

public class Ship {

    private List<Integer> coordinates;
    private Set<Integer> aureole;

    private static List<Integer> knockedP1 = new ArrayList<>();
    private static List<Integer> knockedP2 = new ArrayList<>();
    private static List<Integer> killedP1 = new ArrayList<>();
    private static List<Integer> killedP2 = new ArrayList<>();

    public Ship(List<Integer> coordinates){
        this.coordinates = coordinates;
        this.aureole = makeAureole();
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public void changeCoordinates(int id, int i, int shot) {
        if(id == 1){
            this.coordinates.set(i, -1);
            knockedP1.add(shot);
        } else {
            this.coordinates.set(i, -1);
            knockedP2.add(shot);
        }
    }

    public Set<Integer> makeAureole(){
        Set<Integer> total = new HashSet<>();
        for(int dot : coordinates){
            if (dot == 0){
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot+1, dot+10, dot+11, dot));
                total.addAll(tempDotAureole);
            } else if(dot == 9){
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-1, dot+9, dot+10, dot));
                total.addAll(tempDotAureole);
            } else if(dot == 90){
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-10, dot-9, dot+1, dot));
                total.addAll(tempDotAureole);
            } else if(dot == 99){
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-11, dot-10, dot-1, dot));
                total.addAll(tempDotAureole);
            } else if (dot < 10) {
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-1,  dot,    dot+1,
                        dot+9,  dot+10, dot+11));
                total.addAll(tempDotAureole);
            } else if (dot > 90) {
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-11, dot-10, dot-9,
                        dot-1, dot, dot+1));
                total.addAll(tempDotAureole);
            } else if(dot%10 == 0) {
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot - 10, dot - 9, dot + 1,
                        dot + 11, dot, dot + 10));
                total.addAll(tempDotAureole);
            } else if(dot%10 == 9){
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-11, dot-10, dot-1,
                        dot+10, dot, dot+9));
                total.addAll(tempDotAureole);
            } else{
                Set<Integer> tempDotAureole = new HashSet<>(Arrays.asList(dot-11, dot-10, dot-9,
                        dot-1,  dot,    dot+1,
                        dot+9,  dot+10, dot+11));
                total.addAll(tempDotAureole);
            }
        }
        return total;
    }

    public Set<Integer> getAureole() {
        return aureole;
    }

    public static List<Integer> getKnocked(int id) {
        if (id == 1) {
            return knockedP1;
        } else return knockedP2;
    }

    public static List<Integer> getKilled(int id) {
        if (id == 1) {
            return killedP1;
        } else return killedP2;
    }

    public static void knockedToKilled(int id){
        getKilled(id).addAll(getKnocked(id));
        getKnocked(id).clear();
    }

}