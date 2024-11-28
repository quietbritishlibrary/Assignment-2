package amazonsystem;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestWishlist.class, TestCredits.class, TestCart.class, TestComments.class})
public class AllTests {

}
