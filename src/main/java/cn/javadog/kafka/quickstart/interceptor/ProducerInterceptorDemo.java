package cn.javadog.kafka.quickstart.interceptor;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author 余勇
 * @date 2020年04月21日 17:13:00
 */
public class ProducerInterceptorDemo implements ProducerInterceptor<String,String> {
	private volatile long sendSuccess = 0;
	private volatile long sendFailure = 0;

	@Override
	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		if(record.value().length()<=0) {
			return null;
		}
		return record;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		if (exception == null) {
			sendSuccess++;
		} else {
			sendFailure ++;
		}
	}

	@Override
	public void close() {
		double successRatio = (double)sendSuccess / (sendFailure + sendSuccess);
		System.out.println("[INFO] 发送成功率="+String.format("%f", successRatio * 100)+"%");
	}

	@Override
	public void configure(Map<String, ?> configs) {}
}
