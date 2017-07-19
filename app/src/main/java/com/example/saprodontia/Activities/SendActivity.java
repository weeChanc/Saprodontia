package com.example.saprodontia.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.saprodontia.Adapter.Adapter;
import com.example.saprodontia.Models.ContentModle;
import com.example.saprodontia.Models.FileInfo;
import com.example.saprodontia.Models.AppInfoModle;
import com.example.saprodontia.R;

import java.util.List;

public class SendActivity extends AppCompatActivity implements View.OnClickListener {

    private OnSendListener mOnSendListener;
    private AppInfoModle mAppInfoModle;
    private ContentModle mContentModle;

    private Adapter appAdapter;
    private Adapter docuAdapter;
    private Adapter videoAdapter;
    private Adapter anotherAdapter;


    private RecyclerView recyclerView;
    private List<FileInfo> infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        mContentModle = new ContentModle(this);
        mAppInfoModle = new AppInfoModle();
        recyclerView = (RecyclerView) findViewById(R.id.recycle_all);

        infos = mAppInfoModle.initSimAppInfos();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appAdapter = new Adapter(this, infos );
        recyclerView.setAdapter(appAdapter);


        RadioButton radio_photo = (RadioButton) findViewById(R.id.radio_photo);
        RadioButton radio_app = (RadioButton) findViewById(R.id.radio_app);
        RadioButton radio_viedo = (RadioButton) findViewById(R.id.radio_video);
        RadioButton radio_docu = (RadioButton) findViewById(R.id.radio_docu);
        RadioButton radio_another = (RadioButton) findViewById(R.id.radio_another);
        Button bt_ensure = (Button)findViewById(R.id.bt_ensure);
        bt_ensure.setOnClickListener(this);
        radio_another.setOnClickListener(this);
        radio_docu.setOnClickListener(this);
        radio_viedo.setOnClickListener(this);
        radio_app.setOnClickListener(this);
        radio_photo.setOnClickListener(this);

        mAppInfoModle.setmOnDataChangeListener(new AppInfoModle.OnDataChangeListener() {
            @Override
            public void onDataChange() {
                appAdapter.notifyDataSetChanged();
            }
        });

        radio_app.setChecked(true);


//        AppInfoModle modleAt = new AppInfoModle();
//        List<ApplicationInfo> infos = AppInfoModle.get();
//        ApplicationInfo i = infos.get(1);
//
//        for(ApplicationInfo info : infos){
//            LogUtil.e(info.loadLabel(getPackageManager()));
//        }
//        List<FileInfo> t = AppInfoModle.getSimAppinfos();


//
//        final WifiP2pManager wm = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
//        final WifiP2pManager.Channel channel=  wm.initialize(this,getMainLooper(),null);
//        mWifiReceiver = new WifiReceiver(wm,channel,this);



//        wm.discoverPeers(channel, new WifiP2pManager.ActionListener() {
//            @Override
//            public void onSuccess() {
//                LogUtil.e("搜索成功");
////                wm.requestPeers(channel, new WifiP2pManager.PeerListListener() {
////                    @Override
////                    public void onPeersAvailable(WifiP2pDeviceList peers) {
////                        LogUtil.e("AVAILABLLE");
////                        LogUtil.e(peers.getDeviceList().size());
////                    }
////                });
//            }
//
//            @Override
//            public void onFailure(int reason) {
//                LogUtil.e("搜索失败");
//            }
//        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    byte[] buffer = new byte[1024*8];
//                    WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//                    WifiInfo wifiInfo = wm.getConnectionInfo();
//                    LogUtil.e(Formatter.formatIpAddress(wifiInfo.getIpAddress()));
//                    LogUtil.e(Formatter.formatIpAddress(wm.getDhcpInfo().serverAddress));
//                    Socket socket = new Socket(Formatter.formatIpAddress(wm.getDhcpInfo().serverAddress),65535);
////                    Socket socket = new Socket(InetAddress.getLocalHost(),65535);
//                    FileInputStream fis = new FileInputStream(new File(infos.get(15).getLocation()));
//                    OutputStream os =  socket.getOutputStream();
//                    int len = 0;
//                    while(( len =fis.read(buffer) ) !=-1){
//                        LogUtil.e("SENDING");
//                        os.write(buffer,0,len);
//                    }
//
//                    fis.close();
//                    os.close();
//                    socket.close();
//
//
//
//
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();



    }

    @Override
    protected void onResume() {

//        IntentFilter mIntentFilter = new IntentFilter();
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

//        registerReceiver(mWifiReceiver,mIntentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
//        unregisterReceiver(mWifiReceiver);
        super.onPause();
    }

    public void setOnSendListener(OnSendListener listener){
        mOnSendListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_ensure:{
                mOnSendListener.send();
                break;
            }
            case R.id.radio_photo :{

//                recyclerView.setAdapter();
                break;
            }
            case R.id.radio_app :{
                recyclerView.setAdapter(appAdapter);
                break;
            }
            case R.id.radio_docu:{
                if(docuAdapter == null)
                docuAdapter = new Adapter(this,mContentModle.getFileInfos());
                recyclerView.setAdapter(docuAdapter);
            }
        }
    }

    public interface OnSendListener{
        void send();
    }




//    class WifiReceiver extends BroadcastReceiver{
//        WifiP2pManager wm;
//        WifiP2pManager.Channel channel;
//        Context context;
//        List<WifiP2pDevice> peer = new ArrayList<>();
//
//        WifiP2pDevice device;
//        WifiP2pConfig config = new WifiP2pConfig();
//
//        final WifiP2pManager.PeerListListener listener = new WifiP2pManager.PeerListListener() {
//            @Override
//            public void onPeersAvailable(WifiP2pDeviceList peers) {
//                peer.clear();
//                peer.addAll(peers.getDeviceList());
//                LogUtil.e("PEER" + peers.getDeviceList().size());
//            }
//        };
//
//        public WifiReceiver(final WifiP2pManager wm, final WifiP2pManager.Channel channel, Context context) {
//            this.wm = wm;
//            this.channel = channel;
//            this.context = context;
//    }
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//
//            if(action.equals(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)){
//                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);
//
//                if(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED){
//                    LogUtil.e("ENABLE");
////                    sendBroadcast(new Intent(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION));
//                }else{
//                    LogUtil.e("UNABLE");
//                }
//            }
//
//            if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)){
//                LogUtil.e("WIFI_P2P_PEERS_CHANGED_ACTION");
//                // TODO: 2017/7/17  这个分支中调用requestPeers()获取设备列表
//                wm.requestPeers(channel,listener);
//            }
//
//            if(action.equals(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)){
//                //// TODO: 2017/7/17  get self info.
//            }
//        }
//    }
}
