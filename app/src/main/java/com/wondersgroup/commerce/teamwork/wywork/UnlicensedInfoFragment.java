package com.wondersgroup.commerce.teamwork.wywork;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.teamwork.dailycheck.BookBean;
import com.wondersgroup.commerce.teamwork.wywork.javabean.EtpsInfoChangeBean;
import com.wondersgroup.commerce.teamwork.wywork.javabean.ImgFirstBean;
import com.wondersgroup.commerce.teamwork.wywork.javabeansend.CnUpload;
import com.wondersgroup.commerce.widget.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UnlicensedInfoFragment extends Fragment {

	private View view;
	private LinearLayoutForListView dailyList;
	private EtpsInfoAdapter adapter;
	private Dialog progressDialog;
	private AppCompatActivity activity;
	// private List<CnApp> complainCnApps = new ArrayList<CnApp>();
	public static final int SHOW_RESPONSE = 1;
	public static final int SHOW_ERROR = 2;
	public static final int SHOW_ERROR_DIALOG = 4;
	public static final int SHOW_complain_RESPONSE = 5;
	private RootAppcation application;
	ExampleAdapter expandalbeAdapter;

	private TextView problem;
	private TextView treatment;
	private TextView checkDate;
//	private TextView checkedName;
//	private TextView checkedNumber;
	private TextView inspector;
	private TextView inspection;
	private TextView submitUser;
	private TextView inspectUser;
	private TextView memo;
	private TextView gpsInfo;

	private TextView abbuseSource;
	private TextView abbusePerson;
	private TextView abbuseSex;
	private TextView abbuseAge;
	private TextView abbuseCompany;
	private TextView abbuseOccupation;
	private TextView abbuseName;
	private TextView abbuseNumber;
	private TextView abbuseIdType;
	private TextView abbuseIdNumber;
	private TextView abbuseAddress;
	private TextView abbuseLeaderCompany;
	private TextView abbuseLeaderName;
	private TextView abbuseLeaderRegNumber;
	private TextView abbuseLeaderAddress;
	private TextView abbuseLeaderNumber;
	private TextView abbuseLocation;
	private TextView abbuseDate;
	private TextView abbuseValue;
	private TextView abbuseScale;
	private TextView abbuseIllegal;
	private TextView abbuseType;
	private TextView abbuseTitle;
	private TextView abbuseBasis;
	private TextView abbuseFormmer;

	// new
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Dialog showimageAlertDialog;
	private View photoShowView;
	public static final int SHOW_IMG_RESPONSE = 5;
	private Gson gson = new Gson();

	// new
	private TextView inspectionText;
	private Button confirmButton;

	private Map<String, String> dealMap;
	private List<BookBean> users;
	Map<String, String> cardTypeMap;
	Map<String, String> caseFromMap;
	Map<String, String> entityTypeMap;
	Map<String, String> legalBasisMap;
	Map<String, String> unLicensedTypeMap;
	Map<String, String> abbuseFormmerMap;
	Map<String, String> sexMap;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.q_unlicensed_info, container, false);
		activity = (AppCompatActivity) getActivity();
		application = (RootAppcation) activity.getApplication();
		TextView title = (TextView) activity.findViewById(R.id.toolbar_title);
		title.setText("无照管理");
		progressDialog = new LoadingDialog.Builder(getActivity()).build();

		// new
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		imageView2 = (ImageView) view.findViewById(R.id.imageView2);
		imageView3 = (ImageView) view.findViewById(R.id.imageView3);
		imageView1.setBackground(null);
		imageView2.setBackground(null);
		imageView3.setBackground(null);
		String imageAddress = Url.QJ_IN_USE + "fetchImg/"
				+ application.getRecordId();
		HttpClientUtil.callWebServiceForGet(imageAddress,
				new HttpCallbackListener() {

					@Override
					public void onFinish(String response) {
						Message message = new Message();
						message.what = SHOW_IMG_RESPONSE;
						message.obj = response.toString();
						handler.sendMessage(message);
					}

					@Override
					public void onError(Exception e) {
//						Message message = new Message();
//						message.what = SHOW_ERROR;
//						message.obj = e.toString();
//						handler.sendMessage(message);

					}
				});

		initView();

		// initcomplainList();
		setHasOptionsMenu(true);
		return view;
	}

	private void initView() {

		treatment = (TextView) view.findViewById(R.id.treatment);
		inspector = (TextView) view.findViewById(R.id.inspector);
		treatment.setTag("");
		inspector.setTag("");
		dealMap = application.getInfoBean().getDeal();
		users = application.getInfoBean().getUserList();
		treatment.setTag(application.getInfoBean().getAppRecordContent()
				.getTreatment());
		inspector.setTag(application.getInfoBean().getAppCheckInfo()
				.getInspector());
		String[] inspectorStrings = inspector.getTag().toString().split(",");
		String inspectorString = " ";
		for (int i = 0; i < inspectorStrings.length; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (users.get(j).getUserId().equals(inspectorStrings[i])) {
					inspectorString += users.get(j).getName() + ",";
				}

			}

		}
		inspectorString = inspectorString.substring(0,
				inspectorString.length() - 1);
		inspector.setText(inspectorString);

		String[] dealStrings = treatment.getTag().toString().split(",");
		String dealString = " ";
		for (int i = 0; i < dealStrings.length; i++) {
			if (dealMap.get(dealStrings[i]) != null) {
				dealString += dealMap.get(dealStrings[i]) + ",";
			}
		}
		dealString = dealString.substring(0, dealString.length() - 1);
		treatment.setText(dealString);

		abbuseIdType = (TextView) view.findViewById(R.id.abbuseIdType);
		abbuseSource = (TextView) view.findViewById(R.id.abbuseSource);
		abbuseTitle = (TextView) view.findViewById(R.id.abbuseTitle);
		abbuseBasis = (TextView) view.findViewById(R.id.abbuseBasis);
		abbuseType = (TextView) view.findViewById(R.id.abbuseType);
		abbuseFormmer = (TextView) view.findViewById(R.id.abbuseFormmer);
		abbuseSex = (TextView) view.findViewById(R.id.abbuseSex);

		abbuseIdType.setTag("");
		abbuseSource.setTag("");
		abbuseTitle.setTag("");
		abbuseBasis.setTag("");
		abbuseType.setTag("");
		abbuseFormmer.setTag("");
		abbuseSex.setTag("");

		abbuseIdType.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseIdType());
		abbuseSource.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseSource());
		abbuseTitle.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseTitle());
		abbuseBasis.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseBasis());
		abbuseType.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseType());
		abbuseFormmer.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseFormmer());
		abbuseSex.setTag(application.getInfoBean().getAppRecordContent()
				.getAbbuseSex());

		cardTypeMap = application.getInfoBean().getCardType();
		caseFromMap = application.getInfoBean().getCaseFrom();
		entityTypeMap = application.getInfoBean().getEntityType();
		legalBasisMap = application.getInfoBean().getLegalBasis();
		unLicensedTypeMap = application.getInfoBean().getUnLicensedType();
		abbuseFormmerMap = application.getInfoBean().getAbbuseFormmer();
		sexMap = application.getInfoBean().getSex();

		String[] sexStrings = abbuseSex.getTag().toString().split(",");
		String sexString = " ";
		for (int i = 0; i < sexStrings.length; i++) {
			if (sexMap.get(sexStrings[i]) != null) {
				sexString += sexMap.get(sexStrings[i]) + ",";
			}
		}
		sexString = sexString.substring(0, sexString.length() - 1);
		abbuseSex.setText(sexString);

		String[] formmerStrings = abbuseFormmer.getTag().toString().split(",");
		String formmerString = " ";
		for (int i = 0; i < formmerStrings.length; i++) {
			if (abbuseFormmerMap.get(formmerStrings[i]) != null) {
				formmerString += abbuseFormmerMap.get(formmerStrings[i]) + ",";
			}

		}
		formmerString = formmerString.substring(0, formmerString.length() - 1);
		abbuseFormmer.setText(formmerString);

		String[] cardStrings = abbuseIdType.getTag().toString().split(",");
		String cardString = " ";
		for (int i = 0; i < cardStrings.length; i++) {
			if (cardTypeMap.get(cardStrings[i]) != null) {
				cardString += cardTypeMap.get(cardStrings[i]) + ",";
			}

		}
		cardString = cardString.substring(0, cardString.length() - 1);
		abbuseIdType.setText(cardString);

		String[] formStrings = abbuseSource.getTag().toString().split(",");
		String formString = " ";
		for (int i = 0; i < formStrings.length; i++) {
			if (caseFromMap.get(formStrings[i]) != null) {
				formString += caseFromMap.get(formStrings[i]) + ",";
			}

		}
		formString = formString.substring(0, formString.length() - 1);
		abbuseSource.setText(formString);

		String[] entityStrings = abbuseTitle.getTag().toString().split(",");
		String entityString = " ";
		for (int i = 0; i < entityStrings.length; i++) {
			if (entityTypeMap.get(entityStrings[i]) != null) {
				entityString += entityTypeMap.get(entityStrings[i]) + ",";
			}

		}
		entityString = entityString.substring(0, entityString.length() - 1);
		abbuseTitle.setText(entityString);

		String[] basisStrings = abbuseBasis.getTag().toString().split(",");
		String basisString = " ";
		for (int i = 0; i < basisStrings.length; i++) {
			if (legalBasisMap.get(basisStrings[i]) != null) {
				basisString += legalBasisMap.get(basisStrings[i]) + ",";
			}

		}
		basisString = basisString.substring(0, basisString.length() - 1);
		abbuseBasis.setText(basisString);

		String[] unlicenseStrings = abbuseType.getTag().toString().split(",");
		String unlicenseString = " ";
		for (int i = 0; i < unlicenseStrings.length; i++) {
			if (unLicensedTypeMap.get(unlicenseStrings[i]) != null) {
				unlicenseString += unLicensedTypeMap.get(unlicenseStrings[i])
						+ ",";
			}

		}
		unlicenseString=unlicenseString.substring(0, unlicenseString.length() - 1);
		abbuseType.setText(unlicenseString);

		problem = (TextView) view.findViewById(R.id.problem);
		gpsInfo = (TextView) view.findViewById(R.id.gpsInfo);
		checkDate = (TextView) view.findViewById(R.id.checkDate);
