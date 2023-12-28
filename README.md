# kafka-streams-101

## AppSimpleDateFormat
This is an example of Kafka Streams use of Processor API.
The message in Kafka is supossed to have a timestamp in seconds form 1970 and is converted in the call to the processor to yyyy-mm-dd hh24:mi:ss format

while true
do 
  echo $(date +%s)
  sleep 5
done | ./kafka-console-producer.sh --bootstrap-server worker01:9092 --topic topic_in

./kafka-console-consumer.sh --bootstrap-server worker01:9092 --topic topic_out
