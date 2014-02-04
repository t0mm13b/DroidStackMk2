package ie.t0mm13b.droidstackmk2.drawer;

/***
 * Interface callback to respond from {@link DrawerArrayAdapter} where a tap on the row item in the drawer.
 * - Internally visible <b>within</b> {@link DrawerFragment}
 * <br/>
 * <b>Note:</b> Not used for outside scope of {@link DrawerFragment}
 * 
 * @author t0mm13b
 * @see DrawerArrayAdapter
 * @see DrawerFragment
 */
public interface IDrawerEntry {
	/***
	 * Callback in which the implementer, in this case, {@link DrawerFragment} will handle the position of the row tapped.
	 * @param position - offset into the {@link DrawerArrayAdapter} which <b>shall</b> hold the {@link DrawerRowEntry}
	 */
	public void cbDrawerEntryClicked(int position);
}
