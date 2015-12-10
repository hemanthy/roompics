import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class GenerateJsonData {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		PresistCrawelData presistCrawelData = new PresistCrawelData();
		
		GenerateJsonData generateJsonData = new GenerateJsonData();
		
		List<JsonData> JsonDataList = new ArrayList<JsonData>();
		 for(int i=1;i<900;i++){
			 String title = presistCrawelData.getMobileTitleById(i);
			 JsonData jsonData = generateJsonData.new JsonData();
			 jsonData.setTitle(title);
			 jsonData.setValue(title);
			 JsonDataList.add(jsonData);
		 }
		 Gson gson = new Gson();
		 String jsonData1 = gson.toJson((Object)JsonDataList);
		 System.out.println();
		 System.out.println(jsonData1);
		
	}
	
	class JsonData {
		private String title;
		
		private String value;
		
		

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
}
