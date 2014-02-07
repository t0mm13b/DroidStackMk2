package ie.t0mm13b.droidstackmk2.drawer;

import com.squareup.picasso.Picasso;

import ie.t0mm13b.droidstackmk2.R;
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
	
	public interface IDrawerUserPickerDialog{
		public void cbSelectedSEAccount(String seUserId);
	}
	
	private TextView mTVSiteName;
	private ImageView mIVSiteLogo;
	private EditText mEditSENumber;
	private Button mButtonSubmit;
	private IDrawerUserPickerDialog mUserPickerDialog;
	
	public static DrawerUserDialogFragment newInstance(DrawerRowEntry dre){
		DrawerUserDialogFragment dudFrag = new DrawerUserDialogFragment();
		Bundle args = new Bundle();
		args.putParcelable(DUD_DRE_KEY, dre);
		dudFrag.setArguments(args);
		return dudFrag;
	}
	
	public void setIDrawerUserPickerDialog(IDrawerUserPickerDialog iDrawrUsrPickrDlg){
		mUserPickerDialog = iDrawrUsrPickrDlg;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstance){
		Bundle args = getArguments();
		DrawerRowEntry dre = null;
		if (args != null && args.containsKey(DUD_DRE_KEY)){
			dre = args.getParcelable(DUD_DRE_KEY);
		}
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
			Picasso.with(getActivity()).load(dre.getDrawerIcon()).into(mIVSiteLogo);
		}
		mButtonSubmit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (mUserPickerDialog != null){
					mUserPickerDialog.cbSelectedSEAccount(mEditSENumber.getText().toString());
				}
				dismiss();
			}
			
		});
		//int args
		return dudlg;
	}
}
