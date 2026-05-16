package utilities;
import org.apache.commons.text.RandomStringGenerator;
import utilities.RandomDataUtil;

public class RandomDataUtil {

    private static final RandomStringGenerator alphabeticGenerator =
            new RandomStringGenerator.Builder()
                    .withinRange('a', 'z')
                    .build();

    private static final RandomStringGenerator alphanumericGenerator =
            new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(Character::isLetterOrDigit)
                    .build();

    private static final RandomStringGenerator numericGenerator =
            new RandomStringGenerator.Builder()
                    .withinRange('0', '9')
                    .build();

    public static String generateFirstName() {
        return "FN_" + alphabeticGenerator.generate(5);
    }

    public static String generateLastName() {
        return "LN_" + alphabeticGenerator.generate(5);
    }

    public static String generateEmail() {
        return "user" + numericGenerator.generate(5) + "@gmail.com";
    }

    public static String generatePassword() {
        return "Pwd@" + alphanumericGenerator.generate(6);
    }
}


