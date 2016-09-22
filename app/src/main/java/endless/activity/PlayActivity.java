package endless.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import endless.FindMusic.FindSongs;

public class PlayActivity extends BaseActivity {
    private Button bt2;
    private Button bt3;
    ImageButton iv_bt_play;
    Intent intent;
    TextView tv;
    private MediaPlayer mp;
    //声明一个变量判断是否为暂停，默认为false
    private boolean isPaused  = false;



    @Override
    protected int setContent() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        bt2 = (Button)findViewById(R.id.bt_stop);
        bt3 = (Button)findViewById(R.id.bt_pause);
        iv_bt_play = (ImageButton)findViewById(R.id.iv_bt_play);
        tv  = (TextView)findViewById(R.id.tv);
        //创建MediaPlayer 对象，将raw文件夹下的hello.mp3
        FindSongs songs =new FindSongs();
        intent=getIntent();
        Bundle bundle = intent.getExtras();
        int i = bundle.getInt("key");
        Log.i("aaa",""+i);
        Uri uri =Uri.parse(songs.getMp3Infos(getContentResolver()).get(i).getUrl());
        mp = MediaPlayer.create(this,uri);
        //增加播放音乐按钮的事件
       iv_bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    if(mp!= null){
                        mp.stop();
                    }
                    mp.prepare();
                    mp.start();
                    tv.setText("音乐播放中.....");
                }catch (Exception e){
                    tv.setText("播放发生异常");
                    e.printStackTrace();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(mp !=null){
                        mp.stop();
                        tv.setText("音乐播放停止。。。");
                    }
                }catch (Exception e){
                    tv.setText("音乐播放异常");
                    e.printStackTrace();
                }

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(mp !=null){
                        mp.pause();
                        isPaused=true;
                        tv.setText("开始播放");

                    }
                }catch (Exception e){
                    tv.setText("音乐播放异常");
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    protected void setListener() {

    }
}
