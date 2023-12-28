package processors;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;


public class ProcessorSpeedMeter implements Processor<String, String, String, String> {
//    private KeyValueStore<String, Integer> kvStore;

    ProcessorContext<String, String> _context ;
    
    static int count = 0 ;
    
    @Override
    public void init(final ProcessorContext<String, String> context) {
    	_context = context ;
    	register(this) ;
    }

   static ArrayList<ProcessorSpeedMeter> arrProcs = new ArrayList<ProcessorSpeedMeter>() ; 
    private static synchronized void register(ProcessorSpeedMeter obj) {
    	arrProcs.add(obj) ;
	}

    
    static SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd HH:mm:ss") ;

    
    static long sumRecords = 0 ;
    static long sumSize = 0 ;
    static public void printStats() {
    	
    	long sumRecordsAux = 0 ;
    	long sumSizeAux = 0 ;
    	
    	for (ProcessorSpeedMeter p: arrProcs) {
    		sumRecordsAux += p.numRecords ; 
    		sumSizeAux += p.size ; 
    	}
    	
    	System.out.println ( "STATS -> " + sdf.format(new Date())  + " Records: " + (sumRecordsAux - sumRecords) 
    			+ " size: " + (sumSizeAux - sumSize)/1024/1024 + " Mbytes" ) ;
    	sumRecords = sumRecordsAux ;
    	sumSize = sumSizeAux ;
    }
    
    
    
    long tsNext = 1000*(System.currentTimeMillis()/1000) ;
    
    long numRecords = 0 ;
    long size = 0 ;
    
    @Override
    public void process(final Record<String, String> record) {
    	
    	if ( numRecords == 0 ) {
    		System.out.println(record.value()) ;
    	}
    	
    	numRecords++ ;
    	size += record.value().length() ;
    }

    @Override
    public void close() {
        // close any resources managed by this processor
        // Note: Do not close any StateStores as these are managed by the library
    }


}
