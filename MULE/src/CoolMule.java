import java.io.Serializable;

/**
 * Created by Peter on 10/15/2015.
 */
public class CoolMule implements Serializable {
    private Property location;
    private String type;

    public CoolMule(String type, Property location) {
        this.location = location;
        this.type = type;
    }

    public final Property getLocation() {
        return location;
    }

    public final void setLocation(Property location) {
        this.location = location;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object other) {
        if (other != null && other instanceof CoolMule) {
            CoolMule otherMule = (CoolMule) other;
            return otherMule.getLocation().equals(this.getLocation());
        }
        return false;
    }
}
