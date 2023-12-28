# kafka-streams-101

To compile a standalone jar with all the depependencies included : ``mvn clean install assembly:single`` 

### AppSimpleDateFormat
This is an example of Kafka Streams use of Processor API.<br>
The message in Kafka is supossed to have a timestamp in seconds form 1970 and is converted in the call to the processor to yyyy-mm-dd hh24:mi:ss format

To load data into input topic:<br>
``while true``<br>
``do``<br>
``  echo $(date +%s)``<br>
``  sleep 5``<br>
``done | ./kafka-console-producer.sh --bootstrap-server worker01:9092 --topic topic_in``<br>

To view data from output topic:<br>
``./kafka-console-consumer.sh --bootstrap-server worker01:9092 --topic topic_out``
