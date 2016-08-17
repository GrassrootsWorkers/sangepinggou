package adapter;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class ClassAdapter extends Source implements TargetSource {
    @Override
    public String getAddress() {
        return "河北";
    }
}