//		checkedName = (TextView) view.findViewById(R.id.checkedName);
//		checkedNumber = (TextView) view.findViewById(R.id.checkedNumber);
		inspector = (TextView) view.findViewById(R.id.inspector);
		inspection = (TextView) view.findViewById(R.id.inspection);
		submitUser = (TextView) view.findViewById(R.id.submitUser);
		memo = (TextView) view.findViewById(R.id.memo);
		abbusePerson = (TextView) view.findViewById(R.id.abbusePerson);

		abbuseAge = (TextView) view.findViewById(R.id.abbuseAge);
		abbuseCompany = (TextView) view.findViewById(R.id.abbuseCompany);
		abbuseOccupation = (TextView) view.findViewById(R.id.abbuseOccupation);
		abbuseName = (TextView) view.findViewById(R.id.abbuseName);
		abbuseNumber = (TextView) view.findViewById(R.id.abbuseNumber);
		abbuseIdNumber = (TextView) view.findViewById(R.id.abbuseIdNumber);
		abbuseAddress = (TextView) view.findViewById(R.id.abbuseAddress);
		abbuseLeaderCompany = (TextView) view
				.findViewById(R.id.abbuseLeaderCompany);
		abbuseLeaderName = (TextView) view.findViewById(R.id.abbuseLeaderName);
		abbuseLeaderRegNumber = (TextView) view
				.findViewById(R.id.abbuseLeaderRegNumber);
		abbuseLeaderAddress = (TextView) view
				.findViewById(R.id.abbuseLeaderAddress);
		abbuseLeaderNumber = (TextView) view
				.findViewById(R.id.abbuseLeaderNumber);
		abbuseLocation = (TextView) view.findViewById(R.id.abbuseLocation);
		abbuseDate = (TextView) view.findViewById(R.id.abbuseDate);
		abbuseValue = (TextView) view.findViewById(R.id.abbuseValue);
		abbuseScale = (TextView) view.findViewById(R.id.abbuseScale);
		abbuseIllegal = (TextView) view.findViewById(R.id.abbuseIllegal);

		/* appRecordContent */
		// problem.setText(application.getInfoBean().getAppRecordContent()
		// .getProblem());

		abbuseLeaderName.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderName());
		abbuseLeaderCompany.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderCompany());
		abbuseAddress.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseAddress());
		abbuseIdNumber.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseIdNumber());
		abbuseNumber.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseNumber());
		abbuseName.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseName());
		abbuseOccupation.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseOccupation());
		abbuseCompany.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseCompany());
		abbuseAge.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseAge());
		abbusePerson.setText(application.getInfoBean().getAppRecordContent()
				.getAbbusePerson());

		abbuseIllegal.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseIllegal());
		abbuseScale.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseScale());
		abbuseValue.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseValue());
		abbuseDate.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseDate());
		abbuseLocation.setText(application.getInfoBean().getAppRecordContent()
				.getAbbuseLocation());
		abbuseLeaderNumber.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderNumber());
		abbuseLeaderAddress.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderAddress());
		abbuseLeaderRegNumber.setText(application.getInfoBean()
				.getAppRecordContent().getAbbuseLeaderRegNumber());
		checkDate.setText(application.getInfoBean().getAppCheckInfo()
				.getCheckDate());
