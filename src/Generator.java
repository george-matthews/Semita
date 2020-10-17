import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.almasb.grammy.Grammar;
import com.almasb.grammy.Grammy;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;


import world.Actor;
import world.Relation;

public class Generator {
	
	static Random r = new Random();
	
	public static void generate(Actor actor) throws IOException {
		Lorem lorem = LoremIpsum.getInstance();
		String json = FileUtils.readFileToString(new File("resources/grammar/grammar.json"));
		
		//Populate data from actor
		json = json.replace("$actorName", actor.fullName);
		json = json.replace("$birthDate", formatDate(actor.birthDate));
		json = json.replace("$deathDate", formatDate(actor.deathDate));
		json = json.replace("$role", actor.position != null ? actor.position.description : "Senator");
		json = json.replace("$descriptor", actor.getDescriptor());
		json = json.replace("$locationType", actor.getLocation().type);
		json = json.replace("$locationName", actor.getLocation().name);
		json = json.replace("$identifier", actor.cognomen != null ? actor.cognomen : actor.familyName);
		json = json.replace("$contribution", "{"+actor.getDescriptor()+"}");
		json = json.replace("$lorem", lorem.getTitle(1, 3));
		json = json.replace("$randSubtext", lorem.getTitle(1,1));
		json = json.replace("$randOrigin", lorem.getTitle(1,1));
		
		//Handle listed relation
		Relation relation = actor.relations.get(0);
		json = json.replace("$relationName", relation.subject.fullName);
		json = json.replace("$relation", "{"+relation.links.get(0).description+"}");
		json = json.replace("$relText", "{"+relation.links.get(0).description+"Desc}");
		json = json.replace("$relPosition", relation.subject.position != null ? relation.subject.position.description : "Senator");
		
		String startGrammar = actor.cognomen != null ? "startCog" : "startNoCog";
		
		Grammar grammar = Grammy.createGrammar(json);
		webify(grammar.flatten(startGrammar), actor, grammar);
		
	}
	
	
	public static void webify(String genText, Actor actor, Grammar grammar) throws IOException {
		File htmlTemplateFile = new File("resources/template/template.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String title = actor.fullName;
		
		htmlString = htmlString.replace("$title", title);
		htmlString = htmlString.replace("$header", title);
		htmlString = htmlString.replace("$body", genText);
		htmlString = htmlString.replace("$imgNum", Integer.toString(r.nextInt(30)));
		htmlString = htmlString.replace("$subtext",  grammar.flatten("subtext"));
		
		File newHtmlFile = new File("output/"+(actor.position != null ? actor.position.description : "Senator")+"/"+title+".html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		
	}
	
	private static String formatDate(GregorianCalendar date) {
		return date.get(date.DAY_OF_MONTH) + " "
				+ date.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.US) + " "
				+ date.get(date.YEAR);
	}

}
