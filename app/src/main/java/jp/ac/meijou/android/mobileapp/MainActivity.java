package jp.ac.meijou.android.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.mobileapp.databinding.ActivityMainBinding;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // リクエスト先にgistを指定
        var request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon/1000")
                .build();

        // 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // レスポンスボディをGist型に変換
                var gist = gistJsonAdapter.fromJson(response.body().source());
                // 中身の取り出し
                Optional.ofNullable(gist)

                        .ifPresent(gistFile -> {
                            // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる

                            //Viewに表示するところ。Listでデータを持っているのでうまく切り分けてください......
                            runOnUiThread(() -> binding.myPokemon1Name.setText(gistFile.stats.get(5).base_stat));
                            //String res = gistFile.stats.toString();
                            //runOnUiThread(() -> binding.textView.setText(res);
                        });
            }
        });
    }
}