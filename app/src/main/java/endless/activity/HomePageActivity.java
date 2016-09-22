package endless.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import endless.AppConstant.AppConstant;
import endless.FindMusic.FindSongs;
import endless.FindMusic.FindVideo;
import endless.MusicData.MusicData;
import endless.adapter.MusicAdapter;

public class HomePageActivity extends Activity implements View.OnClickListener {
    //歌手图片
    ImageView iv_picture;
    //歌单
    //歌手名字
    TextView tv_name;
    //歌曲名字
    TextView tv_music_name;
    ImageView iv_menu;
    //播放或者暂停
    ImageView iv_play_pause;
    //下一首
    ImageView iv_next;
    List<MusicData> list;
    String[] items;
    int mWhich;
    TextView tv_menu_title;
    TextView tv_menu_artist;
    //播放视频按钮
    private TextView mTextView01;
    private VideoView mVideoView01;
    private  String strVideoPath;
    private Button mbutton1,mbutton2;
    private  String TGA= "HIPPO_VIDEOVIEW";
    private boolean bifSDExist = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_home_page);
        initView();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            bifSDExist = true;
        }else{
            bifSDExist = false ;
            mMakeTextToast(getResources().getText(R.string.app_name).toString(),true);
        }
        mTextView01 =(TextView)findViewById(R.id.myTextView1);
        mVideoView01=(VideoView)findViewById(R.id.myVideoView1);
        mbutton1=(Button)findViewById(R.id.myButton1);
        mbutton2= (Button)findViewById(R.id.myButton2);
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bifSDExist){
                    strVideoPath = "file:///sdcard/hello.mp4";
                    playVideo(strVideoPath);
                }
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bifSDExist){
                    strVideoPath = "file:///sdcard/hello.mp4";
                    playVideo(strVideoPath);
                }
            }
        });

    }
    private  void playVideo(String strpath){
        if(strpath !=""){
            mVideoView01.setVideoURI(Uri.parse(strpath));
             MediaController mediaController =new MediaController(HomePageActivity.this);
            mediaController.setVisibility(View.VISIBLE);
            mVideoView01.setMediaController(mediaController);
            mVideoView01.requestFocus();
            mVideoView01.start();
            if(mVideoView01.isPlaying()){
                mTextView01.setText("aaa");
            }
        }
    }
    public void mMakeTextToast(String str,boolean isLong){
        if(isLong){
            Toast.makeText(HomePageActivity.this,str,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(HomePageActivity.this,str,Toast.LENGTH_SHORT).show();

        }
    }

    protected void initView() {
        tv_menu_title=(TextView)findViewById(R.id.tv_menu_title) ;
        tv_menu_artist=(TextView)findViewById(R.id.tv_menu_artist);
        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        iv_picture.setOnClickListener(this);
        iv_play_pause = (ImageView) findViewById(R.id.iv_play_pause);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(this);
        initItem();
    }
    private void initItem() {
        //设置列表参数 此处为数组  可以用ArrayList
        FindSongs songs = new FindSongs();
        list = songs.getMp3Infos(getContentResolver());
        if (list.size() == 0) {
            tv_menu_title.setText("null");
            tv_menu_artist.setText("null");
        } else {
            items = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                items[i] = list.get(i).getTitle() + " ———— " + list.get(i).getArtist();
            }
            mWhich = (int) (Math.random() * list.size());
            tv_menu_title.setText(list.get(mWhich).getTitle());
            tv_menu_artist.setText(list.get(mWhich).getArtist() + "");
        }
    }
    protected void dialogMusic() {
        //得到构造器
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("请选择：");
//        builder.setMessage("是否退出");
        //设置了列表实现就不能设置setMassage 否则设置不起作用
        builder.setIcon(R.mipmap.ic_launcher);
        //设置列表显示
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(HomePageActivity.this, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key", which);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(HomePageActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(HomePageActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_picture:
                Intent intent = new Intent(HomePageActivity.this, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key", mWhich);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.iv_menu:
                dialogMusic();
                break;
        }
    }


}
