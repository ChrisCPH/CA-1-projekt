package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonId implements Serializable {
    private static final long serialVersionUID = 2089319229147332654L;
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "PersonHobby_id", nullable = false)
    private Integer personhobbyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonhobbyId() {
        return personhobbyId;
    }

    public void setPersonhobbyId(Integer personhobbyId) {
        this.personhobbyId = personhobbyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonId entity = (PersonId) o;
        return Objects.equals(this.personhobbyId, entity.personhobbyId) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personhobbyId, id);
    }

}