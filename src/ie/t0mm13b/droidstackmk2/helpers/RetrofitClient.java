package ie.t0mm13b.droidstackmk2.helpers;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Client;

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
			if (clientReplacement != null){
				mRestAdapterClient = new RestAdapter.Builder()
				.setRequestInterceptor(new RetrofitRequestInterceptor())
				.setClient(clientReplacement)
				.setServer(SEURL)
				.build();
			}else{
				mRestAdapterClient = new RestAdapter.Builder()
				.setRequestInterceptor(new RetrofitRequestInterceptor())
				.setServer(SEURL)
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
}
