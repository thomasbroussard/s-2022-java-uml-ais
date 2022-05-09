package fr.epita.exercises.titanic.datamodel;

public class Passenger {

    private int pclass;
    private int survived;
    private int passengerId;
    private String sex;
    private int age;
    private String embarked;

    public Passenger(int pclass, int survived, int passengerId, String sex, int age, String embarked) {
        this.pclass = pclass;
        this.survived = survived;
        this.passengerId = passengerId;
        this.sex = sex;
        this.age = age;
        this.embarked = embarked;
    }

    public int getPclass() {
        return pclass;
    }

    public void setPclass(int pclass) {
        this.pclass = pclass;
    }

    public int getSurvived() {
        return survived;
    }

    public void setSurvived(int survived) {
        this.survived = survived;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }
}
