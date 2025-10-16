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
   public abstract String toString();

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
