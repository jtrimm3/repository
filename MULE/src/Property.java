import java.awt.*;

/**
 * Created by Peter on 9/20/2015.
 */
public class Property {
    private Point location;
    private double price;

    public Property(Point location) {
        this.location = location;
    }

    public Point getPoint() {
        return location;
    }

    public boolean equals(Property p) {
        return p.getPoint().equals(this.getPoint());
    }
}
