public class CargoPlane extends Aircraft {
    public CargoPlane(String model, int capacity, int speed) {
        super(model, capacity, speed);
    }

    @Override
    public void performFlight() {
        System.out.println("Cargo performFlight");
    }


}
