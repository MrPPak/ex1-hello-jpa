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

            Movie movie = new Movie();
            movie.setDirector("ccccc");
            movie.setActor("dddddd");
            movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);

            em.persist(movie);

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
