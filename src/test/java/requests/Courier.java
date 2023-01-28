package requests;

public class Courier {

    private String login;
    private String password;
    private String firstName;


    //Конструктор со всеми параметрами
    public Courier(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    //Конструктор без обязательного поля
    public Courier(String password){

        this.password = password;
    }

    public Courier(String login, String password){
        this.login = login;
        this.password = password;
    }

    //Конструктор без параметров
    public Courier(){
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
