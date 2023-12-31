package processors;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ProcessorJSON implements Processor<String, String, String, String> {

    ProcessorContext<String, String> _context ;
    
    @Override
    public void init(final ProcessorContext<String, String> context) {

    	_context = context ;
    	
    }

    
    JSONParser parser = new JSONParser();
    
    @Override
    public void process(final Record<String, String> record) {
    	
    	System.out.println("INPUT: key[" + record.key() + "] - data["  + record.value() + "]") ;

        final String content = record.value();
        String outText = "" ;
    	try {
    		JSONObject jsonObject = (JSONObject) parser.parse(content) ;
    		
    		for ( Object key : jsonObject.keySet() ) {
    			if (outText.length() > 0 ) outText+= "\n" ;
    			outText += key.toString() + ":" + jsonObject.get(key) ;
    		}
    		
    		
    	} catch (Exception ex) {
    		outText = "Content is not valid JSON [" + content + "] " + ex.toString() ;
    	}
    	
    	Record<String, String> recordOut = new Record<String, String>(record.key() , outText , System.currentTimeMillis()) ;
		_context.forward(recordOut);     	

    }

    @Override
    public void close() {
        // close any resources managed by this processor
        // Note: Do not close any StateStores as these are managed by the library
    }


}
