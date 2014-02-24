package ie.t0mm13b.droidstackmk2.helpers;

import android.util.Log;

public class Utils {
	public static final String SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY = "DRE_KEY";
	public static final String SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY = "DRE_POSN";
	public static final String SE_GENERIC_FRAG_ARGS_TYPEOFQUESTIONS_KEY = "TOQ_KEY";
	//
	public static int FRAGTYPE_QUESTIONS = 0;
	public static int FRAGTYPE_FEATURED_QUESTIONS = 1;
	public static int FRAGTYPE_UNANSWERED_QUESTIONS = 2;
	//
	public static final boolean DEBUG = true;
	
	public static final void LogIt(final String argTag, final String argMessage){
		if (DEBUG){
			Log.d(argTag, argMessage);
		}
	}
}
