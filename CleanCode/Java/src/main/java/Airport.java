import Planes.ExperimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane currentPlane : this.planes) {
            if (currentPlane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) currentPlane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane currentPlane : this.planes)
            if (currentPlane instanceof MilitaryPlane)
                militaryPlanes.add((MilitaryPlane) currentPlane);
        return militaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);

        for (PassengerPlane currentPlane : passengerPlanes) {
            planeWithMaxCapacity =
                currentPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity() 
                ? currentPlane : planeWithMaxCapacity;
        }

        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();

        for (MilitaryPlane currentPlane : getMilitaryPlanes()) 
            if (currentPlane.getType() == MilitaryType.TRANSPORT) 
                transportMilitaryPlanes.add(currentPlane);
                
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();

        for (MilitaryPlane currentPlane : getMilitaryPlanes())
            if (currentPlane.getType() == MilitaryType.BOMBER)
                bomberMilitaryPlanes.add(currentPlane);

        return bomberMilitaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        
        for (Plane currentPlane : planes)
            if (currentPlane instanceof ExperimentalPlane)
                experimentalPlanes.add((ExperimentalPlane) currentPlane);

        return experimentalPlanes;
    }

    public Airport sortByMaxDistance() {
        Collections.sort(planes,
            new Comparator<Plane>() {
                public int compare(Plane o1, Plane o2) {
                    return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
                }
            }
        );
        return this;
    }

    public Airport sortByMaxSpeed() {
        Collections.sort(planes,
            new Comparator<Plane>() {
                public int compare(Plane o1, Plane o2) {
                    return o1.getMaxSpeed() - o2.getMaxSpeed();
                }
            }
        );
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        Collections.sort(planes,
            new Comparator<Plane>() {
                public int compare(Plane o1, Plane o2) {
                    return o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity();
                }
            }
        );
        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airport{" +
        "Planes=" + planes.toString() +
        '}';
    }
}
