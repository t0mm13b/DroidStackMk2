package ie.t0mm13b.droidstackmk2.drawer;

/***
 * The main callback interface handled by the <b>main</b> fragment activity that handles the navigation drawer.<br/>
 * The Navigation Panel, in this project, is split into two layouts, the upper layout, and the list.
 * 
 * @author t0mm13b
 *
 */
public interface IDrawerListItem {
	/***
	 * The callback when the item in the list (on the Navigation Drawer) is tapped.
	 * 
	 * @param position - index into the {@link DrawerArrayAdapter}'s list
	 * @param dre - the actual {@link DrawerRowEntry} object for record-keeping
	 * @see DrawerArrayAdapter
	 * @see DrawerRowEntry
	 */
	public void cbDrawerListItemClick(int position, DrawerRowEntry dre);
}
