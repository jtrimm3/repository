import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peter on 9/20/2015.
 */
public class Property implements Serializable{
    private Point location;

    public Property(Point loc) {
        this.location = loc;
    }

    public final Point getPoint() {
        return location;
    }

    public final boolean equals(Object p) {
        if (p instanceof Property) {
            Property property = (Property) p;
            return property.getPoint().equals(this.getPoint());
        }
        return false;
    }

    public final int hashCode() {
        return getPoint().hashCode();
    }
}
