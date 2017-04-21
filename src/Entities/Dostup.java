package Entities;

/**
 * Created by omyag on 28.03.2017.
 */
public class Dostup {

    private int number;
    private int auto;
    private String free;

    public Dostup(int number, int auto, String free) {
        this.number = number;
        this.auto = auto;
        this.free = free;
    }

    public Dostup(){

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Доступность автомобилей{" +
                "Номер=" + number +
                ", Автомобиль=" + auto +
                ", Доступность='" + free + '\'' +
                '}';
    }
}
