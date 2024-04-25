package thecommerceproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;

    @Column(nullable = false)
    private String memberPwd;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;
}