//		checkedName.setText(application.getInfoBean().getAppCheckInfo()
//				.getCheckedName());
//		checkedNumber.setText(application.getInfoBean().getAppCheckInfo()
//				.getCheckedNumber());
		submitUser.setText(application.getInfoBean().getAppCheckInfo()
				.getSubmitUser());
		memo.setText(application.getInfoBean().getAppCheckInfo().getMemo());
		gpsInfo.setText(application.getInfoBean().getAppCheckInfo()
				.getGpsInfo());

		// new
		inspectUser = (TextView) view.findViewById(R.id.inspectUser);
		inspectUser.setText(application.getInfoBean().getAppCheckInfo()
				.getInspectUser());
		inspectionText = (TextView) view.findViewById(R.id.inspection_text);
		confirmButton = (Button) view.findViewById(R.id.confirmButton);
		if (application.getInfoBean().getAppCheckInfo().getInspection() != null) {
			inspectionText.setVisibility(View.VISIBLE);
			inspectionText.setText(application.getInfoBean().getAppCheckInfo()
					.getInspection());
			inspection.setVisibility(View.GONE);
			confirmButton.setVisibility(View.GONE);
		}

		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(inspection.getText())) {
					Toast.makeText(activity, "请填写督察意见", Toast.LENGTH_SHORT)
							.show();
				} else {
					progressDialog.show();
					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("inspectUser", application
								.getLoginUserInfo().getUserId());
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						jsonObject.put("inspection", inspection.getText()
								.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						jsonObject.put("recordId", application.getInfoBean()
								.getAppRecordContent().getRecordId());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String netAddress = Url.QJ_IN_USE + "updateCheckInfo";
					HttpClientUtil.callWebService(jsonObject.toString(),
							netAddress, new HttpCallbackListener() {

								@Override
								public void onFinish(String response) {
									Message message = new Message();
									message.what = SHOW_RESPONSE;
									message.obj = response.toString();
									handler.sendMessage(message);
								}

								@Override
								public void onError(Exception e) {
									Message message = new Message();
									message.what = SHOW_ERROR;
									message.obj = e.toString();
									handler.sendMessage(message);

								}
							});
				}

			}
		});

	}

	public void initcomplainList() {
		if (application.getInfoBean() != null) {
			LinkedHashMap<String, String> map = application.getInfoBean()
					.getEtpsInfoVo();
			MapToListUtil mapToListUtil = new MapToListUtil(map);
			List<EtpsInfoChangeBean> etpsInfoChangeBeans = mapToListUtil
					.mapToEtpsChangeInfo();

			dailyList = (LinearLayoutForListView) view
					.findViewById(R.id.demo_list);
			adapter = new EtpsInfoAdapter(activity,
					R.layout.radio_buton_info_list_item, etpsInfoChangeBeans);
			dailyList.setAdapter(adapter);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// // noinspection SimplifiableIfStatement
		// if (id == R.id.register) {
		// Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
		// return true;
		// }
		// if (id == R.id.forget_passwd) {
		// Toast.makeText(LoginActivity.this, "忘记密码",
		// Toast.LENGTH_SHORT).show();
		// return true;
		// }
		if (id == android.R.id.home) {
			UtilForFragment.popBackStack(activity);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				JSONObject caseInfo = null;
				try {
					caseInfo = new JSONObject(msg.obj.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String code = "";
				try {
					code = caseInfo.getString("code");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String result = null;
				try {
					result = caseInfo.getString("result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (code.equals("200")) {
					Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
					if (progressDialog.isShowing()) {
						progressDialog.cancel();
					}

				} else {
					if (progressDialog.isShowing()) {
						progressDialog.cancel();
					}
					Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_SHORT)
							.show();
				}
				break;

			case SHOW_IMG_RESPONSE:
				JSONObject imgJson = null;
				try {
					imgJson = new JSONObject(msg.obj.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String imgCode = "";
				try {
					imgCode = imgJson.getString("code");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String imgResult = null;
				try {
					imgResult = imgJson.getString("result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImgFirstBean imgFirstBean = gson.fromJson(msg.obj.toString(),
						ImgFirstBean.class);
				if (imgFirstBean.getCode() == 200) {

					int i = 0;
					for (CnUpload pic : imgFirstBean.getResult()) {
						if (i == 0) {
							String raw = pic.getFilePath();
							bitmap1 = BitMapUtil.base64ToBitmap(raw);
							imageView1.setImageBitmap(bitmap1);
							imageView1
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											LayoutInflater layoutInflater = (LayoutInflater) getActivity()
													.getSystemService(
															Context.LAYOUT_INFLATER_SERVICE);
											photoShowView = layoutInflater
													.inflate(
															R.layout.photo_view,
															null);
											ImageView imageView = (ImageView) photoShowView
													.findViewById(R.id.show_photo);
											imageView
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {
															showimageAlertDialog
																	.cancel();

														}
													});
											imageView.setImageBitmap(bitmap1);
											showimageAlertDialog = new Dialog(
													getActivity(),
													android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
											showimageAlertDialog
													.setContentView(photoShowView);
											showimageAlertDialog.show();
										}
									});
						}
						if (i == 1) {
							String raw = pic.getFilePath();
							bitmap2 = BitMapUtil.base64ToBitmap(raw);
							imageView2.setImageBitmap(bitmap2);
							imageView2
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											LayoutInflater layoutInflater = (LayoutInflater) getActivity()
													.getSystemService(
															Context.LAYOUT_INFLATER_SERVICE);
											photoShowView = layoutInflater
													.inflate(
															R.layout.photo_view,
															null);
											ImageView imageView = (ImageView) photoShowView
													.findViewById(R.id.show_photo);
											imageView
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {
															showimageAlertDialog
																	.cancel();

														}
													});
											imageView.setImageBitmap(bitmap2);
											showimageAlertDialog = new Dialog(
													getActivity(),
													android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
											showimageAlertDialog
													.setContentView(photoShowView);
											showimageAlertDialog.show();
										}
									});
						}
						if (i == 2) {
							String raw = pic.getFilePath();
							bitmap3 = BitMapUtil.base64ToBitmap(raw);
							imageView3.setImageBitmap(bitmap3);
							imageView3
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											LayoutInflater layoutInflater = (LayoutInflater) getActivity()
													.getSystemService(
															Context.LAYOUT_INFLATER_SERVICE);
											photoShowView = layoutInflater
													.inflate(
															R.layout.photo_view,
															null);
											ImageView imageView = (ImageView) photoShowView
													.findViewById(R.id.show_photo);
											imageView
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View v) {
															showimageAlertDialog
																	.cancel();

														}
													});
											imageView.setImageBitmap(bitmap3);
											showimageAlertDialog = new Dialog(
													getActivity(),
													android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
											showimageAlertDialog
													.setContentView(photoShowView);
											showimageAlertDialog.show();
										}
									});
						}
						i++;
					}

				}
				break;

			case SHOW_ERROR:
				if (progressDialog.isShowing()) {
					progressDialog.cancel();
				}

				Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}
	};

}
