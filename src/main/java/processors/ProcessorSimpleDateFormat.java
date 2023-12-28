package processors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;


public class ProcessorSimpleDateFormat implements Processor<String, String, String, String> {

    ProcessorContext<String, String> _context ;
    
    @Override
    public void init(final ProcessorContext<String, String> context) {

    	_context = context ;
    	
    }

    SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd HH:mm:ss") ;
    
    @Override
    public void process(final Record<String, String> record) {
    	
    	System.out.println("INPUT: key[" + record.key() + "] - data["  + record.value() + "]") ;

        final String content = record.value();
        String dateStr = "" ;
    	try {
	        	dateStr = "INPUT: key[" + record.key() + "] - data["  + record.value() + "]," 
    	+ " OUPUT[" + sdf.format(new Date(Long.parseLong(content)*1000)) + "]" ;
    	} catch (Exception ex) {
    		dateStr = "Content not a date in seconds from 1970 [" + content + "]" ;
    	}
    	
    	Record<String, String> recordOut = new Record<String, String>(record.key() , dateStr , System.currentTimeMillis()) ;
		_context.forward(recordOut);     	

    }

    @Override
    public void close() {
        // close any resources managed by this processor
        // Note: Do not close any StateStores as these are managed by the library
    }


}
