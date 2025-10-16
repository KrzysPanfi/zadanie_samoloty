public class PassangerPlane extends Aircraft {
    public PassangerPlane(String model, int capacity, int speed) {
        super(model, capacity, speed);
    }

    @Override
    public void performFlight() {
        System.out.println("PassangerPlane performFlight");
    }
    public String toString() {
        return getModel() + " " + getCapacity() + " " + getSpeed();

    }

}
