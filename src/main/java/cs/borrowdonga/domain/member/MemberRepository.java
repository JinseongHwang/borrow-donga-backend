package cs.borrowdonga.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findMemberByStudentNumber(String studentNumber);
}
