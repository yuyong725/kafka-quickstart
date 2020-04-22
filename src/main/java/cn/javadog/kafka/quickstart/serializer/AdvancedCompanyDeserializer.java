package cn.javadog.kafka.quickstart.serializer;

import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author 余勇
 * @date 2020年04月22日 20:48:00
 */
public class AdvancedCompanyDeserializer implements Deserializer<Company> {
	
	@Override
	public Company deserialize(String topic, byte[] data) {
		if (data == null) {
			return null;
		}
		Schema schema = RuntimeSchema.getSchema(Company.class);
		Company ans = new Company();
		ProtostuffIOUtil.mergeFrom(data, ans, schema);
		return ans;
	}
}
