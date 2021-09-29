package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
