package springproxy;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class UserLoginImpl implements UserLogin {
    @Override
    public void login(String name) {
        System.out.print(name+"login");
    }
}
