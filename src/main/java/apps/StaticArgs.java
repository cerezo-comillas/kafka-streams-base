package apps;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class StaticArgs {

	public static String numThreads = "1" ;
	public static String appID = "generic" ;
	public static String bootServers = "localhost:9092" ;
	public static String topicIn = "topic_in" ;
	public static String topicOut = "topic_out" ;
	
	
	public static void process(String[] args) {
		Options options = new Options();
		
		options.addOption(new Option("h", "help", false, "Print this help"));
		options.addOption(new Option("a", "appid", true, "Application ID [default generic]"));
		options.addOption(new Option("s", "boot-servers", true, "boot server to connect [default localhost:9092]"));
		options.addOption(new Option("i", "topic-in", true, "input topic [default topic_in]"));
		options.addOption(new Option("o", "topic-out", true, "output topic [default topic_out]"));
		options.addOption(new Option("t", "num-threads", true, "Number of threads in client [default 1]"));
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null ;
		try {
			cmd = parser.parse(options, args);
		} catch (org.apache.commons.cli.ParseException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
		if ( cmd.hasOption('h') || cmd.getOptions().length < 0 ) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("AppSimpleDateFormat", options);
			System.exit(0) ;
		}
    	
		if ( cmd.hasOption('a') ) {
			appID = cmd.getOptionValue('a') ;
		}
    	
		if ( cmd.hasOption('i') ) {
			topicIn = cmd.getOptionValue('i') ;
		}
		if ( cmd.hasOption('o') ) {
			topicOut = cmd.getOptionValue('o') ;
		}
		if ( cmd.hasOption('s') ) {
			bootServers = cmd.getOptionValue('s') ;
		}

		if ( cmd.hasOption('t') ) {
			numThreads = cmd.getOptionValue('t') ;
		}

		
		
	}
	
	
}
