import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.ResultSet;
class ResultSetToJSONArrayConverter{
public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.add(obj);
            }
        }
        return jsonArray;
    }
	}