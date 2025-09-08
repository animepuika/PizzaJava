public class User extends UserTypes {

    public User(IUserType usertype) {
        super(usertype);
    }

    public void setUserType(IUserType usertype) {  // <-- add this
        this.usertype = usertype;
    }

    @Override
    public void accessUser() {
        if (usertype != null) usertype.accessUser();
    }
}

