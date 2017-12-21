package salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);

	}



	@Bean
	public CommandLineRunner initGamePlayer(PlayerRepository playerRepo,
											GameRepository gameRepo,
											GamePlayerRepository gamePlayerRepo,
											ShipRepository shipRepo,
											SalvoRepository salvoRepo,
											ScoreRepository scoreRepo) {
		return (args) -> {
			// save a couple of customers

			Date fecha1= new Date();
			Date fecha2 = Date.from(fecha1.toInstant().plusSeconds(3600));
			Date fecha3 = Date.from(fecha1.toInstant().plusSeconds(7200));

			List<String> shipslocation = Arrays.asList("A1", "A2", "A3");
			List<String> shipslocation2= Arrays.asList("B6", "B7", "B8","B9");
			List<String> shipslocation3= Arrays.asList("C1", "C2");
			List<String> shipslocation4=Arrays.asList("D9","D10");
			List<String> shipslocation5=Arrays.asList("I4","I5","I6");

			List<String> salvolocation1= Arrays.asList("B5","D3");
			List<String> salvolocation2= Arrays.asList("E7","G2", "A1", "A3");
			List<String> salvolocation3= Arrays.asList("H6");
			List<String> salvolocation4= Arrays.asList("H8", "H7", "F5", "A1","A3");

			Player player1= new Player("jbauer@ctu.gov","Jack","Bauer");
			Player player2= new Player("c.obrianB@ctu.gov","Chloe","OBrian");
			Player player3= new Player("Kim_bauer@gmail.com","Kim","Bauer");
			Player player4= new Player("t.almeida@ctu.gov","Tony","Almeida");
			Player player5= new Player("nickB@hotmail.com","Nick","Parson");

			Game game1= new Game(fecha1);
			Game game2= new Game(fecha2);
			Game game3= new Game(fecha3);

			GamePlayer gameplay1 = new GamePlayer(player1,game3);
			GamePlayer gameplay2 = new GamePlayer(player2,game3);
			GamePlayer gameplay3 = new GamePlayer(player3,game1);
			GamePlayer gameplay4 = new GamePlayer(player4,game1);
			GamePlayer gameplay5 = new GamePlayer(player5,game2);




			Ship cruiser=new Ship("cruiser",shipslocation, gameplay1);
			Ship patrol= new Ship("patrol",shipslocation2 ,gameplay1);
			Ship destroyer=new Ship("destroyer",shipslocation3,gameplay4);

			Ship cruiser1=new Ship("cruiser",shipslocation2, gameplay2);
			Ship patrol1= new Ship("patrol",shipslocation ,gameplay3);
			Ship petrol= new Ship("petrol",shipslocation4, gameplay3);
			Ship petrol1= new Ship("petrol", shipslocation5,gameplay2);
			Ship petrol3= new Ship("petrol", shipslocation3,gameplay1);

			Salvo salvo1= new Salvo(1,salvolocation1,gameplay1);
			Salvo salvo2= new Salvo(2,salvolocation2,gameplay1);
			Salvo salvo3= new Salvo(3,salvolocation3,gameplay1);
			Salvo salvo4= new Salvo(3,salvolocation4,gameplay2);
			Salvo salvo5= new Salvo(2,salvolocation3,gameplay3);
			Salvo salvo6= new Salvo(1,salvolocation3,gameplay4);
			Salvo salvo7= new Salvo(2,salvolocation2,gameplay2);

			Score score1=new Score(0.5,fecha2,game3,player1);
			Score score11=new Score(1,fecha2,game3,player1);
			Score score3=new Score(0,fecha3,game3,player1);
			Score score2=new Score(1.5,fecha3,game3,player2);

			gameRepo.save(game1);
			gameRepo.save(game2);
			gameRepo.save(game3);

			playerRepo.save(player1);
			playerRepo.save(player2);
			playerRepo.save(player3);
			playerRepo.save(player4);
			playerRepo.save(player5);

			gamePlayerRepo.save(gameplay1);
			gamePlayerRepo.save(gameplay2);
			gamePlayerRepo.save(gameplay3);
			gamePlayerRepo.save(gameplay4);
			gamePlayerRepo.save(gameplay5);

			shipRepo.save(cruiser);
			shipRepo.save(patrol);
			shipRepo.save(destroyer);
			shipRepo.save(cruiser1);
			shipRepo.save(patrol1);
			shipRepo.save(petrol);
			shipRepo.save(petrol1);
			shipRepo.save(petrol3);

			salvoRepo.save(salvo1);
			salvoRepo.save(salvo2);
			salvoRepo.save(salvo3);
			salvoRepo.save(salvo4);
			salvoRepo.save(salvo5);
			salvoRepo.save(salvo6);
			salvoRepo.save(salvo7);

			scoreRepo.save(score1);
			scoreRepo.save(score2);
			scoreRepo.save(score3);
			scoreRepo.save(score11);


		};
	}


}
