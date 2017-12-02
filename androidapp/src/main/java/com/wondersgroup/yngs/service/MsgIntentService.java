package com.wondersgroup.yngs.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.activity.LoginActivity;
import com.wondersgroup.yngs.entity.MsgResult;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate msgHandler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MsgIntentService extends IntentService {
    private static final String ACTION_MSG = "com.wondersgroup.yngs.service.action.MSG";
    private static final String ACTION_BAZ = "com.wondersgroup.yngs.service.action.BAZ";

    /*private static final String EXTRA_USER_ID = "com.wondersgroup.yngs.service.extra.USERID";
    private static final String EXTRA_DEPT_ID = "com.wondersgroup.yngs.service.extra.DEPTID";
    private static final String EXTRA_ORGAN_ID = "com.wondersgroug.yngs.service.extra.ORGANID";*/
    private static final String EXTRA_CUR_NUM = "com.wondersgroug.yngs.service.extra.CURNUM";
    private static long interval=5*60000;

    private static final Handler msgHandler=new Handler();
    private static Runnable msgRunnable;
    private static int curNum=0;

    public MsgIntentService() {
        super("MsgIntentService");
    }

    /**
     * Starts this service to perform action Msg with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionMsg(Context context,int curNum) {
        Intent intent = new Intent(context, MsgIntentService.class);
        intent.setAction(ACTION_MSG);
        intent.putExtra(EXTRA_CUR_NUM, curNum);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MsgIntentService.class);
        intent.setAction(ACTION_BAZ);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_MSG.equals(action)) {
                final int curNum = intent.getIntExtra(EXTRA_CUR_NUM,0);
                handleActionMsg(curNum);
            } else if (ACTION_BAZ.equals(action)) {
                handleActionBaz();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionMsg(int curNum) {
        this.curNum=curNum;
        //msgHandler = new Handler();
        if(msgRunnable==null) {
            msgRunnable = new Runnable() {
                @Override
                public void run() {
                    fetchTodoCount();
                    msgHandler.postDelayed(this, interval);
                }
            };
        }
        msgHandler.postDelayed(msgRunnable, interval);
    }
    public static void cancelActionMsg(){
        if(msgRunnable!=null){
            msgHandler.removeCallbacks(msgRunnable);
            msgRunnable=null;
        }
    }
    public static void updateInterval(long newInterval){
        interval=newInterval;
        msgHandler.removeCallbacks(msgRunnable);
        msgHandler.postDelayed(msgRunnable,interval);
    }
    public static void updateCurNum(int newNum){
        curNum=newNum;
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz() {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void issueNotification(int count){
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.app_logo_small)
                .setContentTitle("云南微企")
                .setAutoCancel(true)
                .setContentText("目前您有"+count+"条未处理的待办，请尽快处理。");
        Intent result=new Intent(getApplicationContext(), LoginActivity.class);
        PendingIntent resultPending=PendingIntent.getActivity(getApplicationContext(), 0, result, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPending);
        manager.notify(001, builder.build());
    }

    private void fetchTodoCount(){
        SharedPreferences sp=getSharedPreferences("Default",MODE_PRIVATE);
        Map<String,String> body=new HashMap<>();
        body.put("userId",sp.getString("userId",""));
        body.put("deptId",sp.getString("deptId",""));
        body.put("organId",sp.getString("organId",""));
        body.put("wsCodeReq",ApiManager.getWsCodeReq());
        Call<MsgResult> call = ApiManager.yunNanApi.getTodoCount(body);
        call.enqueue(new Callback<MsgResult>() {
            @Override
            public void onResponse(Response<MsgResult> response, Retrofit retrofit) {
                if(response.isSuccess()&&response.body()!=null){
                    MsgResult result=response.body();
                    if("200".equals(result.getResultCode())){
                        int newCount=Integer.parseInt(result.getResult().getCount());
                        /*if(newCount>curNum&&curNum!=-1){
                            issueNotification(newCount-curNum);
                        }*/
                        if(newCount>0)issueNotification(newCount);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
