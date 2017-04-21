package Entities;

/**
 * Created by omyag on 28.03.2017.
 */
public class Sdelki {
    private int number;
    private String dVidachi;
    private String dSdachi;
    private int client;
    private int auto;
    private int manager;
    private String free;

    public Sdelki(int number, String dVidachi, String dSdachi, int client, int auto, int manager, String free) {
        this.number = number;
        this.dVidachi = dVidachi;
        this.dSdachi = dSdachi;
        this.client = client;
        this.auto = auto;
        this.manager = manager;
        this.free = free;
    }

    public Sdelki(){

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getdVidachi() {
        return dVidachi;
    }

    public void setdVidachi(String dVidachi) {
        this.dVidachi = dVidachi;
    }

    public String getdSdachi() {
        return dSdachi;
    }

    public void setdSdachi(String dSdachi) {
        this.dSdachi = dSdachi;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Сделки{" +
                "Номер=" + number +
                ", Дата выдачи='" + dVidachi + '\'' +
                ", Дата сдачи='" + dSdachi + '\'' +
                ", Клиент=" + client +
                ", Автомобиль=" + auto +
                ", Машину выдал=" + manager +
                ", Доступность='" + free + '\'' +
                '}';
    }
}
