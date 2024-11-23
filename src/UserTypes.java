
public abstract class UserTypes {
protected IUserType usertype;
	
	protected UserTypes(IUserType usertype) {
		this.usertype = usertype;
	}
public abstract void accessUser();
}
