package cn.itcast.domain;
//管理员实体类
public class Manager {
    private String manId;
    private String manPassword;

    public String getManId() {
        return manId;
    }

    public void setManId(String manId) {
        this.manId = manId;
    }

    public String getManPassword() {
        return manPassword;
    }

    public void setManPassword(String manPassword) {
        this.manPassword = manPassword;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "manId='" + manId + '\'' +
                ", manPassword='" + manPassword + '\'' +
                '}';
    }
}
