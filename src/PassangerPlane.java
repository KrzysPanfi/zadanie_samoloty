public class PassangerPlane extends Aircraft implements Flyable, Serviceable {
    public PassangerPlane(String model, int capacity, int speed) {
        super(model, capacity, speed);
    }

    @Override
    public void performFlight() {
        System.out.println("PassangerPlane leci");
    }
    public void fly() {
        performFlight();
    }
    public void service() {
        System.out.println("PassangerPlane service");
    }
}
