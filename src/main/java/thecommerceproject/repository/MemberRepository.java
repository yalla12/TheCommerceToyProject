package thecommerceproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thecommerceproject.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
