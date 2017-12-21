package salvo.salvo;

import javax.persistence.*;
import java.util.Set;




@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long player_id;
    private String userName;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> games;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<Score> scores;



    public Player() {

    }

    public Player(String user, String first, String last) {
        this.userName = user;
        this.firstName = first;
        this.lastName = last;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;

    }
    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return userName + " " + firstName + " " + lastName;
    }

    public void addGamePlayer(GamePlayer gm) {
        gm.setPlayer(this);
        games.add(gm);
    }

    public long getId() {
        return player_id;
    }

    public Set<GamePlayer> getgameplayer() {
        return games;
    }
}

