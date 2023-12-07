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
            var mypoke2_name = binding.myPokemon2Name.getText().toString();
            var mypoke2_url = Poke_URL;
            var mypoke3_name = binding.myPokemon3Name.getText().toString();
            var mypoke3_url = Poke_URL;
            var mypoke4_name = binding.myPokemon4Name.getText().toString();
            var mypoke4_url = Poke_URL;
            var mypoke5_name = binding.myPokemon5Name.getText().toString();
            var mypoke5_url = Poke_URL;
            var mypoke6_name = binding.myPokemon6Name.getText().toString();
            var mypoke6_url = Poke_URL;

            pokemon mypoke1 = new pokemon(mypoke1_name, 1, 0);
            mypoke1_url += mypoke1.getPoke_Number();

            pokemon mypoke2 = new pokemon(mypoke2_name, 1, 0);
            mypoke2_url += mypoke2.getPoke_Number();

            pokemon mypoke3 = new pokemon(mypoke3_name, 1, 0);
            mypoke3_url += mypoke3.getPoke_Number();

            pokemon mypoke4 = new pokemon(mypoke4_name, 1, 0);
            mypoke4_url += mypoke4.getPoke_Number();

            pokemon mypoke5 = new pokemon(mypoke5_name, 1, 0);
            mypoke5_url += mypoke5.getPoke_Number();

            pokemon mypoke6 = new pokemon(mypoke6_name, 1, 0);
            mypoke6_url += mypoke6.getPoke_Number();

            // リクエスト先にgistを指定1
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

            // リクエスト先にgistを指定2
            var request2 = new Request.Builder()
                    .url(mypoke2_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request2).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.myPokemon2Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });

            // リクエスト先にgistを指定3
            var request3 = new Request.Builder()
                    .url(mypoke3_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request3).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.myPokemon3Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });

            // リクエスト先にgistを指定4
            var request4 = new Request.Builder()
                    .url(mypoke4_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request4).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.myPokemon4Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });

            // リクエスト先にgistを指定5
            var request5 = new Request.Builder()
                    .url(mypoke5_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request5).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.myPokemon5Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });


            // リクエスト先にgistを指定5
            var request6 = new Request.Builder()
                    .url(mypoke6_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request6).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.myPokemon6Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });


        });


        binding.enemyPokemonButton.setOnClickListener(view->{
            var enemypoke1_name = binding.enemyPokemon1Name.getText().toString();
            var enemypoke1_url = Poke_URL;
            var enemypoke2_name = binding.enemyPokemon2Name.getText().toString();
            var enemypoke2_url = Poke_URL;
            var enemypoke3_name = binding.enemyPokemon3Name.getText().toString();
            var enemypoke3_url = Poke_URL;
            var enemypoke4_name = binding.enemyPokemon4Name.getText().toString();
            var enemypoke4_url = Poke_URL;
            var enemypoke5_name = binding.enemyPokemon5Name.getText().toString();
            var enemypoke5_url = Poke_URL;
            var enemypoke6_name = binding.enemyPokemon6Name.getText().toString();
            var enemypoke6_url = Poke_URL;

            pokemon enemypoke1 = new pokemon(enemypoke1_name, 1, 0);
            enemypoke1_url += enemypoke1.getPoke_Number();

            pokemon enemypoke2 = new pokemon(enemypoke2_name, 1, 0);
            enemypoke2_url += enemypoke2.getPoke_Number();

            pokemon enemypoke3 = new pokemon(enemypoke3_name, 1, 0);
            enemypoke3_url += enemypoke3.getPoke_Number();

            pokemon enemypoke4 = new pokemon(enemypoke4_name, 1, 0);
            enemypoke4_url += enemypoke4.getPoke_Number();

            pokemon enemypoke5 = new pokemon(enemypoke5_name, 1, 0);
            enemypoke5_url += enemypoke5.getPoke_Number();

            pokemon enemypoke6 = new pokemon(enemypoke6_name, 1, 0);
            enemypoke6_url += enemypoke6.getPoke_Number();

            // リクエスト先にgistを指定1
            var request = new Request.Builder()
                    .url(enemypoke1_url)
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

                                runOnUiThread(() -> binding.enemyPokemon1Score.setText(gistFile.stats.get(5).base_stat));
                                //String res = gistFile.stats.get(0).toString();
                                //runOnUiThread(() -> binding.textView.setText(res));

                            });
                }
            });

            // リクエスト先にgistを指定2
            var request2 = new Request.Builder()
                    .url(enemypoke2_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request2).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.enemyPokemon2Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });

            // リクエスト先にgistを指定3
            var request3 = new Request.Builder()
                    .url(enemypoke3_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request3).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.enemyPokemon3Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });

            // リクエスト先にgistを指定4
            var request4 = new Request.Builder()
                    .url(enemypoke4_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request4).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.enemyPokemon4Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });

            // リクエスト先にgistを指定5
            var request5 = new Request.Builder()
                    .url(enemypoke5_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request5).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.enemyPokemon5Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });


            // リクエスト先にgistを指定5
            var request6 = new Request.Builder()
                    .url(enemypoke6_url)
                    .build();
            // 非同期通信でリクエスト
            okHttpClient.newCall(request6).enqueue(new Callback() {
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

                                runOnUiThread(() -> binding.enemyPokemon6Score.setText(gistFile.stats.get(5).base_stat));
                            });
                }
            });


        });
    }
}