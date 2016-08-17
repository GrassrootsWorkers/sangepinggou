package adapter;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class WrapperAdapter implements TargetSource {
    private Source source;

    public WrapperAdapter(Source source){
        this.source = source;
    }
    @Override
    public String getName() {
        return source.getName();
    }

    @Override
    public String getAddress() {
        return "河北省唐山市";
    }
}
