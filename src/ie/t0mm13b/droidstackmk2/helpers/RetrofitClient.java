package ie.t0mm13b.droidstackmk2.helpers;


import ie.t0mm13b.droidstackmk2.DroidStackMk2App;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;


import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

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
	private static OkHttpClient mOkHttpClient;
	private static HttpResponseCache mHttpRespCache;
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
	public void Initialize(Client clientReplacement) throws IOException{
		if (!isRestClientReady){
			mOkHttpClient = new OkHttpClient();
			File fCache;
//			if (Utils.isExternalStorageWritable()){
//				fCache = Environment.getDownloadCacheDirectory();
//				Utils.LogIt(TAG, "Initialize(...) - using External Storage Cache Directory");
//			}else{
				fCache = DroidStackMk2App.getAppContext().getCacheDir();
				Utils.LogIt(TAG, "Initialize(...) - using Internal Storage Cache Directory");
//			}
			mHttpRespCache = new HttpResponseCache(fCache, 1024);
			mOkHttpClient.setOkResponseCache(mHttpRespCache);
			GsonBuilder gb = new GsonBuilder(); 
			gb.registerTypeAdapter(String.class, new StringConverter()); 
			Gson gson = gb.create();
			if (clientReplacement != null){
				mRestAdapterClient = new RestAdapter.Builder()
				.setRequestInterceptor(new RetrofitRequestInterceptor())
				.setClient(clientReplacement)
				.setConverter(new GsonConverter(gson))
				.setEndpoint(SEURL)
				.build();
			}else{
				mRestAdapterClient = new RestAdapter.Builder()
				.setRequestInterceptor(new RetrofitRequestInterceptor())
				.setEndpoint(SEURL)
				.setClient(new OkClient(mOkHttpClient))
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
	
	public static boolean IsClientReady(){
		return isRestClientReady;
	}
	
	public boolean Shutdown(){
		boolean rv = false;
		isRestClientReady = false;
		if (mHttpRespCache != null){
			boolean bHttpRespCacheClosd = false;
			boolean bHttpRespCacheDeletd = false;
			try {
				mHttpRespCache.close();
				bHttpRespCacheClosd = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bHttpRespCacheClosd){
				try {
					mHttpRespCache.delete();
					bHttpRespCacheDeletd = true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rv = (bHttpRespCacheDeletd && bHttpRespCacheClosd);
			if (rv){
				Utils.LogIt(TAG, "Shutdown(...) - Closed HttpResponseCache and deleted");
			}else{
				Utils.LogIt(TAG, "Shutdown(...) - Failed to close HttpResponseCache and delete");
			}
		}
		return rv;
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
			argReqFacade.addHeader("Cache-Control", "max-age=300");
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
//			Utils.LogIt(TAG, "serialize(...)");
		    if (src == null){ 
		    	return new JsonPrimitive(""); 
		    }else{ 
		        return new JsonPrimitive(src.toString());
	        }
		}
		@Override
		public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
		        throws JsonParseException { 
//			Utils.LogIt(TAG, "deserialize(...)");
			return json.getAsJsonPrimitive().getAsString(); 
		} 
	}

}
