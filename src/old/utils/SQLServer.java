package old.utils;
//package com.wms.manhattan.utils;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.json.JSONObject;
//
//public class SQLServer {
//
//	protected static final Logger log = LogManager.getLogger("SQLServer");
//
//	/**
//	 * Execute a SQL call and return results as JSON.
//	 * 
//	 * @param url URL of a data handler.
//	 * @param query SQL query to execute
//	 * @return A JSONObject containing results
//	 */
//	public static JSONObject executeSqlRemotely(String url, String query) throws Exception {
//
//		return executeSqlRemotely(url, query, null);
//	}
//
//	/**
//	 * Execute a SQL call and return results as JSON using a specific connection string.
//	 * 
//	 * @param url URL of a data handler.
//	 * @param query SQL query to execute
//	 * @param connectionStringName Name of connection string to use (optional, pass null to use first one)
//	 * @return A JSONObject containing results
//	 */
//	public static JSONObject executeSqlRemotely(String url, String query, String connectionStringName) throws Exception {
//
//		/*
//		Here is an example
//
//		@Test 
//		public void setIt_Sql_Test() {
//			
//			TestStatus testStatus = getTestStatus();
//	
//			// Retrieve raw query from configuration (query may contain replaceable parameters like:
//			//  where Ranks = 1 and GRP_NAME_C = '%s' and TieStatus = 'Pre-Tied'
//			//
//			String query = getContext().getStringProperty("query-PogTieStatus");
//	
//			// Example of replacing a parameter in query
//			//
//			query = String.format(query, "A002MIT");
//			System.out.println(query);
//	
//			// Retrieve data handler endpoint from configuration
//			//
//			String dataHandlerEndpoint = getContext().getStringProperty("dataHandler");
//			System.out.println(dataHandlerEndpoint);
//	
//			// Execute the query
//			try {
//				JSONObject obj = SQLServer.executeSqlRemotely(dataHandlerEndpoint, query);
//	
//				JSONObject error = obj.getJSONObject("Error");
//				System.out.println(error);
//	
//				JSONArray msg = obj.getJSONArray("Data");
//				for (int i = 0; i < msg.length(); ++i) {
//					JSONObject rec = msg.getJSONObject(i);
//	
//					String groupName = rec.getString("GRP_NAME_C");
//					String tieStatus = rec.getString("TieStatus");
//	
//					System.out.println(groupName + " " + tieStatus);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	
//			assertAllValidations(testStatus);
//		}
//		*/
//
//		log.debug("Executing SQL call to " + url);
//		log.debug(" SQL Query: " + query);
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(url);
//
//		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//		urlParameters.add(new BasicNameValuePair("query", query));
//		if (connectionStringName != null) {
//			urlParameters.add(new BasicNameValuePair("connection", connectionStringName));			
//		}
//		post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//		HttpResponse response = client.execute(post);
//
//		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//		StringBuffer result = new StringBuffer();
//		String line = "";
//		while ((line = rd.readLine()) != null) {
//			result.append(line);
//		}
//
//		log.debug(" JSON Result: " + result.toString());
//		return new JSONObject(result.toString());
//	}
//}
