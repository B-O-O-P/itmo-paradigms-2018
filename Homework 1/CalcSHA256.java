import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Calculate SHA-256 hash of files.
 */
public class CalcSHA256 {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String filename = args[0], s, algorithm = "SHA-256";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        while ((s = bufferedReader.readLine()) != null) {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(Files.readAllBytes(FileSystems.getDefault().getPath(s)));
            byte[] digest = messageDigest.digest();
            
            String res = DatatypeConverter.printHexBinary(digest).toUpperCase();
            System.out.println(res);
        }
    }
}