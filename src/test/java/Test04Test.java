import com.vcanus.programmingtest.Test04;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
public class Test04Test {
    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    public static String filePath;
    public static int rowSize;
    public static int colSize;
    public static String delimiter;

    public static String wrongPath;
    public static int wrongRowSize;
    public static int wrongColSize;
    public static String wrongDelimiter;

    public static Test04 defaultTest;
//    data
//    0 0 0 0 0 0 0 0 0 0
//    0 0 0 0 1 0 0 0 0 0
//    0 0 0 1 1 1 0 0 0 0
//    0 1 1 1 1 1 1 0 0 0
//    0 1 1 1 1 1 1 1 1 0
//    0 1 1 1 1 1 1 1 1 0
//    0 0 1 1 1 1 1 1 0 0
//    0 0 0 1 1 1 1 0 0 0
//    0 0 0 0 1 0 0 0 0 0
//    0 0 0 0 0 0 0 0 0 0

    @BeforeClass
    public static void setUp() {
        filePath = "./src/main/resources/pond.txt";
        rowSize = 10;
        colSize = 10;
        delimiter = " ";

        wrongPath = "";
        wrongRowSize = 0;
        wrongColSize = 0;
        wrongDelimiter = ",";

        defaultTest = new Test04();
        try{
            defaultTest.readFile(
                    filePath,
                    rowSize,
                    colSize,
                    delimiter
            );
        } catch (Exception e) {
            Assert.fail();
        }
    }

//    @Test(expected = IOException.class)
//    public void fileException() throws IOException {
//    }

    @Test
    public void readFile() throws IOException {
        Test04 test = new Test04();
        Assert.assertTrue(
                test.readFile(
                        filePath,
                        rowSize,
                        colSize,
                        delimiter)
        );
    }

    @Test
    public void readFileWithWrongRowSize() throws IOException {
        Test04 test = new Test04();
        Assert.assertFalse(
                test.readFile(
                        filePath,
                        wrongRowSize,
                        colSize,
                        delimiter)
        );
    }

    @Test
    public void readFileWithWrongColSize() throws IOException {
        Test04 test = new Test04();
        Assert.assertFalse(
                test.readFile(
                        filePath,
                        rowSize,
                        wrongColSize,
                        delimiter)
        );
    }

    @Test
    public void readFileWithWrongDelimiter() throws IOException {
        Test04 test = new Test04();
        Assert.assertFalse(
                test.readFile(
                        filePath,
                        rowSize,
                        colSize,
                        wrongDelimiter)
        );
    }

    @Test
    public void readFileAndCheckCells() throws IOException {
        defaultTest.updateCells();
        assertEquals(1, defaultTest.at(3,1));
        assertEquals(0, defaultTest.at(1,3));
        assertEquals(1, defaultTest.at(8,4));
        assertEquals(0, defaultTest.at(5,9));
    }

    @Test
    public void updateCells() {
        defaultTest.updateCells();
        defaultTest.print();
        int sum = defaultTest.sum();
        Assert.assertEquals(68, sum);
    }
}
