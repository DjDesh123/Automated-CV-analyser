import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;


public class PasswordHashing {

    private String Salt ;

    // hashing constructer
    public PasswordHashing() {
        // better verison of random for stuff like password hashing
        SecureRandom rdm = new SecureRandom();
        // creates a 128 bit array
        byte[] SaltByte = new byte[16];
        // populates teh array randomly
        rdm.nextBytes(SaltByte);

        // basically put its back in form for a string easier for files and databases
        this.Salt = Base64.getEncoder().encodeToString(SaltByte);
    }

    // converts the bytes into a string
    public String GetSalt(){
        return Salt;
    }

    // need to understand this better myself
    public String HashingPassword(String Password, String Salt) {
        try{
            //gets a hashing alogrithms (SHA-256 is a one way encrytyion)
            // the reason you need to hash is becuase if you just put salt in then people who are trying ot break in can see the password already
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // turns the Base64 byte into Bytes
            byte[] saltBytes = Base64.getDecoder().decode(Salt.trim());

            // applies the salt to the hashing process
            md.update(saltBytes);

            // converts the password into bytes
            byte[] PasswordBytes = Password.getBytes();

            //generate hashed password
            byte[] HashedBytes = md.digest(PasswordBytes);


            // turns the hashed bytes into a mroe readable Base64 String
            //in addition not all databases can handle binary properly so its a lot easier to extract and store in base64
            return Base64.getEncoder().encodeToString(HashedBytes);
        } catch (NoSuchAlgorithmException e) {
            // handles algorithm exceptions
            throw new RuntimeException("Error hash handling " ,e);
        }

    }

}
