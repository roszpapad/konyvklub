package hu.roszpapad.konyvklub.commands;

public class AddressCommand {

    private Long id;
    //private UserCommand user;
    private String city;
    private String street;
    private String number;

    public AddressCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public UserCommand getUser() {
        return user;
    }

    public void setUser(UserCommand user) {
        this.user = user;
    }*/

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
