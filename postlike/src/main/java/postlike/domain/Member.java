package postlike.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();
}
