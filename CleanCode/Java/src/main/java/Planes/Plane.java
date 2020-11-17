package Planes;

import java.util.Objects;

abstract public class Plane {
    String model;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    public Plane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return this.maxLoadCapacity;
    }

    @Override
    public String toString() {
        return "Plane{" 
                + "model='" + model + '\'' 
                + ", maxSpeed=" + maxSpeed 
                + ", maxFlightDistance=" + maxFlightDistance 
                + ", maxLoadCapacity=" + maxLoadCapacity 
                + '}';
    }

    @Override
    public boolean equals(Object compareTo) {
        if (this == compareTo) return true;
        if (!(compareTo instanceof Plane)) return false;
        return  maxSpeed == ((Plane) compareTo).maxSpeed 
                && maxFlightDistance == ((Plane) compareTo).maxFlightDistance
                && maxLoadCapacity == ((Plane) compareTo).maxLoadCapacity
                && Objects.equals(model, ((Plane) compareTo).model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    }
}
