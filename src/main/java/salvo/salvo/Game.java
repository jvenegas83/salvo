package salvo.salvo;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;



@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long game_id;
    private Date gameDate;
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<GamePlayer> players;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER) //game
    Set<Score> scores;

    public Game() {
    }
    public Game(Date gameDate) {
        this.gameDate = gameDate;

    }


    public Set<GamePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<GamePlayer> players) {
        this.players = players;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<Score> getScore() {
        return scores;
    }

    public void setScore(Set<Score> score) {
        this.scores = score;
    }

    public Date getDate() {
        return gameDate;
    }

    public void setDate(Date date) {
        this.gameDate = date;
    }

    public void addGamePlayer(GamePlayer gm) {
        gm.setGame(this);
        players.add(gm);
    }

    public long getId(){
        return game_id;
    }
    public Set<GamePlayer> getgamePlayer(){
        return players;
    }
}
