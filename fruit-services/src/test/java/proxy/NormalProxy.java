package proxy;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class NormalProxy implements  Subject {

    Subject subject = null;
    String changeName = null;
    public NormalProxy(String changeName){
        this.subject = new TeacherSubject();
        this.changeName = changeName;
    }
    @Override
    public String getSubjectName() {
       String subjectName = subject.getSubjectName();
        return subjectName+"_proxy";
    }

    @Override
    public void setSubjectName(String subjectName) {
        subject.setSubjectName("before Proxy_"+subjectName);
    }
}
