package salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class SalvoController {


    @Autowired
    GameRepository RepoGame;


    @RequestMapping("/games")

    public List<Object> getAllGames() {
        return RepoGame
                .findAll()
                .stream()
                .map(g -> makeGameDTO(g))
                .collect(Collectors.toList());
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        Set<GamePlayer> gplayer = game.getgamePlayer();
        dto.put("id", game.getId());
        dto.put("created", game.getDate());
        dto.put("gamePlayers", gplayer.stream()
                                        .map(g -> makePlayerDTO(g))
                                        .collect(Collectors.toList()));

        return dto;
    }
    public Map<String,Object> getPlayers(Player player) {
        Map<String, Object> gp = new LinkedHashMap<>();
        gp.put("id", player.getId());
        gp.put("email", player.getUserName());

        return gp;
    }
    private Map<String, Object> makePlayerDTO(GamePlayer playerdto) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", playerdto.get_id());
        dto.put("player", getPlayers(playerdto.getPlayer()));
        if(!playerdto.getScore().isEmpty()) {
            Player player=RepoPlayer.findOne(playerdto.get_id());
            if(player!=null) {
                dto.put("score", player.getScores()
                        .stream()
                        .map(sc -> makeScoreDTO(sc))
                        .collect(Collectors.toList()));
            }
        }
        return dto;
    }

    private Map<String, Object> makeScoreDTO(Score score){
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("finish date", score.getFinishDate());
        dto.put("score", score.getScore());
        return dto;
    }

    @Autowired
    PlayerRepository RepoPlayer;
    @RequestMapping("/leaderboard")
    public List<Object> getAllPlayers() {
        return RepoPlayer
                .findAll()
                .stream()
                .map(g -> makePlayerListDTO(g))
                .collect(Collectors.toList());
    }
    private Map<String, Object>makePlayerListDTO(Player p){
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", p.getId());
        dto.put("email", p.getUserName());
        dto.put("score", getScores(p));
        return dto;
    }

    //filtrar por player id ya que sino los pilla todos.
    private Map<String, Object>getScores(Player p){
        Map<String, Object> dto= new LinkedHashMap<>();
        dto.put("ties", getScoreTies(p));
        dto.put("win", getScoreWin(p));
        dto.put("lost", getScoreLost(p));
        dto.put("total", getTotalScore(p));
        return dto;
    }
    private int getScoreTies(Player p){
        int ties=0;
        for (Score entry : p.getScores()){
            if(entry.getScore()==0.5)
                ties++;

        }
        return ties;
    }
    private int getScoreWin(Player p){
        int win=0;
        for(Score entry: p.getScores()){
            if(entry.getScore()==1){
                win++;
            }
        }
        return win;
    }
    private int getScoreLost(Player p){
        int lost=0;
        for (Score entry : p.getScores()){
            if(entry.getScore()==0)
                lost++;

        }
        return lost;
    }
    private double getTotalScore(Player p){
        double total=0;
        for (Score entry : p.getScores()){
            total+=entry.getScore();
        }
        return total;
    }
    @Autowired
    GamePlayerRepository RepoGamePlayer;

    @RequestMapping(method = RequestMethod.GET, value="/game_view/{gamePlayerNumber}")
    public Map<String, Object> findGameInfo(@PathVariable long gamePlayerNumber) {
        Map<String, Object> gp = new LinkedHashMap<>();
        GamePlayer gData=RepoGamePlayer.findOne(gamePlayerNumber);
        gp.put("id", gData.getGame().getId());
        gp.put("created", gData.getGame().getDate());
        gp.put("gamePlayers", gData.getGame().getgamePlayer()
                                .stream()
                                .map(player -> makePlayerDTO(player))
                                .collect(Collectors.toList()));

        gp.put("ships", gData.getShips()
                                .stream()
                                .map(ship -> makeShipDTO(ship))
                                .collect(Collectors.toList()));
        gp.put("salvo", gData.getGame().getgamePlayer()
                                .stream()
                                .map(salvo -> makeSalvoDTO(salvo))
                                .collect(Collectors.toList()));
        return gp;
    }
    private Map<String, Object> makeShipDTO(Ship sh) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", sh.getType());
        dto.put("shipLocations", sh.getLocations());
        return dto;
    }
    private Map<String, Object>makeSalvoDTO(GamePlayer sv){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id",sv.get_id());
        dto.put("turns",sv.getSalvoes()
                        .stream()
                        .map(sl -> SalvoDTO(sl))
                        .collect(Collectors.toList()));
        return dto;

    }
    private Map<String,Object>SalvoDTO(Salvo sl){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turnNumber", sl.getTurnNumber());
        dto.put("salvoLocations", sl.getSalvoLocations());
        return dto;
    }

}
