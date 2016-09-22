package endless.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by z on 2016/9/22.
 */
public class PlayMVClass {
    private VideoView mVideoView;
    private  String  strVideoPath ="";
    private  String  TAG = "HIPPO_VIDEOVIEW";
    private  boolean bIfSDExist = false;
    Context context;
    Cursor cursor;
    ContentResolver contentResolver;

    public PlayMVClass(VideoView videoView, Context context,ContentResolver contentResolver){
        mVideoView = videoView;
        this.context = context;
        this.contentResolver = contentResolver;
    }

    public void playMV(){
        cursor =contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        String url = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));  //文件路径
            mVideoView.setVideoURI(Uri.parse(url));
            mVideoView.setMediaController(new MediaController(context));
            mVideoView.requestFocus();
            mVideoView.start();
        }

    }


