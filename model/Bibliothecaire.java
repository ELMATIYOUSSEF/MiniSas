package model;


public class Bibliothecaire extends Person {
    private String email ;
    private  String password ;


    public Bibliothecaire(String name, String Email , String password){
        super( name );
        this.email = Email ;
        this.password = password ;
    }
    public Bibliothecaire(int id,String name, String Email ){
        super(id, name );
        this.email = Email ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
