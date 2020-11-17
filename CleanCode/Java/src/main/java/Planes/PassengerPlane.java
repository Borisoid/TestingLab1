package Planes;

import java.util.Objects;

public class PassengerPlane extends Plane{

    private int passengersCapacity;

    public PassengerPlane(String model, int maxSpeed, int maxFlightDistance, 
        int maxLoadCapacity, int passengersCapacity) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" 
                + passengersCapacity 
                + '}');
    }

    @Override
    public boolean equals(Object compareTo) {
        if (this == compareTo) return true;
        if (!(compareTo instanceof PassengerPlane) 
            || !super.equals(compareTo))
            return false;
        return passengersCapacity == ((PassengerPlane)compareTo).passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }
}
