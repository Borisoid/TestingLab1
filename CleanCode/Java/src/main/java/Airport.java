import Planes.ExperimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

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
        return this.planes.stream().filter(MilitaryPlane.class::isInstance).
            map(p -> (MilitaryPlane) p).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return this.planes.stream().filter(ExperimentalPlane.class::isInstance).
            map(p -> (ExperimentalPlane) p).collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return Collections.max(getPassengerPlanes(), 
            Comparator.comparing(plane -> plane.getPassengersCapacity())
        );
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanes().stream().
            filter(plane -> plane.getType() == MilitaryType.TRANSPORT).
            collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanes().stream().
            filter(plane -> plane.getType() == MilitaryType.BOMBER).
            collect(Collectors.toList());
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
