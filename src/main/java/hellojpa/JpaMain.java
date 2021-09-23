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
           /* Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member); */

           /* Member findMember = em.find(Member.class, 1L); // 조회
            findMember.setName("HelloJPA");*/

            /*em.remove(findMember); // 삭제*/

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(2)
                    .getResultList();

            for (Member member : result){
                // sout + tab -> syso 완성
                System.out.println("member.name = " + member.getName());
            }

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
