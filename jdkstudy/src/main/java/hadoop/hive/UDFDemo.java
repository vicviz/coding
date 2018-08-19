package hadoop.hive;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

/**
 * @author zhaowei
 */
@Description(
        name = "insertToJimdb",
        value = "_FUNC_(key, value) - The first parameter is a key string."
                + "The second parameter is a value. It returns true or false.",
        extended = "Example: SELECT _FUNC_(key, value) from table;"
)
public class UDFDemo extends GenericUDF implements java.io.Serializable {
    private static final long serialVersionUID = 231412312312582L;

    private ObjectInspector prefixOI;
    private ObjectInspector keyOI;
    private ObjectInspector valueOI;

    private StringObjectInspector prefixOIStr;
    private StringObjectInspector keyOIStr;
    private StringObjectInspector valueOIStr;

    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        if (objectInspectors.length != 3) {
            throw new UDFArgumentLengthException("should have 3 parameters");
        }
        prefixOI = objectInspectors[0];
        keyOI = objectInspectors[1];
        valueOI = objectInspectors[2];
        if (!(prefixOI instanceof StringObjectInspector)) {
            throw new UDFArgumentException("prefix should be string");
        }
        if (!(keyOI instanceof StringObjectInspector)) {
            throw new UDFArgumentException("key should be string");
        }
        if (!(valueOI instanceof StringObjectInspector)) {
            throw new UDFArgumentException("value should be string");
        }
        prefixOIStr = (StringObjectInspector) objectInspectors[0];
        keyOIStr = (StringObjectInspector) objectInspectors[1];
        valueOIStr = (StringObjectInspector) objectInspectors[2];
        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }

    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        if(deferredObjects.length != 3) {
            return null;
        }
        if(deferredObjects[0].get() == null || deferredObjects[1].get() == null || deferredObjects[2].get() == null) {
            return null;
        }
        String prefix = prefixOIStr.getPrimitiveJavaObject(deferredObjects[0].get());
        String key = keyOIStr.getPrimitiveJavaObject(deferredObjects[1].get());
        String value = valueOIStr.getPrimitiveJavaObject(deferredObjects[2].get());
//        redis.set((prefix + key), value);
        return prefix + key + ":" + value;
    }

    @Override
    public String getDisplayString(String[] strings) {
        StringBuilder sb = new StringBuilder();
        if (strings != null) {
            for (String s: strings) {
                sb.append(s).append(",");
            }
        }
        sb.append("in display");
        return sb.toString();
    }
}
