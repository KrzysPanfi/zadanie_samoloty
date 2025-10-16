public class CargoPlane extends Aircraft implements Flyable {
    public CargoPlane(String model, int capacity, int speed) {
        super(model, capacity, speed);
    }

    @Override
    public void performFlight() {
        System.out.println("Cargoplane leci");
    }

    @Override
    public void fly() {
        performFlight();
    }
}
