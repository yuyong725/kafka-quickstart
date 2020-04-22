package cn.javadog.kafka.quickstart.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author 余勇
 * @date 2020年04月22日 20:48:00
 */
public class AdvancedCompanySerializer implements Serializer<Company> {

	@Override
	public byte[] serialize(String topic, Company data) {
		if (data == null) {
			return null;
		}
		Schema schema = RuntimeSchema.getSchema(data.getClass());
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		byte[] protostuff = null;
		try {
			protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
		return protostuff;
	}
}
