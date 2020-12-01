package huonglh.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Hau Huong
 */
public class HashingCode {

    public static String encode(String s) throws Exception {
        String result = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(s.getBytes(StandardCharsets.UTF_8));
        result = DatatypeConverter.printHexBinary(hash);
        return result;
    }
}
