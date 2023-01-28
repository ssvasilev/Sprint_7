package requests;

public class Order {

    private  String firstNameSet;
    private  String lastNameSet;
    private  String addressSet;
    private  String metroStationSet;
    private  String phoneSet;
    private  String rentTimeSet;
    private  String deliveryDateSet;
    private  String commentSet;
    private  String colorSet;

    //Конструктор со всеми параметрами
    public Order(String firstNameSet, String lastNameSet, String addressSet, String metroStationSet, String phoneSet, String rentTimeSet, String deliveryDateSet, String commentSet, String colorSet) {
        this.firstNameSet = firstNameSet;
        this.lastNameSet = lastNameSet;
        this.addressSet = addressSet;
        this.metroStationSet = metroStationSet;
        this.phoneSet = phoneSet;
        this.rentTimeSet = rentTimeSet;
        this.deliveryDateSet = deliveryDateSet;
        this.commentSet = commentSet;
        this.colorSet = colorSet;
    }

    //Конструктор без параметров
    public Order() {
    }

    public String getFirstNameSet() {
        return firstNameSet;
    }

    public void setFirstNameSet(String firstNameSet) {
        this.firstNameSet = firstNameSet;
    }

    public String getLastNameSet() {
        return lastNameSet;
    }

    public void setLastNameSet(String lastNameSet) {
        this.lastNameSet = lastNameSet;
    }

    public String getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(String addressSet) {
        this.addressSet = addressSet;
    }

    public String getMetroStationSet() {
        return metroStationSet;
    }

    public void setMetroStationSet(String metroStationSet) {
        this.metroStationSet = metroStationSet;
    }

    public String getPhoneSet() {
        return phoneSet;
    }

    public void setPhoneSet(String phoneSet) {
        this.phoneSet = phoneSet;
    }

    public String getRentTimeSet() {
        return rentTimeSet;
    }

    public void setRentTimeSet(String rentTimeSet) {
        this.rentTimeSet = rentTimeSet;
    }

    public String getDeliveryDateSet() {
        return deliveryDateSet;
    }

    public void setDeliveryDateSet(String deliveryDateSet) {
        this.deliveryDateSet = deliveryDateSet;
    }

    public String getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(String commentSet) {
        this.commentSet = commentSet;
    }

    public String getColorSet() {
        return colorSet;
    }

    public void setColorSet(String colorSet) {
        this.colorSet = colorSet;
    }
}
