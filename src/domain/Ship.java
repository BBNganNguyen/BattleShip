package domain;

import java.util.Objects;
import java.util.Set;

public class Ship {
    private int shipNo;
    private Set<Point> points;

    public Ship(int shipNo, Set<Point> points) {
        this.shipNo = shipNo;
        this.points = points;
    }

    public int getShipNo() {
        return shipNo;
    }

    public void setShipNo(int shipNo) {
        this.shipNo = shipNo;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return shipNo == ship.shipNo && Objects.equals(points, ship.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipNo, points);
    }

}
