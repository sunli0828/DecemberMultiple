package mlb.bawei.com.synthesize.bean;

/**
 * @author
 * @date 2018/12/29
 */
public class MessageBean {
    public LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public MessageBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
