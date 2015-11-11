import java.io.Serializable;

/**
 * Created by Peter on 10/15/2015.
 */
public class CoolMule implements Serializable {
    private Property location;
    private String type;

    public CoolMule(String t, Property loc) {
        this.location = loc;
        this.type = t;
    }

    public final Property getLocation() {
        return location;
    }

    public final void setLocation(Property loc) {
        this.location = loc;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String t) {
        this.type = t;
    }

    public final boolean equals(Object other) {
        if (other instanceof CoolMule) {
            CoolMule otherMule = (CoolMule) other;
            return otherMule.getLocation().equals(this.getLocation());
        }
        return false;
    }

    public final int hashCode() {
        return this.getLocation().hashCode();
    }
}
