import java.util.Objects;

public abstract class Aircraft {
   private String model;
   private int capacity;
   private int speed;


   public Aircraft(String model, int capacity, int speed) {
       this.model = model;
       this.capacity = capacity;
       this.speed = speed;
   }
   public abstract void performFlight();

    @Override
    public String toString() {
        return "Aircraft{" +
                "model='" + model + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return getCapacity() == aircraft.getCapacity() && getSpeed() == aircraft.getSpeed() && Objects.equals(getModel(), aircraft.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getCapacity(), getSpeed());
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
