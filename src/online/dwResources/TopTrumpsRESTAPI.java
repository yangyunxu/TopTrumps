package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import basic.*;
import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input

public class TopTrumpsRESTAPI {

	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws IOException {
		launchGame();
	}

	@GET
	@Path("/launchGame")
	public void launchGame() throws IOException {
		Database.connection();
		Database.createTable();
	}

	@GET
	@Path("/numberGames")
	public int numberOfGames() throws IOException {
		return Database.getNumberOfGames();
	}

	@GET
	@Path("/numberHumanWins")
	public int numberOfHumanWins() throws IOException {
		return Database.getNumberOfHumanWins();
	}

	@GET
	@Path("/numberAiWins")
	public int getNumberOfAiWins() throws IOException {
		return Database.getNumberOfAIWins();
	}

	@GET
	@Path("/numberOfAverageDraws")
	public double numberOfAverageDraws() throws IOException {
		return Database.getAverageNumberOfDraws();
	}

	@GET
	@Path("/longestRounds")
	public int numberOfLongestRounds() throws IOException {

		return Database.getLongestGame();
	}




	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
}
