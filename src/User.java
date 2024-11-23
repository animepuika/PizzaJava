
public class User extends UserTypes {

	public User(IUserType usertype) {
		super(usertype);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void accessUser() {
		// TODO Auto-generated method stub
		usertype.accessUser();
		
	}

}
