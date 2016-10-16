package optional;

import com.farmer.fruit.models.farmer.Farmer;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by liuzhi on 2016/10/6.
 */
public class TestOptional {
    @Test
    public void testOptional(){
        Farmer f = null;
        Optional<Farmer> farmerOptional = Optional.ofNullable(f);
    }
}
