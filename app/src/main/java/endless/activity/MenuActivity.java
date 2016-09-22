package endless.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import endless.FindMusic.FindSongs;
import endless.MusicData.MusicData;
import endless.adapter.MusicAdapter;

public class MenuActivity extends BaseActivity {
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    private FindSongs finder;                                              //查找歌曲的类的实例
    private List<MusicData> list;
    private MusicAdapter musicAdapter;
    ListView lv_music;


    @Override
    protected int setContent() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {
        lv_music = (ListView)findViewById(R.id.lv_music);
        finder = new FindSongs();//
        list =finder.getMp3Infos(getContentResolver());// 返回list
        musicAdapter = new MusicAdapter(this,list);
        lv_music.setAdapter(musicAdapter);
        itemOnClick();
    }

    @Override
    protected void setListener() {


    }
    private  void  itemOnClick(){
        lv_music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent  intent = new Intent(MenuActivity.this,PlayActivity.class);
                MyPreparedListener myPreparedListener =new MyPreparedListener(position);
                myPreparedListener.onPrepared(mediaPlayer);
                startActivity(intent);
            }
        });

    }



    private class MyPreparedListener implements MediaPlayer.OnPreparedListener {

        private int position;

        public MyPreparedListener(int position) {
            this.position = position;
        }

        public void onPrepared(MediaPlayer mp) {
            if (position > 0)
                mediaPlayer.seekTo(position);
            mediaPlayer.start();
        }
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void onDestory() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

}
