package hellojpa;

import org.hibernate.internal.build.AllowSysOut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try { // ctrl + alt + t
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 새로운 팀B
            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            // 회원1에 새로운 팀B 설정
            member.setTeam(teamB);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 라인 이동 : alt + shift + 방향키
            em.close();
        }

        emf.close();
    }
}
