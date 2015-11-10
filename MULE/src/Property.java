import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peter on 9/20/2015.
 */
public class Property implements Serializable{
    private Point location;

    public Property(Point location) {
        this.location = location;
    }

    public Point getPoint() {
        return location;
    }

    public boolean equals(Object p) {
        if (p != null && p instanceof Property) {
            Property property = (Property) p;
            return property.getPoint().equals(this.getPoint());
        }
        return false;
    }
}
