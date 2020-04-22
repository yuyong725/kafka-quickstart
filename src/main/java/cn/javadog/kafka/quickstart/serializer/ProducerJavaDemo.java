package cn.javadog.kafka.quickstart.serializer;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author 余勇
 * @date 2020年04月21日 18:29:00
 */
public class ProducerJavaDemo {

	public static final String brokerList = "127.0.0.1:9092";
	public static final String topic = "serializer-topic";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		properties.put("value.serializer", "cn.javadog.kafka.quickstart.serializer.DemoSerializer");
		properties.put("value.serializer", "cn.javadog.kafka.quickstart.serializer.AdvancedCompanySerializer");
		properties.put("bootstrap.servers", brokerList);

		Producer<String,Company> producer = new KafkaProducer<>(properties);

		while (true) {
			Company company = new Company("calm data", "hangzhou");

			ProducerRecord<String, Company> producerRecord = new ProducerRecord<>(topic, "key123", company);
			try {
				producer.send(producerRecord, (metadata, exception) -> {
					System.out.print(metadata.offset()+"    ");
					System.out.print(metadata.topic()+"    ");
					System.out.println(metadata.partition());
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
