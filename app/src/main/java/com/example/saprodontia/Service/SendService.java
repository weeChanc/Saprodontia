package com.example.saprodontia.Service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.annotation.Nullable;
import android.text.format.Formatter;

import com.example.saprodontia.Models.FileInfo;
import com.example.saprodontia.Utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SendService extends IntentService {

    private ArrayList<FileInfo> datas;
    private int len = 0;

    public SendService() {
        super("SendService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        datas = intent.getParcelableArrayListExtra("datas");

            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            for (int i = 0; i < datas.size(); i++) {
                try {
                    Socket socket = new Socket(Formatter.formatIpAddress(wm.getDhcpInfo().serverAddress), 8888);
                    new Thread(new SendTask(socket, datas.get(i))).start();
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class SendTask implements Runnable {

        private byte[] msg;
        private File file;
        private Socket socket;
        private FileInputStream fis;
        private OutputStream os;
        private byte[] buffer;


        public SendTask(Socket socket, FileInfo data) {
            buffer = new byte[1024];
            msg = (data.getName()+"="+data.getInitSize()).getBytes();
            file = new File(data.getLocation());
            this.socket = socket;

        }

        @Override
        public void run() {
            try {
                os = socket.getOutputStream();
                fis = new FileInputStream(file);
                os.write(msg);
                os.flush();
                Thread.sleep(1000);
                while ((len = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.flush();
                os.close();
                fis.close();
                socket.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
