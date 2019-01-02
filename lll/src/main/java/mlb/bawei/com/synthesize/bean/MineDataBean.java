package mlb.bawei.com.synthesize.bean;

/**
 * @author
 * @date 2018/12/30
 */
public class MineDataBean {
    Integer integer;
    String name;

    public MineDataBean(Integer integer, String name) {
        this.integer = integer;
        this.name = name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
