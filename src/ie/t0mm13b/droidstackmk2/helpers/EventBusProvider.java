package ie.t0mm13b.droidstackmk2.helpers;

import com.squareup.otto.Bus;

/***
 * A super clean way of publish/subscribe, singleton class!
 * 
 * @author t0mm13b
 *
 */
public class EventBusProvider {
	private static class EventBusProviderLoader{
		private static final Bus mEventBus = new Bus();
	}
	private EventBusProvider(){
		if (EventBusProviderLoader.mEventBus != null) throw new IllegalStateException("Otto Bus is already loaded");
	}
	
	public static synchronized Bus getInstance(){
		return EventBusProviderLoader.mEventBus;
	}
}
