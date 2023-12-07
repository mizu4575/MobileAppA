package jp.ac.meijou.android.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

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

    private TextView myPokemon1Score=findViewById(R.id.myPokemon1Score);
    private TextView myPokemon2Score=findViewById(R.id.myPokemon2Score);
    private TextView myPokemon3Score=findViewById(R.id.myPokemon3Score);
    private TextView myPokemon4Score=findViewById(R.id.myPokemon4Score);
    private TextView myPokemon5Score=findViewById(R.id.myPokemon5Score);
    private TextView myPokemon6Score=findViewById(R.id.myPokemon6Score);
    private TextView enemyPokemon1Score=findViewById(R.id.enemyPokemon1Score);
    private TextView enemyPokemon2Score=findViewById(R.id.enemyPokemon2Score);
    private TextView enemyPokemon3Score=findViewById(R.id.enemyPokemon3Score);
    private TextView enemyPokemon4Score=findViewById(R.id.enemyPokemon4Score);
    private TextView enemyPokemon5Score=findViewById(R.id.enemyPokemon5Score);
    private TextView enemyPokemon6Score=findViewById(R.id.enemyPokemon6Score);

    private EditText myPokemon1Name=findViewById(R.id.myPokemon1Name);
    private EditText myPokemon2Name=findViewById(R.id.myPokemon2Name);
    private EditText myPokemon3Name=findViewById(R.id.myPokemon3Name);
    private EditText myPokemon4Name=findViewById(R.id.myPokemon4Name);
    private EditText myPokemon5Name=findViewById(R.id.myPokemon5Name);
    private EditText myPokemon6Name=findViewById(R.id.myPokemon6Name);
    private EditText enemyPokemon1Name=findViewById(R.id.enemyPokemon1Name);
    private EditText enemyPokemon2Name=findViewById(R.id.enemyPokemon2Name);
    private EditText enemyPokemon3Name=findViewById(R.id.enemyPokemon3Name);
    private EditText enemyPokemon4Name=findViewById(R.id.enemyPokemon4Name);
    private EditText enemyPokemon5Name=findViewById(R.id.enemyPokemon5Name);
    private EditText enemyPokemon6Name=findViewById(R.id.enemyPokemon6Name);

    private Button myPokemonButton=findViewById(R.id.myPokemonButton);
    private Button enemyPokemonButton=findViewById(R.id.enemyPokemonButton);

    String Poke_URL = "https://pokeapi.co/api/v2/pokemon/"; //URLにPoke_を足す



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //URLの末尾に図鑑番号を足す
        Poke_URL += pokemon.setPoke_Number(//edittextをうまいこと使ってください);
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


                            //Viewに表示するところ。Listでデータを持っているのでうまく切り分けてください......

                            runOnUiThread(() -> binding.textView.setText(gistFile.stats.get(5).base_stat));
                            //String res = gistFile.stats.get(0).toString();
                            //runOnUiThread(() -> binding.textView.setText(res));
                          
                        });
            }
        });

        myPokemonButton.setOnClickListener(this);//
        enemyPokemonButton.setOnClickListener(this);

        public void onClickMyPokemonButton(View v){//自分のパーティー側のボタンが押されたときの動き
            //名前取り出す
            var Name1=binding.myPokemon1Name.text.toString();
            var Name2=binding.myPokemon2Name.text.toString();
            var Name3=binding.myPokemon3Name.text.toString();
            var Name4=binding.myPokemon4Name.text.toString();
            var Name5=binding.myPokemon5Name.text.toString();
            var Name6=binding.myPokemon6Name.text.toString();

            //図鑑番号取り出す
            int number1=pokemon.setPoke_Number(Name1);
            int number2=pokemon.setPoke_Number(Name2);
            int number3=pokemon.setPoke_Number(Name3);
            int number4=pokemon.setPoke_Number(Name4);
            int number5=pokemon.setPoke_Number(Name5);
            int number6=pokemon.setPoke_Number(Name6);

            //myPokemon1Scoreにapi呼び出しで入手した素早さを表示。。のつもりだけどそもそもちゃんとできてないかも、、、
            myPokemon1Score.setText(onResponse());


            Poke_URL += pokemon.setPoke_Number

        }


    }
}