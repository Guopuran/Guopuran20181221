package guopuran.bwie.com.guopuran.bean;

public class ZiBean {
    private int cc;
    private String text1;
    private String text2;

    public ZiBean(int cc, String text1, String text2) {
        this.cc = cc;
        this.text1 = text1;
        this.text2 = text2;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
