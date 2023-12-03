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

    String Poke_URL = "https://pokeapi.co/api/v2/pokemon/"; //URLにPoke_を足す



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.myPokemonButton.setOnClickListener(view->{
            var mypoke1_name = binding.myPokemon1Name.getText().toString();
            var mypoke1_url = Poke_URL;

            pokemon mypoke1 = new pokemon(mypoke1_name, 1, 0);
            mypoke1_url += mypoke1.getPoke_Number();

            // リクエスト先にgistを指定
            var request = new Request.Builder()
                    .url(mypoke1_url)
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

                                runOnUiThread(() -> binding.myPokemon1Score.setText(gistFile.stats.get(5).base_stat));
                                //String res = gistFile.stats.get(0).toString();
                                //runOnUiThread(() -> binding.textView.setText(res));

                            });
                }

            });
        });
        /*
        pokemon mypoke1 = new pokemon(binding.myPokemon1Name.getText().toString(), 1, 0);

        //URLの末尾に図鑑番号を足す
        Poke_URL += mypoke1.getPoke_Number();
        ////////////////////////////////////////////////////

        // リクエスト先にgistを指定
        var request = new Request.Builder()
                .url(Poke_URL)
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

                            runOnUiThread(() -> binding.myPokemon1Score.setText(gistFile.stats.get(5).base_stat));
                            //String res = gistFile.stats.get(0).toString();
                            //runOnUiThread(() -> binding.textView.setText(res));
                          
                        });
            }
        });*/
    }
}