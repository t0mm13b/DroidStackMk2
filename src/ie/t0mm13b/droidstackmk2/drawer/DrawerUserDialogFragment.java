package ie.t0mm13b.droidstackmk2.drawer;

import com.squareup.picasso.Picasso;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.events.DrawerUserDialogEvent;
import ie.t0mm13b.droidstackmk2.helpers.EventBusProvider;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerUserDialogFragment extends DialogFragment{
	
	private static final String DUD_DRE_KEY = "DUD_DRE";
	private static final String DUD_DRE_POS = "DUD_POS";
	
	private TextView mTVSiteName;
	private ImageView mIVSiteLogo;
	private EditText mEditSENumber;
	private Button mButtonSubmit;
	
	public static DrawerUserDialogFragment newInstance(DrawerRowEntry dre, int position){
		DrawerUserDialogFragment dudFrag = new DrawerUserDialogFragment();
		Bundle args = new Bundle();
		args.putParcelable(DUD_DRE_KEY, dre);
		args.putInt(DUD_DRE_POS, position);
		dudFrag.setArguments(args);
		return dudFrag;
	}
	
//	public void setIDrawerUserPickerDialog(IDrawerUserPickerDialog iDrawrUsrPickrDlg){
//		mUserPickerDialog = iDrawrUsrPickrDlg;
//	}
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
				EventBusProvider.getInstance().post(new DrawerUserDialogEvent(mEditSENumber.getText().toString(), dre, position));
				dismiss();
			}
			
		});
		//int args
		return dudlg;
	}
}
