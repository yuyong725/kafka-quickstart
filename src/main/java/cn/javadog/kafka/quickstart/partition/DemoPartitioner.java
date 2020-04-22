package cn.javadog.kafka.quickstart.partition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

/**
 * @author 余勇
 * @date 2020年04月22日 23:20:00
 */
public class DemoPartitioner implements Partitioner {

	private final AtomicInteger atomicInteger = new AtomicInteger(0);

	@Override
	public void configure(Map<String, ?> configs) {}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
		int numPartitions = partitions.size();
		if (null == keyBytes || keyBytes.length<1) {
			return atomicInteger.getAndIncrement() % numPartitions;
		}
		//借用String的hashCode的计算方式
		int hash = 0;
		for (byte b : keyBytes) {
			hash = 31 * hash + b;
		}
		return hash % numPartitions;
	}

	@Override
	public void close() {}
}
