package proxy;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class TeacherSubject implements  Subject {
    private String subjectName;
    @Override
    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
