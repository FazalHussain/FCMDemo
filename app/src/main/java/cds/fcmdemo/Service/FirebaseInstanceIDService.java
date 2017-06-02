package cds.fcmdemo.Service;

import android.app.DownloadManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Fazal on 9/9/2016.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        RegisterToken(token);
    }

    private void RegisterToken(String token) {
        try{
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("Token",token)
                    .build();

            Request request = new Request.Builder()
                    .url("http://freecs13.hostei.com/php services/register.php")
                    .post(body)
                    .build();

            client.newCall(request).execute();
            }catch (Exception e){
                e.printStackTrace();
            }

    }
}
