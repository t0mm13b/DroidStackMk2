package ie.t0mm13b.droidstackmk2.drawer;

import com.squareup.picasso.Picasso;
import static butterknife.ButterKnife.findById;
import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.events.StackExchangeUserDialogEvent;
import ie.t0mm13b.droidstackmk2.helpers.EventBusProvider;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class StackExchangeUserDialog extends DialogFragment{
	
	private static final String DUD_DRE_KEY = "DUD_DRE";
	private static final String DUD_DRE_POS = "DUD_POS";
	
	private TextView mTVSiteName;
	private ImageView mIVSiteLogo;
	private EditText mEditSENumber;
	private Button mButtonSubmit;
	
	public static StackExchangeUserDialog newInstance(DrawerRowEntry dre, int position){
		StackExchangeUserDialog dudFrag = new StackExchangeUserDialog();
		Bundle args = new Bundle();
		args.putParcelable(DUD_DRE_KEY, dre);
		args.putInt(DUD_DRE_POS, position);
		dudFrag.setArguments(args);
		return dudFrag;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstance){
		Bundle args = getArguments();
		DrawerRowEntry ldre = null;
		int pos = -1;
		if (args != null){
			if (args.containsKey(DUD_DRE_KEY)) ldre = args.getParcelable(DUD_DRE_KEY);
			if (args.containsKey(DUD_DRE_POS)) pos = args.getInt(DUD_DRE_POS);
		}
		final int position = pos;
		final DrawerRowEntry dre = ldre;
		final Dialog dudlg = new Dialog(getActivity());
		dudlg.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dudlg.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dudlg.setContentView(R.layout.drawer_userpicker_dlgfrag);
		dudlg.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(DialogInterface argDlg, int argKeyCode, KeyEvent argKeyEvent) {
				if (argKeyCode == KeyEvent.KEYCODE_BACK && argKeyEvent.getRepeatCount() == 0){
					EventBusProvider.getInstance().post(new StackExchangeUserDialogEvent("", dre, position, true));
					dismiss();
					return true;
				}
				return false;
			}
			
		});
		//dudlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		mTVSiteName = (TextView) dudlg.findViewById(R.id.tvDrawrUsrPickrSelectdSite);
		mIVSiteLogo = (ImageView) dudlg.findViewById(R.id.ivDrwrSelectdSite);
		mEditSENumber = (EditText)dudlg.findViewById(R.id.etDrawrUsrPickrAccNbr);
		mButtonSubmit = (Button)dudlg.findViewById(R.id.btnDrawrUsrPickrSubmit);
		if (dre != null){
			mTVSiteName.setText(dre.getDrawerText());
			Picasso.with(getActivity())
				.load(dre.getDrawerIcon())
				.resize(96, 96)
				.into(mIVSiteLogo);
		}
		mButtonSubmit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EventBusProvider.getInstance().post(new StackExchangeUserDialogEvent(mEditSENumber.getText().toString(), dre, position, false));
				dismiss();
			}
			
		});
		//int args
		return dudlg;
	}
	
}
