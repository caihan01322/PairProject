package Entity;

public class LoginReturn {
    int loginStatus;
	String name;//返回登陆者名字
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public int getLoginStatus() {
        return loginStatus;
    }
    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }


}
