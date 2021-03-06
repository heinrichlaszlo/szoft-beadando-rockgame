package game.results;

import util.jpa.GenericJpaDao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * DAO class for the {@link GameResult} entity.
 */
public class GameResultDao extends GenericJpaDao<GameResult> {

    /**
     * The constructor that accepts no arguments.
     */
    public GameResultDao() {
        super(GameResult.class);
    }

    /**
     *
     *
     * @return the list of all results with respect the time spent for playing the game
     */
    @Transactional
    public List<GameResult> findAll() {
        return entityManager.createQuery("SELECT r FROM GameResult r order by r.duration asc", GameResult.class)
                .getResultList();
    }
}
