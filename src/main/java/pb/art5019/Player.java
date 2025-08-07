package pb.art5019;

public class Player {
    private String name;



    private int points;
    private int randomizedOrder;
    private boolean isBlocked;

    public void setRandomizedOrder(int randomizedOrder) {
        this.randomizedOrder = randomizedOrder;
    }

    public int getRandomizedOrder() {
        return randomizedOrder;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
