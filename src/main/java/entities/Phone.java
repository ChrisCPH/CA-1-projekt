package entities;

import javax.persistence.*;

@Entity
@NamedQuery(name ="Phone.deleteAllRows",query = "DELETE from Phone ")
@Table(name = "Phone")
public class Phone {
    public Phone() {
    }

    public Phone(String number, String description) {
        this.number  = number;
        this.description = description;
    }

    public Phone(String number, String description, Person person) {
        this.number = number;
        this.description = description;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false, length = 45)
    private String number;

    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", person=" + person +
                '}';
    }

}