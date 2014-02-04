package ie.t0mm13b.droidstackmk2.interfaces;

/***
 * This callback is to enable adjusting the ActionBar's title after the fragment gets popped off the fragmentmanager's stack.
 * <br/>
 * Look in {@link BaseFragment} which invokes the callback, which is implemented by the main fragmentActivity.
 * 
 * @author t0mm13b
 *
 */
public interface IFragmentNotify {
	/***
	 * When the back key or the &lt; chevron in the action bar gets pressed, the callback gets invoked as the fragment gets popped off
	 */
	public void cbFragmentFinished();
}
