import java.io.File;

/**
 * Created by Peter on 11/30/2015.
 */
public class Extension {
    public final static String mul = "mul";

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    public static boolean hasValidExtension(File f) {
        return getExtension(f).equals(mul);
    }
}
