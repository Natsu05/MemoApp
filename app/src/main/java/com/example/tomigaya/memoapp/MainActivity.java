package com.example.tomigaya.memoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //タイトル入力欄
    private EditText titleEditText;

    //コメント入力欄
    private  EditText commentEditTExt;

    //電話番号入力欄
    private EditText phoneEditText;

    //プリファレンス
    private SharedPreferences preferences;

    //タイトル保存用のキー
    private static final String KEY_TITLE = "title";
    //コメント保存用のキー
    private static final String KEY_COMMENT = "comment";

    //電話番号保存用のキー
    private static final String KEY_PHONE = "phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //レイアウトより入力欄を取得
        titleEditText = (EditText)findViewById(R.id.main_title_etx);
        commentEditTExt = (EditText)findViewById(R.id.main_comment_etx);
        phoneEditText = (EditText)findViewById(R.id.main_phone_etx);

        //プリファレンスをデフォルト名で作成(保存用のファイルを作成)
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //プリファレンスにデータが保存されていれば、保存されているデータをセットする
        //タイトル
        String title = preferences.getString(KEY_TITLE, "");
        titleEditText.setText(title);
        //コメント
        String comment = preferences.getString(KEY_COMMENT,"");
        commentEditTExt.setText(comment);

        //電話番号
        String phone = preferences.getString(KEY_PHONE,"");
        phoneEditText.setText(phone);

        //レイアウトより、ボタンを取得
        Button saveBtn =(Button)findViewById(R.id.main_save_btn);
        saveBtn.setOnClickListener(this);

        //レイアウトより、削除ボタンを取得
        Button deletBtn = (Button)findViewById(R.id.main_delete_btn);
        deletBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //プリファレンスエディタの初期化(書き込める状態)
        SharedPreferences.Editor editor = preferences.edit();


        //getId():押されたビューのIDを取得
        switch (v.getId()){

            //保存ボタンが押された場合
            case R.id.main_save_btn:
                //入力されているタイトル、コメントを取得
                String title = titleEditText.getText().toString();
                String comment = commentEditTExt.getText().toString();
                String phone = phoneEditText.getText().toString();

                //プリファレンスにデータを書き込み
                editor.putString(KEY_TITLE,title);
                editor.putString(KEY_COMMENT,comment);
                editor.putString(KEY_PHONE,phone);
                editor.commit();//上書き保存

                //Toastを表示し、保存された旨を表示
                //LENGTH_SHORT:表示時間が短い
                Toast.makeText(this,"保存しました。",Toast.LENGTH_SHORT).show();

                break;

            //削除ボタンが押された場合
            case R.id.main_delete_btn:

                //プリファレンスからデータを削除
                editor.remove(KEY_TITLE);
                editor.remove(KEY_COMMENT);
                editor.remove(KEY_PHONE);
                editor.commit();//上書き保存

                //入力欄をからにする
                titleEditText.setText("");
                commentEditTExt.setText("");
                phoneEditText.setText("");

                //を表示し、削除された旨を表示
                Toast.makeText(this,"削除しました。",Toast.LENGTH_SHORT).show();

                break;
        }

    }
}
