import org.testng.Assert;
import org.testng.annotations.Test;
import validation.CustomFileValidator;

public class ValidatorTests {

    private static final String CORRECT_FILE_PATH = "src\\main\\resources\\xml_data\\tariffs.xml";
    private static final String WRONG_FILE_PATH = "src\\main\\resources\\xml_data\\tariffs123.xml";

    @Test
    public void validatorTest() {

        Assert.assertTrue(CustomFileValidator.isFileValid(CORRECT_FILE_PATH));
        Assert.assertFalse(CustomFileValidator.isFileValid(WRONG_FILE_PATH));

    }


}
