package Entities;

/**
 * Created by omyag on 28.03.2017.
 */
public class Sotrudniki {
    private int id;
    private String fio;
    private String dPriema;
    private String doljnost;


    public Sotrudniki(int id, String fio, String dPriema, String doljnost) {
        this.id = id;
        this.fio = fio;
        this.dPriema = dPriema;
        this.doljnost = doljnost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getdPriema() {
        return dPriema;
    }

    public void setdPriema(String dPriema) {
        this.dPriema = dPriema;
    }

    public String getDoljnost() {
        return doljnost;
    }

    public void setDoljnost(String doljnost) {
        this.doljnost = doljnost;
    }

    @Override
    public String toString() {
        return "Сотрудники{" +
                "id=" + id +
                ", ФИО='" + fio + '\'' +
                ", Дата приема='" + dPriema + '\'' +
                ", Должность='" + doljnost + '\'' +
                '}';
    }
}
