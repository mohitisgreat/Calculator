import com.mohit.calculator.parser.*;
import com.mohit.calculator.parser.ast.*;

import org.junit.Test;
import org.junit.Assert;

/**
 * ParserTest
 */
public class ParserTest {

    @Test
    public void ExpTest1() {
        try
        {
            String expression = "344-34*10";
            Parser parser = new Parser(expression);
            IASTBase ast = parser.parse();
            double out = ast.compute();
            Assert.assertEquals(out, 4.0, 0.00000001);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}