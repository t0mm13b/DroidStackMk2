package ie.t0mm13b.droidstackmk2.helpers;

import ie.t0mm13b.droidstackmk2.DroidStackMk2App;
import ie.t0mm13b.droidstackmk2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import android.net.Uri;
import android.util.Log;

/***
 * Fake StackExchange Server Client
 * 
 * @author t0mm13b
 *
 */
public class FakeStackExchange implements Client{
	private static final String TAG = "FakeStackExchange";
	private List<Header> fakeHeaders;
	private Map<String, Header> headerMap = new HashMap<String, Header>();
	// Could have hashmap of each json, some NOT all of them!
	public FakeStackExchange(){
		headerMap.put("Access-Control-Allow-Credentials", new Header("Access-Control-Allow-Credentials", "false"));
		headerMap.put("Access-Control-Allow-Methods", new Header("Access-Control-Allow-Methods", "GET, POST"));
		headerMap.put("Access-Control-Allow-Origin", new Header("Access-Control-Allow-Origin", "*"));
		headerMap.put("Cache-Control", new Header("Cache-Control", "private"));
		//headerMap.put("Content-Encoding", new Header("Content-Encoding", "gzip"));
		headerMap.put("Content-Type", new Header("Content-Type", "application/json; charset=utf-8"));
		headerMap.put("Content-Length", new Header("Content-Length", "0"));
	}
	@Override
	public Response execute(Request argRequest) throws IOException {
		// TODO Auto-generated method stub
		Uri uri = Uri.parse(argRequest.getUrl());

		Utils.LogIt(TAG, "execute(...) - Uri: " + uri.toString());

        String responseString = "";
        Utils.LogIt(TAG, "execute(...) - Uri.getPath() = " + uri.getPath());
        if (uri.getPath().startsWith("/2.1/users/206367")) responseString = DroidStackMk2App.getAppContext().getString(R.string.rest_api_site_so_selected);
        if (uri.getPath().startsWith("/2.1/users/26367/associated")) responseString = DroidStackMk2App.getAppContext().getString(R.string.rest_api_site_so_associated_acc);
        if (uri.getPath().startsWith("/2.1/sites")) responseString = DroidStackMk2App.getAppContext().getString(R.string.rest_api_site_get_all_sites);
        if (headerMap.containsKey("Content-Length")){
        	headerMap.put("Content-Length", new Header("Content-Length", String.valueOf(responseString.length())));
        	Utils.LogIt(TAG, "Content-Length: " + String.valueOf(responseString.length()));
        	Utils.LogIt(TAG, "responseString: " + responseString);
        	
        }
        fakeHeaders = new ArrayList<Header>(headerMap.values());
        return new Response(200, "OK", fakeHeaders, new TypedByteArray("application/json", responseString.getBytes()));

	}
	
}