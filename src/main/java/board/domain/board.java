package board.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @NotNull
    public String title;
    @NotNull
    @Type(type="org.hibernate.type.TextType")
    public String content;
}
