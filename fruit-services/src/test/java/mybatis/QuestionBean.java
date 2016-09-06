package mybatis;

/**
 * Created by liuzhi on 2016/8/23.
 */
public class QuestionBean {
    private Long id;
    private String questionContent;
    private String optionContent;
    private String answer;
    private String analysis;
    private String qcapFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getQcapFlag() {
        return qcapFlag;
    }

    public void setQcapFlag(String qcapFlag) {
        this.qcapFlag = qcapFlag;
    }
}
