package ie.t0mm13b.droidstackmk2.helpers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

/***
 * Singleton class that takes care of the Retrofit client
 * 
 * @author t0mm13b
 *
 */
public class RetrofitClient {
	private static final String TAG = "RetrofitClient";
	private static final String SEURL = "http://api.stackexchange.com/2.1";
	//
	private static final String SEKEYP_NAME = "key";
	private static final String SEKEYP_VALUE = "eTSVGWl)FNM7y9hfqnyC6A((";
	//
	
	private static RestAdapter mRestAdapterClient;
	private static boolean isRestClientReady;
	//
	public static final int SE_MAX_PAGESIZE = 50;
	
	/***
	 * Private inner class that is a loader for the Retrofit clent
	 * @author t0mm13b
	 *
	 */
	private static class RetrofitClientLoader{
		private static final RetrofitClient mRetrofitClient = new RetrofitClient();
	}
	private RetrofitClient(){
		if (RetrofitClientLoader.mRetrofitClient != null) throw new IllegalStateException("Restful Client already initialized");
		isRestClientReady = false;
	}
	public static synchronized RetrofitClient getInstance(){
		return RetrofitClientLoader.mRetrofitClient;
	}
	public RestAdapter getRESTfulClient(){
		return mRestAdapterClient;
	}
	public void Initialize(Client clientReplacement){
		if (!isRestClientReady){
			GsonBuilder gb = new GsonBuilder(); 
			gb.registerTypeAdapter(String.class, new StringConverter()); 
			Gson gson = gb.create();
			if (clientReplacement != null){
				mRestAdapterClient = new RestAdapter.Builder()
				.setRequestInterceptor(new RetrofitRequestInterceptor())
				.setClient(clientReplacement)
				.setConverter(new GsonConverter(gson))
				.setServer(SEURL)
				.build();
			}else{
				mRestAdapterClient = new RestAdapter.Builder()
				.setRequestInterceptor(new RetrofitRequestInterceptor())
				.setServer(SEURL)
				.setConverter(new GsonConverter(gson))
				.build();
			}
			
			isRestClientReady = true;
		}
	}
	public void SetLogging(LogLevel retrofitLogLevel){
		if (isRestClientReady){
			mRestAdapterClient.setLogLevel(retrofitLogLevel);
		}
	}
	/***
	 * Add in the API key dynamically alongside the other parameters at run-time.
	 * 
	 * @author t0mm13b
	 * @see http://stackoverflow.com/questions/20382253/dynamically-add-optional-parameters-to-api-requests
	 */
	private class RetrofitRequestInterceptor implements RequestInterceptor{

		@Override
		public void intercept(RequestFacade argReqFacade) {
			argReqFacade.addQueryParam(SEKEYP_NAME, SEKEYP_VALUE); // Add in the API key to get around quota limitations
		}
		
	}

	/***
	 * This class handles certain fields where string value might not be present and to return back "" instead of literal null.
	 * For {@link String} types only
	 * 
	 * @author t0mm13b
	 *
	 */
	public class StringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
		private static final String TAG = "StringConverter";
		@Override
		public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
			Utils.LogIt(TAG, "serialize(...)");
		    if (src == null){ 
		    	return new JsonPrimitive(""); 
		    }else{ 
		        return new JsonPrimitive(src.toString());
	        }
		}
		@Override
		public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
		        throws JsonParseException { 
			Utils.LogIt(TAG, "deserialize(...)");
			return json.getAsJsonPrimitive().getAsString(); 
		} 
	}
	
	/***
	 * Fake StackExchange Server Client
	 * 
	 * @author t0mm13b
	 *
	 */
	public class FakeStackExchange implements Client{
		private static final String TAG = "FakeStackExchange";
		// Could have hashmap of each json, some NOT all of them!
		@Override
		public Response execute(Request argRequest) throws IOException {
			// TODO Auto-generated method stub
			Uri uri = Uri.parse(argRequest.getUrl());

	        Log.d(TAG, "fetching uri: " + uri.toString());

	        String responseString = "";

	        if(uri.getPath().equals("/path/of/interest")) {
	            responseString = "JSON STRING HERE";
	        } else {
	            responseString = "OTHER JSON RESPONSE STRING";
	        }

	        return new Response(200, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));

		}
		
	}

}
