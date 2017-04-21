package Entities;

/**
 * Created by omyag on 28.03.2017.
 */
public class Clients {

    private int id;
    private String fio;
    private String doc;


    public Clients(int id, String fio, String doc) {
        this.id = id;
        this.fio = fio;
        this.doc = doc;
    }

    public Clients(){

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

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    @Override
    public String toString() {
        return "Клиенты{" +
                "id=" + id +
                ", ФИО='" + fio + '\'' +
                ", Документ='" + doc + '\'' +
                '}';
    }
}
